package Praktikum_2;

import java.util.Vector;

public class BenutzerVerwaltungAdmin implements BenutzerVerwaltung {

    private static final int PASSWORT_MAX = 32;

    private static final int PASSWORT_MIN = 4;

    /// Better use a hash map for large amounts of users.
    private Vector<Benutzer> benutzerListe;

    BenutzerVerwaltungAdmin() {
        this.benutzerListe = new Vector<Benutzer>();
    }

    /**
     * 
     */
    @Override
    public void benutzerEintragen(Benutzer benutzer)
            throws BenutzerVerwaltungsSystem, BenutzerVorhanden,
            PasswortRichtlinie {
        if (benutzerVorhanden(benutzer)) {
            throw new BenutzerVorhanden("Der Benutzer existiert bereits!");
        } else {
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
     * @throws BenutzerVerwaltungsSystem
     * @throws BenutzerNichtVorhanden
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
     * @return
     */
    public boolean benutzerVorhanden(Benutzer benutzer) {
        for (Benutzer cur : this.benutzerListe) {
            if (cur.userId.equals(benutzer.userId)) {
                return true;
            }
        }
        return false;
    }

}
