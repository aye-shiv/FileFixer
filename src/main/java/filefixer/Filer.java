package filefixer;

import java.util.*;
import java.io.*;

public class Filer{
    private File csvFile;
    private File filesToRename;
    private File renamedFiles;
    private ArrayList<File> files;
    private ArrayList<Entry> entries;
    private ArrayList<Entry> missingSubmissions;
    
    private boolean status; //Checks if this class instances were created correctly

    public Filer(){
        
        this.filesToRename = new File("src/main/resources/filesToRename");
        if(!this.filesToRename.exists()){
            System.out.println("[Error]: The '"+ this.filesToRename.getName() + "' folder does not exist");
            this.status = false;
            return;
        }
        
        //Find the csv file
        this.csvFile = null;
        for(File file : this.filesToRename.listFiles()){
            if(file.getName().endsWith(".csv")){
                this.csvFile = file;
                break;
            }
        }
        if(this.csvFile == null){
          System.out.println("[Error]: The csv file does not exist");
          System.out.println("[Note]: Place the student's csv in project '" + this.filesToRename.getName() +"' folder");
            this.status = false;
            return;
        }
        
        this.renamedFiles = new File("src/main/resources/filesToRename/renamedFiles");
        if(!renamedFiles.exists()){
            renamedFiles.mkdirs(); //Create the folder if path not found
        }
        this.files = new ArrayList<File>();
        this.entries = new ArrayList<Entry>();
        this.missingSubmissions = new ArrayList<Entry>();
        this.status = true;
    }
    
    
    public void readEntries(){
        try {
            Scanner reader = new Scanner(this.csvFile);
            String[] info = new String[10];
            String line = reader.nextLine(); //Skip the first line
            
            while(reader.hasNextLine()){
                line = reader.nextLine();
                info = line.split(",", -1);

                Entry entry = new Entry(
                    info[0], //Identifer
                    info[1], //Full Name
                    info[2], //ID Number
                    info[3], //Email Address
                    info[4], //Status
                    info[5], //Grade
                    info[6], //Maximum Grade
                    info[7], //Grade can be changed
                    info[8], //Last modified (grade)
                    info[9]  //Feedback comments
                );
                
                this.entries.add(entry);
            }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    
    public void readFiles(){
        
        for(File file : this.filesToRename.listFiles()){
            
            if(file.isDirectory() || file.getName().endsWith(".csv")){
                //Do nothing for the csv file and other folders
            } else {
                this.files.add(file);
            }
        }
        
    }
    
    //Ensure naming conventions are met
    public String renameFile(File file, Entry entry){
        String newFileName = "";
        newFileName += entry.getFullName() + "_";
        newFileName += entry.getIdentifer() + "_";
        newFileName += "assignsubmission_file_";
        newFileName += file.getName();
        
        String destination = "src/main/resources/filesToRename/renamedFiles/" + newFileName;
        File destFile = new File(destination);
        
        if(file.renameTo(destFile)){
            System.out.println(" [File moved successfully]: " + "[" + file.getName() + "]" + " --> " + "[" + newFileName + "]");
        } else {
            System.out.println("     [Failed to move file]: [" + newFileName + "]");
        }
        return newFileName;
        
    }
    
    public void renameFiles(){
        
        String header = "Files renamed and moved to '"+ this.renamedFiles.getName() + "' folder\n";
        header += "==================================================";
        System.out.println(header);
        
        for(Entry entry: this.entries){
            boolean found = false;
            boolean checkID = true;
            boolean checkName = true;
            String studentID = entry.getIDNumber();
            String[] studentName = entry.getFullName().toLowerCase().split(" |-");
            
            if(studentID.length() == 0 || studentName.length == 0) //Empty Entry
                continue;
            else if(studentID.length() == 0) //No ID to check
                checkID = false;
            else if(studentName.length == 0) //No Name to check
                checkName = false;
            else if(studentName.length != 0) //No Name to check
                if(studentName[0].length() == 0)
                    checkName = false;
            
            for(File file: this.files){
                String filename = file.getName().toLowerCase();
                
                if(checkID && filename.contains(studentID)){
                    found = true;
                    renameFile(file, entry);
                    break;
                } else if(checkName) {
                    boolean test = true;
                    for(String name: studentName){
                        if(!filename.contains(name.replaceAll("\\s", ""))){
                            test = false;
                            break;
                        }
                    }
                    
                    if(test){
                        found = true;
                        renameFile(file, entry);
                        break;
                    }
                    
                }
            }
            
            if(!found)
                this.missingSubmissions.add(entry);
                
        }
        
        System.out.println("\n\n[Note]: If files failed to move, it probably already exists in the '" + this.renamedFiles.getName() + "' folder");
        
        
    }
    public void printListofMissingSubmissions(){
        String list = "Students with Missing Submissions\n";
        list += "==========================================\n";
        for(Entry entry: this.missingSubmissions){
            list += entry.getFullName();
            list += " [" + entry.getIDNumber() + "] \n";
        }
        System.out.println(list);
    }

    //ACCESSORS
    public File getCSVFile() { return this.csvFile; }
    public ArrayList<File> getFiles() { return this.files; }
    public boolean getStatus(){ return this.status; }
}
