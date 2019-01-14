package prak4gemklassen;
/**
 * Throw this exception when a user already exists but the action requires a new
 * and unique one.
 * 
 * @author Stephan Schauerte
 *
 */
public class BenutzerVorhanden extends Exception {

    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = 8503660000372692833L;

    /**
     * Create a new BenutzerVorhanden exception.
     * 
     * @param msg
     *            Detailed description of what went wrong.
     */
    public BenutzerVorhanden(String msg) {
        super(msg);
    }
}