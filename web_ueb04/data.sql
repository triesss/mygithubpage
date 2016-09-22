-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 26. Jun 2016 um 04:50
-- Server-Version: 10.1.13-MariaDB
-- PHP-Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `data`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kategorie`
--

CREATE TABLE `kategorie` (
  `id` int(11) NOT NULL,
  `bezeichner` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `kategorie`
--

INSERT INTO `kategorie` (`id`, `bezeichner`) VALUES
(1, 'Gesundheit'),
(2, 'Sicherheit'),
(3, 'Politik');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `news`
--

CREATE TABLE `news` (
  `id` int(11) NOT NULL,
  `autor_id` int(11) NOT NULL,
  `kategorie_id` int(11) NOT NULL,
  `titel` varchar(32) NOT NULL,
  `untertitel` varchar(128) NOT NULL,
  `text` varchar(2048) NOT NULL,
  `foto` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `news`
--

INSERT INTO `news` (`id`, `autor_id`, `kategorie_id`, `titel`, `untertitel`, `text`, `foto`) VALUES
(1, 1, 1, 'Ausgewogene Ernaehrung', 'Vegetarische Pferdelassagne in der Mensa', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.', 'images/a.png'),
(2, 1, 1, '\nBier, Brutzeln und Baden', 'Sommerfest erstmals mit Pool', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. ', 'images/b.png'),
(3, 2, 3, 'Come to the dark side', 'Trump in Umfragen vorne', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. ', 'images/c.jpg'),
(4, 3, 2, 'Dreiste Diebe', 'Gesamter Matevorrat gestohlen', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. ', 'images/d.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_authors`
--

CREATE TABLE `autor` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `telefon` varchar(32) NOT NULL,
  `fax` varchar(32) NOT NULL,
  `kategorie_id` int(11) NOT NULL,
  `foto` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `t_authors`
--

INSERT INTO `autor` (`id`, `name`, `email`, `telefon`, `fax`, `kategorie_id`, `foto`) VALUES
(1, 'AutorName', 'potat@pe.de', '0435 532465', '09523 52359', 1, 'images/dummy_m.png'),
(2, 'AutorName2', 'potat2@pe.de', '0435 534142', '09523 5525234', 1, 'images/dummy_f.png'),
(3, 'Alexander', 'aleander@pe.de', '0190 666666', '0180 666666', 1, 'images/dummy_m.png');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `kategorie`
--
ALTER TABLE `kategorie`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `t_authors`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
