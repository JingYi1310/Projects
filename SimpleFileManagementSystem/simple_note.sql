-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 11, 2025 at 09:38 AM
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
-- Database: `simple_note`
--

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `note_id` int NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`note_id`, `title`, `content`, `date`) VALUES
(1, 'Pre-Task: Simple File Management System 📂', 'Objective: Build a fun and simple web application for a file management system where users can manage text-based notes (e.g., title and content). The app should show off basic CRUD (Create, Read, Update, Delete) functionality. 🛠️\nRequirements:\n- Frontend: Use Vue.js (JavaScript-based) to create a responsive user interface for managing notes. 🌐\n- Backend: Build a RESTful API to handle CRUD operations. Use Java Spring Boot (preferred, as it’s our tech stack) or any JavaScript-based backend framework (e.g., Node.js with Express) if you’re not familiar with Spring Boot. ⚙️\n- Database: Use MySQL/Postgres to store the notes. If MySQL/Postgres setup is tricky, you can use an in-memory database like H2 (for Spring Boot) or SQLite. 🗄️\n\nFeatures:\n- Create a note with a title and content. ✍️\n- View a list of all notes. 📋\n- Update an existing note. 🔄\n- Delete a note. 🗑️\n\nMinimum UI: A clean, simple interface with a form to add/edit notes and a list to display them. Basic CSS (e.g., Tailwind CSS or plain CSS) is enough. 🎨\n\nTech Stack (Recommended):\n- Frontend: Vue.js (required, as it’s our core framework). ✅\n- Backend: Java Spring Boot (preferred, as it’s our tech stack) or Node.js/Express (okay for simplicity). 🖥️\n- Database: MySQL (preferred) or H2/SQLite (for easy setup). 💾\n- Language: JavaScript is a must for frontend; Java is a plus for backend. 🧑‍💻\n\nEvaluation Criteria:\n- Code functionality (CRUD operations work smoothly). ✔️\n- Code organization and clarity (e.g., modular Vue components, clear API routes). 🗂️\n- Basic error handling (e.g., empty input validation). 🚨\n- Clear setup instructions in the GitHub README.md. 📖\n\nBonus (optional): Deploy the app (e.g., on Heroku, Netlify, or Railway) and share the live URL. 🌟\n\nTips:\n- Keep it simple—focus on functionality over fancy design. 😎\n- Check out Vue.js documentation (https://vuejs.org) and Spring Boot tutorials (https://spring.io) for help. 📚\n- Test your CRUD operations thoroughly before submitting. 🧪\n\nAll the Best! 🎈\n\nWe can’t wait to see what you create! 🤩 If you have any questions, reach out via email or WhatsApp. Good luck, and have a blast coding! 🚀', '2025-05-11 17:32:00');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
