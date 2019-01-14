package prak4gemklassen;
/**
 * Throw this exception when a user does not exist but the action requires one.
 * 
 * @author Stephan Schauerte
 *
 */
public class BenutzerNichtVorhanden extends Exception {

    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = -7587772602066886490L;

    /**
     * Create a new BenutzerNichtVorhanden exception.
     * 
     * @param msg
     *            Detailed description of what went wrong.
     */
    public BenutzerNichtVorhanden(String msg) {
        super(msg);
    }
}