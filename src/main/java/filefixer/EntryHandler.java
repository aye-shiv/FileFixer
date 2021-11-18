package filefixer;

import java.io.*;
interface EntryHandler {
    public boolean isEntry(File file);
    public int getPosition();
    public String getData();
}
