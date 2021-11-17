package filefixer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class EntryTest {
    private Entry shiv;
    @BeforeEach public void setUp(){
        shiv = new Entry("Participant 01232000", 
        "Shivam Persad", 
        "816016854", 
        "shivtestmail@gmail.com", 
        "temp", 
        "temp", 
        "100", 
        "temp", 
        "temp", 
        "temp");
    }

    @Test
    public void testGetIdentifier(){
        System.out.println("getIdentifier");
        String expResult = "01232000";
        String result = shiv.getIdentifer();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFullName(){
        System.out.println("getFullName");
        String expResult = "Shivam Persad";
        String result = shiv.getFullName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetIDNumber(){
        System.out.println("getIDNumber");
        String expResult = "816016854";
        String result = shiv.getIDNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetEmail(){
        System.out.println("getEmail");
        String expResult = "shivtestmail@gmail.com";
        String result = shiv.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStatus(){
        System.out.println("getStatus");
        String expResult = "temp";
        String result = shiv.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetGrade(){
        System.out.println("getGrade");
        String expResult = "temp";
        String result = shiv.getGrade();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMaxGrade(){
        System.out.println("getMaxGrade");
        String expResult = "100";
        String result = shiv.getMaxGrade();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetGradeCanBeChanged(){
        System.out.println("getGradeCanBeChanged");
        String expResult = "temp";
        String result = shiv.getGradeCanBeChanged();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetLastModified(){
        System.out.println("getLastModified");
        String expResult = "temp";
        String result = shiv.getLastModified();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFeedback(){
        System.out.println("getFeedback");
        String expResult = "temp";
        String result = shiv.getFeedback();
        assertEquals(expResult, result);
    }
}
