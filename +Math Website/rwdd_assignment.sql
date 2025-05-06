-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 06, 2025 at 01:21 PM
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
-- Database: `rwdd_assignment`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE IF NOT EXISTS `administrator` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `admin_password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `admin_email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`admin_id`, `admin_name`, `admin_password`, `admin_email`) VALUES
(1, 'Admin 1', 'admin1', 'admin01@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
CREATE TABLE IF NOT EXISTS `announcement` (
  `announcement_id` int NOT NULL AUTO_INCREMENT,
  `announcement_text` varchar(10000) COLLATE utf8mb4_general_ci NOT NULL,
  `announcement_date` datetime NOT NULL,
  `teacher_id` int NOT NULL,
  `class_id` int NOT NULL,
  PRIMARY KEY (`announcement_id`),
  KEY `FK_announcement_teacher_id` (`teacher_id`),
  KEY `FK_announcement_class_id` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `announcement`
--

INSERT INTO `announcement` (`announcement_id`, `announcement_text`, `announcement_date`, `teacher_id`, `class_id`) VALUES
(1, 'The upcoming exam on December 20, 2024 will focus on Algebra and Trigonometry. If you have any questions, feel free to reach out during my office hours on Monday and Wednesday from 2:00 PM to 4:00 PM.', '2024-12-09 08:25:51', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_id` int NOT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `FK_class_teacher_id` (`teacher_id`),
  KEY `FK_class_admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `teacher_id`, `admin_id`) VALUES
(1, '4A/2024', 1, 1),
(3, '4B/2024', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class_quiz`
--

DROP TABLE IF EXISTS `class_quiz`;
CREATE TABLE IF NOT EXISTS `class_quiz` (
  `class_quiz_id` int NOT NULL AUTO_INCREMENT,
  `post_date` datetime NOT NULL,
  `due_date` date DEFAULT NULL,
  `due_time` time DEFAULT NULL,
  `class_id` int NOT NULL,
  `quiz_id` int NOT NULL,
  PRIMARY KEY (`class_quiz_id`),
  KEY `FK_classquiz_class_id` (`class_id`),
  KEY `FK_classquiz_quiz_id` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class_quiz`
--

INSERT INTO `class_quiz` (`class_quiz_id`, `post_date`, `due_date`, `due_time`, `class_id`, `quiz_id`) VALUES
(1, '2024-11-13 00:00:00', '2024-11-21', '22:59:59', 1, 1),
(2, '2024-11-24 00:00:00', '2024-12-31', '23:59:59', 1, 2),
(77, '2024-12-17 06:48:14', '2025-01-11', '18:54:00', 3, 2),
(78, '2024-12-17 10:19:33', NULL, NULL, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `forgot_pw`
--

DROP TABLE IF EXISTS `forgot_pw`;
CREATE TABLE IF NOT EXISTS `forgot_pw` (
  `forgot_pw_id` int NOT NULL AUTO_INCREMENT,
  `id` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `random_pw` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`forgot_pw_id`),
  KEY `FK_forgotpw_teacher_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `forgot_pw`
--

INSERT INTO `forgot_pw` (`forgot_pw_id`, `id`, `name`, `email`, `random_pw`) VALUES
(10, 1, 'Jing Yi ', 'jingyi697@gmail.com', 'b6246b5198350aa6e1ea'),
(11, 1, 'Jing Yi ', 'jingyi697@gmail.com', 'be255d19801076e0a90c'),
(13, 1, 'Jing Yi ', 'jingyi697@gmail.com', '02694574fd5629ec0a31');

-- --------------------------------------------------------

--
-- Table structure for table `mcq_answer`
--

DROP TABLE IF EXISTS `mcq_answer`;
CREATE TABLE IF NOT EXISTS `mcq_answer` (
  `mcq_answer_id` int NOT NULL AUTO_INCREMENT,
  `mcq_answer_text` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `is_ques_ans_correct` tinyint(1) NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`mcq_answer_id`),
  KEY `FK_mcqanswer_question_id` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mcq_answer`
--

INSERT INTO `mcq_answer` (`mcq_answer_id`, `mcq_answer_text`, `is_ques_ans_correct`, `question_id`) VALUES
(1, '205', 1, 1),
(2, '204', 0, 1),
(3, '206', 0, 1),
(4, '203', 0, 1),
(5, '15', 0, 2),
(6, '13', 1, 2),
(7, '14', 0, 2),
(8, '12', 0, 2),
(9, '1.3', 1, 4),
(10, '1.2', 0, 4),
(11, '1.4', 0, 4),
(12, '1.1', 0, 4),
(54, 'x = 5, x= 3', 0, 131),
(55, 'x = 4, x =2', 1, 131);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question_text` varchar(10000) COLLATE utf8mb4_general_ci NOT NULL,
  `question_type` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `question_score` int NOT NULL,
  `quiz_id` int NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK_question_quiz_id` (`quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`question_id`, `question_text`, `question_type`, `question_score`, `quiz_id`) VALUES
(1, '11001101 base 2 = ______ base 10', 'Multiple Choice Question', 2, 1),
(2, '1101 base 2 = ______ base 10', 'Multiple Choice Question', 2, 1),
(3, '110010110 base 2 = ______ base 10', 'Subjective Question', 5, 1),
(4, '5(4x-3) = 11, x = ______', 'Multiple Choice Question', 2, 2),
(5, '3x-2(6x-3) = 42, x = ______', 'Subjective Question', 5, 2),
(129, 'fdasfasf', 'Subjective Question', 5, 129),
(130, 'x^2 + 2x + 5 = 0, x = ?', 'Subjective Question', 5, 130),
(131, '2x^2 + 5x + 6 = 0', 'Multiple Choice Question', 3, 130);

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE IF NOT EXISTS `quiz` (
  `quiz_id` int NOT NULL AUTO_INCREMENT,
  `quiz_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `quiz_description` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `creation_time` datetime NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`quiz_id`),
  KEY `FK_quiz_teacher_id` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`quiz_id`, `quiz_name`, `quiz_description`, `creation_time`, `teacher_id`) VALUES
(1, 'C1: Number Base Conversion', 'Converting from base 2 to base 10', '2024-11-20 21:06:46', 1),
(2, 'Chapter 2: Linear Equation', 'A linear equation is a mathematical statement that represents a straight line when graphed on a coordinate plane.', '2024-11-24 14:54:39', 1),
(129, 'dfafsf', 'dsfasf', '2024-12-17 10:26:02', 1),
(130, 'chapter 3', 'quadratic equation', '2024-12-17 10:55:31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `structure_answer`
--

DROP TABLE IF EXISTS `structure_answer`;
CREATE TABLE IF NOT EXISTS `structure_answer` (
  `struc_answer_id` int NOT NULL AUTO_INCREMENT,
  `struc_answer_text` varchar(10000) COLLATE utf8mb4_general_ci NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`struc_answer_id`),
  KEY `FK_structureanswer_question_id` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `structure_answer`
--

INSERT INTO `structure_answer` (`struc_answer_id`, `struc_answer_text`, `question_id`) VALUES
(1, '406', 3),
(2, '-4', 5),
(48, 'fdsf', 129),
(49, '5', 130);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `student_password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `student_email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `class_id` int DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `FK_student_class_id` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `student_name`, `student_password`, `student_email`, `class_id`) VALUES
(1, 'Jing Yi ', 'jy123', 'jingyi123456@gmail.com', 1),
(2, 'Student 1', 'student1', 'student01@gmail.com', NULL),
(3, 'Student 2', 'student2', 'student02@gmail.com', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student_answer`
--

DROP TABLE IF EXISTS `student_answer`;
CREATE TABLE IF NOT EXISTS `student_answer` (
  `stu_ans_id` int NOT NULL AUTO_INCREMENT,
  `stu_ans` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `is_stu_ans_correct` tinyint(1) NOT NULL,
  `stu_ques_score` int NOT NULL,
  `stu_quiz_id` int NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`stu_ans_id`),
  KEY `FK_studentanswer_question_id` (`question_id`),
  KEY `FK_studentanswer_stu_quiz_id` (`stu_quiz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_answer`
--

INSERT INTO `student_answer` (`stu_ans_id`, `stu_ans`, `is_stu_ans_correct`, `stu_ques_score`, `stu_quiz_id`, `question_id`) VALUES
(24, '204', 0, 0, 11, 1),
(25, '13', 1, 2, 11, 2),
(26, '42', 0, 0, 11, 3),
(27, '1.2', 0, 0, 12, 4),
(28, '-4', 1, 5, 12, 5);

-- --------------------------------------------------------

--
-- Table structure for table `student_take_quiz`
--

DROP TABLE IF EXISTS `student_take_quiz`;
CREATE TABLE IF NOT EXISTS `student_take_quiz` (
  `stu_quiz_id` int NOT NULL AUTO_INCREMENT,
  `score` float NOT NULL,
  `submitted_date` date NOT NULL,
  `submitted_status` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `student_id` int NOT NULL,
  `class_quiz_id` int NOT NULL,
  PRIMARY KEY (`stu_quiz_id`),
  KEY `FK_studenttakequiz_class_quiz_id` (`class_quiz_id`),
  KEY `FK_studenttakequiz_student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_take_quiz`
--

INSERT INTO `student_take_quiz` (`stu_quiz_id`, `score`, `submitted_date`, `submitted_status`, `student_id`, `class_quiz_id`) VALUES
(11, 2, '2024-12-17', 'Submitted Late', 1, 1),
(12, 5, '2024-12-17', 'Submitted', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `teacher_name`, `teacher_password`, `teacher_email`) VALUES
(1, 'Xin Yun', 'xy123', 'xinyun123456@gmail.com'),
(2, 'Yee Shuen', 'ys123', 'yeeshuen123456@gmail.com'),
(5, 'Teacher2', 'Teacher2', 'teacher02@gmail.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `announcement`
--
ALTER TABLE `announcement`
  ADD CONSTRAINT `FK_announcement_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_announcement_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `FK_class_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `administrator` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_class_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `class_quiz`
--
ALTER TABLE `class_quiz`
  ADD CONSTRAINT `FK_classquiz_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_classquiz_quiz_id` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `forgot_pw`
--
ALTER TABLE `forgot_pw`
  ADD CONSTRAINT `FK_forgotpw_admin_id` FOREIGN KEY (`id`) REFERENCES `administrator` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_forgotpw_student_id` FOREIGN KEY (`id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_forgotpw_teacher_id` FOREIGN KEY (`id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `mcq_answer`
--
ALTER TABLE `mcq_answer`
  ADD CONSTRAINT `FK_mcqanswer_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FK_question_quiz_id` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `FK_quiz_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `structure_answer`
--
ALTER TABLE `structure_answer`
  ADD CONSTRAINT `FK_structureanswer_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FK_student_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `student_answer`
--
ALTER TABLE `student_answer`
  ADD CONSTRAINT `FK_studentanswer_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_studentanswer_stu_quiz_id` FOREIGN KEY (`stu_quiz_id`) REFERENCES `student_take_quiz` (`stu_quiz_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `student_take_quiz`
--
ALTER TABLE `student_take_quiz`
  ADD CONSTRAINT `FK_studenttakequiz_class_quiz_id` FOREIGN KEY (`class_quiz_id`) REFERENCES `class_quiz` (`class_quiz_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_studenttakequiz_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
