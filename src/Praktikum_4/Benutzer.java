package Praktikum_4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.io.Serializable;

/**
 * User account information.
 * 
 * These information are needed for user registration and login.
 * 
 * @author Stephan Schauerte
 *
 */
public class Benutzer implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5055085168596333609L;

    /**
     * The user id is a unique String representing the user.
     */
    String userId;

    /**
     * The password of the user account.
     */
    char[] passWort;

    /**
     * Default constructor to create a new user.
     */
    public Benutzer() {
        this.userId = "";
        this.passWort = new char[0];
    }

    /**
     * Create a new Benutzer by passing a userId and a passWort.
     * 
     * @param userId
     *            An identifying String.
     * @param passWort
     *            The password for the user.
     */
    public Benutzer(String userId, char[] passWort) {
        this.userId = new String(userId);
        this.passWort = passWort.clone();
    }

    /**
     * Check if the userId and passWort of both Benutzer match.
     */
    public boolean equals(Object other) {
        // TODO: remove assertion!
        if (other instanceof Benutzer) {
            boolean isEqualId = this.userId.equals(((Benutzer) other).userId);
            boolean isEqualPw = String.copyValueOf(this.passWort)
                    .equals(String.copyValueOf(((Benutzer) other).passWort));

            return isEqualId && isEqualPw;
        } else {
            return false;
        }
    }

    /**
     * Override the toString() method for Benutzer.
     */
    public String toString() {
        return "Benutzer { userId:" + this.userId + " passWort:"
                + String.copyValueOf(this.passWort) + " }";
    }
}

class BenutzerTest {
    
    @Test
    public void NewBenutzerTest() {
        Benutzer benutzer = new Benutzer();
        assertEquals("", benutzer.userId);
        assertEquals(0, benutzer.passWort.length);
    }
    
    @Test
    public void NewBenutzerWithParameterTest() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        assertEquals("Stephan", benutzer.userId);
        assertEquals(8, benutzer.passWort.length);
        assertEquals("Test1234", String.copyValueOf(benutzer.passWort));
    }

    @Test
    public void BenutzerEqualsTest() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan", "Test1234".toCharArray());
        assertTrue(benutzer.equals(other));
    }
    
    @Test
    public void BenutzerEqualsNotUserIdTest() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan2", "Test1234".toCharArray());
        assertFalse(benutzer.equals(other));
    }
    
    @Test
    public void BenutzerEqualsNotPassWortTest() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan", "Test12345".toCharArray());
        assertFalse(benutzer.equals(other));
    }
    
    @Test
    public void BenutzerToStringTest() {
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        String benutzerString = "Benutzer { userId:Stephan passWort:Test1234 }";
        assertEquals(benutzerString, benutzer.toString());
    }
}
