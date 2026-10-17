-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2026 at 03:46 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_people`
--

-- --------------------------------------------------------

--
-- Table structure for table `actor`
--

CREATE TABLE `actor` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `birth_year` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `award_status` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `actor`
--

INSERT INTO `actor` (`id`, `name`, `birth_year`, `category`, `award_status`, `gender`) VALUES
('ACT001', 'Cillian Murphy', 1976, 'Drama / Biopic', 'Oscar Winner', 'Male'),
('ACT002', 'Tom Holland', 1996, 'Action / Sci-Fi', 'Rising Star', 'Male'),
('ACT003', 'Zendaya', 1996, 'Drama / Biopic', 'Golden Globe Winner', 'Female'),
('ACT004', 'Keanu Reeves', 1964, 'Action / Sci-Fi', 'Rising Star', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
