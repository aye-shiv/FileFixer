package filefixer;

public class Entry{
    private String identifier;
    private String fullName;
    private String IDNumber;
    
    private boolean found = false;
    
    public Entry(
    String identifier, String fullName, String IDNumber){
        if(identifier.length() != 0){
            String[] tempID = identifier.split(" ");
            this.identifier = tempID[1]; //Separate the Identifier
        }
        else this.identifier = identifier;
        this.fullName = fullName;
        this.IDNumber = IDNumber;
    }
    
    //Setter
    public void setFound(boolean found) { this.found = found; }
    
    //Accessors
    public String getIdentifer() { return this.identifier; }
    public String getFullName() { return this.fullName; }
    public String getIDNumber() { return this.IDNumber; }
    public boolean isFound() { return this.found; }
}
