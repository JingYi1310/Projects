package assessmentfeedbacksystem;

import java.time.*;

public class AcademicLeader extends User implements FileSerializable {
    
    private String department;
    
    // Abstract method
    @Override
    public String getRole(){
        return "Academic Leader";
    }
    
    @Override
    public String serialize(){
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
            getDepartment(),
            ""
        );
    }
    
    // Constructor
    public AcademicLeader(){
        super();
    }
    
    public AcademicLeader(String user_id, String name,String email, String password, String gender, LocalDate dob,
                            String contact_no, String role, String created_by, LocalDate created_at, String department){
        super(user_id, name, email, password, gender, dob, contact_no, role, created_by, created_at);
        this.department = department;
    }
    
    public AcademicLeader(String user_id){
        super(user_id);
    }
    
    
    // Getter and setter
    public String getDepartment(){
        return department;
    }
    
    public void setDepartment(String department){
        this.department = department;
    }
}
