package filefixer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

public class EntryTest {
    private Entry shiv;
    @BeforeEach public void setUp(){
        shiv = new Entry("Participant 01232000", 
        "Shivam Persad", 
        "816016854");
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
}
