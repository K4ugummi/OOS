package oos;

/**
 * Throw this exception when a user already exists but the action requires a new
 * and unique one.
 * 
 * @author Stephan Schauerte
 *
 */
class BenutzerVorhanden extends Exception {

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

/**
 * Throw this exception when a user does not exist but the action requires one.
 * 
 * @author Stephan Schauerte
 *
 */
class BenutzerNichtVorhanden extends Exception {

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

/**
 * Throw this exception when internal data errors occur.
 * 
 * @author Stephan Schauerte
 *
 */
class BenutzerVerwaltungsSystem extends Exception {

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

/**
 * Throw this exception when there are password issues.
 * 
 * @author Stephan Schauerte
 *
 */
class BenutzerRichtlinie extends Exception {
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
