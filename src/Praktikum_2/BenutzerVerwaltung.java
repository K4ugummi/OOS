package Praktikum_2;

/**
 * Public interface to allow user registration.
 * 
 * @author Stephan Schauerte
 *
 */
public interface BenutzerVerwaltung {
    /**
     * 
     * @param benutzer
     * @throws BenutzerVerwaltungsSystem
     * @throws BenutzerVorhanden
     * @throws PasswortRichtlinie
     */
    void benutzerEintragen(Benutzer benutzer) throws BenutzerVerwaltungsSystem,
            BenutzerVorhanden, PasswortRichtlinie;

    /**
     * Check if a user <b>benutzer</b> is already inside the list of registered
     * users. Returns <b>true</b> if the user already exists and the passwords
     * match.
     * 
     * @param benutzer
     * @return
     */
    boolean benutzerOk(Benutzer benutzer);
}
