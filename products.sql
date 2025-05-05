-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 04, 2025 lúc 06:12 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlchta`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(100) NOT NULL,
  `expected_quantity` int(11) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`product_id`, `category_id`, `name`, `price`, `image`, `expected_quantity`, `status`) VALUES
(11, 1, 'Burger Bò Phô Mai', 45000, '/image/products/hamburger.png', 50, 1),
(12, 1, 'Burger Gà Giòn', 42000, '/image/products/hamburger.png', 40, 1),
(13, 2, 'Gà Rán Truyền Thống', 39000, '/image/products/hamburger.png', 60, 1),
(14, 2, 'Gà Rán Cay', 40000, '/image/products/hamburger.png', 55, 1),
(15, 3, 'Pizza Phô Mai', 68000, '/image/products/hamburger.png', 30, 1),
(16, 3, 'Pizza Hải Sản', 75000, '/image/products/hamburger.png', 25, 1),
(17, 4, 'Mì Spaghetti Bò Bằm', 52000, '/image/products/hamburger.png', 35, 1),
(18, 4, 'Mì Ý Sốt Kem', 50000, '/image/products/hamburger.png', 30, 1),
(19, 5, 'Khoai Tây Chiên Lớn', 25000, '/image/products/hamburger.png', 70, 1),
(20, 5, 'Phô Mai Que', 30000, '/image/products/hamburger.png', 40, 1),
(21, 6, 'Burger Chay', 35000, '/image/products/hamburger.png', 20, 1),
(22, 6, 'Mì Chay Sốt Nấm', 38000, '/image/products/hamburger.png', 15, 1),
(23, 7, 'Trà Sữa Trân Châu', 32000, '/image/products/hamburger.png', 45, 1),
(24, 7, 'Pepsi Lon', 15000, '/image/products/hamburger.png', 50, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `fk_cate` (`category_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
