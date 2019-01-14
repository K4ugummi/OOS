package prak4gemklassen;

/**
 * Throw this exception when internal data errors occur.
 * 
 * @author Stephan Schauerte
 *
 */
public class BenutzerVerwaltungsSystem extends Exception {

    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = 2621140286839320150L;

    /**
     * Create a new BenutzerBerwaltungsSystem exception.
     * 
     * @param msg
     *            Detailed description of what went wrong.
     */
    public BenutzerVerwaltungsSystem(String msg) {
        super(msg);
    }
}