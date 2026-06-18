package assessmentfeedbacksystem;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Assessment implements FileSerializable{
    private String assessmentId;
    private String assessmentName;
    private AssessmentType assessmentType;
    private Module module;
    private int totalMarks;
    private int weightPercentage;
    private AssessmentStatus assessmentStatus;
    private Lecturer lecturer;
    private LocalDate createdAt;
    private static final String ASSESSMENT_FILE = "Assessment.txt";
    
    // Constructor
    public Assessment(String assessment_id, String assessment_name, AssessmentType assessment_type, Module module, int total_marks, 
            int weight_percentage, AssessmentStatus status, Lecturer lecturer, LocalDate created_at){
        this.assessmentId = assessment_id;
        this.assessmentName = assessment_name;
        this.assessmentType = assessment_type;
        this.module = module;
        this.totalMarks = total_marks;
        this.weightPercentage = weight_percentage;
        this.assessmentStatus = status;
        this.lecturer = lecturer;
        this.createdAt = created_at;
    }
    
    // Getter
    public static String getFileName(){
        return ASSESSMENT_FILE;
    }
    
    public String getAssessmentId(){
        return assessmentId;
    }
    
    public String getAssessmentName(){
        return assessmentName;
    }
    
    public AssessmentType getAssessmentType(){
        return assessmentType;
    }

    public Module getModule(){
        return module;
    }
    
    public int getTotalMarks(){
        return totalMarks;
    }
    
    public int getWeightPercentage(){
        return weightPercentage;
    }
    
    public AssessmentStatus getAssessmentStatus(){
        return assessmentStatus;
    }
    
    public Lecturer getLecturer(){
        return lecturer;
    }
    
    public LocalDate getCreatedAt(){
        return createdAt;
    }
    
    // Setter
    public void setAssessmentId(String assessment_id){
        this.assessmentId = assessment_id;
    }
    
    public void setAssessmentName(String assessment_name){
        this.assessmentName = assessment_name;
    }
    
    public void setAssessmentType(AssessmentType assessment_type){
        this.assessmentType = assessment_type;
    }
    
    public void setModule(Module module){
        this.module = module;
    }
    
    public void setTotalMarks(int total_marks){
        this.totalMarks = total_marks;
    }
    
    public void setWeightPercentage(int weight_percentage){
        this.weightPercentage = weight_percentage;
    }
    
    public void setAssessmentStatus(AssessmentStatus status){
        this.assessmentStatus = status;
    }
    
    public void setCreatedAt(LocalDate created_at){
        this.createdAt = created_at;
    }
    
    public void setLecturer(Lecturer lecturer){
        this.lecturer = lecturer;
    }
    
    // Interface method
    @Override
    public String serialize(){
        return String.join(";", 
                getAssessmentId(),
                getAssessmentName(),
                getAssessmentType().toString(),
                getModule().getModuleId(),
                Integer.toString(getTotalMarks()),
                Integer.toString(getWeightPercentage()),
                getAssessmentStatus().toString(),
                getLecturer().getUser_id(),
                getCreatedAt().toString()
        );
    }
    
    // Get all assessment
    public static List<Assessment> getAllAssessments() {
        List<Module> allModules = Module.getAllModules();
        List<String> lines = FileManager.readFile(ASSESSMENT_FILE);
        List<Assessment> assessments = new ArrayList<>();

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
                assessments.add(new Assessment(
                    data[0],
                    data[1], 
                    AssessmentType.valueOf(data[2].trim().toUpperCase().replace(" ", "_")),
                    moduleObj,
                    totalMarks,
                    weightPercentage, 
                    AssessmentStatus.valueOf(data[6].toUpperCase().replace(" ", "_")),
                    lecturer, 
                    dateCreated
                ));

            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Skipping invalid assessment line: " + line);
            }
        }
        return assessments;
    }   

    // Get assessment by assessment id
    public static Assessment getAssessmentById(String assessmentId) {
        List<Assessment> assessments = getAllAssessments();
        for (Assessment a : assessments) {
            if (a.getAssessmentId().equals(assessmentId)) {
                return a;
            }
        }
        return null;
    }
    
    // Get assessment by module id
    public static List<Assessment> getAssessmentsByModule(String moduleId) {
        List<Assessment> moduleAssessments = new ArrayList<>();
        List<Assessment> all = getAllAssessments();
        
        for (Assessment a : all) {
            if (a.getModule() != null && a.getModule().getModuleId().equals(moduleId)) {
                moduleAssessments.add(a);
            }
        }
        return moduleAssessments;
    }

    // Update assessment status
    public static boolean changeAssessmentStatus(String ass_id, String status){
        try{
            List<String> lines = FileManager.readFile(ASSESSMENT_FILE);
            List<String> updatedLines = new ArrayList<>(lines);

            for (int i = 0; i < updatedLines.size(); i++){
                String[] parts = updatedLines.get(i).split(";");
                if (ass_id.equals(parts[0])){
                    String updatedLine = String.join(";", 
                            parts[0],
                            parts[1], 
                            parts[2], 
                            parts[3], 
                            parts[4], 
                            parts[5], 
                            status, 
                            parts[7], 
                            parts[8]
                    );
                    updatedLines.set(i, updatedLine);
                    break;
                }
            }

            FileManager.writeFile(ASSESSMENT_FILE, updatedLines, false);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    // Create new assessment or update assessment detail in txt file
    public static boolean saveOrUpdateAssessment(String data) {
        try {
            List<String> lines = FileManager.readFile(ASSESSMENT_FILE);
            List<String> updatedLines = new ArrayList<>();

            String id = data.split(";")[0];
            boolean found = false;

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");
                if (id.equals(parts[0])) {
                    updatedLines.add(data);
                    found = true;
                } else {
                    updatedLines.add(line);
                }
            }

            if (!found) {
                updatedLines.add(data);
            }

            FileManager.writeFile(ASSESSMENT_FILE, updatedLines, false);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Generate new assessment id
    public static String getNewAssessmentId(){
        List<String> lines = FileManager.readFile(ASSESSMENT_FILE);
        int max = 0;

        for (String line : lines) {
            String[] data = line.split(";");

            String id = data[0];

            if (id.startsWith("A")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    max = Math.max(max, num);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return String.format("A%03d", max + 1);
    }
    
    // Count the remaining percentage of final result for a module
    public static int remainingPercentage(String module_id){
        List<String> lines = FileManager.readFile(ASSESSMENT_FILE);
        int percentage = 0;
        
        for (String line: lines){
            String[] data = line.split(";");
            if (module_id.equals(data[3])){
                try {
                    percentage += Integer.parseInt(data[5]);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return 100 - percentage;
    }
    
    // Get draft and weighted assessment by module id
    public static List<Assessment> getWeightedAssessments(String module_id) {
        List<Assessment> ass = getAllAssessments();
        List<Assessment> result = new ArrayList<>();

        for (Assessment a : ass) {
            if (module_id.equals(a.getModule().getModuleId())
                    && a.getWeightPercentage() > 0
                    && a.getAssessmentStatus() == AssessmentStatus.DRAFT) {

                result.add(a);
            }
        }
        return result;
    }
    
    // Publish all weighted assessment by module id 
    public static String publishAllFinalAssessments(String module_id){
        List <Assessment> ass = getWeightedAssessments(module_id);
        
        if (ass.isEmpty()) {
            return "No draft final assessments found to publish.";
        }
        
        int total = 0;
        for (Assessment a : ass) {
            total += a.getWeightPercentage();
        }

        if (total != 100) {
            return "Total weightage must be exactly 100%. "
                 + "Current total: " + total + "%.";
        }

        try {
            for (Assessment a: ass){
                changeAssessmentStatus(a.getAssessmentId(), AssessmentStatus.PUBLISHED.name().toLowerCase());
            }
            return null;
        }catch (Exception e){
            return "An unexpected error occurred while publishing assessments. "
             + "Please try again.";
        }
    }
    
    // Delete assessment
    public static boolean deleteAssessment(String assessmentId) {
        List<String> lines = new ArrayList<>();

        for (Assessment a : getAllAssessments()) {
            if (!a.getAssessmentId().equals(assessmentId)) {
                String data = a.serialize(); 
                lines.add(data);
            }
        }
        try {
            FileManager.writeFile(ASSESSMENT_FILE, lines, false);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
