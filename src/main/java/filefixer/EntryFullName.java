package filefixer;

import java.io.*;

public class EntryFullName implements EntryHandler{
    private int position;
    private String data;
    
    public EntryFullName(String data, int position){
        this.position = position;
        this.data = data;
    }

    @Override
    public boolean isEntry(File file){
        boolean found = true;
        String filename = file.getName().toLowerCase();
        String[] studentName = getData().toLowerCase().split(" |-");

        for(String name: studentName){
            if(!filename.contains(name.replaceAll("\\s", ""))){
                found = false;
                break;
            }
        }
        return found;
    }
    
    //ACCESSOR
    public int getPosition() { return this.position; }
    public String getData() { return this.data; }
}
