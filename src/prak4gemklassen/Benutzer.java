package prak4gemklassen;

import java.io.Serializable;

/**
 * User account information.
 * 
 * These information are needed for user registration and login.
 * 
 * @author Stephan Schauerte
 *
 */
public class Benutzer implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5055085168596333609L;

    /**
     * The user id is a unique String representing the user.
     */
    public String userId;

    /**
     * The password of the user account.
     */
    public char[] passWort;

    /**
     * Default constructor to create a new user.
     */
    public Benutzer() {
        this.userId = "";
        this.passWort = new char[0];
    }

    /**
     * Create a new Benutzer by passing a userId and a passWort.
     * 
     * @param userId
     *            An identifying String.
     * @param passWort
     *            The password for the user.
     */
    public Benutzer(String userId, char[] passWort) {
        this.userId = new String(userId);
        this.passWort = passWort.clone();
    }

    /**
     * Check if the userId and passWort of both Benutzer match.
     */
    public boolean equals(Object other) {
        // TODO: remove assertion!
        if (other instanceof Benutzer) {
            boolean isEqualId = this.userId.equals(((Benutzer) other).userId);
            boolean isEqualPw = String.copyValueOf(this.passWort)
                    .equals(String.copyValueOf(((Benutzer) other).passWort));

            return isEqualId && isEqualPw;
        } else {
            return false;
        }
    }

    /**
     * Override the toString() method for Benutzer.
     */
    public String toString() {
        return "Benutzer { userId:" + this.userId + " passWort:"
                + String.copyValueOf(this.passWort) + " }";
    }
}
