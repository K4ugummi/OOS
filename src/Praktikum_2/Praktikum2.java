package Praktikum_2;

public class Praktikum2 {

    public static void main(String[] args) {
        BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();

        char[] passwort = "Test1234".toCharArray();
        char[] kurz = "123".toCharArray();
        char[] lang = "1234567890qwertzuiopasdfghjklyxcv".toCharArray();

        Benutzer benutzer1 = new Benutzer("Stephan", passwort);
        Benutzer benutzer1clone = new Benutzer("Stephan", passwort);
        Benutzer benutzer2 = new Benutzer("Michael", passwort);

        Benutzer benutzerZuKurz = new Benutzer("Kurz", kurz);
        Benutzer benutzerZuLang = new Benutzer("Lang", lang);

        System.out.println("Benutzer1 = " + benutzer1.toString());
        System.out.println("Benutzer1clone = " + benutzer1clone.toString());
        System.out.println("Benutzer2 = " + benutzer2.toString());
        System.out.println("BenutzerZuKurz = " + benutzerZuKurz.toString());
        System.out.println("BenutzerZuLang = " + benutzerZuLang.toString());

        if (benutzer1.equals(benutzer2)) {
            System.out.println("Benutzer1 == Benutzer2");
        } else {
            System.out.println("Benutzer1 != Benutzer2");
        }

        if (benutzer1.equals(benutzer1)) {
            System.out.println("Benutzer1 == Benutzer1");
        } else {
            System.out.println("Benutzer1 != Benutzer1");
        }
        if (benutzer1.equals(benutzer1clone)) {
            System.out.println("Benutzer1 == Benutzer1clone");
        } else {
            System.out.println("Benutzer1 != Benutzer1clone");
        }

        // Add user to the user list. No exception should be thrown here.
        System.out.println("Test: Passwort zu kurz...");
        try {
            admin.benutzerEintragen(benutzerZuKurz);
            System.out.println("BenutzerZuKurz hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Add user to the user list. No exception should be thrown here.
        System.out.println("Test: Passwort zu lang...");
        try {
            admin.benutzerEintragen(benutzerZuLang);
            System.out.println("BenutzerZuKurz hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Add user to the user list. No exception should be thrown here.
        System.out.println("Test: Benutzer1 hinzufuegen...");
        try {
            admin.benutzerEintragen(benutzer1);
            System.out.println("Benutzer1 hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Try to add an existing user and catch the exception.
        System.out.println("Test: Benutzer1 hinzufuegen...");
        try {
            admin.benutzerEintragen(benutzer1);
            System.out.println("Benutzer1 hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Try to add an existing user and catch the exception.
        System.out.println("Test: Benutzer1clone hinzufuegen...");
        try {
            admin.benutzerEintragen(benutzer1);
            System.out.println("Benutzer1 hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Delete an existing user. No exception should be thrown here.
        System.out.println("Test: Benutzer1 loeschen...");
        try {
            admin.benutzerLoeschen(benutzer1);
            System.out.println("Benutzer1 geloescht");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Try to delete a non existing user and catch the exception.
        System.out.println("Test: Benutzer1 loeschen...");
        try {
            admin.benutzerLoeschen(benutzer1);
            System.out.println("Benutzer1 geloescht");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Add another user to the user list.
        System.out.println("Test: Benutzer2 einfuegen");
        try {
            admin.benutzerEintragen(benutzer2);
            System.out.println("Benutzer2 hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Try to add the same other user again and catch the exception.
        System.out.println("Test: Benutzer2 einfuegen...");
        try {
            admin.benutzerEintragen(benutzer2);
            System.out.println("User 2 hinzugefuegt");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Delete the other user.
        System.out.println("Test: Benutzer2 loeschen");
        try {
            admin.benutzerLoeschen(benutzer2);
            System.out.println("User 2 geloescht");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }

        // Try to delete the other user again and catch the exception.
        System.out.println("Test: Benutzer2 loeschen");
        try {
            admin.benutzerLoeschen(benutzer2);
            System.out.println("User 2 geloescht");
        } catch (Exception e) {
            System.out.println("Exception aufgefangen: " + e.getMessage());
        }
    }
}
