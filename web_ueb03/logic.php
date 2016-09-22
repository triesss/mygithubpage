<?php
    /* Zum Wechseln der Kategorien wird eine Session benötigt. */
    session_start();

    /** Globaler Sessionstorage bei Bedarf initialisieren. */
    if(!isset($_SESSION['Kategorie'])){
        $_SESSION['Kategorie'] = "all";
    }

    if(isset($_POST) && !empty($_POST)){
      if(isset($_POST['titel']) && isset($_POST['untertitel']) && isset($_POST['text']) && isset($_POST['foto']) && isset($_POST['autor_id']) && isset($_POST['kategorie_id'])){
          saveData($_POST);
      }
      if(isset($_POST['action']) && $_POST['action'] == 'delete'){
          deleteNews($_POST['news_id']);
      }
    }

    function sqlConnect(){
        $user = 'root';
        $password = '';
        $connection = mysqli_connect('localhost', $user, $password,"data");
        if(mysqli_connect_errno()){
          echo "Keine Datenbankverbindung möglich: " .mysqli_connect_errno();
        }
        return $connection;
    }


    /**
     * [saveData description]
     * @param  [type] $array [description]
     * @return [type]        [description]
     */
    function saveData($array){
      $connection = sqlConnect();
      $autor = mysqli_real_escape_string($connection,$array['autor_id']);
      $kategorie = mysqli_real_escape_string($connection,$array['kategorie_id']);
      $text = mysqli_real_escape_string($connection,$array['text']);
      $untertitel = mysqli_real_escape_string($connection,$array['untertitel']);
      $titel = mysqli_real_escape_string($connection,$array['titel']);
      $foto = mysqli_real_escape_string($connection,$array['foto']);

      $sql = "INSERT INTO news (autor_id, kategorie_id, titel, untertitel, text, foto) VALUES('$autor','$kategorie','$titel','$untertitel','$text','$foto')";

      if(!mysqli_query($connection,$sql)){
        die('Error: '.mysqli_error($connection));
      }

      mysqli_close($connection);
      $ref = $_SERVER['HTTP_REFERER'];
      header("Location:".$ref);
    }



    /**
     * Liest aus der Datenbank "data" die Autoren Zeilenweise aus.
     *
     * @return das Array aus allen Zeilen.
     */
    function fetchAuthors(){
        $connection = sqlConnect();
        $sql = "SELECT * FROM Autor";
        $ergebnis = mysqli_query($connection,$sql);
        if($ergebnis != FALSE){
            $length = mysqli_num_rows($ergebnis);
            for($i = 0; $i < $length; $i += 1){
                $list[$i] = mysqli_fetch_row($ergebnis);
            }
            mysqli_close($connection);
            return $list;
        }
        mysqli_close($connection);
        return FALSE;
    }

    /**
     * Sucht aus der Datenbank die zugehörige Kategorie raus.
     *
     * @param id, die id zur Kategorie.
     *
     * @return die Kategorie.
     */
    function fetchCategory($id){
        $connection = sqlConnect();
        $ergebnis = mysqli_query($connection,"SELECT * FROM kategorie");
        if($ergebnis != FALSE){
            $length = mysqli_num_rows($ergebnis);

            for($i = 0; $i < $length; $i += 1){
                $row = mysqli_fetch_row($ergebnis);
                if($row[0] == $id){
                    mysqli_close($connection);
                    return $row[1];
                }
            }
        }
        mysqli_close($connection);
        return FALSE;
    }

    /**
     * Liest aus der Datenbank "data" die Kategorien Zeilenweise aus.
     *
     * @return das Array aus allen Zeilen.
     */
    function fetchCategories(){
        $connection = sqlConnect();
        $ergebnis = mysqli_query($connection,"SELECT * FROM kategorie");
        if($ergebnis != FALSE){
            $length = mysqli_num_rows($ergebnis);

            for($i = 0; $i < $length; $i += 1){
                $list[$i] = mysqli_fetch_row($ergebnis);
            }
            mysqli_close($connection);
            return $list;
        }
        mysqli_close($connection);
        return FALSE;
    }

    /**
     * Liest aus der Datenbank "data" die News nach Kategorien Zeilenweise aus.
     *
     * @param categoryId, der Fremdschlüssel auf die Kategorie.
     *
     * @return das Array aus allen Zeilen oder FALSE.
     */
    function fetchNewsByCategories($categoryId){
        $connection = sqlConnect();
        /* Selektiert nur die Einträge mit der korrekten Kategorie. */
        $ergebnis = mysqli_query($connection,"SELECT * FROM news WHERE
                                    kategorie_id = " . $categoryId);
        if($ergebnis != FALSE){
            $length = mysqli_num_rows($ergebnis);

            /* Zum identifizieren leerer Anfragen. */
            if($length == 0){
                $list = FALSE;
            }

            for($i = 0; $i < $length; $i += 1){
                $list[$i] = mysqli_fetch_row($ergebnis);
            }
            mysqli_close($connection);
            return $list;
        }
        mysqli_close($connection);
        return FALSE;
    }

    /**
     * Liest aus der Datenbank "data" die News nach Kategorien Zeilenweise aus.
     *
     * @return das Array aus allen Zeilen oder FALSE.
     */
    function fetchNews(){
        $connection= ssqlConnect();
        /* Selektiert nur die Einträge mit der korrekten Kategorie. */
        $ergebnis = mysqli_query($connection,"SELECT * FROM news");
        if($ergebnis != FALSE){
            $length = mysqli_num_rows($ergebnis);

            /* Zum identifizieren leerer Anfragen. */
            if($length == 0){
                $list = FALSE;
            }

            for($i = 0; $i < $length; $i += 1){
                $list[$i] = mysqli_fetch_row($ergebnis);
            }
            mysqli_close($connection);
            return $list;
        }
        mysqli_close($connection);
        return FALSE;
    }

    function deleteNews($id){
      $connection = sqlConnect();
      $id = mysqli_real_escape_string($id);
      $sql = "DELETE FROM news WHERE id = $id";
      mysqli_query($connection,$sql);
    }

    /**
     * Liest aus der Datenbank "data" die News nach Kategorien Zeilenweise aus,
     * je nach Zustand der Session.
     *
     * @return das Array aus allen Zeilen oder FALSE.
     */
    function fetchSelectedNews(){
        if($_SESSION['Kategorie'] == "all"){
            return fetchNews();
        }else{
            return fetchNewsByCategories($_SESSION['Kategorie']);
        }
    }

    /**
     * Setzt den Sessionstatus auf eine Kategorie.
     *
     * @param id, der Kategorieschluessel.
     */
    function setSession($id){
        $_SESSION['Kategorie'] = $id;
    }
?>
