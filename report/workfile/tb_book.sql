-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2015 at 02:41 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `latihan-report`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_book`
--

CREATE TABLE IF NOT EXISTS `tb_book` (
  `id` bigint(20) NOT NULL,
  `author` varchar(150) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status_delete` bit(1) DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  `name` varchar(60) NOT NULL,
  `price` float NOT NULL,
  `release_date` date NOT NULL,
  `stock` int(11) NOT NULL,
  `updateTime` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_book`
--

INSERT INTO `tb_book` (`id`, `author`, `create_time`, `status_delete`, `delete_time`, `name`, `price`, `release_date`, `stock`, `updateTime`) VALUES
(1, 'muhammad suta S.Kom', '2015-10-11 14:17:00', b'0', NULL, 'matematika', 40000, '2015-10-01', 10, NULL),
(2, 'Drs. Budi Darmawan', '2015-10-11 14:17:00', b'1', NULL, 'bahasa indonesia', 40000, '2015-10-01', 10, NULL),
(3, 'Dra. Fitri Rahmawati', '2015-10-11 14:17:00', b'0', NULL, 'bahasa inggris', 40000, '2015-10-01', 10, NULL),
(4, 'Drs. Budi Handoko', '2015-10-11 14:17:00', b'0', NULL, 'bahasa sunda', 40000, '2015-10-01', 10, NULL),
(5, 'Drs. Heri Setiawan', '2015-10-11 14:17:00', b'0', NULL, 'bahasa jawa', 40000, '2015-10-01', 10, NULL),
(6, 'Drs. Maulana yusuf', '2015-10-11 14:17:00', b'0', NULL, 'penjaskes', 40000, '2015-10-01', 10, NULL),
(7, 'Drs. Bayu Setiawan', '2015-10-11 14:17:00', b'0', NULL, 'KKPI', 40000, '2015-10-01', 10, NULL),
(8, 'Drs. Rudi', '2015-10-11 14:17:00', b'0', NULL, 'IPA', 40000, '2015-10-01', 10, NULL),
(9, 'Ahmad Tarmiji M.Kom', '2015-10-11 14:17:00', b'0', NULL, 'IPS', 40000, '2015-10-01', 10, NULL),
(10, 'Fajar M.Kom', '2015-10-11 14:17:00', b'0', NULL, 'Fisika', 40000, '2015-10-01', 10, NULL),
(11, 'Drs. Fahmi M.Kom, M.M', '2015-10-11 14:17:00', b'0', NULL, 'Biologi', 40000, '2015-10-01', 10, NULL),
(12, 'Dra. Rika', '2015-10-11 14:17:00', b'0', NULL, 'Bahasa Mandarin', 40000, '2015-10-01', 10, NULL),
(14, 'Drs. Zaya Kusuma', '2015-10-15 09:07:42', b'0', NULL, 'Java Programming', 50000, '2010-09-09', 10, NULL),
(15, 'Drs. Zaya Kusuma', '2015-10-15 10:16:42', b'0', NULL, 'Java Programming', 50000, '2010-09-09', 10, NULL),
(16, 'Drs. Zaya Kusuma', '2015-10-15 10:20:35', b'0', NULL, 'Java Programming', 50000, '2010-09-09', 10, NULL),
(17, 'Dra. Kusuma Dewi', '2015-10-15 11:28:17', b'0', NULL, 'Matlab', 50000, '2008-10-01', 5, NULL),
(18, 'Dra. Kusuma Dewi', '2015-10-15 11:50:22', b'0', NULL, 'Matlab', 50000, '2008-10-01', 5, NULL),
(19, 'Dra. Kusuma Dewi', '2015-10-15 11:50:41', b'0', NULL, 'Matlab', 50000, '2008-10-01', 5, NULL),
(20, 'Dra. Kusuma Dewi', '2015-10-15 12:55:24', b'0', NULL, 'Matlab', 50000, '2008-10-01', 5, NULL),
(21, 'Drs. Ilham Gunawan', '2015-10-15 13:09:10', b'0', NULL, 'Pemograman Web', 3000, '2010-09-01', 6, NULL),
(22, 'Drs. Fajar Kurniawan', '2015-10-15 13:38:59', b'0', NULL, 'Pemograman C++', 30000, '2009-09-01', 20, NULL),
(23, 'Drs. Anwar', '2015-10-15 13:40:00', b'0', NULL, 'Pemograman C', 60000, '2014-09-01', 10, NULL),
(24, 'Dra. Riska Rosalina S.kom', '2015-10-15 13:52:18', b'0', NULL, 'Komputer Grafik', 80000, '2007-10-01', 5, NULL),
(25, 'Dra. Rika S.kom', '2015-10-15 13:57:31', b'0', NULL, 'Komputer Grafik Advance', 50000, '2014-10-01', 10, NULL),
(26, 'Drs. Anton Setiayawan', '2015-10-15 14:08:34', b'0', NULL, 'Komputer Grafik Beggineer', 150000, '2008-08-12', 10, NULL),
(27, 'Dra. Riska Rosalina S.kom', '2015-10-15 14:10:49', b'0', NULL, 'Adobe Photoshop', 75000, '2008-12-24', 12, NULL),
(28, 'Dra. Windy Astuti', '2015-10-15 14:22:54', b'0', NULL, 'Buku Adobe Flash', 150000, '2014-10-01', 5, NULL),
(29, 'Dra. Windy Astuti', '2015-10-15 14:22:54', b'0', NULL, 'Buku Adobe Flash', 150000, '2014-10-01', 5, NULL),
(30, 'Dra. Windy Astuti', '2015-10-15 14:22:54', b'0', NULL, 'Buku Adobe Flash', 150000, '2014-10-01', 5, NULL),
(31, 'Dra. Windy Astuti', '2015-10-15 14:22:54', b'0', NULL, 'Buku Adobe Flash', 150000, '2014-10-01', 5, NULL),
(32, 'Dra. Riska Rosalina S.kom', '2015-10-15 14:10:49', b'0', NULL, 'Adobe Photoshop', 75000, '2008-12-24', 12, NULL),
(33, 'Drs. Anton Setiayawan', '2015-10-15 14:08:34', b'0', NULL, 'Komputer Grafik Beggineer', 150000, '2008-08-12', 10, NULL),
(34, 'Dra.Nur Saiful', '2015-10-29 09:07:11', b'0', NULL, 'Visual Basic', 50000, '2008-10-01', 10, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_book`
--
ALTER TABLE `tb_book`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_book`
--
ALTER TABLE `tb_book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
