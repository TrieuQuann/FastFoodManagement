-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2025 at 11:32 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlchta`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `name`) VALUES
(1, 'Bánh mì'),
(2, 'Burger'),
(3, 'Pizza'),
(4, 'Mì xào'),
(5, 'Cơm chiên'),
(6, 'Gà rán'),
(7, 'Xúc xích'),
(8, 'Khoai tây chiên'),
(9, 'Trà sữa'),
(10, 'Sinh tố');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `cus_id` int(11) NOT NULL,
  `cus_name` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`cus_id`, `cus_name`, `phone`, `email`) VALUES
(1, 'Nguyễn Văn Anh', '0901234567', 'nguyenvanAnh@gmail.com'),
(2, 'Trần Thị Bình', '0901234568', 'tranthiBinh@gmail.com'),
(3, 'Lê Minh Chánh', '0901234569', 'leminhChanh@gmail.com'),
(4, 'Phạm Thị Định', '0901234570', 'phamthiDinh@gmail.com'),
(5, 'Vũ Thị Em', '0901234571', 'vuthiem@gmail.com'),
(6, 'Hoàng Minh Khai', '0901234572', 'hoangminhkhai@gmail.com'),
(7, 'Lâm Thanh Giang', '0901234573', 'lamthanggiang@gmail.com'),
(8, 'Nguyễn Thị Hạnh', '0901234574', 'nguyenthihanh@gmail.com'),
(9, 'Trương Văn Yến', '0901234575', 'truongvanyen@gmail.com'),
(10, 'Bùi Minh Như', '0901234576', 'buiminhnhu@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `eid` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`eid`, `position_id`, `name`, `phone`, `address`, `gender`) VALUES
(1, 1, 'Lê Đức Minh', '0901234580', 'Sài Gòn', 'Nam'),
(2, 2, 'Trần Thị Ngà', '0901234581', 'Hà Nội', 'Nữ'),
(3, 1, 'Nguyễn Văn Oanh', '0901234582', 'Đà Nẵng', 'Nam'),
(4, 2, 'Hoàng Minh Phượng', '0901234583', 'Hải Phòng', 'Nam'),
(5, 3, 'Phạm Minh Quân', '0901234584', 'Hồ Chí Minh', 'Nam'),
(6, 4, 'Bùi Thi Yên', '0901234585', 'Cần Thơ', 'Nữ'),
(7, 2, 'Vũ Đức Sanh', '0901234586', 'Long An', 'Nam'),
(8, 3, 'Lâm Thanh Tuyền', '0901234587', 'Vũng Tàu', 'Nam'),
(9, 4, 'Trương Thị Uyên', '0901234588', 'Nha Trang', 'Nữ'),
(10, 2, 'Đoàn Minh Vy', '0901234589', 'Bình Dương', 'Nam');

-- --------------------------------------------------------

--
-- Table structure for table `invendetail`
--

CREATE TABLE `invendetail` (
  `inven_id` int(11) NOT NULL,
  `invoice_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `inven_id` int(11) NOT NULL,
  `name` varchar(156) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`inven_id`, `name`, `quantity`, `unit`) VALUES
(96, 'Bánh mì', 1000, 'chiếc'),
(97, 'Thịt gà', 500, 'kg'),
(98, 'Khoai tây', 300, 'kg'),
(99, 'Trà sữa', 200, 'lít'),
(100, 'Sữa đặc', 150, 'lít'),
(101, 'Bột mì', 100, 'kg'),
(102, 'Xúc xích', 200, 'kg'),
(103, 'Dưa leo', 100, 'kg'),
(104, 'Cà chua', 50, 'kg'),
(105, 'Hành tây', 40, 'kg'),
(106, 'Cà phê', 20, 'kg'),
(107, 'Đá viên', 500, 'kg'),
(108, 'Gà rán', 300, 'kg'),
(109, 'Sốt mayonnaise', 50, 'lít'),
(110, 'Sốt cà chua', 50, 'lít'),
(111, 'Hạt tiêu', 10, 'kg'),
(112, 'Muối', 10, 'kg'),
(113, 'Dầu ăn', 200, 'lít'),
(114, 'Bánh pizza', 200, 'chiếc'),
(115, 'Phô mai', 100, 'kg'),
(116, 'Nước mắm', 100, 'lít'),
(117, 'Đường', 50, 'kg'),
(118, 'Nước ngọt', 150, 'lít'),
(119, 'Nước ép trái cây', 100, 'lít'),
(120, 'Kem tươi', 50, 'kg'),
(121, 'Bánh quy', 50, 'kg'),
(122, 'Gia vị', 30, 'kg'),
(123, 'Sữa tươi', 100, 'lít'),
(124, 'Nước tương', 50, 'lít'),
(125, 'Bột năng', 30, 'kg');

-- --------------------------------------------------------

--
-- Table structure for table `invoiceinvent`
--

CREATE TABLE `invoiceinvent` (
  `invoice_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `quantityadded` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

CREATE TABLE `orderdetails` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL,
  `total_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `cus_id` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `total_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `cus_id`, `eid`, `order_date`, `total_amount`) VALUES
(1, 1, 1, '2025-04-01', 150000),
(2, 2, 2, '2025-04-01', 120000),
(3, 3, 3, '2025-04-01', 100000),
(4, 4, 4, '2025-04-01', 180000),
(5, 5, 5, '2025-04-01', 160000),
(6, 6, 6, '2025-04-01', 140000),
(7, 7, 7, '2025-04-01', 170000),
(8, 8, 8, '2025-04-01', 90000),
(9, 9, 9, '2025-04-01', 130000),
(10, 10, 10, '2025-04-01', 110000);

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `permission_id` int(11) NOT NULL,
  `permission_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `position_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`position_id`, `name`) VALUES
(1, 'thu ngân'),
(2, 'pha chế'),
(3, 'đầu bếp'),
(4, 'phục vụ');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(100) NOT NULL,
  `expected_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `recipe`
--

CREATE TABLE `recipe` (
  `product_id` int(11) NOT NULL,
  `inven_id` int(11) NOT NULL,
  `unit` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rolepermission`
--

CREATE TABLE `rolepermission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `prop` varchar(51) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplier_id` int(11) NOT NULL,
  `supplier_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `paswd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`cus_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`eid`),
  ADD KEY `fk_position` (`position_id`);

--
-- Indexes for table `invendetail`
--
ALTER TABLE `invendetail`
  ADD PRIMARY KEY (`inven_id`,`invoice_id`),
  ADD KEY `fk_invoice` (`invoice_id`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inven_id`);

--
-- Indexes for table `invoiceinvent`
--
ALTER TABLE `invoiceinvent`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `fk_suppli` (`supplier_id`);

--
-- Indexes for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `fk_product2` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fk_cusid` (`cus_id`),
  ADD KEY `fk_eid2` (`eid`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`permission_id`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`position_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `fk_cate` (`category_id`);

--
-- Indexes for table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`product_id`,`inven_id`),
  ADD KEY `fk_inven2` (`inven_id`);

--
-- Indexes for table `rolepermission`
--
ALTER TABLE `rolepermission`
  ADD PRIMARY KEY (`role_id`,`permission_id`,`prop`),
  ADD KEY `fk_permission` (`permission_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `fk_roleid` (`role_id`),
  ADD KEY `fk_eid` (`eid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `eid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=189;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `inven_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- AUTO_INCREMENT for table `invoiceinvent`
--
ALTER TABLE `invoiceinvent`
  MODIFY `invoice_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `permission_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `position`
--
ALTER TABLE `position`
  MODIFY `position_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `fk_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`position_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `invendetail`
--
ALTER TABLE `invendetail`
  ADD CONSTRAINT `fk_inven` FOREIGN KEY (`inven_id`) REFERENCES `inventory` (`inven_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoiceinvent` (`invoice_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `invoiceinvent`
--
ALTER TABLE `invoiceinvent`
  ADD CONSTRAINT `fk_suppli` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_product2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_cusid` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_eid2` FOREIGN KEY (`eid`) REFERENCES `employee` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_cate` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `fk_inven2` FOREIGN KEY (`inven_id`) REFERENCES `inventory` (`inven_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rolepermission`
--
ALTER TABLE `rolepermission`
  ADD CONSTRAINT `fk_permission` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_eid` FOREIGN KEY (`eid`) REFERENCES `employee` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_roleid` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
