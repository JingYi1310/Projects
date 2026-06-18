package assessmentfeedbacksystem;

import static assessmentfeedbacksystem.FileManager.readFile;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;

// encapsulation, inheritance, polymorphism
public class Module implements FileSerializable{
    
    private static final String MODULE_FILE = "Module.txt"; // static final means cannot change and shared by all instances
    
    // encapsulation
    private String moduleId;
    private String moduleCode;
    private String moduleName;
    private double creditHour;
    private Lecturer lecturer;
    private String status;
    private String createdBy;
    private LocalDate dateCreated;
    
    // constructor
    public Module(String moduleId, String moduleCode, String moduleName, double creditHour, 
                  Lecturer lecturerId, String status, String createdBy, LocalDate dateCreated){
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.creditHour = creditHour;
        this.lecturer = lecturerId;
        this.status = status;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }
    
     public Module(String moduleId){
        this.moduleId = moduleId;
    }
    
    // getter
    public String getModuleId(){
        return moduleId;
    }
    
    public String getModuleCode(){
        return moduleCode;
    }
    
    public String getModuleName(){
        return moduleName;
    }
    
    public double getCreditHour(){
        return creditHour;
    }

    public Lecturer getLecturer(){
        return lecturer;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getCreatedBy(){
        return createdBy;
    }
    
    public LocalDate getDateCreated(){
        return dateCreated;
    }
    
    public static String getFileName(){
        return MODULE_FILE;
    }
    
    // setter
    // no setter for moduleId because it is a unique identifier (primary key)
    // changing it will break references in other places
    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }
    
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }
    
    public void setCreditHour(double creditHour){
        this.creditHour = creditHour;
    }
    
    public void setLecturer (Lecturer lecturer){
        this.lecturer = lecturer;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    
    public void setDateCreated(LocalDate dateCreated){
        this.dateCreated = dateCreated;
    }
    
    // polymorphism
    @Override
    public String serialize(){
        return String.join(";",
                getModuleId(),
                getModuleCode(),
                getModuleName(),
                Double.toString(getCreditHour()),
                getLecturer().getUser_id(),
                getStatus(),
                getCreatedBy(),
                getDateCreated().toString()
        );
    }
    
    // file operations
    
    // read all modules
    public static List<Module> getAllModules() {

        List<String> lines = FileManager.readFile(MODULE_FILE);
        List<Module> modules = new ArrayList<>();

        for (String line : lines) {

            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length != 8) continue;

            try {
                double creditHour = Double.parseDouble(data[3]); // convert from text to number
                LocalDate dateCreated = LocalDate.parse(data[7]); // convert from text to LocalDate format

                modules.add(new Module(
                        data[0], data[1], data[2],
                        creditHour, Lecturer.getLecturerById(data[4]), data[5],
                        data[6], dateCreated
                ));

            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Skipping invalid module line: " + line);
            }
        }
        return modules;
    }
    
    // filter active modules
    public static List<Module> getActiveModules() {
        List<Module> result = new ArrayList<>();
        for (Module m : getAllModules()) {
            if ("Active".equalsIgnoreCase(m.getStatus())) {
                result.add(m);
            }
        }
        return result;
    }
    
    // filter inactive modules
    public static List<Module> getInactiveModules() {
        List<Module> result = new ArrayList<>();
        for (Module m : getAllModules()) {
            if ("Inactive".equalsIgnoreCase(m.getStatus())) {
                result.add(m);
            }
        }
        return result;
    }
    
    // create modules
    public static boolean createModule(Module module) {
        try {
            FileManager.writeFile(MODULE_FILE, module.serialize(), true);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    // update module
    public static boolean updateModule(Module updated) {

        List<Module> modules = getAllModules();
        List<String> lines = new ArrayList<>();

        for (Module m : modules) {
            if (m.getModuleId().equals(updated.getModuleId())) {
                lines.add(updated.serialize());
            } else {
                lines.add(m.serialize());
            }
        }

        FileManager.writeFile(MODULE_FILE, lines, false);
        return true;
    }
    
    // deactivate module (if class is created)
    public static boolean deactivateModule(String moduleId, boolean classExists) {

        if (!classExists) return false;

        List<Module> modules = getAllModules();
        List<String> lines = new ArrayList<>();

        for (Module m : modules) {
            if (m.getModuleId().equals(moduleId)) {
                m.setStatus("Inactive");
            }
            lines.add(m.serialize());
        }

        FileManager.writeFile(MODULE_FILE, lines, false);
        return true;
    }
    
    // delete module (if class is not created)
    public static boolean deleteModule(String moduleId, boolean classExists) {

        if (classExists) return false;

        List<String> lines = new ArrayList<>();

        for (Module m : getAllModules()) {
            if (!m.getModuleId().equals(moduleId)) {
                lines.add(m.serialize());
            }
        }

        FileManager.writeFile(MODULE_FILE, lines, false);
        return true;
    }
    
    // Get assessments by module id
    public static List<Assessment> getAssessmentByModuleId(Module module){
        List<Assessment> assessmentList = new ArrayList<>();
        List<Module> allModules = Module.getAllModules();
        List<String> lines = readFile(Assessment.getFileName());
        
        for (String line : lines) {

            if (line.trim().isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length != 9) continue;

            try {
                int totalMarks = Integer.parseInt(data[4]);
                int weightPercentage = Integer.parseInt(data[5]); 
                LocalDate dateCreated = LocalDate.parse(data[8]);
                String moduleIdFromFile = data[3];
                Module moduleObj = allModules.stream()
                                         .filter(m -> m.getModuleId().equals(moduleIdFromFile))
                                         .findFirst()
                                         .orElse(null);

                if (moduleObj == null) {
                    System.out.println("Module not found for class: " + data[0]);
                    continue;
                }
                
                String lecturerId = data[7];
                Lecturer lecturer = new Lecturer(lecturerId);
                
                if (module.getModuleId().equals(moduleObj.getModuleId())){
                    assessmentList.add(new Assessment(
                            data[0],
                            data[1], 
                            AssessmentType.valueOf(data[2].toUpperCase()),
                            moduleObj,
                            totalMarks,
                            weightPercentage, 
                            AssessmentStatus.valueOf(data[6].toUpperCase()),
                            lecturer, 
                            dateCreated
                    ));
                }

            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Skipping invalid assessment line: " + line);
            }
        }
        return assessmentList;
    }
    
    // Get module object by module id
    public static Module getModuleById(String moduleId) {
        for (Module m : getAllModules()) {
            if (m.getModuleId().equals(moduleId)) {
                return m;
            }
        }
        return null;
    }
}
