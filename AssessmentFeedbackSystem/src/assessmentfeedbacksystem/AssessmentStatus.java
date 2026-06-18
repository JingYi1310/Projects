package assessmentfeedbacksystem;

public enum AssessmentStatus {
    PUBLISHED,
    DRAFT,
    RESULTS_PUBLISHED;
    
    @Override
    public String toString(){
        if (name().contains("_")){
            String[] parts = name().split("_");
            String replace = "";
            for (String part: parts){
                String name = part.charAt(0) + part.substring(1).toLowerCase();
                replace += name + " ";
            }
            return replace.trim();
        }   
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
