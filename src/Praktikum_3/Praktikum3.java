package Praktikum_3;

/**
 * This praktikum focuses on handling exceptions in java.
 * @author Stephan Schauerte
 *
 */
public class Praktikum3 {

    /**
     * Test the implemented user managing system and the possible exceptions.
     * 
     * @param args
     */
    public static void main(String[] args) {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
        
        Benutzer benutzer = new Benutzer("Dieter", "Test1234".toCharArray());
        
        try {
            admin.benutzerLoeschen(benutzer);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("Ende");
    }
}
