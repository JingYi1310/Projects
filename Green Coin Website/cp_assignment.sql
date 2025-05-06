-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 06, 2025 at 01:29 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cp_assignment`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `name`, `email`, `password`) VALUES
(1, 'Green Coin', 'greencoinreward@gmail.com', 'GreenC$1n!');

-- --------------------------------------------------------

--
-- Table structure for table `admin_notification`
--

DROP TABLE IF EXISTS `admin_notification`;
CREATE TABLE IF NOT EXISTS `admin_notification` (
  `anoti_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `datetime` datetime NOT NULL,
  `title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `announcement` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`anoti_id`),
  KEY `FK_adm_noti_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_notification`
--

INSERT INTO `admin_notification` (`anoti_id`, `user_id`, `datetime`, `title`, `announcement`, `status`) VALUES
(1, 1, '2025-01-03 08:41:19', '🆕 New User Sign-Up', 'A new user have successfully signed up on the platform. They can now participate in recyling activities and earn reward.', 'unread'),
(2, 1, '2025-01-04 08:35:53', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-01-10 at 10:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(3, 1, '2025-01-15 12:43:52', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(4, 2, '2025-01-06 21:50:28', '🆕 New User Sign-Up', 'A new user have successfully signed up on the platform. They can now participate in recyling activities and earn reward.', 'unread'),
(5, 1, '2025-01-19 10:15:20', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(6, 1, '2025-01-14 21:59:59', '📝 New User Review Received!', 'A user has share their recycling experience. Check the review section to view and respond.', 'unread'),
(7, 1, '2025-01-26 22:03:45', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-02-02 at 16:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(8, 1, '2025-02-05 09:21:12', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-02-11 at 11:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(9, 2, '2025-02-02 21:41:59', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-02-11 at 11:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(10, 3, '2025-02-18 22:39:15', '🆕 New User Sign-Up', 'A new user have successfully signed up on the platform. They can now participate in recyling activities and earn reward.', 'unread'),
(11, 3, '2025-02-20 13:15:57', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-03-02 at 10:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(12, 1, '2025-02-23 12:51:19', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-03-02 at 14:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(13, 1, '2025-03-07 05:13:09', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(14, 2, '2025-03-06 10:16:25', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(15, 1, '2025-03-20 07:35:40', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-04-02 at 11:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(16, 2, '2025-01-21 09:32:15', '📝 New User Review Received!', 'A user has share their recycling experience. Check the review section to view and respond.', 'unread'),
(17, 1, '2025-02-15 14:12:29', '🎁 New Reward Redemption!', 'A user has redeemed Phone Stand. Please ensure the reward is prepared and available for collection at all designated dropoff points.', 'unread'),
(18, 2, '2025-03-14 13:05:12', '🎁 New Reward Redemption!', 'A user has redeemed Meat Two Cuisine Cash Voucher (RM20). Please ensure the reward is prepared and available for collection at all designated dropoff points.', 'unread'),
(19, 3, '2025-03-15 13:35:01', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(20, 3, '2025-04-19 06:39:14', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-04-30 at 13:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(21, 2, '2025-03-21 12:12:41', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(22, 1, '2025-04-09 23:21:04', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(23, 3, '2025-04-22 08:01:34', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(24, 3, '2025-03-22 20:31:44', '📝 New User Review Received!', 'A user has share their recycling experience. Check the review section to view and respond.', 'unread'),
(25, 2, '2025-04-22 19:01:49', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-04-30 at 13:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(26, 1, '2025-05-03 07:41:09', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-05-12 at 14:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(27, 3, '2025-05-05 18:29:23', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-05-15 at 17:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(28, 2, '2025-05-09 23:12:52', '📅 New Pickup Request Received!', 'A user has submitted a pickup request for 2025-05-15 at 17:00:00. Please review and approve or reject the request as soon as possible.', 'unread'),
(29, 1, '2025-05-13 10:16:25', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(30, 1, '2025-04-26 10:33:38', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(31, 3, '2025-05-11 15:10:43', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread'),
(32, 2, '2025-05-12 08:37:13', '📦 New Dropoff Request Received!', 'A user has submitted a dropoff request. Please review and process it accordingly.', 'unread');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
CREATE TABLE IF NOT EXISTS `driver` (
  `driver_id` int NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contact_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `number_plate` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `driver_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `driver_license_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`driver_id`),
  KEY `FK_driver_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`driver_id`, `driver_name`, `contact_no`, `email`, `age`, `gender`, `address`, `number_plate`, `driver_image`, `driver_license_image`, `status`, `admin_id`) VALUES
(1, 'Tan Jia Hao', '011 - 2560 2353', 'tanjiahao@gmail.com', 28, 'Male', '19-1, Jalan 3/149k, Bandar Baru Sri Petaling, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', 'VCF 8081', '1Nj1KlncVoqxr_jprMg0laXj0rbN5C9sV', '1-WkIP3QAyj5c8fnRbLx5rEqjJIsb_yt3', 'Available', 1),
(2, 'Hakim Bin Salleh', '012 - 509 5204', 'hakimbinsalleh@gmail.com', 40, 'Male', '50, Jalan Kuchai Maju 10, Kuchai Entrepreneurs Park, 58200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', 'VED 7410', '15eBbRsgjCwX9YtmxZ1nrCoFb3fgxyptn', '1b4jjDn-mZnsUUCpeIa4Raavw-uXa5VbL', 'Available', 1),
(3, 'Arun Kumar A/L Rajan', '018 - 2563 7206', 'arunkumar@gmail.com', 32, 'Male', '47-1, Jalan Jalil Perkasa 10, Taman Esplanad, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', 'VAA 9208', '1RPWqLEWdiqPAF2kLnXgflAvRdi677iba', '1MYS3K3MmVfzzZW7MQLyEcOUeJ1pXbM03', 'Available', 1);

-- --------------------------------------------------------

--
-- Table structure for table `dropoff`
--

DROP TABLE IF EXISTS `dropoff`;
CREATE TABLE IF NOT EXISTS `dropoff` (
  `dropoff_id` int NOT NULL AUTO_INCREMENT,
  `datetime_submit_form` datetime NOT NULL,
  `dropoff_date` date NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int NOT NULL,
  `location_id` int NOT NULL,
  `item_image` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `total_point_earned` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  PRIMARY KEY (`dropoff_id`),
  KEY `FK_dropoff_user_id` (`user_id`),
  KEY `FK_dropoff_location_id` (`location_id`),
  KEY `FK_dropoff_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dropoff`
--

INSERT INTO `dropoff` (`dropoff_id`, `datetime_submit_form`, `dropoff_date`, `status`, `user_id`, `location_id`, `item_image`, `total_point_earned`, `admin_id`) VALUES
(1, '2025-01-15 12:43:52', '2025-01-17', 'Complete', 1, 2, '1Tfi3NmKcYrjj3r_hgus8K2Cng1AWIP2W', 2, 1),
(2, '2025-01-19 10:15:20', '2025-01-20', 'Complete', 2, 12, '17hoDBftaHkPDwDVN_iNOkeJjldyxlTi-', 10, 1),
(3, '2025-03-07 05:13:09', '2025-03-08', 'Complete', 1, 6, '12ZB2qnkw92oQEicPC5ba-LB-ML2fBp8O', 50, 1),
(4, '2025-03-06 10:16:25', '2025-03-09', 'Complete', 2, 6, '12ZB2qnkw92oQEicPC5ba-LB-ML2fBp8O', 50, 1),
(5, '2025-03-15 13:35:01', '2025-03-16', 'Complete', 3, 9, '1EC4wMJiJCk2t3NNYHqq8hjLOxezBPXnk', 20, 1),
(6, '2025-03-21 12:12:41', '2025-03-22', 'Complete', 2, 7, '1cnO8rINnEEp86q5N3I192Cku-fWp3OSa', 10, 1),
(7, '2025-04-09 23:21:04', '2025-04-12', 'Complete', 1, 7, '10LXedZQAMy18wOpcOAjlJ6DCkdRIqZcw', 20, 1),
(8, '2025-04-22 08:01:34', '2025-04-24', 'Complete', 3, 9, '1UyRf7fgtjzOSs_HJ7Ix-q7YaONuZoME2', 30, 1),
(9, '2025-05-13 10:16:25', '2025-05-15', 'unread', 1, 7, NULL, NULL, NULL),
(10, '2025-04-26 10:33:38', '2025-04-29', 'Complete', 3, 6, '1Ht8QDprZDhcWskeRUs4Ann3XJr78w_gd', 30, 1),
(11, '2025-05-11 15:10:43', '2025-05-15', 'unread', 3, 8, NULL, NULL, NULL),
(12, '2025-05-12 08:37:13', '2025-05-13', 'Complete', 2, 14, '1rF-qTo8Hp7MeN71lMCZdIlYzbKw0CuD', 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `email_verification`
--

DROP TABLE IF EXISTS `email_verification`;
CREATE TABLE IF NOT EXISTS `email_verification` (
  `email_veri_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `veri_code` int NOT NULL,
  `created_at` datetime NOT NULL,
  `expired_at` datetime NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`email_veri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
CREATE TABLE IF NOT EXISTS `faq` (
  `faq_id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `answer` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`faq_id`),
  KEY `FK_faq_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `faq`
--

INSERT INTO `faq` (`faq_id`, `question`, `answer`, `category`, `admin_id`) VALUES
(1, 'What is Green Coin?', 'Green Coin is a digital platform that allows users to recycle e-waste conveniently through pickup scheduling and drop-off locations while earning reward points for their contributions.', 'General', 1),
(2, 'Why should I recycle my e-waste?', 'E-waste contains harmful materials that can pollute the environment if not disposed of properly. Recycling helps recover valuable materials and reduces environmental impact.', 'General', 1),
(3, 'Who can use Green Coin?', 'Our platform is available for households, small businesses, and individuals who want to recycle electronic waste in an eco-friendly way.', 'General', 1),
(4, 'How do I create an account?', 'Simply click on the \"Sign Up\" button, fill in your details, and verify your email. Once registered, you can start scheduling pickups or locating drop-off points.', 'General', 1),
(5, 'Is there a fee for using Green Coin?', 'No, our platform is free to use! You can recycle your e-waste without any additional cost.', 'General', 1),
(6, 'How do I schedule a pickup?', 'After logging in, go to the \"Schedule Pickup\" section, select the items you want to recycle, choose a pickup date and time, and submit your request.', 'Pickup Scheduling', 1),
(7, 'Which areas are covered for pickup services?', 'Currently, we provide pickup services within Kuala Lumpur and selected areas in Selangor. You can check availability when scheduling a pickup.', 'Pickup Scheduling', 1),
(8, 'Can I modify or cancel my pickup request?', 'Yes, you can modify or cancel your request before the scheduled date by accessing the \"My Pickups\" section in your account.', 'Pickup Scheduling', 1),
(9, 'Do I need to prepare my e-waste before pickup?', 'Yes, we recommend placing your items in a secure and accessible location. For larger items, ensure they are ready for easy collection.', 'Pickup Scheduling', 1),
(10, 'What types of e-waste can be picked up?', 'We accept various electronics, including computers, mobile phones, printers, and small household appliances. A full list is available in the pickup section.', 'Pickup Scheduling', 1),
(11, 'How do I find the nearest drop-off point?', 'You can use our \"Drop-Off Locator\" to find the closest collection center. The map will show locations, operating hours, and accepted e-waste types.', 'Drop-off Points', 1),
(12, 'Do I need to inform the system before dropping off my e-waste?', 'Yes, before dropping off, you need to fill in a short form on our website indicating the items you plan to recycle.', 'Drop-off Points', 1),
(13, 'How is my recycling confirmed at a drop-off point?', 'Our assigned staff at each drop-off point will verify the items and update your recycling history in the system.', 'Drop-off Points', 1),
(14, 'What should I do if my drop-off is not recorded?', 'If your recycling record is missing, please contact our support team with proof of drop-off, such as a receipt or confirmation from the collection center.', 'Drop-off Points', 1),
(15, 'How do I earn reward points?', 'You earn points based on the type and quantity of e-waste you recycle, whether through scheduled pickup or drop-off locations.', 'Rewards', 1),
(16, 'Where can I check my reward points?', 'You can view your total points and transaction history in the \"My Rewards\" section of your account.', 'Rewards', 1),
(17, 'What can I redeem with my points?', 'Points can be redeemed for vouchers, discounts, or eco-friendly products. Check the \"Rewards Catalog\" for available options.', 'Rewards', 1),
(18, 'Do my points expire?', 'Yes, points are valid for a specific period. The expiration date for each reward is displayed in your account.', 'Rewards', 1),
(19, 'Can I transfer my points to another user?', 'No, reward points are non-transferable and can only be used by the account holder.', 'Rewards', 1),
(20, 'What happens if my points are deducted unfairly?', 'If you notice any incorrect point deductions, please contact our support team with details, and we will investigate the issue.', 'Rewards', 1);

-- --------------------------------------------------------

--
-- Table structure for table `forgot_pw`
--

DROP TABLE IF EXISTS `forgot_pw`;
CREATE TABLE IF NOT EXISTS `forgot_pw` (
  `forgot_pw_id` int NOT NULL AUTO_INCREMENT,
  `veri_code` int NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime NOT NULL,
  `expired_at` datetime NOT NULL,
  PRIMARY KEY (`forgot_pw_id`),
  KEY `FK_forgotpw_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `point_given` int NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK_item_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `point_given`, `status`, `admin_id`) VALUES
(1, 'Tablet', 30, 'Available', 1),
(2, 'Smartphone', 20, 'Available', 1),
(3, 'Laptop', 50, 'Available', 1),
(4, 'Gaming Console', 10, 'Available', 1),
(5, 'Camera', 10, 'Available', 1),
(6, 'Audio Device', 10, 'Available', 1),
(7, 'Smartwatch', 10, 'Available', 1),
(8, 'CPU & Server', 10, 'Available', 1),
(9, 'Keyboards & Docking Stations', 5, 'Available', 1),
(10, 'Modems, Switches & Routers', 10, 'Available', 1),
(11, 'TV & Computer Monitor', 40, 'Available', 1),
(12, 'Printers, Scanners & Fax Machines', 20, 'Available', 1),
(14, 'DVD & VCR Players', 5, 'Available', 1),
(15, 'Refrigerator', 80, 'Available', 1),
(16, 'Microwave & Oven', 50, 'Available', 1),
(17, 'Washing Machine & Dryer', 80, 'Available', 1),
(18, 'Fan', 30, 'Available', 1),
(19, 'Vacuum', 20, 'Available', 1),
(21, 'Batteries & Accessories', 2, 'Available', 1),
(22, 'Industrial Electronics', 2, 'Available', 1),
(23, 'Mixer & Blender', 30, 'Available', 1),
(24, 'Rice Cooker & Air Fryer', 30, 'Available', 1);

-- --------------------------------------------------------

--
-- Table structure for table `item_dropoff`
--

DROP TABLE IF EXISTS `item_dropoff`;
CREATE TABLE IF NOT EXISTS `item_dropoff` (
  `item_dropoff_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `quantity` int NOT NULL,
  `dropoff_id` int NOT NULL,
  PRIMARY KEY (`item_dropoff_id`),
  KEY `FK_item_dropoff_dropoff_id` (`dropoff_id`),
  KEY `FK_item_dropoff_item_id` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item_dropoff`
--

INSERT INTO `item_dropoff` (`item_dropoff_id`, `item_id`, `quantity`, `dropoff_id`) VALUES
(1, 21, 1, 1),
(2, 5, 1, 2),
(3, 3, 1, 4),
(4, 3, 1, 3),
(5, 2, 1, 5),
(6, 7, 1, 6),
(7, 12, 1, 7),
(8, 23, 1, 8),
(9, 1, 1, 10),
(10, 4, 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `item_pickup`
--

DROP TABLE IF EXISTS `item_pickup`;
CREATE TABLE IF NOT EXISTS `item_pickup` (
  `item_pickup_id` int NOT NULL AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `quantity` int NOT NULL,
  `pickup_request_id` int NOT NULL,
  PRIMARY KEY (`item_pickup_id`),
  KEY `FK_item_pickup_pickup_request_id` (`pickup_request_id`),
  KEY `FK_item_pickup_item_id` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item_pickup`
--

INSERT INTO `item_pickup` (`item_pickup_id`, `item_id`, `quantity`, `pickup_request_id`) VALUES
(1, 17, 1, 1),
(2, 18, 1, 2),
(3, 18, 1, 3),
(4, 15, 2, 4),
(5, 24, 1, 5),
(6, 16, 1, 6),
(7, 19, 1, 8),
(8, 15, 2, 7),
(9, 11, 1, 9),
(10, 12, 1, 10),
(11, 16, 1, 11),
(12, 24, 1, 11),
(13, 8, 1, 12),
(14, 9, 1, 12),
(15, 14, 1, 13);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `location_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contact_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `location_name`, `address`, `contact_no`, `description`, `status`) VALUES
(1, 'Green Coin Sri Petaling', 'No 63 & 63, 1, Jalan Radin Tengah, Bandar Baru Sri Petaling, 57000 Kuala Lumpur', '03 - 9054 0493', '10am - 6pm', 'Available'),
(2, 'Green Coin Bukit Bintang', '120-120A, Jln Bukit Bintang, Bukit Bintang, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 2142 7843', '10am - 6pm', 'Available'),
(3, 'Green Coin Bangsar', '48, Jalan Telawi 5, Bangsar, 59100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 2201 2551', '10am - 6pm', 'Available'),
(4, 'Green Coin Jalan Tun Perak', 'Wisma Teck Lee, 38, Jalan Tun Perak, Ground & Mezzanine Floor, 50050 Kuala Lumpur', '03 - 2026 6899', '10am - 6pm', 'Available'),
(5, 'Green Coin Cheras', 'Lot PT 7430, HSD 111856, KM 5, 5, Jln Cheras, 56000 Cheras, Federal Territory of Kuala Lumpur', '03 - 9134 2127', '10am - 6pm', 'Available'),
(6, 'Green Coin Desa Pandan', 'Lot 2 & 2-1, Jalan 1/76d, Desa Pandan, 55100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 9202 8218', '10am - 6pm', 'Available'),
(7, 'Green Coin Kuchai Lama', '1/116B, off, Jalan Kuchai Lama, Kuchai Entrepreneurs Park, 58200 Kuala Lumpur', '03 - 7982 2180', '10am - 6pm', 'Available'),
(8, 'Green Coin Taman Connaught', 'Lot 18113, Jalan Cendikiawan, Mukim Petaling, 55100 Kuala Lumpur, Federal Territory of Kuala Lumpur', '03 - 8958 7179', '10am - 6pm', 'Available'),
(9, 'Green Coin Bukit Kerinchi', '2, Jalan Pantai Sentral, Bukit Kerinchi, 59200 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 2241 1097', '10am - 6pm', 'Available'),
(10, 'Green Coin Taman Desa', 'Jalan 2/109e, Taman Danau Desa, 58100 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 7972 0127', '10am - 6pm', 'Available'),
(12, 'Green Coin Setapak', 'Lot 8, Section, 85, Jalan Pahang, Setapak, 53000 Kuala Lumpur', '03 - 4031 1346', '10am - 6pm', 'Available'),
(13, 'Green Coin Titiwangsa', '9, Jalan Fletcher, Titiwangsa, 50400 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 2602 2147', '10am - 6pm', 'Available'),
(14, 'Green Coin Bukit Jalil', 'Jalan Teknologi 5, Taman Teknologi Malaysia, 57000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur', '03 - 8996 1000', '10am - 6pm', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `pickup_request`
--

DROP TABLE IF EXISTS `pickup_request`;
CREATE TABLE IF NOT EXISTS `pickup_request` (
  `pickup_request_id` int NOT NULL AUTO_INCREMENT,
  `time_slot_id` int NOT NULL,
  `datetime_submit_form` datetime NOT NULL,
  `address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `contact_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `item_image` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int NOT NULL,
  `total_point_earned` int DEFAULT NULL,
  `driver_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  PRIMARY KEY (`pickup_request_id`),
  KEY `FK_pickup_request_user_id` (`user_id`),
  KEY `FK_pickup_request_driver_id` (`driver_id`),
  KEY `FK_pickup_request_time_slot_id` (`time_slot_id`),
  KEY `FK_pickup_request_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pickup_request`
--

INSERT INTO `pickup_request` (`pickup_request_id`, `time_slot_id`, `datetime_submit_form`, `address`, `contact_no`, `remark`, `item_image`, `status`, `user_id`, `total_point_earned`, `driver_id`, `admin_id`) VALUES
(1, 6, '2025-01-04 08:35:53', '123, Jalan ABC, Taman DEF, Kuala Lumpur', '011 - 1234 5678', NULL, '1yXJTd6udOYsROs8xwHMS0fGTKZKJngrB', 'Completed', 1, 80, 1, 1),
(2, 16, '2025-01-26 22:03:45', '-', '011 - 1234 5678', NULL, '1u-LGwngwpXjS8CyZx6hMa1OrXy2_jycw', 'Rejected', 1, 0, NULL, 1),
(3, 20, '2025-02-05 09:21:12', '123, Jalan ABC, Taman DEF, Kuala Lumpur', '011 - 1234 5678', NULL, '1u-LGwngwpXjS8CyZx6hMa1OrXy2_jycw', 'Completed', 1, 30, 3, 1),
(4, 20, '2025-02-02 21:41:59', 'GHI, Jalan JKL, Taman XYZ, Kuala Lumpur', '011 - 2233 4455', NULL, '1EgvS2vzvO_fxjmsKaF4uh6DhCrlDGVdw', 'Completed', 2, 160, 2, 1),
(5, 33, '2025-02-20 13:15:57', '456, Jalan 789, Taman 123, Kuala Lumpur', '011 - 1234 1234', NULL, '1SbXQ1O3kpPueeIV1qKAYkEc9nfltwxPQ', 'Completed', 3, 30, 1, 1),
(6, 34, '2025-02-23 12:51:19', '123, Jalan ABC, Taman DEF, Kuala Lumpur', '011 - 1234 5678', NULL, '1viodLteRKVpJ67bSylH5aYIwQ1x3dTJY', 'Completed', 1, 50, 3, 1),
(7, 45, '2025-03-20 07:35:40', '123, Jalan ABC, Taman DEF, Kuala Lumpur', '011 - 1234 5678', NULL, '1EgvS2vzvO_fxjmsKaF4uh6DhCrlDGVdw', 'Completed', 1, 160, 3, 1),
(8, 49, '2025-04-03 13:07:52', 'A1, Jalan 123E, Taman B1C2, Negeri Sembilan', '011 - 9876 5432', NULL, '1srql-sflsJ9QxtKUJ0H6aJLLsQxHVXix', 'Rejected', 1, 0, NULL, 1),
(9, 54, '2025-04-19 06:39:14', '456, Jalan 789, Taman 123, Kuala Lumpur', '011 - 1234 1234', NULL, '1NkUw9O9V6nXEDG7jJ5bNH3aXBxdQKVUI', 'Completed', 3, 40, 1, 1),
(10, 54, '2025-04-22 19:01:49', '456, Jalan 789, Taman 123, Kuala Lumpur', '011 - 1234 1234', NULL, '10LXedZQAMy18wOpcOAjlJ6DCkdRIqZcw', 'Completed', 2, 20, 2, 1),
(11, 57, '2025-05-03 07:41:09', 'A1, Jalan 123E, Taman B1C2, Negeri Sembilan', '011 - 9876 5432', NULL, '1yOjUCwqcqRwkaGMdgP9aJhCDAvxekb2D', 'Completed', 1, 80, 2, 1),
(12, 58, '2025-05-05 18:29:23', '456, Jalan 789, Taman 123, Kuala Lumpur', '011 - 1234 1234', NULL, '1mza5_wxC4waaHCBT2Rs7TvBpX5AzvdJP', 'Approved', 3, NULL, 2, 1),
(13, 58, '2025-05-09 23:12:52', 'GHI, Jalan JKL, Taman XYZ, Johor', '011 - 2233 4455', NULL, '1nVAwDEKYWG6fLJkxlWAseo7kiXqYL12S', 'Unread', 2, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `redeem_reward`
--

DROP TABLE IF EXISTS `redeem_reward`;
CREATE TABLE IF NOT EXISTS `redeem_reward` (
  `redeem_reward_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `redeem_datetime` datetime NOT NULL,
  `reward_id` int NOT NULL,
  `location_id` int DEFAULT NULL,
  `collect_datetime` datetime DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  PRIMARY KEY (`redeem_reward_id`),
  KEY `FK_redeem_reward_user_id` (`user_id`),
  KEY `FK_redeem_reward_location_id` (`location_id`),
  KEY `FK_redeem_reward_reward_id` (`reward_id`),
  KEY `FK_redeem_reward_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `redeem_reward`
--

INSERT INTO `redeem_reward` (`redeem_reward_id`, `user_id`, `redeem_datetime`, `reward_id`, `location_id`, `collect_datetime`, `status`, `admin_id`) VALUES
(1, 1, '2025-02-15 14:12:29', 9, 10, '2025-02-17 10:09:16', 'Redeemed', 1),
(2, 2, '2025-03-14 13:05:12', 1, 14, '2025-03-15 17:31:05', 'Redeemed', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reply_review`
--

DROP TABLE IF EXISTS `reply_review`;
CREATE TABLE IF NOT EXISTS `reply_review` (
  `reply_review_id` int NOT NULL AUTO_INCREMENT,
  `review` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date` datetime NOT NULL,
  `review_id` int NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`reply_review_id`),
  KEY `FK_reply_review_review_id` (`review_id`),
  KEY `FK_reply_review_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reply_review`
--

INSERT INTO `reply_review` (`reply_review_id`, `review`, `date`, `review_id`, `admin_id`) VALUES
(1, 'Thank you so much for your kind words! We\'re glad to hear you found the website easy to use and that scheduling your pickup was a breeze. We\'re especially happy that you appreciate the rewards for recycling—it\'s our way of saying thanks for making a positive impact! We hear you on the drop-off locations and are actively working on expanding our network to make things even more convenient. Thanks again for your feedback and support!', '2025-01-23 11:11:48', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `dropoff_id` int DEFAULT NULL,
  `pickup_request_id` int DEFAULT NULL,
  `review` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date` datetime NOT NULL,
  `star` int NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FK_review_pickup_request_id` (`pickup_request_id`),
  KEY `FK_review_dropoff_id` (`dropoff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`review_id`, `dropoff_id`, `pickup_request_id`, `review`, `date`, `star`) VALUES
(1, NULL, 1, 'The pickup service was smooth and right on time! The team was friendly, efficient, and made the whole process super easy. It feels great to recycle responsibly without any hassle. Highly recommend!', '2025-01-14 21:59:59', 5),
(2, 2, NULL, 'The recycling process is smooth, and I love that they offer both pickup and drop-off options. The rewards system is a great motivator! Would be nice to have more detailed tracking of the recycling process, though.', '2025-01-21 09:32:15', 4),
(3, 5, NULL, 'The drop-off process was smooth and stress-free. The staff was friendly and handled everything with care. Highly recommend!', '2025-03-22 20:31:44', 5);

-- --------------------------------------------------------

--
-- Table structure for table `reward`
--

DROP TABLE IF EXISTS `reward`;
CREATE TABLE IF NOT EXISTS `reward` (
  `reward_id` int NOT NULL AUTO_INCREMENT,
  `reward_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `point_needed` int NOT NULL,
  `reward_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantity` int NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`reward_id`),
  KEY `FK_reward_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reward`
--

INSERT INTO `reward` (`reward_id`, `reward_name`, `point_needed`, `reward_image`, `quantity`, `status`, `category`, `admin_id`) VALUES
(1, 'Meat Two Cuisine Cash Voucher (RM20)', 200, '1FLHPBCad0bHqB6bGPPF7vuKrlomAGFat', 100, 'Available', 'Food & Beverage', 1),
(2, 'Moonbucks Coffee Shop Cash Voucher (RM20)', 250, '1yIrrfg9bg72AkH0Cu4sr_gxs1hUE1wel', 99, 'Available', 'Food & Beverage', 1),
(3, 'McDaisy Cash Voucher (RM20)', 288, '15AuYiJU3lZBs0-hkPYGDgzNrxgNjqJoD', 99, 'Available', 'Food & Beverage', 1),
(4, 'Shoppy Online Shopping Voucher (RM35)', 1000, '14YUvbI0T2xJIiNFw0O1PcmGHz7DQ2UZ2', 100, 'Available', 'Health & Lifestyle', 1),
(5, 'Wotsen Cash Voucher (RM50)', 1500, '1lOmDOlAhHgUiUhR5Qq4OvRB01cViixJU', 100, 'Available', 'Health & Lifestyle', 1),
(6, '88 Speedmart Cash Voucher (RM20)', 388, '1DcOVXiWepYqegxMI3PeZI1SCBABQvzCx', 100, 'Available', 'Health & Lifestyle', 1),
(7, 'Orange Wireless Earbuds', 25888, '1SN5xEN9M2IBdDPRK_PD4yuNHEfCVL6BN', 100, 'Available', 'Tech Accessories', 1),
(8, 'iGreen Portable Power Bank', 3200, '1jHUco20S4-Je9kxdLbHnrCO-JB3wKPfl', 100, 'Available', 'Tech Accessories', 1),
(9, 'Phone Stand', 100, '1zd27tIYol_dq6f0sos1ylm0sj9lVEcpw', 98, 'Available', 'Tech Accessories', 1),
(10, 'Dami Fitness Band', 3000, '12Qg6wR1uEtvX2hEuyqrLrnv2NQSI1uq2', 100, 'Available', 'Tech Accessories', 1),
(11, 'Bottleware Water Bottle', 700, '1YcIVeCdpkBvqpf9JNkrp-FowrruUPhyG', 100, 'Available', 'Eco-Friendly Products', 1),
(12, 'Green Coin Tote Bag', 150, '1GhFvXX_Hngd0Gy5-5GIK20eRiaRugIC_', 99, 'Available', 'Eco-Friendly Products', 1);

-- --------------------------------------------------------

--
-- Table structure for table `time_slot`
--

DROP TABLE IF EXISTS `time_slot`;
CREATE TABLE IF NOT EXISTS `time_slot` (
  `time_slot_id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `no_driver_per_slot` int NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`time_slot_id`),
  KEY `FK_time_slot_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `time_slot`
--

INSERT INTO `time_slot` (`time_slot_id`, `date`, `time`, `no_driver_per_slot`, `admin_id`) VALUES
(1, '2025-01-02', '10:00:00', 3, 1),
(2, '2025-01-02', '13:00:00', 3, 1),
(3, '2025-01-05', '11:00:00', 3, 1),
(4, '2025-01-07', '16:00:00', 3, 1),
(6, '2025-01-10', '10:00:00', 2, 1),
(7, '2025-01-10', '12:00:00', 3, 1),
(8, '2025-01-13', '14:00:00', 3, 1),
(9, '2025-01-13', '17:00:00', 3, 1),
(10, '2025-01-18', '10:00:00', 3, 1),
(11, '2025-01-18', '13:00:00', 3, 1),
(12, '2025-01-22', '16:00:00', 3, 1),
(13, '2025-01-23', '15:00:00', 3, 1),
(14, '2025-01-27', '14:00:00', 3, 1),
(15, '2025-01-31', '13:00:00', 3, 1),
(16, '2025-02-02', '16:00:00', 3, 1),
(17, '2025-02-05', '10:00:00', 3, 1),
(18, '2025-02-05', '12:00:00', 3, 1),
(19, '2025-02-09', '17:00:00', 3, 1),
(20, '2025-02-11', '11:00:00', 1, 1),
(21, '2025-02-11', '16:00:00', 3, 1),
(22, '2025-02-15', '11:00:00', 3, 1),
(23, '2025-02-15', '14:00:00', 3, 1),
(24, '2025-02-16', '12:00:00', 3, 1),
(25, '2025-02-19', '13:00:00', 3, 1),
(26, '2025-02-21', '15:00:00', 3, 1),
(29, '2025-02-24', '13:00:00', 3, 1),
(30, '2025-02-25', '14:00:00', 3, 1),
(31, '2025-02-27', '11:00:00', 3, 1),
(32, '2025-02-27', '15:00:00', 3, 1),
(33, '2025-03-02', '10:00:00', 2, 1),
(34, '2025-03-02', '14:00:00', 2, 1),
(35, '2025-03-06', '14:00:00', 3, 1),
(36, '2025-03-09', '15:00:00', 3, 1),
(37, '2025-03-11', '12:00:00', 3, 1),
(38, '2025-03-15', '13:00:00', 3, 1),
(39, '2025-03-17', '16:00:00', 3, 1),
(40, '2025-03-21', '14:00:00', 3, 1),
(41, '2025-03-21', '17:00:00', 3, 1),
(42, '2025-03-25', '16:00:00', 3, 1),
(43, '2025-03-30', '11:00:00', 3, 1),
(44, '2025-03-30', '14:00:00', 3, 1),
(45, '2025-04-02', '11:00:00', 2, 1),
(46, '2025-04-05', '15:00:00', 3, 1),
(47, '2025-04-08', '12:00:00', 3, 1),
(48, '2025-04-11', '16:00:00', 3, 1),
(49, '2025-04-16', '13:00:00', 3, 1),
(50, '2025-04-20', '13:00:00', 3, 1),
(51, '2025-04-25', '10:00:00', 3, 1),
(52, '2025-04-27', '13:00:00', 3, 1),
(53, '2025-04-27', '16:00:00', 3, 1),
(54, '2025-04-30', '13:00:00', 1, 1),
(55, '2025-05-04', '16:00:00', 3, 1),
(56, '2025-05-09', '10:00:00', 3, 1),
(57, '2025-05-12', '14:00:00', 2, 1),
(58, '2025-05-15', '17:00:00', 2, 1),
(59, '2025-05-19', '15:00:00', 3, 1),
(60, '2025-05-23', '13:00:00', 3, 1),
(61, '2025-05-26', '11:00:00', 3, 1),
(62, '2025-05-30', '15:00:00', 3, 1),
(63, '2025-06-03', '16:00:00', 3, 1),
(64, '2025-06-07', '10:00:00', 3, 1),
(65, '2025-06-09', '14:00:00', 3, 1),
(66, '2025-06-13', '15:00:00', 3, 1),
(67, '2025-06-15', '12:00:00', 3, 1),
(68, '2025-06-18', '11:00:00', 3, 1),
(69, '2025-06-21', '15:00:00', 3, 1),
(70, '2025-06-24', '10:00:00', 3, 1),
(71, '2025-06-27', '16:00:00', 3, 1),
(72, '2025-06-30', '14:00:00', 3, 1),
(73, '2025-05-15', '11:00:00', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dob` date NOT NULL,
  `address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` int DEFAULT NULL,
  `points` int NOT NULL,
  `profile_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `email`, `password`, `dob`, `address`, `phone_number`, `points`, `profile_image`, `created_at`, `status`) VALUES
(1, 'Jing Yi', 'jingyi123456@gmail.com', 'Jingyi123.', '2005-10-13', 'Sri Petaling, Kuala Lumpur', 1123456789, 372, 'User-Profile-Avatar-5.png', '2025-01-03 08:41:19', 'Verified'),
(2, 'Xin Yun', 'ongxinyun05@gmail.com', 'Xinyun123.', '2005-04-25', 'GHI, Jalan JKL, Taman XYZ, Kuala Lumpur', 1122334455, 60, 'User-Profile-Avatar-7.png', '2025-01-06 21:50:28', 'Verified'),
(3, 'Yee Shuen', 'yeeshuen123456@gmail.com', 'Yeeshuen123.', '2005-01-07', '456, Jalan 789, Taman 123, Kuala Lumpur', 1112341234, 370, 'User-Profile-Avatar-3.png', '2025-02-18 22:39:15', 'Verified');

-- --------------------------------------------------------

--
-- Table structure for table `user_notification`
--

DROP TABLE IF EXISTS `user_notification`;
CREATE TABLE IF NOT EXISTS `user_notification` (
  `unoti_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `datetime` datetime NOT NULL,
  `title` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `announcement` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `admin_id` int DEFAULT NULL,
  PRIMARY KEY (`unoti_id`),
  KEY `FK_usernoti_user_id` (`user_id`),
  KEY `FK_usernoti_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_notification`
--

INSERT INTO `user_notification` (`unoti_id`, `user_id`, `datetime`, `title`, `announcement`, `status`, `admin_id`) VALUES
(1, 1, '2025-01-03 08:41:19', 'Welcome to Green Coin, Jing Yi! 🌱', 'Hi, Jing Yi. Welcome to Greencoin! 🎉 We\'re excited to have you on board as part of our mission to \r\n                        promote responsible e-waste recycling. With Greencoin, you can easily schedule pickups for\r\n                        your old electronics, find nearby drop-off locations, and track your recycling progress. \r\n                        Plus, every item you recycle earns you points that can be redeemed for rewards! Start exploring \r\n                        now and make a positive impact on the environment while enjoying exclusive benefits. \r\n                        Happy recycling! 🌍♻️\r\n', 'read', NULL),
(2, 1, '2025-01-04 08:35:53', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-01-10 at 10:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(3, 1, '2025-01-04 10:05:10', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-01-10 at 10:00:00 has been approved. Our driver, Tan Jia Hao, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 011 - 2560 2353. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(4, 1, '2025-01-10 11:35:53', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 80 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(5, 1, '2025-01-15 12:43:52', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(6, 1, '2025-01-17 15:12:59', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 2 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(7, 2, '2025-01-06 21:50:28', 'Welcome to Green Coin, Xin Yun! 🌱', 'Hi, Xin Yun. Welcome to Greencoin! 🎉 We\'re excited to have you on board as part of our mission to \r\n                        promote responsible e-waste recycling. With Greencoin, you can easily schedule pickups for\r\n                        your old electronics, find nearby drop-off locations, and track your recycling progress. \r\n                        Plus, every item you recycle earns you points that can be redeemed for rewards! Start exploring \r\n                        now and make a positive impact on the environment while enjoying exclusive benefits. \r\n                        Happy recycling! 🌍♻️\r\n', 'read', NULL),
(8, 2, '2025-01-19 10:15:20', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(9, 2, '2025-01-20 17:20:07', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 10 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(10, 1, '2025-01-14 21:59:59', 'Review Submitted! 📝', 'Thank you for your feedback! 💬\r\n        Your review has been successfully submitted.\r\n        We truly appreciate you taking the time to share your experience — it helps us grow and serve you better. 🌟🌿\r\n', 'read', NULL),
(11, 1, '2025-01-26 22:03:45', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-02-02 at 16:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(12, 1, '2025-01-29 12:16:39', 'Pickup Request Rejected ❌ ', 'We\'re sorry! Your pickup request has been rejected due to the following reason: Incomplete information. You may submit a new request with updated details. If you have any questions, feel free to reach out. 🙏♻️\r\n', 'read', 1),
(13, 1, '2025-02-05 09:21:12', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-02-11 at 11:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(14, 1, '2025-02-06 14:19:58', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-02-11 at 11:00:00 has been approved. Our driver, Arun Kumar A/L Rajan, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 018 - 2563 7206. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(15, 1, '2025-02-11 16:52:54', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 30 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(16, 2, '2025-02-02 21:41:59', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-02-11 at 11:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(17, 2, '2025-02-03 15:45:10', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-02-11 at 11:00:00 has been approved. Our driver, Hakim Bin Salleh, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 012 - 509 5204. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(18, 2, '2025-02-11 17:11:14', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 160 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(19, 3, '2025-02-18 22:39:15', 'Welcome to Green Coin, Yee Shuen! 🌱', 'Hi, Yee Shuen. Welcome to Greencoin! 🎉 We\'re excited to have you on board as part of our mission to \r\n                        promote responsible e-waste recycling. With Greencoin, you can easily schedule pickups for\r\n                        your old electronics, find nearby drop-off locations, and track your recycling progress. \r\n                        Plus, every item you recycle earns you points that can be redeemed for rewards! Start exploring \r\n                        now and make a positive impact on the environment while enjoying exclusive benefits. \r\n                        Happy recycling! 🌍♻️\r\n', 'read', NULL),
(20, 3, '2025-02-20 13:15:57', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-03-02 at 10:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(21, 3, '2025-02-22 10:19:20', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-03-02 at 10:00:00 has been approved. Our driver, Tan Jia Hao, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 011 - 2560 2353. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(22, 3, '2025-03-02 11:35:53', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 30 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(23, 1, '2025-02-23 12:51:19', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-03-02 at 14:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(24, 1, '2025-02-25 16:05:12', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-03-02 at 14:00:00 has been approved. Our driver, Arun Kumar A/L Rajan, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 018 - 2563 7206. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(25, 1, '2025-03-02 16:13:08', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 50 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(26, 1, '2025-03-07 10:15:20', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(27, 1, '2025-03-07 05:13:09', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 50 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(28, 2, '2025-03-06 10:16:25', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(29, 2, '2025-03-08 11:15:47', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 50 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(30, 1, '2025-03-20 07:35:40', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-04-02 at 11:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(31, 1, '2025-03-22 11:16:33', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-04-02 at 11:00:00 has been approved. Our driver, Arun Kumar A/L Rajan, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 018 - 2563 7206. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(32, 1, '2025-04-02 12:34:51', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 160 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(33, 2, '2025-01-21 09:32:15', 'Review Submitted! 📝', 'Thank you for your feedback! 💬\r\n        Your review has been successfully submitted.\r\n        We truly appreciate you taking the time to share your experience — it helps us grow and serve you better. 🌟🌿\r\n', 'read', NULL),
(34, 2, '2025-01-23 11:11:48', 'Your Review Got a Reply! 💬', 'Your review has received a response! 📝✨ Visit the Review page to read the reply and stay connected with our team. Thank you for sharing your feedback! 💚', 'read', 1),
(35, 1, '2025-02-15 14:12:29', 'Reward Redeemed Successfully! 🎁', 'Great news! 🎁 You\'ve successfully redeemed Phone Stand for 100 points. \r\n                                    You can now visit any drop-off point to collect it. \r\n                                    Thank you for making a positive impact on the environment! 🌱♻️', 'read', 1),
(36, 2, '2025-03-14 13:05:12', 'Reward Redeemed Successfully! 🎁', 'Great news! 🎁 You\'ve successfully redeemed Meat Two Cuisine Cash Voucher (RM20) for 200 points. \r\n                                    You can now visit any drop-off point to collect it. \r\n                                    Thank you for making a positive impact on the environment! 🌱♻️', 'read', 1),
(37, 3, '2025-03-15 13:35:01', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(38, 3, '2025-03-16 17:09:01', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 20 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(39, 1, '2025-04-03 13:07:52', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-04-16 at 13:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(40, 1, '2025-04-04 10:21:10', 'Pickup Request Rejected ❌ ', 'We\'re sorry! Your pickup request has been rejected due to the following reason: Outside service area. You may submit a new request with updated details. If you have any questions, feel free to reach out. 🙏♻️\r\n', 'read', 1),
(41, 3, '2025-04-19 06:39:14', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-04-30 at 13:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(42, 3, '2025-04-21 11:10:41', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-04-30 at 13:00:00 has been approved. Our driver, Tan Jia Hao, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 011 - 2560 2353. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(43, 3, '2025-04-30 16:12:57', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 40 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(44, 2, '2025-03-21 12:12:41', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(45, 2, '2025-03-22 14:30:35', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 10 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(46, 1, '2025-04-09 23:21:04', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(47, 1, '2025-04-12 16:11:45', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 20 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(48, 3, '2025-04-22 08:01:34', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(49, 3, '2025-04-24 15:19:53', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 30 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(50, 3, '2025-03-22 20:31:44', 'Review Submitted! 📝', 'Thank you for your feedback! 💬\r\n        Your review has been successfully submitted.\r\n        We truly appreciate you taking the time to share your experience — it helps us grow and serve you better. 🌟🌿\r\n', 'read', NULL),
(51, 2, '2025-04-22 19:01:49', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-04-30 at 13:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(52, 2, '2025-04-25 11:16:49', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-04-30 at 13:00:00 has been approved. Our driver, Tan Jia Hao, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 011 - 2560 2353. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(53, 2, '2025-04-30 16:19:57', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 20 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'read', 1),
(54, 1, '2025-05-03 07:41:09', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-05-12 at 14:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(55, 1, '2025-05-05 10:51:22', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-05-12 at 14:00:00 has been approved. Our driver, Hakim Bin Salleh, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 012 - 509 5204. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'unread', 1),
(56, 1, '2025-05-12 11:07:28', 'Pickup Completed & Points Awarded ✅', 'Your pickup request has been successfully completed and verified. You\'ve been awarded 80 points for your contribution! 🌱🎉 Thank you for recycling with us and helping to create a cleaner, greener future. ♻️💚', 'unread', 1),
(57, 3, '2025-05-05 18:29:23', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-05-15 at 17:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(58, 3, '2025-05-07 10:39:13', 'Pickup Request Approved! 🚛', '🚛 Great news! Your pickup request for 2025-05-15 at 17:00:00 has been approved. Our driver, Hakim Bin Salleh, will arrive at your location during the scheduled time. If you need to get in touch, you can contact them at 012 - 509 5204. Please have your e-waste ready for collection. Thank you for recycling with us! ♻️💚\r\n', 'read', 1),
(59, 2, '2025-05-09 23:12:52', 'Pickup Request Submitted ✅', 'Your pickup request has been successfully scheduled! 🚚♻️\r\n        Please note that your request, scheduled for 2025-05-15 at 17:00:00, is currently under review.\r\n        You will receive a confirmation once it has been approved by our team.\r\n        Thank you for recycling and taking a step toward a cleaner, greener future! 🌿✨', 'read', NULL),
(60, 1, '2025-05-13 10:16:25', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'unread', NULL),
(61, 3, '2025-04-26 10:33:38', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'read', NULL),
(62, 3, '2025-04-29 16:28:31', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 30 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'read', 1),
(63, 3, '2025-05-11 15:10:43', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'unread', NULL),
(64, 2, '2025-05-12 08:37:13', 'Drop-Off Request Submitted ✅', 'Your drop-off request has been successfully submitted! 📦♻️\r\n                                        Please proceed to your selected drop-off location on the chosen date. \r\n                                        Points will be assigned upon verification of your drop-off. \r\n                                        Thank you for recycling and making a difference! 🌱✨', 'unread', NULL),
(65, 2, '2025-05-13 17:14:39', 'Drop-Off Completed & Points Awarded 📦', 'Your drop-off has been successfully verified, and you\'ve been awarded 10 points! ♻️👏 Thank you for doing your part in protecting the environment—every item counts toward a greener future! 🌍💚', 'unread', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_notification`
--
ALTER TABLE `admin_notification`
  ADD CONSTRAINT `FK_adm_noti_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `driver`
--
ALTER TABLE `driver`
  ADD CONSTRAINT `FK_driver_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `dropoff`
--
ALTER TABLE `dropoff`
  ADD CONSTRAINT `FK_dropoff_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_dropoff_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_dropoff_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `faq`
--
ALTER TABLE `faq`
  ADD CONSTRAINT `FK_faq_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `forgot_pw`
--
ALTER TABLE `forgot_pw`
  ADD CONSTRAINT `FK_forgotpw_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FK_item_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `item_dropoff`
--
ALTER TABLE `item_dropoff`
  ADD CONSTRAINT `FK_item_dropoff_dropoff_id` FOREIGN KEY (`dropoff_id`) REFERENCES `dropoff` (`dropoff_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_item_dropoff_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `item_pickup`
--
ALTER TABLE `item_pickup`
  ADD CONSTRAINT `FK_item_pickup_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_item_pickup_pickup_request_id` FOREIGN KEY (`pickup_request_id`) REFERENCES `pickup_request` (`pickup_request_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `pickup_request`
--
ALTER TABLE `pickup_request`
  ADD CONSTRAINT `FK_pickup_request_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_pickup_request_driver_id` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_pickup_request_time_slot_id` FOREIGN KEY (`time_slot_id`) REFERENCES `time_slot` (`time_slot_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_pickup_request_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `redeem_reward`
--
ALTER TABLE `redeem_reward`
  ADD CONSTRAINT `FK_redeem_reward_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_redeem_reward_location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_redeem_reward_reward_id` FOREIGN KEY (`reward_id`) REFERENCES `reward` (`reward_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_redeem_reward_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `reply_review`
--
ALTER TABLE `reply_review`
  ADD CONSTRAINT `FK_reply_review_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_reply_review_review_id` FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_review_dropoff_id` FOREIGN KEY (`dropoff_id`) REFERENCES `dropoff` (`dropoff_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_review_pickup_request_id` FOREIGN KEY (`pickup_request_id`) REFERENCES `pickup_request` (`pickup_request_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `reward`
--
ALTER TABLE `reward`
  ADD CONSTRAINT `FK_reward_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `time_slot`
--
ALTER TABLE `time_slot`
  ADD CONSTRAINT `FK_time_slot_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `user_notification`
--
ALTER TABLE `user_notification`
  ADD CONSTRAINT `FK_usernoti_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_usernoti_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
