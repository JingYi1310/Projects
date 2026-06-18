package assessmentfeedbacksystem;

import static assessmentfeedbacksystem.FileManager.readFile;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Lecturer extends User implements FileSerializable{
    
    private String department;
    private String leader_id;
    
    // Abstract method
    @Override
    public String getRole(){
        return "Lecturer";
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
            getDepartment(),
            getLeaderId()
        );
    }
    
    // Constructor
    public Lecturer(){
        super();
    }
    
    public Lecturer(String user_id, String name,String email, String password, String gender, LocalDate dob,
                    String contact_no, String role, String created_by, LocalDate created_at, String department, String leader_id){
        super(user_id, name, email, password, gender, dob, contact_no, role, created_by, created_at);
        this.department = department;
        this.leader_id = leader_id;
    }
    
    public Lecturer(String user_id){
        super(user_id);
    }
    
    // Getter and setter
    public String getDepartment(){
        return department;
    }
    
    public void setDepartment(String department){
        this.department = department;
    }
    
    public String getLeaderId(){
        return leader_id;
    }
    
    public void setLeaderId(String leader_id){
        this.leader_id = leader_id;
    }
    
    // Get class teach by the lecturer
    public static List<ClassModel> getClassByLecturerId(Lecturer lecturer){
        List<ClassModel> classList = new ArrayList<>();
        List<Module> allModules = Module.getAllModules();
        
        List<String> lines = readFile(ClassModel.getFileName());
        for (String line: lines){
            if (line.trim().isEmpty()) continue;
            
            String[] data = line.split(";");
            if (data.length != 8) continue;
            String moduleIdFromFile = data[5];
            Module moduleObj = allModules.stream()
                                         .filter(m -> m.getModuleId().equals(moduleIdFromFile))
                                         .findFirst()
                                         .orElse(null);

            if (moduleObj == null) {
                System.out.println("Module not found for class: " + data[0]);
                continue;
            }
            
            if (moduleObj.getLecturer().getUser_id().equals(lecturer.getUser_id())){
                ClassModel c = new ClassModel(
                    data[0],
                    data[1],
                    DayOfWeek.valueOf(data[2].trim().toUpperCase()),
                    LocalTime.parse(data[3]),
                    LocalTime.parse(data[4]),
                    data[6],
                    LocalDate.parse(data[7]),
                    moduleObj
                );

                classList.add(c);
            }
        }
        return classList;
    }
    
    // Get lecturer name by lecturer id 
    public static String getLecturerNameById(String lecturerId) {
        List<String> lines = FileManager.readFile("User.txt");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue; // skip empty line
            
            String[] data = line.split(";");
            
            if (data.length < 8) continue; // skip if line < 8 fields
            
            if (data[0].equalsIgnoreCase(lecturerId) && data[7].equalsIgnoreCase("Lecturer")) {
                return data[1];
            }
        }
        return "Unknown Lecturer";
    }
    
    
    // Get department by lecturer ID
    public static String getFacultyByLecturerId(String lecturerId) {
        if (lecturerId == null || lecturerId.trim().isEmpty()) {
            return "Unknown";
        }
        
        List<String> lines = FileManager.readFile("User.txt");
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            String[] data = line.split(";");
            
            // Check if this is a Lecturer record with enough fields
            if (data.length >= 11 && 
                data[0].equalsIgnoreCase(lecturerId) && 
                data[7].equalsIgnoreCase("Lecturer")) {
                
                // Return department/faculty (column 10)
                return data[10];
            }
        }
        return "Unknown";
    }
    
    // Get full Lecturer object by ID
    public static Lecturer getLecturerById(String lecturerId) {
        if (lecturerId == null || lecturerId.trim().isEmpty()) {
            return null;
        }
        
        List<String> lines = FileManager.readFile("User.txt");
        
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            String[] data = line.split(";");
            
            // Check if this is a Lecturer record
            if (data.length >= 12 && 
                data[0].equalsIgnoreCase(lecturerId) && 
                data[7].equalsIgnoreCase("Lecturer")) {
                
                try {
                    LocalDate dob = LocalDate.parse(data[5]);
                    LocalDate createdAt = LocalDate.parse(data[9]);
                    
                    return new Lecturer(
                        data[0],  // user_id
                        data[1],  // name
                        data[2],  // email
                        data[3],  // password
                        data[4],  // gender
                        dob,      // dob
                        data[6],  // contact_no
                        data[7],  // role
                        data[8],  // created_by
                        createdAt, // created_at
                        data[10], // department
                        data[11]  // leader_id
                    );
                    
                } catch (Exception e) {
                    System.err.println("Error parsing lecturer data: " + line);
                    return null;
                }
            }
        }
        return null;
    }
}
