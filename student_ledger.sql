-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 21 Lis 2017, 02:13
-- Wersja serwera: 10.1.25-MariaDB
-- Wersja PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `student_ledger`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lecture`
--

CREATE TABLE `lecture` (
  `id` bigint(20) NOT NULL,
  `class_number` bigint(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `subject_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `lecture`
--

INSERT INTO `lecture` (`id`, `class_number`, `date`, `subject_id`, `teacher_id`) VALUES
(1, 211, '2017-11-20 15:05:16', 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `course` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `student`
--

INSERT INTO `student` (`id`, `course`, `email`, `name`, `surname`) VALUES
(1, 'Informatyka', 'janex@gmail.com', 'Jan', 'Kowalski'),
(3, 'Ekonomia', 'nowak11@gmail.com', 'Janusz', 'Nowak'),
(4, 'Informatyka', 'adi@gmail.com', 'Adrian', 'Bielak'),
(5, 'Informatyka', 'miro@gmail.com', 'Mirek', 'Abecadlo'),
(6, 'Informatyka', 'robb@gmail.com', 'Robert', 'Klawiatura'),
(7, 'Informatyka', 'eryk@gmail.com', 'Eryk', 'Monitor');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `student_lecture`
--

CREATE TABLE `student_lecture` (
  `student_id` bigint(20) NOT NULL,
  `lecture_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `student_lecture`
--

INSERT INTO `student_lecture` (`student_id`, `lecture_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subject`
--

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `subject`
--

INSERT INTO `subject` (`id`, `name`) VALUES
(1, 'Podstawy Programowania'),
(2, 'Programowanie Obiektowe');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `salary` double NOT NULL,
  `surname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `salary`, `surname`) VALUES
(1, 'Adam', 2500, 'Adamowski'),
(3, 'Ewa', 3400.5, 'Wisniowska');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `lecture`
--
ALTER TABLE `lecture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe4a1kd2dml7m5hr42aal7nvew` (`subject_id`),
  ADD KEY `FK2ea1ueblrv09ngwf3i0lf0h2o` (`teacher_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`);

--
-- Indexes for table `student_lecture`
--
ALTER TABLE `student_lecture`
  ADD PRIMARY KEY (`student_id`,`lecture_id`),
  ADD KEY `FKl0fgyx5u34qg1mrv7kpu2bl9f` (`lecture_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_p1jgir6qcpmqnxt4a8105wsot` (`name`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `lecture`
--
ALTER TABLE `lecture`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT dla tabeli `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT dla tabeli `subject`
--
ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT dla tabeli `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `lecture`
--
ALTER TABLE `lecture`
  ADD CONSTRAINT `FK2ea1ueblrv09ngwf3i0lf0h2o` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKe4a1kd2dml7m5hr42aal7nvew` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`);

--
-- Ograniczenia dla tabeli `student_lecture`
--
ALTER TABLE `student_lecture`
  ADD CONSTRAINT `FK4fyixiqgfiml67sunbq8fcubh` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `FKl0fgyx5u34qg1mrv7kpu2bl9f` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
