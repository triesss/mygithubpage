<!DOCTYPE HTML>
<html>
<head>
	<script src="style.js"></script>

    <!-- Spezieller font geladen. -->
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- Lädt das normale oder alternativ Stylesheet. -->
    <script>
        loadStyle();
    </script>

	<link rel="stylesheet" type="text/css" media="print" href="print.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>News</title>
</head>
<body>
    <!-- Skiplinks für den Screenreader auf Teaser und Artikel. -->
    <div class="screenReaderSkipLinks">
        <a class="screenReaderSkipLink" href="#navigationSkip">Navigation</a>
        <a class="screenReaderSkipLink" href="#menuSkip">Menü</a>
        <a class="screenReaderSkipLink" href="#articlesSkip">Artikel</a>
        <?php
            require('logic.php');
            $list = fetchSelectedNews();
            $length = count($list);
            for($i = 0; $i < $length; $i += 1){
                echo "<a class=\"screenReaderSkipLink\" href=\"#artikel" . $i . "Skip\"> Artikel ". $i . "</a>";
            }
        ?>
    </div>
<header>
	<a href="index.html">
    <img class="logo" src="images/fh_logo.png" alt="Logo"/></a>
	<!-- Navigationsleiste -->
	<div class="navigation" id="navigationSkip">
		<a href="index.html" class="navlink">Home</a>
		<a href="about.php" class="navlink">About</a>
		<a href="about2.html" class="navlink">Company</a>
		<a href="news.php" class="navlink">News</a>
		<a href="article.html" class="navlink">Article</a>
        <a href="backend.php" class="navlink">Backend</a>
	</div>
</header>
	<!-- Linkes Menu -->
	<div id="menuSkip" class="menu">
		<button onclick="toogleDiv('menu')"><img src="buttons/button1.svg" alt="button1"/></button>
		<ul>
            <?php
                $list = fetchCategories();
                $length = count($list);

                for($i = 0; $i < $length; $i += 1){
                    echo "<li>
                            <a href=\"article.html\">" . $list[$i][1] . "</a><br />";
                            $subList = fetchNewsByCategories($list[$i][0]);
                            for($z = 0; $z < count($subList); $z += 1){
                                echo "<a href=\"article.html\">" . $subList[$z][3] . "</a><br />";
                            }
                    echo "</li>";
                }
            ?>
		</ul>
	</div>
	<!-- Aufeinander Folgende Articles -->
	<div id="articlesSkip" class="newsflash">
        <!-- Erstellt die Reihe an Artikeln. -->
        <?php
            $list = fetchSelectedNews();
            $length = count($list);
            for($i = 0; $i < $length; $i += 1){
                echo "<div id=\"artikel". $i . "Skip\">
                        <img src=\"" . $list[$i][6] . "\" alt=\"Kein Datenbankeintrag.\"/>
                        <h2>" . $list[$i][3] . "</h2>
                        <h3>" . $list[$i][4] . "</h3>
                        <p>"  . $list[$i][5] . "</p>
                        <a href=\"article.html\">To the article.</a>
                      </div>";
            }
        ?>
	</div>
<footer>
	<!-- Lädt ein anderes Layout. -->
	<a onclick="toggleStyle()">Alternative Layout</a>
	<a href="contact.html">Kontakt</a>
	<a href="imprint.html">Impressum</a>
	Copyright (c) 2016 Copyright Holder All Rights Reserved.
</footer>
</body>
</html>
