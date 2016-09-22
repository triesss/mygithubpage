<?php
    require('logic.php');
?>
<?php require "head.php" ?>
	<title>Über uns</title>
</head>
<body>
    <!-- Skiplinks für den Screenreader auf Teaser und Artikel. -->
    <div class="screenReaderSkipLinks">
        <a class="screenReaderSkipLink" href="#navigationSkip">Navigation</a>
        <a class="screenReaderSkipLink" href="#autorsSkip">Autoren</a>
    </div>
    <?php include "navigation.php" ?>
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
<?php require "foot.php" ?>
