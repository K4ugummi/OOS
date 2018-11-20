package Praktikum_2;

import java.util.Vector;

/**
 * Managing class that implements BenutzerVerwalrtung to handle users.
 * @author Stephan Schauerte
 *
 */
public class BenutzerVerwaltungAdmin implements BenutzerVerwaltung {

    /**
     * Defines a maximum password length for a new user..
     */
    private static final int PASSWORT_MAX = 32;

    /**
     * Defines a minimum password length for a new user.
     */
    private static final int PASSWORT_MIN = 4;

    /// Better use a hash map for a large amount of users.
    private Vector<Benutzer> benutzerListe;

    /**
     * Default contructor.
     */
    public BenutzerVerwaltungAdmin() {
        this.benutzerListe = new Vector<Benutzer>();
    }

    /**
     * Try to add a user to the user list.
     */
    @Override
    public void benutzerEintragen(Benutzer benutzer)
            throws BenutzerVerwaltungsSystem, BenutzerVorhanden,
            PasswortRichtlinie {
        if (benutzerVorhanden(benutzer)) {
            throw new BenutzerVorhanden("Der Benutzer existiert bereits!");
        } else {
            // Check settings like password length.
            if (benutzer.passWort.length < 4) {
                throw new PasswortRichtlinie(
                        "Das passWort ist zu kurz!" + " Es sollte mindestens "
                                + PASSWORT_MIN + " Zeichen lang sein");
            } else if (benutzer.passWort.length > 32) {
                throw new PasswortRichtlinie(
                        "Das passWort is zu lang!" + " Es sollte maximal "
                                + PASSWORT_MAX + " Zeichen lang sein");
            }

            // Try to add the user.
            if (!this.benutzerListe.add(benutzer)) {
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
        if (benutzerOk(benutzer)) {
            if (!this.benutzerListe.remove(benutzer)) {
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
