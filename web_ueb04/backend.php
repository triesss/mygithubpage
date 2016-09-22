<?php
	require('logic.php');
?>
<?php require "head.php" ?>
	<title>Backend</title>
</head>
<body>
    <!-- Skiplinks für den Screenreader auf Teaser und Artikel. -->
    <div class="screenReaderSkipLinks">
        <a class="screenReaderSkipLink" href="#navigationSkip">Navigation</a>
        <a class="screenReaderSkipLink" href="#autorsSkip">Autoren</a>
    </div>
<?php include "navigation.php" ?>
<div class="contactform">
    <div class="formular">
        <form method="post" action="logic.php">
            <label>Title:</label><br />
            <input type="text" required value="" name="titel"/><br />
            <label>Subtitle:</label><br />
            <input type="text" required value="" name="untertitel"/><br />
            <label>Text:</label><br />
            <textarea required name="text" rows="10"></textarea><br />
            <label>Foto:</label><br />
            <input type="text" required value="" name="foto" /><br />
            <label>Autor:</label><br />
						<select name="autor_id">
							<?php
									$list = fetchAuthors();
									$length = count($list);
									for($i = 0; $i < $length; $i += 1){
											echo '<option value="'. $list[$i][0] . '">' . $list[$i][1] . '</option>';
									}
							?>
						</select><br /
            <label>Link:</label><br />
            <select name="kategorie_id">
							<?php
								$categories = fetchCategories();
								foreach ($categories as $key => $value) {
									echo '<option value="'.$value[0].'">'.$value[1].'</option>';
								}
							?>
						</select><br />
            <button type="submit" name="btnAddNews">Absenden</button>
        </form>
    </div>
</div>
<div class="contactform">
    <div class="formular">
        <form method="post" action="logic.php">
            <label>Delete News:</label><br />
						<select name="news_id">
							<?php
									$list = fetchNews();
									foreach ($list as $k => $v) {
										echo '<option value="'.$v[0].'">'.$v[3].'</option>';
									}
							?>
						</select><br />
						<input type="hidden" name="action" value="delete"/>
            <button type="submit" name="btnDelNews">Löschen</button>
        </form>
    </div>
</div>
<div class="contactform">
	<div class="formular">
		<form method="post" action="logic.php">
			<input type="hidden" name="action" value="xml"/>
			<button type="submit" name="xmlbtn">XML Erstellen</button>
		</form>
    
    <?php
      if (file_exists("authors.xml")){
        echo '<a href="authors.xml">Link zur XML-Datei.<a/>';
      }
    ?>
	</div>
</div>
<?php require "foot.php" ?>
