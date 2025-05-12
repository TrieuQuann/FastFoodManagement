-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 13, 2025 lúc 01:36 AM
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
(5, 'Thêm hóa đơn', 'image/icon/createOrder.png'),
(6, 'Hóa đơn', 'image/icon/bill.png'),
(7, 'Công thức', 'image/icon/recipe.png'),
(8, 'Nhà cung cấp', 'image/icon/supplier.png'),
(9, 'Phân quyền', 'image/icon/pquyen.png'),
(10, 'Danh mục', 'image/icon/list.png'),
(11, 'Tài khoản', 'image/icon/taikhoan.png'),
(13, 'Khách hàng', 'image/icon/customers.png'),
(14, 'Nhân viên', 'image/icon/staff.png'),
(15, 'Kho', 'image/icon/inventory.png');

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
  `unit` varchar(100) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `inventory`
--

INSERT INTO `inventory` (`inven_id`, `name`, `quantity`, `unit`, `price`) VALUES
(96, 'Bánh mì', 10, 'chiếc', 3000),
(97, 'Thịt gà', 50, 'kg', 90000),
(98, 'Khoai tây', 30, 'kg', 12000),
(99, 'sữa tươi\r\n', 20, 'lít', 30000),
(100, 'Sữa đặc', 15, 'lít', 50000),
(101, 'Bột mì', 10, 'kg', 30000),
(102, 'Xúc xích', 200, 'cái', 2000),
(103, 'Dưa leo', 10, 'kg', 15000),
(104, 'Cà chua', 5, 'kg', 30000),
(105, 'Hành tây', 40, 'củ', 2000),
(106, 'Cà phê', 5000, 'g', 200),
(107, 'Đá viên', 50, 'kg', 2000),
(109, 'Sốt mayonnaise', 5, 'lít', 150000),
(110, 'Sốt cà chua', 5, 'lít', 50000),
(111, 'Bột tiêu', 10000, 'g', 2000),
(112, 'Muối', 10000, 'g', 15),
(113, 'Dầu ăn', 20000, 'mililit', 30),
(115, 'Phô mai', 10, 'kg', 30000),
(116, 'Nước mắm', 30000, 'mililit', 20),
(117, 'Đường', 20000, 'gam', 40);

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
  `total_price` double NOT NULL,
  `status` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orderdetails`
--

INSERT INTO `orderdetails` (`order_id`, `product_id`, `quantity`, `price`, `total_price`, `status`) VALUES
(1, 11, 2, 45000, 90000, 1),
(1, 19, 1, 25000, 25000, 1),
(1, 23, 1, 32000, 32000, 1),
(2, 12, 2, 42000, 84000, 1),
(2, 19, 1, 25000, 25000, 1),
(3, 13, 2, 39000, 78000, 1),
(3, 23, 1, 32000, 32000, 1),
(4, 14, 3, 40000, 120000, 1),
(4, 19, 1, 25000, 25000, 1),
(5, 15, 2, 68000, 136000, 1),
(5, 20, 1, 30000, 30000, 1),
(6, 16, 1, 75000, 75000, 1),
(6, 21, 2, 35000, 70000, 1),
(7, 17, 2, 52000, 104000, 1),
(7, 22, 1, 38000, 38000, 1),
(8, 18, 2, 50000, 100000, 1),
(9, 13, 1, 39000, 39000, 1),
(9, 14, 2, 40000, 80000, 1),
(10, 11, 2, 45000, 90000, 1),
(10, 12, 12, 42000, 504000, 1),
(10, 23, 1, 32000, 32000, 1),
(11, 12, 12, 42000, 504000, 1),
(12, 12, 12, 42000, 504000, 1),
(13, 13, 12, 39000, 468000, 1),
(14, 12, 10, 42000, 420000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `cus_id` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `total_amount` double NOT NULL,
  `deleted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`order_id`, `cus_id`, `eid`, `order_date`, `total_amount`, `deleted`) VALUES
(1, 1, 1, '2025-04-01', 150000, 0),
(2, 2, 2, '2025-04-01', 120000, 0),
(3, 3, 3, '2025-04-01', 100000, 0),
(4, 4, 4, '2025-04-01', 180000, 0),
(5, 5, 5, '2025-04-01', 160000, 0),
(6, 6, 6, '2025-04-01', 140000, 0),
(7, 7, 7, '2025-04-01', 170000, 0),
(8, 8, 8, '2025-04-01', 90000, 0),
(9, 9, 9, '2025-04-01', 130000, 0),
(10, 10, 10, '2025-04-01', 110000, 0),
(12, 1, 1, '2025-05-13', 504000, 0),
(13, 1, 1, '2025-05-13', 468000, 0),
(14, 1, 1, '2025-05-13', 420000, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permissions`
--

CREATE TABLE `permissions` (
  `permission_id` int(11) NOT NULL,
  `permission_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `permissions`
--

INSERT INTO `permissions` (`permission_id`, `permission_name`) VALUES
(1, 'Quản lý sản phẩm'),
(2, 'Quản lý tài khoản'),
(3, 'Quản lý nhân viên'),
(4, 'Quản lý hóa đơn'),
(5, 'Quản lý công thức'),
(6, 'Quản lý danh mục'),
(7, 'Quản lý khách hàng');

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
(11, 1, 'Burger Bò Phô Mai', 45000, '/image/products/burgerBoPhoMai.jpg', 50, 0),
(12, 1, 'Burger Gà Giòn', 42000, '/image/products/burgerGaGion.jpg', 30, 1),
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
(23, 7, 'Trà Sữa Trân Châu', 32000, '/image/products/traSua.jpg', 45, 1),
(24, 7, 'Pepsi Lon', 15000, '/image/products/pepsi.png', 50, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `recipe`
--

CREATE TABLE `recipe` (
  `product_id` int(11) NOT NULL,
  `inven_id` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `total_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `recipe`
--

INSERT INTO `recipe` (`product_id`, `inven_id`, `amount`, `total_price`) VALUES
(12, 96, 3, 9000),
(12, 97, 1, 45000),
(12, 98, 1, 6000),
(12, 99, 3, 90000),
(12, 100, 1, 50000),
(12, 101, 1, 30000),
(12, 102, 3, 6000),
(12, 103, 1, 10500),
(12, 104, 1, 15000),
(12, 105, 3, 6000),
(12, 110, 2, 50000),
(12, 111, 2, 4000),
(12, 115, 3, 90000),
(15, 100, 10, 5000),
(15, 110, 10, 5000),
(15, 112, 10, 150),
(15, 115, 20, 3000),
(16, 112, 10, 150),
(20, 96, 1, 3000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rolepermission`
--

CREATE TABLE `rolepermission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `prop` varchar(51) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `rolepermission`
--

INSERT INTO `rolepermission` (`role_id`, `permission_id`, `prop`) VALUES
(1, 2, 'create'),
(1, 2, 'delete'),
(1, 2, 'read'),
(1, 2, 'update'),
(4, 1, 'create'),
(4, 1, 'delete'),
(4, 1, 'read'),
(4, 1, 'update'),
(4, 5, 'read');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'Quản lý'),
(2, 'Thu ngân'),
(4, 'Quản kho');

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

--
-- Đang đổ dữ liệu cho bảng `suppliers`
--

INSERT INTO `suppliers` (`supplier_id`, `supplier_name`, `email`, `address`, `phone`) VALUES
(1, 'Công ty TNHH Thực Phẩm Việt', 'contact@thucphamviet.vn', '12 Nguyễn Văn Cừ, Hà Nội', '0901122334'),
(2, 'Nhà Cung Cấp Bánh Mì ABC', 'info@banhmiabc.vn', '23 Lê Lợi, TP.HCM', '0912233445'),
(3, 'FreshMeat Co.', 'sales@freshmeat.vn', '89 Tôn Đức Thắng, TP.HCM', '0933344556'),
(4, 'GreenVeggie Việt Nam', 'support@greenveggie.vn', '56 Trần Phú, Đà Nẵng', '0944455667'),
(5, 'Khoai Tây Sạch Co.', 'khoaitay@nguyenlieu.vn', '77 Nguyễn Huệ, Huế', '0955566778'),
(6, 'CheeseLand Supplier', 'cheese@cheeseland.vn', '101 Lý Thường Kiệt, Hà Nội', '0966677889'),
(7, 'FastFood Ingredients Ltd.', 'fast@fastfood.vn', '99 Trường Chinh, Cần Thơ', '0977788990'),
(8, 'Gia Vị Tự Nhiên 123', 'gia.vi@123.vn', '32 Phan Đình Phùng, Hà Nội', '0988899001'),
(9, 'Trứng Gà Organic Co.', 'trungga@organic.vn', '65 Điện Biên Phủ, TP.HCM', '0999900012'),
(10, 'Thực Phẩm Đông Lạnh Nhanh', 'frozen@nhanh.vn', '48 Hoàng Hoa Thám, Hải Phòng', '0900001122'),
(11, 'Trứng gà Binh Phước', 'trungga93@gmail.com', '1 DT741, Bình Phước', '0900001134');

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
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `role_id`, `eid`, `username`, `paswd`) VALUES
(1, 1, 1, 'admin1', '123'),
(2, 2, 3, 'cashier1', 'abc123'),
(3, 4, 12, 'quan123', '123'),
(4, 2, 11, 'giang234', '1234567890');

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
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `permissions`
--
ALTER TABLE `permissions`
  MODIFY `permission_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
