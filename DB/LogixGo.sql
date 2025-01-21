-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 21, 2025 at 05:00 PM
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
(2, 4, '2025-01-30', 'delivered', '1 Victor Hugo', 'Appart 12', 'Lyon', 'Alps', '12345'),
(3, 4, '2025-01-30', 'delivered', '87 Rue St Sever', 'Appart 66', 'Rouen', 'Normandy', '87500'),
(4, 4, '2025-01-30', 'delivered', '15 rue Monte Carlo', 'Appart 5', 'Marseille', 'Normandy', '86000'),
(5, 4, '2025-01-30', 'pending', '36 rue Lafayette', 'House 12', 'Rouen', 'Normandy', '86710'),
(6, 7, '2025-02-02', 'pending', '124 rue Jean Zay', 'Sotteville', 'Grenoble', 'Alps', '65200'),
(7, 7, '2025-02-01', 'pending', '43 rue Garibaldi', 'Parc au princes', 'Nantes', 'Normandy', '66203'),
(8, 7, '2025-01-30', 'pending', '15 rue st Julien', 'Europe', 'St Etienne', 'Paris', '76500'),
(9, 4, '2025-02-02', 'pending', '14 rue st Sever', 'Appart 64', 'Rouen', 'Normandy', '87600'),
(10, 4, '2025-02-02', 'pending', '56 rue Mark Heny', 'Appart 34', 'Turin', 'Florence', '63544');

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
(2, 1, 5),
(2, 4, 2),
(3, 1, 30),
(3, 2, 2),
(3, 3, 12),
(3, 4, 25),
(4, 1, 10),
(4, 3, 13),
(5, 1, 80),
(5, 2, 6),
(5, 3, 1),
(5, 4, 100),
(6, 2, 10),
(6, 3, 3),
(6, 5, 2),
(6, 7, 20),
(6, 8, 4),
(6, 11, 6),
(7, 1, 30),
(7, 6, 3),
(7, 9, 8),
(7, 10, 4),
(8, 2, 6),
(8, 10, 50),
(9, 1, 115),
(9, 12, 2),
(9, 13, 10),
(10, 1, 500),
(10, 10, 100);

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
(5, 'a1b2c3d4', 150),
(8, 'C2D234', 850);

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
(1, 2, 1),
(1, 3, 3),
(1, 4, 2),
(4, 5, 2),
(4, 8, 1),
(5, 6, 1),
(5, 9, 2);

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
(1, '2025-01-30', 5, 'delivered'),
(3, '2025-01-30', 5, 'pending'),
(4, '2025-01-30', 8, 'pending'),
(5, '2025-02-02', 8, 'pending'),
(6, '2025-02-02', 5, 'pending');

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
(5, 'Ann', 'Jane', 'driver@gmail.com', '1122334455', 'pw', 'Driver'),
(6, 'Sam', 'Smith', 'scheduler@gmail.com', '1232456595', 'pw', 'Scheduler'),
(7, 'Billy', 'Jean', 'customer2@gmail.com', '1293745367', 'pw', 'Customer'),
(8, 'Santa', 'Claus', 'driver2@gmail.com', '1234383733', 'pw', 'Driver');

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
  MODIFY `dlv_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `product_prd`
--
ALTER TABLE `product_prd`
  MODIFY `prd_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `route_rte`
--
ALTER TABLE `route_rte`
  MODIFY `rte_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_usr`
--
ALTER TABLE `user_usr`
  MODIFY `usr_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
