package assessmentfeedbacksystem;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.time.*;

public class AdminStaff extends User implements FileSerializable {
    
    // Abstract method
    @Override
    public String getRole(){
        return "Admin Staff";
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
    
    // Constructor
    public AdminStaff(){
        super();
    }
    
    public AdminStaff(String user_id, String name,String email, String password, String gender, LocalDate dob,
                        String contact_no, String role, String created_by, LocalDate created_at){
        super(user_id, name, email, password, gender, dob, contact_no, role, created_by, created_at);
    }
    
    public AdminStaff(String user_id){
        super(user_id);
    }
    
    
    // Get all user 
    public List<User> readUser(){
        List<String> lines = FileManager.readFile(super.getFileName());
        List<User> user_list = new ArrayList<>();
        
        for(String line: lines){
            if (line.trim().isEmpty()) {
                continue; // skip empty lines
            }
            
            String[] user_data = line.split(";");
            if (user_data.length < 8) { // check for minimum fields before accessing
                System.out.println("Skipping corrupted line: " + line);
                continue;
            }
            
            String role = user_data[7];
            LocalDate dob = LocalDate.parse(user_data[5]);
            
            // modify and add created_at
            switch(role){
                case "Academic Leader":
                    String acadCreatedBy = (user_data.length > 8) ? user_data[8] : null;
                    LocalDate acadCreatedAt = (user_data.length > 9 && !user_data[9].isEmpty())
                            ? LocalDate.parse(user_data[9])
                            : null;
                    user_list.add(new AcademicLeader(user_data[0], user_data[1], user_data[2], user_data[3],
                                                       user_data[4], dob, user_data[6], role,
                                                       acadCreatedBy, acadCreatedAt, user_data[10]));
                    break;
                case "Lecturer":
                    String lecCreatedBy = (user_data.length > 8) ? user_data[8] : null;
                    LocalDate lecCreatedAt = (user_data.length > 9 && !user_data[9].isEmpty())
                            ? LocalDate.parse(user_data[9])
                            : null;
                    user_list.add(new Lecturer(user_data[0], user_data[1], user_data[2], user_data[3],
                                               user_data[4], dob, user_data[6], role,
                                               lecCreatedBy, lecCreatedAt, user_data[10], user_data[11]));
                    break;
                case "Student":
                    String stuCreatedBy = (user_data.length > 8) ? user_data[8] : null;
                    LocalDate stuCreatedAt = (user_data.length > 9 && !user_data[9].isEmpty())
                            ? LocalDate.parse(user_data[9])
                            : null;
                    user_list.add(new Student(user_data[0], user_data[1], user_data[2], user_data[3],
                                              user_data[4], dob, user_data[6], role, 
                                              stuCreatedBy, stuCreatedAt));
                    break;
            }
        }
        return user_list;
    }
    
    // Write new user into txt file
    public String[] createUser(String data){
        try{
            FileManager.writeFile(super.getFileName(), data, true);
            return new String[]{"true", "User created successfully"};
        }catch(IOException e){
            return new String[]{"false", "Failed to write to file\""};
        }
    }
    
    // Update user details
    public String[] updateUser(User updated_user){
        List<String> lines = FileManager.readFile(super.getFileName());
        List<String> updated_lines = new ArrayList<>();
        boolean is_found = false;
        
        String updated_user_id = updated_user.getUser_id();
        
        for(String line: lines){
            String[] data = line.split(";");
            String id = data[0];
            if(updated_user_id.equals(id)){
                is_found = true;
                continue;
            }
            updated_lines.add(line);
        }
        
        if (!is_found) {
            return new String[]{"false", "User not found"};
        }
 
        // Modify & add created_at
        String updated_data = String.join(";", 
            updated_user.getUser_id(),
            updated_user.getName(),
            updated_user.getEmail(),
            updated_user.getPassword(),
            updated_user.getGender(),
            updated_user.getDob().toString(),
            updated_user.getContact_no(),
            updated_user.getRole(),
            updated_user.getCreated_by(),
            updated_user.getCreated_at().toString()
        );

        String department = "";
        String leader_id = "";

        if(updated_user instanceof AcademicLeader){
            department = ((AcademicLeader) updated_user).getDepartment();
        } else if (updated_user instanceof Lecturer) {
            department = ((Lecturer) updated_user).getDepartment();
            leader_id = ((Lecturer) updated_user).getLeaderId();
        }

        updated_data += ";" + department + ";" + leader_id;

        updated_lines.add(updated_data);

        try {
            FileManager.writeFile(super.getFileName(), updated_lines, false);
            return new String[]{"true", "User updated successfully"};
        } catch (Exception e) {
            return new String[]{"false", "Failed to write to file"};
        }
    }
    
    // Remove selected user from User.txt file
    public String[] deleteUser(User deleted_user){
        List<String> lines = FileManager.readFile(super.getFileName());
        List<String> updated_lines = new ArrayList<>();
        boolean is_found = false;
        
        String deleted_user_id = deleted_user.getUser_id();
        
        for(String line: lines){  
            String[] data = line.split(";");
            String id = data[0];
            
            if(deleted_user_id.equals(id)){
                is_found = true;
                continue;
            }
            
            updated_lines.add(line); 
        }
        
        if(is_found == false){
            return new String[]{"false", "User not found"};
        }
        
        try{
            FileManager.writeFile(super.getFileName(), updated_lines, false);
            return new String[]{"true", "User deleted successfully"};
        } catch(Exception e){
            return new String[]{"false", "Failed to delete user"};
        }
    }
    
    // Reassigned academic leader to a lecturer
    public String[] assignLecturer(List<String> updated_data, User deleted_user) {
        List<String> lecturer_lines = FileManager.readFile(super.getFileName());

        List<String> updated_lecturer_lines = new ArrayList<>();
        boolean delete_found = false;
        String deleted_user_id = deleted_user.getUser_id();

        for (String line : lecturer_lines) {
            String[] data = line.split(";");
            String user_id = data[0];
            String role = data[7];

            if (role.equals("Academic Leader") && user_id.equals(deleted_user_id)) {
                delete_found = true;
                continue; // skip current loop
            }

            if (role.equals("Lecturer")) {
                for (String pair : updated_data) {
                    String[] parts = pair.split(";");
                    String lecturer_id = parts[0];
                    String new_leader_id = parts[1];

                    if (data[0].equals(lecturer_id)) {
                        data[11] = new_leader_id;
                        break;
                    }
                }
            }
            updated_lecturer_lines.add(String.join(";", data));
        }

        if (!delete_found) {
            return new String[]{"false", "Academic Leader not found"};
        }

        File original = new File(super.getFileName());
        File backup = new File(super.getFileName() + "_bak");

        try {
            if (!original.renameTo(backup)) {
                return new String[]{"false", "Failed to create backup file"};
            }

            FileManager.writeFile(super.getFileName(), updated_lecturer_lines, false);

            backup.delete();

            return new String[]{"true", "Deleted Academic Leader and updated affected Lecturers successfully"};

        } catch (Exception e) {
            backup.renameTo(original);
            return new String[]{"false", "Failed to update. Operation rolled back: " + e.getMessage()};
        }
    }
    
    // Update grading system 
    public String[] updateGrade(List<Grade> grade_list){
        String[] validated_status = Grade.validateGradingSystem(grade_list);
        boolean status = Boolean.parseBoolean(validated_status[0]);
        String validated_status_message = validated_status[1];
        
        if(!status){
            return new String[]{String.valueOf(status), validated_status_message};
        }else{
            List<String> lines_to_write = new ArrayList<>();
            
            for (Grade g : grade_list) {
                lines_to_write.add(
                    g.getGradeId() + ";" +
                    g.getMinMark() + ";" +
                    g.getMaxMark() + ";" +
                    g.getGrade() + ";" +
                    g.getDescription() + ";" +
                    g.getGpa()
                );
            }
            
            try {
                FileManager.writeFile(Grade.getFileName(), lines_to_write, false);
                return new String[]{"true", "Grades updated successfully."};
            } catch (Exception e) {
                return new String[]{"false", "Failed to save grades: " + e.getMessage()};
            }
        }
    }
    
    // Create new class
    public String[] createClassGroup(ClassModel cm){
        try{
            String data = String.join(";", 
                cm.getClassId(),
                cm.getClassName(),
                cm.getDay().toString(),
                cm.getStartTime().toString(),
                cm.getEndTime().toString(),
                cm.getModule().getModuleId(),
                cm.getCreatedBy(),
                cm.getCreatedAt().toString()
            );
            
            System.out.println("Data: " + data);
            
            FileManager.writeFile(ClassModel.getFileName(), data, true);
            return new String[]{"true", "Class Group '" + cm.getClassName() + "' has been created successfully!"};
        }catch(IOException e){
            return new String[]{"false", "Failed to save class: " + e.getMessage()};
        }
    }
    
    public int getTotalClasses() {
        List<ClassModel> allClasses = ClassModel.readAllClasses();
        return allClasses.size();
    }
    
    public int getAvailableModuleNum(){
        List<Module> allModules = Module.getActiveModules();
        return allModules.size();
    }
}
