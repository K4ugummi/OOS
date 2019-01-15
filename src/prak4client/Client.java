package prak4client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prak4gemklassen.*;

public class Client extends Application {
    public BenutzerVerwaltung benutzerVerwaltung;

    private Stage stage;

    private LoginController loginController;

    private AnmeldungsController anmeldungsController;

    private boolean lokal;

    public Client() throws ClassNotFoundException, IOException {
        this.benutzerVerwaltung = new BenutzerVerwaltungAdmin();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.benutzerVerwaltung = new BenutzerVerwaltungAdmin();

        boolean isValidInput = false;
        int dbInitialisieren = -1;
        do {
            System.out.println("Do you want to initialize the Database? (0/1)");
            BufferedReader din = new BufferedReader(
                    new InputStreamReader(System.in));
            dbInitialisieren = Integer.parseInt(din.readLine());
            isValidInput = (dbInitialisieren == 0) || (dbInitialisieren == 1);
        } while (!isValidInput);
        
        if (dbInitialisieren == 1) {
            ((BenutzerVerwaltungAdmin)this.benutzerVerwaltung).dbInitialisieren();
            System.out.println("Initialized new db.");
        }
        else {
            System.out.println("Continuing with existing db.");
        }

        // Erzeugung einer LoginScene mit Übergabe der eigenen Referenz an deren
        // Controller
        this.stage = primaryStage;
        this.setStage("login.fxml", "Login");
    }

    /**
     * 
     */
    void neuAnmeldungLokal() {
        this.lokal = true;
        this.setStage("anmeldung.fxml", "Anmeldung");
    }

    /**
     * 
     */
    void neuAnmeldungRemote() {
        this.lokal = false;
        this.setStage("anmeldung.fxml", "Anmeldung");
    }

    void neuerBenutzer(Benutzer benutzer) {
        try {
            if (lokal) {
                this.benutzerVerwaltung = new BenutzerVerwaltungAdmin();
                ((BenutzerVerwaltungAdmin)this.benutzerVerwaltung).benutzerEintragen(benutzer);
            } else {
                this.benutzerVerwaltung = new ClientOrb();
                ((ClientOrb)this.benutzerVerwaltung).benutzerEintragen(benutzer);
            }
        } catch (BenutzerVerwaltungsSystem | BenutzerVorhanden
                | BenutzerRichtlinie e) {
            System.out.println(e.getMessage());
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("login.fxml"));
            Parent root = (Parent) loader.load();

            this.loginController = (LoginController) loader.getController();
            this.loginController.setClient(this);

            this.stage.setTitle("BenutzerVerwaltung");
            this.stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    void benutzerLoginLokal(Benutzer benutzer) {
        try {
            if (((BenutzerVerwaltungAdmin)this.benutzerVerwaltung).benutzerOk(benutzer)) {
                this.setStage("anwendung.fxml", "Anwendung");
            } else {
                this.loginController.setErrorMessage("Benutzer nicht vorhanden oder Passwort falsch!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void benutzerLoginRemote(Benutzer benutzer) {
        try {
            this.benutzerVerwaltung = new ClientOrb();
            if (((ClientOrb)this.benutzerVerwaltung).benutzerOk(benutzer)) {
                this.setStage("anwendung.fxml", "Anwendung");
            } else {
                this.loginController
                        .setErrorMessage("Benutzer nicht vorhanden oder Passwort falsch!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setStage(String file, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Parent root = (Parent) loader.load();
            
            System.out.println("setStage("+file+ ", " + title + ")");

            if (file.equals("login.fxml")) {
                this.loginController = (LoginController) loader.getController();
                this.loginController.setClient(this);
            }
            else if (file.equals("anmeldung.fxml")) {
                this.anmeldungsController = (AnmeldungsController) loader
                        .getController();
                this.anmeldungsController.setClient(this);
            }

            this.stage.setTitle(title);
            this.stage.setScene(new Scene(root));
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}