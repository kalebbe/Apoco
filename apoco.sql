-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 22, 2019 at 07:06 AM
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
-- Table structure for table `busprofiles`
--

CREATE TABLE `busprofiles` (
  `ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `DOB` varchar(10) NOT NULL,
  `GENDER` varchar(20) NOT NULL,
  `ETHNICITY` varchar(100) NOT NULL,
  `CITY` varchar(50) NOT NULL,
  `STATE` varchar(3) NOT NULL,
  `EDUCATION` varchar(200) NOT NULL,
  `PROFESSION` varchar(200) NOT NULL,
  `PICTURE` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `busprofiles`
--

INSERT INTO `busprofiles` (`ID`, `USER_ID`, `DOB`, `GENDER`, `ETHNICITY`, `CITY`, `STATE`, `EDUCATION`, `PROFESSION`, `PICTURE`) VALUES
(5, 59, '8/8/2006', 'Female', 'White', 'Boston', 'PA', 'Some College', 'Technology', NULL),
(6, 60, '8/10/1980', 'Male', 'White', 'Boston', 'CA', 'Associates Degree', 'Technology', NULL),
(7, 61, '4/1/2014', 'Male', 'Black or African American', 'New York', 'AR', 'Some High School', 'Technology', NULL),
(8, 46, '8/27/1992', 'Male', 'White', 'Glendale', 'AZ', 'Doctorate\'s Degree', 'Science', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `connections`
--

CREATE TABLE `connections` (
  `USER_ID` int(11) NOT NULL,
  `CONN_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `connections`
--

INSERT INTO `connections` (`USER_ID`, `CONN_ID`) VALUES
(46, 61);

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `USER_ID` int(11) NOT NULL,
  `FRIEND_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`USER_ID`, `FRIEND_ID`) VALUES
(62, 46),
(46, 62),
(63, 46),
(46, 63);

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `TITLE` varchar(300) NOT NULL,
  `DATE_POSTED` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(10000) NOT NULL,
  `SALARY` varchar(30) DEFAULT NULL,
  `LINK` varchar(400) NOT NULL,
  `REQUIREMENTS` varchar(10000) NOT NULL,
  `TYPE` varchar(100) NOT NULL,
  `CITY` varchar(100) NOT NULL,
  `STATE` varchar(5) NOT NULL,
  `COMPANY` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`ID`, `USER_ID`, `TITLE`, `DATE_POSTED`, `DESCRIPTION`, `SALARY`, `LINK`, `REQUIREMENTS`, `TYPE`, `CITY`, `STATE`, `COMPANY`) VALUES
(1, 64, 'Junior Developer', '2019-10-21 06:23:59', 'Jump into the exciting world of software development with us at Google today! We expect performance from our employees and we want to know what you can bring to the table. At this job, you will work with our senior developers and aid in the completion of minor software changes.', '70000', 'http://www.madeupjoblink.com/applynow', '-Bachelor\'s degree in Computer Science or a relevant field.\r\n-50 years work experience.\r\n-Blood of a virgin.\r\n-15 professional references.\r\n-A Nobel prize.\r\n-300+ wpm typing speed.', 'Technology', 'San Francisco', 'CA', 'Google'),
(2, 64, 'Customer Service Representative', '2019-10-09 00:00:00', 'Be the face of Google at our headquarters in California! Interact with our guests and answer any calls. This is just a test job listing and I\'m running out of imagination here, so just accept that this is the job description.', '35000', 'http://www.jobposting.com/csr?apoco', 'Be willing to sell out entirely to our brand and advertise us to your friends!', 'Service', 'San Francisco', 'CA', 'Google');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `ID` int(11) NOT NULL,
  `SENDER_ID` int(11) NOT NULL,
  `RECEIVER_ID` int(11) NOT NULL,
  `BODY` varchar(10000) DEFAULT NULL,
  `TYPE` varchar(20) NOT NULL,
  `DATE_SENT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `DATE_POSTED` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VOTES` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `socialfeed`
--

INSERT INTO `socialfeed` (`ID`, `USER_ID`, `NAME`, `POST`, `PRIVACY`, `LINK`, `DATE_POSTED`, `VOTES`) VALUES
(20, 46, 'Kaleb Eberhart', 'this is being made for the purpose of testing and nothing else, so I don\'t know why I\'m even making actual sentences and stuff, but whatever.', 'public', NULL, '2019-10-20 05:48:41', 0);

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
  `GENDER` varchar(20) NOT NULL,
  `ROLE` varchar(20) NOT NULL DEFAULT 'user',
  `PRIVACY` tinyint(1) NOT NULL,
  `STATUS` varchar(20) NOT NULL DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `socialprofiles`
--

INSERT INTO `socialprofiles` (`ID`, `USER_ID`, `PICTURE`, `CAREER`, `CITY`, `STATE`, `RELATIONSHIP`, `BIO`, `EDUCATION`, `SCHOOL`, `JOB`, `BIRTH_DATE`, `GENDER`, `ROLE`, `PRIVACY`, `STATUS`) VALUES
(61, 46, NULL, 'Technology', 'Glendale', 'AZ', 'Single', 'This is a biography, I don\'t know what much more to put here other than that.', 'Some College', 'Grand Canyon University', 'contractor', '8/10/1992', 'Male', 'user', 0, 'active'),
(63, 44, NULL, 'Technology', 'Downey', 'AR', 'Engaged', 'Here is a random biography.. well I don\'t know what to talk about here since this is just being used as an example.. weird.', 'Middle School', 'street', 'researcher', '4/4/2010', 'Female', 'user', 0, 'active'),
(69, 59, NULL, 'Shipping', 'New York', 'AZ', 'Widowed', 'This is an example biography being used to show off my inferior typing abilities. Just kidding, look at the word count above!', 'Middle School', 'Sunburst', 'Being a bum', '7/8/2017', 'Male', 'user', 0, 'active'),
(70, 61, NULL, 'Technology', 'New York', 'AL', 'Separated', 'This iss an example biography and i\'ve written way too many of these to count.', 'Middle School', 'Sunburst', 'Being a bum', '11/5/2014', 'Male', 'user', 0, 'active'),
(71, 62, NULL, 'Journalism', 'Glendale', 'AL', 'Widowed', 'This is a bio. This bio is mine. There are many like it, but this one is mine.', 'None', 'The streets', 'Writing in my diary', '1/1/1991', 'Male', 'user', 0, 'active'),
(72, 63, NULL, 'Customer Service', 'Glendale', 'AZ', 'In a relationship', 'This is a bio, it has a minimum character count of 50, so this will need to a be a bit longer', 'Some High School', 'Greenway', 'Happy', '1/5/1989', 'Male', 'user', 0, 'active');

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
('rickjames@gmail.com', 'rickjames', 'Rick', 'James', '$2a$10$k4/vA/XgaYV6NsIcCstJ7euyE30eiKgm10ubJ4EiiGgtwa0uVcGoW', 44, 'user', 'active'),
('kalebeberhart10@gmail.com', 'kaleb_be', 'Kaleb', 'Eberhart', '$2a$10$s7sCmcPzCwBcZCKfm7byQuJt20bJgxlcJdcD4eYT26xFb1HEP4cKm', 46, 'user', 'active'),
('JohnDoe@email.com', 'JohnDoe', 'John', 'Doe', '$2a$10$fTpmN3THqTk/vPEVCpQnzuHCypkKPzxOS8ot2jZmHPIm0bcMOjf5a', 59, 'user', 'active'),
('example2@email.com', 'johnDoe2', 'John', 'Doe', '$2a$10$AF8T39J15dB7ViTmvy2gQ.NSW8BpIQ0zGONrDQYbAn5ZvbRBPTtOe', 60, 'user', 'active'),
('example3@email.com', 'Somethingrandom', 'Johnathn', 'Dope', '$2a$10$ZwlxoUBUwAbW1s0HYVInueWaLrTg7fDbwSVAzNBtHgbjVVcicv6JK', 61, 'user', 'active'),
('test1@email.com', 'test1', 'test', 'user', '$2a$10$iF8qXISYwT.5/lkVyxMoTuEpVrMyr.GsADhbdgDJN.cctGgxGL6QS', 62, 'user', 'active'),
('test2@email.com', 'test2', 'Kaleb', 'Eberhart', '$2a$10$rykoZZ1w7t1BKhjHdcsQqOV2eHknSLvLUCxBvvfpqiLLBKElhDXqe', 63, 'user', 'active'),
('company@email.com', 'company', 'Google', '', '$2a$10$NBkp4q/4.Vc4.3Wsnd98G.YM5ZLOEVYQZDeUSLz5exDov3b8zPRgK', 64, 'company', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `votes`
--

CREATE TABLE `votes` (
  `USER_ID` int(11) NOT NULL,
  `FEED_ID` int(11) NOT NULL,
  `VOTE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `votes`
--

INSERT INTO `votes` (`USER_ID`, `FEED_ID`, `VOTE`) VALUES
(61, 18, 'Like'),
(46, 18, 'Like');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `busprofiles`
--
ALTER TABLE `busprofiles`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `socialfeed`
--
ALTER TABLE `socialfeed`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `socialprofiles`
--
ALTER TABLE `socialprofiles`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `busprofiles`
--
ALTER TABLE `busprofiles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `jobs`
--
ALTER TABLE `jobs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `socialfeed`
--
ALTER TABLE `socialfeed`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `socialprofiles`
--
ALTER TABLE `socialprofiles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
