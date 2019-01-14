package prak4gemklassen;

/**
 * Throw this exception when there are password issues.
 * 
 * @author Stephan Schauerte
 *
 */
public class BenutzerRichtlinie extends Exception {
    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = 4135657110606156348L;

    int min;

    int max;

    /**
     * Create a new PasswortRichtlinie exception.
     * 
     * @param msg
     *            Detailed description of what went wrong.
     */
    public BenutzerRichtlinie(String msg, int min, int max) {
        super(msg);
        this.min = min;
        this.max = max;
    }
}
