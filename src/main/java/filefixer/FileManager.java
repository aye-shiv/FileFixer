package filefixer;

import java.util.*;
import java.io.*;

public class FileManager{
    private File csvFile;
    private File filesToRename;
    private File renamedFiles;
    private ArrayList<File> files;
    private ArrayList<Entry> entries;
    private ArrayList<EntryHandler> data;
    
    private boolean status; //Checks if this class instances were created correctly

    public FileManager(){
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
        this.data = new ArrayList<EntryHandler>();
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

                if(info[0].length()==0){info[0] = "[MISSING IDENTIFIER]";}
                if(info[1].length()==0){info[1] = "[MISSING NAME]";}
                if(info[2].length()==0){info[2] = "[MISSING ID]";}

                Entry entry = new Entry(
                    info[0], //Identifer
                    info[1], //Full Name
                    info[2] //ID Number
                );
                if(entry.getIDNumber().length() != 0){
                    this.data.add(new EntryID(entry.getIDNumber(), this.entries.size()));
                }
                if(entry.getFullName().length() != 0) {
                    this.data.add(new EntryFullName(entry.getFullName(), this.entries.size()));
                }
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
    
    //Ensure naming conventions are met for a single file
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

        for(File file: this.files){
            for(EntryHandler data: this.data){
                if(data.isEntry(file) && !this.entries.get(data.getPosition()).isFound()){
                    this.entries.get(data.getPosition()).setFound(true);
                    Entry entry = this.entries.get(data.getPosition());
                    renameFile(file, entry);
                }
            }
        }
        System.out.println("\n\n[Note]: If files failed to move, it probably already exists in the '" + this.renamedFiles.getName() + "' folder");    
    }

    public void printListofMissingSubmissions(){
        String list = "Students with Missing Submissions\n";
        list += "==========================================\n";
        for(Entry entry: this.entries){
            if(!entry.isFound()){
                list += entry.getFullName();
                list += " [" + entry.getIDNumber() + "] \n";
            }
        }
        System.out.println(list);
    }

    //ACCESSORS
    public File getCSVFile() { return this.csvFile; }
    public ArrayList<File> getFiles() { return this.files; }
    public boolean getStatus(){ return this.status; }
}
