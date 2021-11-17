package filefixer;

public class Entry{
    private String identifier;
    private String fullName;
    private String IDNumber;
    private String email;
    private String status;
    private String grade;
    private String maxGrade;
    private String gradeCanBeChanged;
    private String lastModified;
    private String feedback;
    
    public Entry(
    String identifier, String fullName, String IDNumber, String email,
    String status, String grade, String maxGrade,
    String gradeCanBeChanged, String lastModified, String feedback){
        if(identifier.length() != 0){
            String[] tempID = identifier.split(" ");
            this.identifier = tempID[1]; //Separate the Identifer
        }
        else this.identifier = identifier;
        this.fullName = fullName;
        this.IDNumber = IDNumber;
        this.email = email;
        this.status = status;
        this.grade = grade;
        this.maxGrade = maxGrade;
        this.gradeCanBeChanged = gradeCanBeChanged;
        this.lastModified = lastModified;
        this.feedback = feedback;
    }
    
    //Accessors
    public String getIdentifer() { return this.identifier; }
    public String getFullName() { return this.fullName; }
    public String getIDNumber() { return this.IDNumber; }
    public String getEmail() { return this.email; }
    public String getStatus() { return this.status; }
    public String getGrade() { return this.grade; }
    public String getMaxGrade() { return this.maxGrade; }
    public String getGradeCanBeChanged() { return this.gradeCanBeChanged; }
    public String getLastModified() { return this.lastModified; }
    public String getFeedback() { return this.feedback; }
}
