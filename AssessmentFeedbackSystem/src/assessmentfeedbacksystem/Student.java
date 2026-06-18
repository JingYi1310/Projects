package assessmentfeedbacksystem;
import java.time.*;
import java.util.List;

public class Student extends User implements FileSerializable {
    
    // abstract method
    @Override
    public String getRole(){
        return "Student";
    }
    
    @Override
    public String serialize() {
        return String.join(";",
            getUser_id(),
            getName(),
            getEmail(),
            getPassword(),
            getGender(),
            getDob().toString(),
            getContact_no(),
            getRole(),
            getCreated_by(),
            getCreated_at().toString(),
            "",
            ""
        );
    }
    
    public Student(){
        super();
    }
    
    public Student(String user_id, String name,String email, String password, String gender, LocalDate dob,
                    String contact_no, String role, String created_by, LocalDate created_at){
        super(user_id, name, email, password, gender, dob, contact_no, role, created_by, created_at);
    }
    
    public Student(String user_id){
        super(user_id);
    }
    
    // Get a Student by their ID from User.txt
    public static Student getStudentById(String studentId) {
        List<String> lines = FileManager.readFile("User.txt");
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(";");
            if (parts.length >= 10) {
                // Check if this is the student we're looking for
                if (parts[0].equals(studentId) && parts[7].equals("Student")) {
                    // User.txt format for Students:
                    // StudentID;Name;Email;Password;Gender;DOB;Phone;Role;AdminID;EnrollmentDate
                    try {
                        Student student = new Student(
                            parts[0],  // user_id
                            parts[1],  // name
                            parts[2],  // email
                            parts[3],  // password
                            parts[4],  // gender
                            LocalDate.parse(parts[5]),  // dob
                            parts[6],  // contact_no
                            parts[7],  // role
                            parts[8],  // created_by
                            LocalDate.parse(parts[9])   // created_at
                        );
                        return student;
                    } catch (Exception e) {
                        System.err.println("Error parsing student data: " + e.getMessage());
                        return null;
                    }
                }
            }
        }
        
        return null; // Student not found
    }
    
    // Update student profile in User.txt
    public boolean updateProfile() {
        List<String> lines = FileManager.readFile("User.txt");
        boolean updated = false;
        
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.trim().isEmpty()) continue;
            
            String[] parts = line.split(";");
            if (parts.length >= 10 && parts[0].equals(this.getUser_id())) {
                // Rebuild the line with updated data
                // Format: StudentID;Name;Email;Password;Gender;DOB;Phone;Role;AdminID;EnrollmentDate;;
                String updatedLine = this.serialize();
                
                lines.set(i, updatedLine);
                updated = true;
                break;
            }
        }
        
        if (updated) {
            FileManager.writeFile("User.txt", lines, false);
            return true;
        }
        
        return false;
    }
    
    // Get phone number (alias for getContact_no for clarity)
    public String getPhone() {
        return this.getContact_no();
    }
    
    // Set phone number (alias for setContact_no for clarity)
    public void setPhone(String phone) {
        this.setContact_no(phone);
    }
    
    // Get student ID (alias for getUser_id for clarity)
    public String getStudentId() {
        return this.getUser_id();
    }
}
