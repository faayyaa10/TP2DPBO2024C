-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2024 at 10:13 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` varchar(255) NOT NULL,
  `noTelp` varchar(100) DEFAULT NULL,
  `agama` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `nim`, `nama`, `jenis_kelamin`, `noTelp`, `agama`) VALUES
(1, '2203999', 'Amelia Zalfa Julianti', 'Perempuan', '085162601007', 'Islam'),
(2, '2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', '088210554788', 'Islam'),
(3, '2202346', 'Muhammad Rifky Afandi', 'Laki-laki', '0895424470816', 'Islam'),
(4, '2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', '081320946893', 'Islam'),
(5, '2202046', 'Nurainun', 'Perempuan', '085889140316', 'Islam'),
(6, '2205101', 'Kelvin Julian Putra', 'Laki-laki', '082258549037', 'Islam'),
(7, '2200163', 'Rifanny Lysara Annastasya', 'Perempuan', '082127398188', 'Islam'),
(8, '2202869', 'Revana Faliha Salma', 'Perempuan', '089516944424', 'Islam'),
(9, '2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', '081806808964', 'Islam'),
(10, '2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', '0895389051300', 'Islam'),
(11, '2200311', 'Raden Rahman Ismail', 'Laki-laki', '08819915128', 'Islam'),
(12, '2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', '081322535069', 'Islam'),
(13, '2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', '08221760621', 'Islam'),
(14, '2205027', 'Muhammad Rizki Revandi', 'Laki-laki', '087871362627', 'Islam'),
(15, '2203484', 'Arya Aydin Margono', 'Laki-laki', '08128242059', 'Islam'),
(16, '2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', '081382110032', 'Islam'),
(17, '2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', '089655031225', 'Islam'),
(18, '2206697', 'Rifa Sania', 'Perempuan', '083824068002', 'Islam'),
(19, '2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', '085861135341', 'Islam'),
(20, '2204343', 'Meiva Labibah Putri', 'Perempuan', '081213768978', 'Islam');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
