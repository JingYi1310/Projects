# Projects
  # +Math Website 
    - Develop a responsive web-based platform focused on mathematics learning and management.
    - Build an interactive and engaging platform for students to practice as well as improve their additional math abilities. 
    - Provide teachers the ability to efficiently develop, organize, grade quizzes and display the results for the students to revise mistakes. 
    - Provide administrators with tools for effective monitoring and managing account utilization along with performance.

    -Database Setup:
      🔹 Insert Database
          1. Go to http://localhost/phpmyadmin/index.php
          2. Create a new database named rwdd_assignment
          3. Select utf8mb4_general_ci as the collation and click Create
          4. Click Import and select the file named rwdd_assignment.sql
      
    
  # Green Coin Website
    - Develop a web-based platform to encourage e-waste recycling by rewarding users with points.
    - Built secure user authentication features, including registration, login, and session handling.
    - Develop an interactive user dashboard to monitor points accumulation, view recycling history, and access available rewards.
    - Design and implemented an admin panel to oversee user management, review recycling submissions, and update reward offerings.

    -Database Setup:
      🔹 Insert Database
          1. Go to http://localhost/phpmyadmin/index.php
          2. Create a new database named cp_assignment
          3. Select utf8mb4_general_ci as the collation and click Create
          4. Click Import and select the file named cp_assignment.sql
    
    - To ensure this website can implement:
      🔹 Step 1: Create a New Project in Google Cloud
          1. Go to: https://console.cloud.google.com/
          2. Click the project dropdown (top left) → click “New Project”.
          3. Give it a name (e.g., Drive Upload Project) and click “Create”.
          4. After creation, make sure it is the selected project.

        🔹 Step 2: Enable Google Drive API
            1. In the left sidebar, go to “APIs & Services” → “Library”.
            2. Search for “Google Drive API”.
            3. Click it, then click “Enable”.

        🔹 Step 3: Create a Service Account
            1. In the sidebar, go to “APIs & Services” → “Credentials”.
            2. Click “+ CREATE CREDENTIALS” → Select “Service Account”.
            3. Fill in the following:
                - Name: e.g., drive-uploader
                - Description (Optional)
            4. Click Create and Continue.

        🔹 Step 4: Grant the Service Account Role
            1. Under “Grant this service account access”, choose:
            2. Role: Editor (or if you want to limit it, use Drive API --> Drive File)
            3. Click Continue, then Done.

        🔹 Step 5: Create the JSON Key
            1. After creating the service account, you’ll return to the list.
            2. Click the service account name.
            3. Go to the "Keys" tab.
            4. Click “Add Key” → “Create new key”.
            5. Choose JSON → Click Create.
            6. A .json file (e.g., keen-something.json) will be downloaded.
            7. Move the file to the Green Coin Project
        
        🔹 Step 6: Share a Google Drive Folder with the Service Account
            1. Open Google Drive: https://drive.google.com/
            2. Create a new folder (e.g., UploadedImages).
            3. Right-click the folder → Click "Share".
            4. Change the Restricted to Anyone with the link and click "Done".
            5. Copy the file id.

        🔹 Step 7: Change the file in the code
            - Admin-Drivers-Detail.php
              1. Replace the keen-diode JSON file path with your file on line 24.
              2. Replace the folder ID with your copied ID on line 30.

           - Admin-Drivers.php
              1. Replace the keen-diode JSON file path with your file on line 26.
              2. Replace the folder ID with your copied ID on line 32.
              
            - Admin-Dropoff-Detail.php
              1. Replace the keen-diode JSON file path with your file on line 24.
              2. Replace the folder ID with your copied ID on line 30.
              
            - Admin-Rewards.php
              1. Replace the keen-diode JSON file path on lines 22 and 59.
              2. Replace the folder ID on line 28.

            - User-PickupScheduling.php
              1. Replace the keen-diode JSON file path on lines 45 and 52.
              2. Replace the folder ID on line 58.

            -User-Profile.php
              1. Replace the keen-diode JSON file path on line 9.
              2. Replace the folder ID on line 15.

    #Simple File Management System
      - Features:
        1. Create a note with a title and content.
        2. View a list of all notes. 
        3. Update an existing note. 
        4. Delete a note.

      - Tech Stack:
        1. Frontend: Vue.js
        2. Backend: PHP
        3. Database: MySQL

      - Database Setup:
      🔹 Insert Database
          1. Go to http://localhost/phpmyadmin/index.php
          2. Create a new database named simple_note
          3. Select utf8mb4_0900_ai_ci as the collation and click Create
          4. Click Import and select the file named simple_note.sql


# Assessment Feedback System
    Project Overview
    The Assessment Feedback System (AFS) is designed to streamline the management of assessment activities and feedback processes within an academic environment. The system supports multiple user roles, including administrative staff, academic leaders, lecturers, and students, each with specific access rights and responsibilities.
    
    Through its role-based structure, the AFS facilitates the efficient management of modules, classes, assessments, grading, and feedback. Additionally, academic leaders can generate and analyze performance reports, enabling improved monitoring, evaluation, and data-driven decision-making to enhance the overall academic process.
    
    Project Structure
    The project is organized as follows:
    
    Root Directory
    All data in the text files is separated using a semicolon (;).
    
    - Assessment.txt
      Contains data related to assessments, including:  
      - Assessment ID (e.g., A001)  
      - Title of the assessment  
      - Type of the assessment (Quiz, Midterm, Assignment, Final Exam, Lab Exercise, Presentation, Test)  
      - Associated module ID (e.g., M001)  
      - Total marks of the assessment  
      - Percentage of marks in the final result  
      - Status of the assessment (Draft, Published, Result Published)  
      - Created by which lecturer (e.g., L001)  
      - Created date (e.g., YYYY-MM-DD)  
    
    - Class.txt
      Stores information about classes, including:  
      - Class ID (e.g., C001)  
      - Class name  
      - Day (Monday, Tuesday, etc.)  
      - Start time  
      - End time  
      - Associated module ID (e.g., M001)  
      - Admin Staff ID - Class created by (e.g., AS001)  
      - Created date (e.g., YYYY-MM-DD)  
    
    - Enrolment.txt  
      Manages enrolment details, including:  
      - Enrolment ID (e.g., E001)  
      - Student ID (e.g., TP001)  
      - Class ID (e.g., C001)  
      - Enrolment date (e.g., YYYY-MM-DD)  
    
    - Feedback.txt 
      Handles feedback data, including:  
      - Feedback ID (e.g., FB001)  
      - Lecturer ID (e.g., L001)  
      - Rating (1-5)  
      - Feedback content (text)  
      - Feedback category  
      - Date of feedback (e.g., YYYY-MM-DD)  
      - Class ID (e.g., C001)  
      - Student ID (e.g., TP001)  
    
    - Grade.txt
      Contains grade-related information, including:  
      - Grade ID (e.g., GR001)  
      - Minimum mark  
      - Maximum mark  
      - Grade (e.g., A, B, C, etc.)  
      - Description (e.g., Distinction, Pass, etc.)  
      - GPA  
    
    - Module.txt
      Stores details about modules, including:  
      - Module ID (e.g., M001)  
      - Module code  
      - Module name  
      - Credit hour  
      - Lecturer ID (e.g., L001)  
      - Module status  
      - Academic Leader ID (e.g., AL001)  
      - Created date (e.g., YYYY-MM-DD)  
    
    - Notifications.txt
      Manages notification records, including:  
      - Notification ID (e.g., N001)  
      - Student ID (e.g., TP001)  
      - Notification category  
      - Message content  
      - Timestamp (e.g., YYYY-MM-DD HH:MM:SS)  
      - Read status  
      - Priority  
    
    - Result.txt
      Contains final result data, including:  
      - Result ID (e.g., R001)  
      - Student ID (e.g., TP001)  
      - Assessment ID (e.g., A001)  
      - Marks obtained  
      - Overall grade (e.g., A, B, C, etc.)  
      - Feedback  
      - Class ID (e.g., C001)  
      - Lecturer ID (e.g., L001)  
      - Given date (e.g., YYYY-MM-DD)  
    
    - User.txt
      Stores user information, including:  
      - User ID (e.g., AS001 for Admin Staff, AL001 for Academic Leader, L001 for Lecturer, TP001 for Student)  
      - Full name  
      - Email  
      - Password  
      - Gender (F, M)  
      - Date of birth  
      - Contact number  
      - Role (e.g., Admin Staff, Academic Leader, Lecturer, Student)  
      - Created by (Admin Staff ID)  
      - Created at (e.g., YYYY-MM-DD)  
      - Department  
