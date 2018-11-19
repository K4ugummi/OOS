package Praktikum_2;

/**
 * 
 * @author stephan
 *
 */
class BenutzerVorhanden extends Exception {

    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = 8503660000372692833L;

    public BenutzerVorhanden(String msg) {
        super(msg);
    }
}

/**
 * 
 * @author stephan
 *
 */
class BenutzerNichtVorhanden extends Exception {

    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = -7587772602066886490L;

    public BenutzerNichtVorhanden(String msg) {
        super(msg);
    }
}

/**
 * 
 * @author stephan
 *
 */
class BenutzerVerwaltungsSystem extends Exception {

    /**
     * I don't know why Eclipse wanted me to add this.
     */
    private static final long serialVersionUID = 2621140286839320150L;

    public BenutzerVerwaltungsSystem(String msg) {
        super(msg);
    }
}

class PasswortRichtlinie extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 4135657110606156348L;

    public PasswortRichtlinie(String msg) {
        super(msg);
    }
}
