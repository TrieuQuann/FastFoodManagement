-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 05, 2025 lúc 04:24 PM
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
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`category_id`, `name`, `status`) VALUES
(1, 'Burger', 1),
(2, 'Gà rán', 1),
(3, 'Pizza', 1),
(4, 'Mì', 1),
(5, 'Đồ ăn nhẹ', 1),
(6, 'Đồ chay', 1),
(7, 'Thức uống', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `cus_id` int(11) NOT NULL,
  `cus_name` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customers`
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
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `eid` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `employee_name` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`eid`, `position_id`, `employee_name`, `phone`, `address`, `gender`) VALUES
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
-- Cấu trúc bảng cho bảng `feature`
--

CREATE TABLE `feature` (
  `id` int(11) NOT NULL,
  `feature_name` varchar(100) NOT NULL,
  `icon_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `feature`
--

INSERT INTO `feature` (`id`, `feature_name`, `icon_url`) VALUES
(1, 'Thống kê', 'image/icon/dashboard.png'),
(2, 'Sản phẩm', 'image/icon/products.png'),
(3, 'Khách hàng', 'image/icon/customers.png'),
(4, 'Nhân viên', 'image/icon/staff.png'),
(5, 'Kho', 'image/icon/inventory.png'),
(6, 'Hóa đơn', 'image/icon/bill.png'),
(7, 'Công thức', 'image/icon/recipe.png'),
(8, 'Nhà cung cấp', 'image/icon/supplier.png'),
(9, 'Phân quyền', 'image/icon/pquyen.png'),
(10, 'Danh mục', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invendetail`
--

CREATE TABLE `invendetail` (
  `inven_id` int(11) NOT NULL,
  `invoice_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `inventory`
--

CREATE TABLE `inventory` (
  `inven_id` int(11) NOT NULL,
  `name` varchar(156) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `inventory`
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
-- Cấu trúc bảng cho bảng `invoiceinvent`
--

CREATE TABLE `invoiceinvent` (
  `invoice_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `quantityadded` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetails`
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
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `cus_id` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `total_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
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
-- Cấu trúc bảng cho bảng `permissions`
--

CREATE TABLE `permissions` (
  `permission_id` int(11) NOT NULL,
  `permission_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `position`
--

CREATE TABLE `position` (
  `position_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `position`
--

INSERT INTO `position` (`position_id`, `name`) VALUES
(1, 'thu ngân'),
(2, 'pha chế'),
(3, 'đầu bếp'),
(4, 'phục vụ');

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
(11, 1, 'Burger Bò Phô Mai', 45000, '/image/products/burgerBoPhoMai.jpg', 50, 1),
(12, 1, 'Burger Gà Giòn', 42000, '/image/products/burgerGaGion.jpg', 40, 1),
(13, 2, 'Gà Rán Truyền Thống', 39000, '/image/products/gaRanTruyenThong.jpg', 60, 1),
(14, 2, 'Gà Rán Cay', 40000, '/image/products/gaRanCay.jpg', 55, 1),
(15, 3, 'Pizza Phô Mai', 68000, '/image/products/pizzaPhoMai.jpg', 30, 1),
(16, 3, 'Pizza Hải Sản', 75000, '/image/products/pizzaHaiSan.jpg', 25, 1),
(17, 4, 'Mì Spaghetti Bò Bằm', 52000, '/image/products/miBoBam.jpg', 35, 1),
(18, 4, 'Mì Ý Sốt Kem', 50000, '/image/products/miYSotKem.jpg', 30, 1),
(19, 5, 'Khoai Tây Chiên Lớn', 25000, '/image/products/khoaiTayChienLon.jpg', 70, 1),
(20, 5, 'Phô Mai Que', 30000, '/image/products/phoMaiQue.jpg', 40, 1),
(21, 6, 'Burger Chay', 35000, '/image/products/burgerChay.jpg', 20, 1),
(22, 6, 'Mì Chay Sốt Nấm', 38000, '/image/products/miChaySotNam.jpg', 15, 1),
(23, 2, 'Trà Sữa Trân Châu', 32000, '/image/products/hamburger.png', 45, 1),
(24, 7, 'Pepsi Lon', 15000, '/image/products/hamburger.png', 50, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `recipe`
--

CREATE TABLE `recipe` (
  `product_id` int(11) NOT NULL,
  `inven_id` int(11) NOT NULL,
  `unit` varchar(100) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rolepermission`
--

CREATE TABLE `rolepermission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `prop` varchar(51) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
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
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `paswd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`cus_id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`eid`),
  ADD KEY `fk_position` (`position_id`);

--
-- Chỉ mục cho bảng `feature`
--
ALTER TABLE `feature`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `invendetail`
--
ALTER TABLE `invendetail`
  ADD PRIMARY KEY (`inven_id`,`invoice_id`),
  ADD KEY `fk_invoice` (`invoice_id`);

--
-- Chỉ mục cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inven_id`);

--
-- Chỉ mục cho bảng `invoiceinvent`
--
ALTER TABLE `invoiceinvent`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `fk_suppli` (`supplier_id`);

--
-- Chỉ mục cho bảng `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `fk_product2` (`product_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fk_cusid` (`cus_id`),
  ADD KEY `fk_eid2` (`eid`);

--
-- Chỉ mục cho bảng `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`permission_id`);

--
-- Chỉ mục cho bảng `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`position_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `fk_cate` (`category_id`);

--
-- Chỉ mục cho bảng `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`product_id`,`inven_id`),
  ADD KEY `fk_inven2` (`inven_id`);

--
-- Chỉ mục cho bảng `rolepermission`
--
ALTER TABLE `rolepermission`
  ADD PRIMARY KEY (`role_id`,`permission_id`,`prop`),
  ADD KEY `fk_permission` (`permission_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `fk_roleid` (`role_id`),
  ADD KEY `fk_eid` (`eid`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `eid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=190;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
