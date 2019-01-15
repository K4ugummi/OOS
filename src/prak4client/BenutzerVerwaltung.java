package prak4client;

import prak4gemklassen.*;

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
     *            userId and passWort to add to the user list
     * @throws BenutzerVerwaltungsSystem
     *             Internal exception when something unknown goes wrong.
     * @throws BenutzerVorhanden
     *             User already exists and can not be added to the list.
     * @throws PasswortRichtlinie
     *             Conflict with the password policy.
     */
    void benutzerEintragen(Benutzer benutzer) throws BenutzerVerwaltungsSystem,
            BenutzerVorhanden, BenutzerRichtlinie;

    /**
     * Check if a user <b>benutzer</b> is already inside the list of registered
     * users. Returns <b>true</b> if the user already exists and the passwords
     * match.
     * 
     * @param benutzer
     *            Check if combination of userId and passWort exist in the list.
     * @return True: User with passed userId and passWort exists. False: User
     *         does not exist.
     */
    boolean benutzerOk(Benutzer benutzer);
}
