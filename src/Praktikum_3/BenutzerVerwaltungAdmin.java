package Praktikum_3;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

/**
 * Managing class that implements BenutzerVerwalrtung to handle users.
 * @author Stephan Schauerte
 *
 */
public class BenutzerVerwaltungAdmin implements BenutzerVerwaltung {

    /**
     * Defines a maximum password length for a new user..
     */
    static final int PASSWORT_MAX_LENGTH = 32;

    /**
     * Defines a minimum password length for a new user.
     */
    static final int PASSWORT_MIN_LENGTH = 4;

    /**
     * Defines a maximum password length for a new user..
     */
    static final int BENUTZER_MAX_LENGTH = 16;

    /**
     * Defines a minimum password length for a new user.
     */
    static final int BENUTZER_MIN_LENGTH = 6;
    
    /**
     * Defines the user database file path.
     */
    static final String USER_DB_PATH = "user.db";

    /// Better use a hash map for a large amount of users.
    Vector<Benutzer> benutzerListe;

    /**
     * Default contructor.
     */
    public BenutzerVerwaltungAdmin() {
        dbInitialisieren();
        dbSchreiben();
    }
    
    void dbInitialisieren() {
        this.benutzerListe = new Vector<Benutzer>();
        
        File file = new File(USER_DB_PATH);
        if(!file.exists()) { 
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    void dbLesen() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(USER_DB_PATH));
            this.benutzerListe = (Vector<Benutzer>)in.readObject();
            in.close();
        } catch (IOException e) {
            dbInitialisieren();
            
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        
    }

    void dbSchreiben() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USER_DB_PATH));
            out.writeObject(this.benutzerListe);
            out.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Try to add a user to the user list.
     */
    @Override
    public void benutzerEintragen(Benutzer benutzer)
            throws BenutzerVerwaltungsSystem, BenutzerVorhanden,
            BenutzerRichtlinie {
        
        this.dbLesen();
        
        if (benutzerVorhanden(benutzer)) {
            throw new BenutzerVorhanden("Der Benutzer existiert bereits!");
        } else {
            // Check settings like password length.
            if (benutzer.passWort.length < PASSWORT_MIN_LENGTH) {
                throw new BenutzerRichtlinie(
                        "passWor zu kurz", PASSWORT_MIN_LENGTH, PASSWORT_MAX_LENGTH);
            } else if (benutzer.passWort.length > PASSWORT_MAX_LENGTH) {
                throw new BenutzerRichtlinie(
                        "passWort zu lang", PASSWORT_MIN_LENGTH, PASSWORT_MAX_LENGTH);
            }
            if (benutzer.userId.length() < BENUTZER_MIN_LENGTH) {
                throw new BenutzerRichtlinie(
                        "userId zu kurz", BENUTZER_MIN_LENGTH, BENUTZER_MAX_LENGTH);
            } else if (benutzer.userId.length() > BENUTZER_MAX_LENGTH) {
                throw new BenutzerRichtlinie(
                        "userId zu lang", BENUTZER_MIN_LENGTH, BENUTZER_MAX_LENGTH);
            }

            // Try to add the user.
            if (this.benutzerListe.add(benutzer)) {
                this.dbSchreiben();
            }
            else {
                throw new BenutzerVerwaltungsSystem("Unbekannter Fehler :(");
            }
        }
    }

    /**
     * Removes the user from the user list if it exists.
     * 
     * @param benutzer
     *            userId and passWort of the user to delete.
     * @throws BenutzerVerwaltungsSystem
     *             Internal error
     * @throws BenutzerNichtVorhanden
     *             User is not in list or the combination of userId and passWort
     *             is wrong.
     */
    public void benutzerLoeschen(Benutzer benutzer)
            throws BenutzerVerwaltungsSystem, BenutzerNichtVorhanden {
        
        this.dbLesen();
        
        if (benutzerOk(benutzer)) {
            if (this.benutzerListe.remove(benutzer)) {
                this.dbSchreiben();
            }
            else {
                throw new BenutzerVerwaltungsSystem("Unbekannter Fehler :(");
            }
        } else {
            throw new BenutzerNichtVorhanden(
                    "Die Kombination aus userId und passWort existiert nicht!");
        }
    }

    /**
     * Checks if user name and password match any user in the user list.
     */
    @Override
    public boolean benutzerOk(Benutzer benutzer) {
        return this.benutzerListe.contains(benutzer);
    }

    /**
     * Check if the user already exists without checking password.
     * 
     * @param benutzer
     *            User to check
     * @return True: If the userId already exists. False: If not.
     */
    public boolean benutzerVorhanden(Benutzer benutzer) {
        for (Benutzer curBenutzer : this.benutzerListe) {
            if (curBenutzer.userId.equals(benutzer.userId)) {
                return true;
            }
        }
        return false;
    }

}

/**
 * Test functions for BenutzerVerwaltungAdmin
 * @author Stephan Schauerte
 *
 */
class BenutzerVerwaltungAdminTest {
    @Test
    public void NewBenutzerVerwaltungAdminTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        assertEquals(0, admin.benutzerListe.size());
    }
    
    @Test
    public void BenutzerEintragenTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        
        try {
            admin.benutzerEintragen(benutzer);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertEquals(1, admin.benutzerListe.size());
        assertEquals("Stephan", admin.benutzerListe.get(0).userId);
        assertEquals("Test1234", String.copyValueOf(admin.benutzerListe.get(0).passWort));
    }
    
    @Test
    public void BenutzerEintragenFehlerTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        
        boolean isDoppeltException = false;
        try {
            admin.benutzerEintragen(benutzer);
            admin.benutzerEintragen(benutzer);
        }
        catch (BenutzerVorhanden e) {
            isDoppeltException = true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertTrue(isDoppeltException);
    }
    
    @Test
    public void BenutzerLoeschenTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        
        try {
            admin.benutzerEintragen(benutzer);
            admin.benutzerLoeschen(benutzer);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertEquals(0, admin.benutzerListe.size());
    }
    
    @Test
    public void BenutzerLoeschenFehlerTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());

        boolean isNichtVorhandenException = false;
        try {
            admin.benutzerLoeschen(benutzer);
        }
        catch (BenutzerNichtVorhanden e) {
            isNichtVorhandenException = true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertTrue(isNichtVorhandenException);
    }
    
    @Test
    public void BenutzerOkTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        
        try {
            admin.benutzerEintragen(benutzer);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertTrue(admin.benutzerOk(benutzer));
    }
    
    @Test
    public void BenutzerOkNotTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());
        Benutzer other = new Benutzer("Stephan", "Test12345".toCharArray());
        
        try {
            admin.benutzerEintragen(benutzer);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertFalse(admin.benutzerOk(other));
    }
    
    @Test
    public void BenutzerPassWortFehlerTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", new char[0]);

        boolean isException = false;
        try {
            admin.benutzerEintragen(benutzer);
        }
        catch (BenutzerRichtlinie e) {
            isException = true;
            assertEquals(BenutzerVerwaltungAdmin.PASSWORT_MIN_LENGTH, e.min);
            assertEquals(BenutzerVerwaltungAdmin.PASSWORT_MAX_LENGTH, e.max);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertTrue(isException);
    }
    
    @Test
    public void BenutzerUserIdFehlerTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("", "Test1234".toCharArray());

        boolean isException = false;
        try {
            admin.benutzerEintragen(benutzer);
        }
        catch (BenutzerRichtlinie e) {
            isException = true;
            assertEquals(BenutzerVerwaltungAdmin.BENUTZER_MIN_LENGTH, e.min);
            assertEquals(BenutzerVerwaltungAdmin.BENUTZER_MAX_LENGTH, e.max);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        assertTrue(isException);
    }
    
    @Test
    public void DbFileExistsTest() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        File file = new File(BenutzerVerwaltungAdmin.USER_DB_PATH);
        
        assertTrue(file.exists());
    }
}
