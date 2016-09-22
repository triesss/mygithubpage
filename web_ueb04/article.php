<?php
	require "logic.php";
 require "head.php"; ?>
	<title>Article</title>
</head>
<body>
	<!-- Skiplinks für den Screenreader auf Teaser und Artikel. -->
	<div class="screenReaderSkipLinks">
	<a class="screenReaderSkipLink" href="#navigationSkip">Navigation</a>
	<a class="screenReaderSkipLink" href="#menuSkip">Menu</a>
	<a class="screenReaderSkipLink" href="#articleSkip">Article</a>
	</div>
<?php include "navigation.php" ?>
	<!-- Linkes Menu -->
	<div class="menu" id="menuSkip" >
		<button onclick="toogleDiv('menu')"><img src="buttons/button1.svg" alt="button1"/></button>
		<ul>
			<li>
				<a href="article.php">Januar</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a>
			</li>
			<li>
				<a href="article.php">Februar</a><br />
			</li>
			<li>
				<a href="article.php">März</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a><br />
				<a href="article.php">Article C</a>
			</li>
			<li>
				<a href="article.php">April</a><br />
				<a href="article.php">Article A</a>
			</li>
			<li>
				<a href="article.php">Mai</a><br />
				<a href="article.php">Article A</a>
			</li>
			<li>
				<a href="article.php">Juni</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a>
			</li>
			<li>
				<a href="article.php">Juli</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a><br />
				<a href="article.php">Article C</a>
			</li>
			<li>
				<a href="article.php">August</a><br />
			</li>
			<li>
				<a href="article.php">September</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a>
			</li>
			<li>
				<a href="article.php">Oktober</a><br />
			</li>
			<li>
				<a href="article.php">November</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a>
			</li>
			<li>
				<a href="article.php">Dezember</a><br />
				<a href="article.php">Article A</a><br />
				<a href="article.php">Article B</a><br />
				<a href="article.php">Merry Christmas</a>
			</li>
		</ul>
	</div>
	<div class="articlebox" id="articleSkip" >
	<?php 
		if(isset($_GET) && isset($_GET['id'])){
			$article = fetchNewsById($_GET['id']);
			echo '<div class="lorem"><h1>'.$article[0][3].'</h1><img src="'.$article[0][6].'" alt="Bild A"/>';
			echo '<small>'.$article[0][4].'</small>';
			echo $article[0][5];
			echo '</div>';
		} else {
			echo '<div class="lorem"><h1>Kein Artikel gefunden</h1>';
			echo '</div>';
		}
	?>
	<!-- Anzuzeigender Artikel -->
	
<!-- 		<div class="lorem"><h1>Lorem ipsum</h1><img src="images/a.png" alt="Bild A"/>
			Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
			Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
			Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.
			Nam liber tempor cum soluta nobis eleifend option
			congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
			Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis.
			At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur</div>
		<div class="lorem2">
			Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
			Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
			<img src="images/b.png" alt="Bild B"/><img src="images/c.jpg" alt="Bild C"/>
			Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.
			Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
			Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis.
			At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur
		</div> -->
	</div>
<?php require "foot.php" ?>