package filefixer;

import java.io.*;

public class EntryID implements EntryHandler{
    private int position;
    private String data;
    
    public EntryID(String data, int position){
        this.position = position;
        this.data = data;
    }

    @Override
    public boolean isEntry(File file){
        String filename = file.getName().toLowerCase();
        if(filename.contains(getData())){
            return true;
        }
        return false;
    }
    
    //Accessors
    public int getPosition() { return this.position; }
    public String getData() { return this.data; }
}
