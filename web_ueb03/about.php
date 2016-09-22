<?php
    require('logic.php');
?>
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
	<title>Über uns</title>
</head>
<body>
    <!-- Skiplinks für den Screenreader auf Teaser und Artikel. -->
    <div class="screenReaderSkipLinks">
        <a class="screenReaderSkipLink" href="#navigationSkip">Navigation</a>
        <a class="screenReaderSkipLink" href="#autorsSkip">Autoren</a>
    </div>
<header>
	<a href="index.html"><img class="logo" src="images/fh_logo.png" alt="Logo"/></a>
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
	<!-- Aufeinanderfolgende Autoren -->
	<div class = "autors" id="autorsSkip">
		<!-- Generiert pro Autor ein Div mit dem Steckbrief. -->
        <?php
            $list = fetchAuthors();
            $length = count($list);

            for($i = 0; $i < $length; $i += 1){
                echo "<div>
                        <img src=\"" . $list[$i][6] . "\"/>
                        <h3>" . $list[$i][1] . "</h3>
                        <ul>
                            <li>" . $list[$i][2] . "</li>
                            <li>" . $list[$i][3] . "</li>
                            <li>" . $list[$i][4] . "</li>
                            <li>" . fetchCategory($list[$i][5]) . "</li>
                        </ul>
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
