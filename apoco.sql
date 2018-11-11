-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2018 at 03:22 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apoco`
--

-- --------------------------------------------------------

--
-- Table structure for table `socialfeed`
--

CREATE TABLE `socialfeed` (
  `ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `POST` varchar(5000) NOT NULL,
  `PRIVACY` varchar(20) NOT NULL,
  `LINK` varchar(500) DEFAULT NULL,
  `DATE_POSTED` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `socialprofiles`
--

CREATE TABLE `socialprofiles` (
  `ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `PICTURE` varchar(100) DEFAULT NULL,
  `CAREER` varchar(50) NOT NULL,
  `CITY` varchar(50) NOT NULL,
  `STATE` varchar(3) NOT NULL,
  `RELATIONSHIP` varchar(20) NOT NULL,
  `BIO` varchar(5000) NOT NULL,
  `EDUCATION` varchar(50) NOT NULL,
  `SCHOOL` varchar(200) NOT NULL,
  `JOB` varchar(200) NOT NULL,
  `BIRTH_DATE` varchar(10) NOT NULL,
  `ROLE` varchar(20) NOT NULL DEFAULT 'user',
  `PRIVACY` tinyint(1) NOT NULL,
  `STATUS` varchar(20) NOT NULL DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `socialprofiles`
--

INSERT INTO `socialprofiles` (`ID`, `USER_ID`, `PICTURE`, `CAREER`, `CITY`, `STATE`, `RELATIONSHIP`, `BIO`, `EDUCATION`, `SCHOOL`, `JOB`, `BIRTH_DATE`, `ROLE`, `PRIVACY`, `STATUS`) VALUES
(55, 46, NULL, 'Technology', 'Glendale', 'AZ', 'Single', 'Well this is about my 20th \"real\" bio on here. Some day I\'ll keep one on here.. but for now, who really cares. I\'m just trying to test my garbo ass website.', 'Some College', 'Grand Canyon University', 'Contractor', '8/10/1992', 'user', 0, 'active');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `EMAIL` varchar(200) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `FIRST_NAME` varchar(30) NOT NULL,
  `LAST_NAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(500) CHARACTER SET latin1 COLLATE latin1_bin NOT NULL,
  `ID` int(11) NOT NULL,
  `ROLE` varchar(50) NOT NULL DEFAULT 'user',
  `STATUS` varchar(100) DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`EMAIL`, `USERNAME`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `ID`, `ROLE`, `STATUS`) VALUES
('rickjames@gmail.com', 'keberhart', 'Kaleb', 'Eberhart', '$2a$10$k4/vA/XgaYV6NsIcCstJ7euyE30eiKgm10ubJ4EiiGgtwa0uVcGoW', 44, 'user', 'active'),
('kalebeberhart10@gmail.com', 'Kaleb_be', 'Kaleb', 'Eberhart', '$2a$10$TpXNIjmbNnbceMP/e0BvIeJdPY/NOlY1xkh4MoF/KEjSiWDI7Uu6C', 46, 'user', 'active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `socialfeed`
--
ALTER TABLE `socialfeed`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `User_id_idx` (`USER_ID`);

--
-- Indexes for table `socialprofiles`
--
ALTER TABLE `socialprofiles`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `uid_idx` (`USER_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `socialfeed`
--
ALTER TABLE `socialfeed`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `socialprofiles`
--
ALTER TABLE `socialprofiles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `socialfeed`
--
ALTER TABLE `socialfeed`
  ADD CONSTRAINT `User_id` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `socialprofiles`
--
ALTER TABLE `socialprofiles`
  ADD CONSTRAINT `uid` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
