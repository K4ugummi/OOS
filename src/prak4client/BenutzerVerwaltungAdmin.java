package prak4client;
import java.util.Vector;
import java.io.*;
import prak4gemklassen.*;

/**
 * Managing class that implements BenutzerVerwalrtung to handle users.
 * 
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
        this.benutzerListe = new Vector<Benutzer>();
        this.dbLesen();
    }

    void dbLoeschen() throws Exception {
        File file = new File(USER_DB_PATH);
        if (!file.delete()) {
            throw new Exception("Could not delete db file.");
        }
    }

    void dbInitialisieren() throws IOException {
        this.benutzerListe = new Vector<Benutzer>();
        this.dbSchreiben();
    }

    void dbLesen() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(USER_DB_PATH));
            this.benutzerListe = (Vector<Benutzer>) in.readObject();
            in.close();
        } catch (IOException e) {
            System.out.println("dbLesen: " + e.getMessage() + " try again.");

        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    void dbSchreiben() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(USER_DB_PATH));
            out.writeObject(this.benutzerListe);
            out.close();
        } catch (IOException e) {
            System.err.println("dbSchreiben " + e.getMessage());
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
                throw new BenutzerRichtlinie("passWor zu kurz",
                        PASSWORT_MIN_LENGTH, PASSWORT_MAX_LENGTH);
            } else if (benutzer.passWort.length > PASSWORT_MAX_LENGTH) {
                throw new BenutzerRichtlinie("passWort zu lang",
                        PASSWORT_MIN_LENGTH, PASSWORT_MAX_LENGTH);
            }
            if (benutzer.userId.length() < BENUTZER_MIN_LENGTH) {
                throw new BenutzerRichtlinie("userId zu kurz",
                        BENUTZER_MIN_LENGTH, BENUTZER_MAX_LENGTH);
            } else if (benutzer.userId.length() > BENUTZER_MAX_LENGTH) {
                throw new BenutzerRichtlinie("userId zu lang",
                        BENUTZER_MIN_LENGTH, BENUTZER_MAX_LENGTH);
            }

            // Try to add the user.
            if (this.benutzerListe.add(benutzer)) {
                this.dbSchreiben();
            } else {
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
            } else {
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
