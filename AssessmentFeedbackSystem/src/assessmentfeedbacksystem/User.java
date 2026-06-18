package assessmentfeedbacksystem;

import java.util.*;
import java.time.LocalDate;

public abstract class User implements FileSerializable{
    private String user_id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private LocalDate dob;
    private String contact_no;
    protected String role;
    private String created_by;
    private LocalDate created_at;
    
    // editable variable: age, password, contact_no, last_error_message
    
    protected final static String USER_FILE = "User.txt";
    
//    // Interface method
    @Override
    public String serialize(){
         return String.join(";",
            getUser_id(),
            getName(),
            getEmail(),
            getPassword(),
            getGender(),
            getDob() != null ? getDob().toString() : "",
            getContact_no(),
            getRole(),
            getCreated_by() != null ? getCreated_by() : "",
            getCreated_at() != null ? getCreated_at().toString() : ""
        );
    }
    
    // Abstract method
    public abstract String getRole();

    
    // Constructor
    public User(){}
    
    public User(String user_id, String name,String email, String password, String gender, LocalDate dob,
                String contact_no, String role, String created_by, LocalDate created_at) {
        this.user_id = user_id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.password = password;
        this.email = email;
        this.contact_no = contact_no;
        this.role = role;
        this.created_by = created_by;
        this.created_at = created_at;
    }
    
    public User(String user_id){
        this.user_id = user_id;
    }
    
    // Getters and Setters
    public String getFileName(){
        return USER_FILE;
    }
    
    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
    
    public LocalDate getDob(){
        return dob;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getContact_no() {
        return contact_no;
    }
    
    public LocalDate getCreated_at(){
        return created_at;
    }
    
    // Setter
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(LocalDate dob){
        this.dob = dob;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    
    public void setCreated_at(LocalDate created_at){
        this.created_at = created_at;
    }

    // Login function
    public static User login(String id_input, String password_input){
        List<String> lines = FileManager.readFile(USER_FILE);
        for(String line: lines){
            if (line.trim().isEmpty()) {
                continue; // skip current loop
            }
            
            String[] user_data = line.split(";");
            
            if (user_data.length < 8) {
                System.out.println("Corrupted line: " + line);
                continue;
            }

            
            // For safety - check id
            String fileId = user_data[0].trim().toUpperCase();
            String inputId = id_input.trim().toUpperCase();
            
            if(fileId.equals(inputId) && user_data[3].equals(password_input)){
                
                String user_role = user_data[7];
                LocalDate dob = LocalDate.parse(user_data[5]);
                
                switch(user_role){
                    case "Admin Staff":
                        String created_by = (user_data.length > 8) ? user_data[8] : "";
                        AdminStaff admin = new AdminStaff(
                            user_data[0],
                            user_data[1],
                            user_data[2],
                            user_data[3],
                            user_data[4],
                            dob,
                            user_data[6],
                            user_data[7],
                            "",
                            null
                        );
                        return admin;
                    case "Academic Leader":
                        String acadCreatedBy = (user_data.length > 8) ? user_data[8] : null;
                        LocalDate acadCreatedAt = (user_data.length > 9 && !user_data[9].isEmpty())
                            ? LocalDate.parse(user_data[9])
                            : null;
                        AcademicLeader academic_leader = new AcademicLeader(
                            user_data[0],
                            user_data[1],
                            user_data[2],
                            user_data[3],
                            user_data[4],
                            dob,
                            user_data[6],
                            user_data[7],
                            acadCreatedBy,
                            acadCreatedAt,
                            user_data[10]
                        );
                        return academic_leader;
                    case "Lecturer":
                        String lecCreatedBy = (user_data.length > 8) ? user_data[8] : null;
                        LocalDate lecCreatedAt = (user_data.length > 9 && !user_data[9].isEmpty())
                                ? LocalDate.parse(user_data[9])
                                : null;
                        Lecturer lecturer = new Lecturer(
                            user_data[0],
                            user_data[1],
                            user_data[2],
                            user_data[3],
                            user_data[4],
                            dob,
                            user_data[6],
                            user_data[7],
                            lecCreatedBy,
                            lecCreatedAt,
                            user_data[10],
                            user_data[11]
                        );
                        return lecturer;
                    case "Student":
                        LocalDate stuCreatedAt = (user_data.length > 9 && !user_data[9].isEmpty())
                            ? LocalDate.parse(user_data[9])
                            : null;
                        Student student = new Student(
                            user_data[0],
                            user_data[1],
                            user_data[2],
                            user_data[3],
                            user_data[4],
                            dob,
                            user_data[6],
                            user_data[7],
                            user_data[8],
                            stuCreatedAt
                        );
                        return student;
                    default:
                        System.out.println("Unknown role: " + user_role);
                        return null;
                }
                
            }
        }
        return null;
    }
    
    // Get user name by user id
    public static String getUserNameById(String userId) {
        List<String> lines = FileManager.readFile("User.txt");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 2 && parts[0].equals(userId)) {
                return parts[1]; // Name is column 1
            }
        }
        return "Unknown";
    }

    // Load student data for profile (static helper for ProfilePanel)
    public static String[] loadStudentData(String userId) {
        List<String> lines = FileManager.readFile("User.txt");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 8 && parts[0].equals(userId)) {
                return parts;
            }
        }
        return null;
    }

    // Update profile (static helper for ProfilePanel)
    public static boolean updateProfile(String userId, String newEmail, 
                                              String newPhone, String newPassword) {
        List<String> lines = FileManager.readFile("User.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");

            if (parts.length >= 8 && parts[0].equals(userId)) {
                found = true;
                parts[2] = newEmail;  // Update email
                parts[6] = newPhone;  // Update phone
                if (newPassword != null && !newPassword.isEmpty()) {
                    parts[3] = newPassword;  // Update password
                }
                updatedLines.add(String.join(";", parts));
            } else {
                updatedLines.add(line);
            }
        }

        if (found) {
            FileManager.writeFile("User.txt", updatedLines, false);
        }
        return found;
    }

    public static boolean updateStudentProfile(String userId, String newName, String newEmail, String newRole) {
        List<String> lines = FileManager.readFile("User.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");

            if (parts.length >= 8 && parts[0].equals(userId)) {
                found = true;
                parts[1] = newName;
                parts[2] = newEmail;
                parts[7] = newRole;
                updatedLines.add(String.join(";", parts));
            } else {
                updatedLines.add(line);
            }
        }

        if (found) {
            FileManager.writeFile("User.txt", updatedLines, false);
        }
        return found;
    }

    public static boolean updatePassword(String userId, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) return false;
        List<String> lines = FileManager.readFile("User.txt");
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");

            if (parts.length >= 4 && parts[0].equals(userId)) {
                found = true;
                parts[3] = newPassword;
                updatedLines.add(String.join(";", parts));
            } else {
                updatedLines.add(line);
            }
        }

        if (found) {
            FileManager.writeFile("User.txt", updatedLines, false);
        }
        return found;
    }

    // Verify password (static helper)
    public static boolean verifyPassword(String userId, String password) {
        List<String> lines = FileManager.readFile("User.txt");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            if (parts.length >= 4 && parts[0].equals(userId)) {
                return parts[3].equals(password);
            }
        }
        return false;
    }
}
