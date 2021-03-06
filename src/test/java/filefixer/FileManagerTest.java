package filefixer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;

public class FileManagerTest {
    private FileManager filer = new FileManager();

    @Test
    public void testGetStatus(){
        System.out.println("getStatus");
        assertTrue(filer.getStatus());
    }

    @Test
    public void testReadEntries(){
        System.out.println("readEntries");
        assertNotNull(filer.getCSVFile());
    }

    @Test
    public void testReadFiles(){
        System.out.println("readFiles");
        assertNotNull(filer.getFiles());
    }

    @Test
    public void testRenameFile(){
        System.out.println("renameFiles"); 
        filer.readEntries();
        filer.readFiles();
        File file = new File("example", ".pdf");
        Entry shiv = new Entry("Participant 01232000", 
        "Shivam Persad", 
        "816016854");
        String expResult = "Shivam Persad_01232000_assignsubmission_file_"+file.getName();
        assertEquals(expResult, filer.renameFile(file, shiv));
    }
}
