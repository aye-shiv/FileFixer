package filefixer;

public class FileFixer{
  public static void main(String[] args) {
    System.out.println("");
    FileManager fileSystem = new FileManager();
    if(!fileSystem.getStatus()){
        return; //Required folders are missing
    }
    
    fileSystem.readEntries();
    fileSystem.readFiles();
    
    fileSystem.renameFiles();
    System.out.println("\n\n");
    fileSystem.printListofMissingSubmissions();
  }
}
  