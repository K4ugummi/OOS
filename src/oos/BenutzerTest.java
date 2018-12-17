package oos;
import static org.junit.Assert.*;

import org.junit.Test;

public class BenutzerTest {

    @Test
    public void testA() {
        Benutzer benutzer = new Benutzer();
        assertEquals("", benutzer.userId);
        assertEquals(0, benutzer.passWort.length);
    }

    @Test
    public void testB() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        assertEquals("Stephan", benutzer.userId);
        assertEquals(8, benutzer.passWort.length);
        assertEquals("Test1234", String.copyValueOf(benutzer.passWort));
    }

    @Test
    public void testC() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan", "Test1234".toCharArray());
        assertEquals(benutzer, other);
    }

    @Test
    public void testD() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan2", "Test1234".toCharArray());
        assertFalse(benutzer.equals(other));
    }

    @Test
    public void testE() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan", "Test12345".toCharArray());
        assertFalse(benutzer.equals(other));
    }

    @Test
    public void testF() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        String benutzerString = "Benutzer { userId:Stephan passWort:Test1234 }";
        assertEquals(benutzerString, benutzer.toString());
    }
}
