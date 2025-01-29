-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 29, 2025 at 12:55 PM
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
-- Database: `LogixGo`
--

-- --------------------------------------------------------

--
-- Table structure for table `constant_cst`
--

CREATE TABLE `constant_cst` (
  `cst_constant_name` varchar(30) NOT NULL,
  `cst_constant_value` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `constant_cst`
--

INSERT INTO `constant_cst` (`cst_constant_name`, `cst_constant_value`) VALUES
('warehouse_address', '48 bd de la Bastille 75012 Paris');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_dlv`
--

CREATE TABLE `delivery_dlv` (
  `dlv_id` int(5) NOT NULL,
  `dlv_usr_id` int(5) DEFAULT NULL,
  `dlv_date` date DEFAULT NULL,
  `dlv_status` varchar(10) DEFAULT NULL,
  `dlv_address_line_1` varchar(30) DEFAULT NULL,
  `dlv_address_line_2` varchar(30) DEFAULT NULL,
  `dlv_city` varchar(30) DEFAULT NULL,
  `dlv_region` varchar(20) DEFAULT NULL,
  `dlv_zip_code` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `delivery_dlv`
--

INSERT INTO `delivery_dlv` (`dlv_id`, `dlv_usr_id`, `dlv_date`, `dlv_status`, `dlv_address_line_1`, `dlv_address_line_2`, `dlv_city`, `dlv_region`, `dlv_zip_code`) VALUES
(13, 4, '2025-02-07', 'pending', '97 rue Toit Familial', 'Appart 23', 'Rouen', 'Normandy', '97200'),
(14, 4, '2025-02-07', 'pending', '12 rue St Sever', 'Appart 43', 'Rouen', 'Normandy', '87200'),
(15, 4, '2025-02-08', 'pending', '87 Rue Lafayette', 'Appart 83', 'Rouen', 'Normandy', '65200'),
(16, 4, '2025-02-08', 'pending', '24 rue Europe', 'Appart 12', 'Rouen', 'Normandy', '88200'),
(17, 11, '2025-02-02', 'pending', 'Shazam 001', 'jhdfhnc', 'abuja', 'north', '55555'),
(18, 11, '2025-02-02', 'pending', 'Shaker ', 'iujwfhow', 'Lagos', 'West', '66666'),
(19, 11, '2025-02-02', 'pending', 'dnjwjdn', 'djcnjnc', 'fiojeo', 'fjoek', '55555');

-- --------------------------------------------------------

--
-- Table structure for table `delivery_item_dli`
--

CREATE TABLE `delivery_item_dli` (
  `dli_delivery_id` int(5) NOT NULL,
  `dli_product_id` int(5) NOT NULL,
  `dli_total_weight` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `delivery_item_dli`
--

INSERT INTO `delivery_item_dli` (`dli_delivery_id`, `dli_product_id`, `dli_total_weight`) VALUES
(13, 1, 10),
(13, 2, 10),
(13, 3, 6),
(13, 9, 13),
(13, 10, 30),
(13, 12, 4),
(14, 2, 12),
(14, 3, 4),
(14, 4, 7),
(14, 5, 3),
(14, 8, 5),
(15, 1, 250),
(15, 6, 4),
(15, 7, 2),
(15, 9, 10),
(16, 2, 80),
(16, 3, 20),
(16, 5, 10),
(16, 8, 30),
(16, 11, 50),
(17, 2, 6),
(17, 3, 13),
(17, 8, 2),
(17, 10, 4),
(17, 11, 5),
(18, 8, 10),
(18, 10, 6),
(19, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `driver_drv`
--

CREATE TABLE `driver_drv` (
  `drv_id` int(5) NOT NULL,
  `drv_truck_registration` varchar(10) DEFAULT NULL,
  `drv_truck_capacity` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `driver_drv`
--

INSERT INTO `driver_drv` (`drv_id`, `drv_truck_registration`, `drv_truck_capacity`) VALUES
(10, 'C172F', 500),
(13, '63367738', 140);

-- --------------------------------------------------------

--
-- Table structure for table `product_prd`
--

CREATE TABLE `product_prd` (
  `prd_id` int(5) NOT NULL,
  `prd_name` varchar(30) DEFAULT NULL,
  `prd_unit_weight` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_prd`
--

INSERT INTO `product_prd` (`prd_id`, `prd_name`, `prd_unit_weight`) VALUES
(1, 'Rice (5kg)', 5),
(2, 'Carrot (2kg)', 2),
(3, 'Beef (1kg)', 1),
(4, 'Tomato (1kg)', 1),
(5, 'Apple (1kg)', 1),
(6, 'Orange (1kg)', 1),
(7, 'Potato (2kg)', 2),
(8, 'Cabbage (1kg)', 1),
(9, 'Onion (1kg)', 1),
(10, 'Chicken (2kg)', 2),
(11, 'Fish (1kg)', 1),
(12, 'Milk (1L)', 1),
(13, 'Cheese (1kg)', 1);

-- --------------------------------------------------------

--
-- Table structure for table `route_delivery_rtd`
--

CREATE TABLE `route_delivery_rtd` (
  `rtd_route_id` int(5) NOT NULL,
  `rtd_delivery_id` int(5) NOT NULL,
  `rtd_order` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `route_delivery_rtd`
--

INSERT INTO `route_delivery_rtd` (`rtd_route_id`, `rtd_delivery_id`, `rtd_order`) VALUES
(10, 15, 2),
(10, 16, 1);

-- --------------------------------------------------------

--
-- Table structure for table `route_rte`
--

CREATE TABLE `route_rte` (
  `rte_id` int(5) NOT NULL,
  `rte_date` date DEFAULT NULL,
  `rte_driver_id` int(5) DEFAULT NULL,
  `rte_status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `route_rte`
--

INSERT INTO `route_rte` (`rte_id`, `rte_date`, `rte_driver_id`, `rte_status`) VALUES
(10, '2025-02-08', 10, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `user_usr`
--

CREATE TABLE `user_usr` (
  `usr_id` int(5) NOT NULL,
  `usr_first_name` varchar(20) DEFAULT NULL,
  `usr_last_name` varchar(20) DEFAULT NULL,
  `usr_email` varchar(30) DEFAULT NULL,
  `usr_phone_number` varchar(11) DEFAULT NULL,
  `usr_password` varchar(20) DEFAULT NULL,
  `usr_role` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_usr`
--

INSERT INTO `user_usr` (`usr_id`, `usr_first_name`, `usr_last_name`, `usr_email`, `usr_phone_number`, `usr_password`, `usr_role`) VALUES
(4, 'Bob', 'Joe', 'customer@gmail.com', '0123456781', 'pw', 'Customer'),
(6, 'Sam', 'Smith', 'scheduler@gmail.com', '1232456595', 'pw', 'Scheduler'),
(10, 'Ann', 'Jane', 'driver@gmail.com', '0263524238', 'pw', 'Driver'),
(11, 'test', 'ezzy', 'test1@test.com', '2233445566', 'tttttttt', 'Customer'),
(12, 'test2', 'ezzy', 'tes@tes.com', '2345678912', 'bbbbbbbb', 'Scheduler'),
(13, 'test3', 'ezzy', 'te@te.com', '2345678912', 'GGGGGGGG', 'Driver');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `constant_cst`
--
ALTER TABLE `constant_cst`
  ADD PRIMARY KEY (`cst_constant_name`);

--
-- Indexes for table `delivery_dlv`
--
ALTER TABLE `delivery_dlv`
  ADD PRIMARY KEY (`dlv_id`),
  ADD KEY `fk_dlv_usr_id` (`dlv_usr_id`);

--
-- Indexes for table `delivery_item_dli`
--
ALTER TABLE `delivery_item_dli`
  ADD PRIMARY KEY (`dli_delivery_id`,`dli_product_id`),
  ADD KEY `fk_dli_product_id` (`dli_product_id`);

--
-- Indexes for table `driver_drv`
--
ALTER TABLE `driver_drv`
  ADD PRIMARY KEY (`drv_id`),
  ADD UNIQUE KEY `uq_drv_truck_registration` (`drv_truck_registration`);

--
-- Indexes for table `product_prd`
--
ALTER TABLE `product_prd`
  ADD PRIMARY KEY (`prd_id`),
  ADD UNIQUE KEY `uq_prd_name` (`prd_name`);

--
-- Indexes for table `route_delivery_rtd`
--
ALTER TABLE `route_delivery_rtd`
  ADD PRIMARY KEY (`rtd_route_id`,`rtd_delivery_id`),
  ADD KEY `fk_rtd_delivery_id` (`rtd_delivery_id`);

--
-- Indexes for table `route_rte`
--
ALTER TABLE `route_rte`
  ADD PRIMARY KEY (`rte_id`),
  ADD KEY `fk_rte_driver_id` (`rte_driver_id`);

--
-- Indexes for table `user_usr`
--
ALTER TABLE `user_usr`
  ADD PRIMARY KEY (`usr_id`),
  ADD UNIQUE KEY `uq_usr_email` (`usr_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `delivery_dlv`
--
ALTER TABLE `delivery_dlv`
  MODIFY `dlv_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `product_prd`
--
ALTER TABLE `product_prd`
  MODIFY `prd_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `route_rte`
--
ALTER TABLE `route_rte`
  MODIFY `rte_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user_usr`
--
ALTER TABLE `user_usr`
  MODIFY `usr_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `delivery_dlv`
--
ALTER TABLE `delivery_dlv`
  ADD CONSTRAINT `fk_dlv_usr_id` FOREIGN KEY (`dlv_usr_id`) REFERENCES `user_usr` (`usr_id`);

--
-- Constraints for table `delivery_item_dli`
--
ALTER TABLE `delivery_item_dli`
  ADD CONSTRAINT `fk_dli_delivery_id` FOREIGN KEY (`dli_delivery_id`) REFERENCES `delivery_dlv` (`dlv_id`),
  ADD CONSTRAINT `fk_dli_product_id` FOREIGN KEY (`dli_product_id`) REFERENCES `product_prd` (`prd_id`);

--
-- Constraints for table `driver_drv`
--
ALTER TABLE `driver_drv`
  ADD CONSTRAINT `fk_drv_usr_id` FOREIGN KEY (`drv_id`) REFERENCES `user_usr` (`usr_id`);

--
-- Constraints for table `route_delivery_rtd`
--
ALTER TABLE `route_delivery_rtd`
  ADD CONSTRAINT `fk_rtd_delivery_id` FOREIGN KEY (`rtd_delivery_id`) REFERENCES `delivery_dlv` (`dlv_id`),
  ADD CONSTRAINT `fk_rtd_route_id` FOREIGN KEY (`rtd_route_id`) REFERENCES `route_rte` (`rte_id`);

--
-- Constraints for table `route_rte`
--
ALTER TABLE `route_rte`
  ADD CONSTRAINT `fk_rte_driver_id` FOREIGN KEY (`rte_driver_id`) REFERENCES `driver_drv` (`drv_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
