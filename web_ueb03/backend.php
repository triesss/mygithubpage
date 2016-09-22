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
	<title>Backend</title>
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

<footer>
	<!-- Lädt ein anderes Layout. -->
	<a onclick="toggleStyle()">Alternative Layout</a>
	<a href="contact.html">Kontakt</a>
	<a href="imprint.html">Impressum</a>
	Copyright (c) 2016 Copyright Holder All Rights Reserved.
</footer>
</body>
</html>
