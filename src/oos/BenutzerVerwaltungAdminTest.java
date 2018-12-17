package oos;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test functions for BenutzerVerwaltungAdmin
 * 
 * @author Stephan Schauerte
 *
 */
public class BenutzerVerwaltungAdminTest {

    @Before
    public void setUp() throws IOException {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();

        admin.dbInitialisieren();
    }

    @Test
    public void testConstructor() {
        System.out.println("testAconstructor()");
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        assertEquals(0, admin.benutzerListe.size());
    }

    @Test
    public void testBenutzerEintragen() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());

        try {
            admin.benutzerEintragen(benutzer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        assertEquals(1, admin.benutzerListe.size());
        assert ("Stephan".equals(admin.benutzerListe.get(0).userId));
        assert ("Test1234".equals(
                String.copyValueOf(admin.benutzerListe.get(0).passWort)));
    }

    @Test
    public void testBenutzerEintragenDoppelt() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());

        boolean isDoppeltException = false;
        try {
            admin.benutzerEintragen(benutzer);
            admin.benutzerEintragen(benutzer);
        } catch (BenutzerVorhanden e) {
            isDoppeltException = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail();
        }

        assertTrue(isDoppeltException);
    }

    @Test
    public void testBenutzerLoeschen() {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        Benutzer benutzer = new Benutzer("Stephan", "Test1234".toCharArray());

        try {
            admin.benutzerEintragen(benutzer);
            admin.benutzerLoeschen(benutzer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fail();
        }

        assertEquals(0, admin.benutzerListe.size());
    }
    
    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown()");
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        admin.dbLoeschen();

        File file = new File(BenutzerVerwaltungAdmin.USER_DB_PATH);
        assertFalse(file.exists());
    }
}
