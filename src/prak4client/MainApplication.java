package prak4client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private AnmeldungsController anmeldungsController;

    private AnwendungsController anwendungsController;

    private BenutzerVerwaltungAdmin benutzerVerwaltungAdmin;

    private LoginController loginController;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.benutzerVerwaltungAdmin = new BenutzerVerwaltungAdmin();

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
            this.benutzerVerwaltungAdmin.dbInitialisieren();
            System.out.println("Initialized new db.");
        }
        System.out.println("ENDE");

        this.stage = primaryStage;

        this.setStage("login.fxml", "Login");
    }

    /**
     * 
     */
    void neuAnmeldung() {
        this.setStage("anmeldung.fxml", "Anmeldung");
    }

    /**
     * 
     * @param benutzer
     * @throws BenutzerEmptyException
     */
    void neuerBenutzer(Benutzer benutzer) {
        try {
            this.benutzerVerwaltungAdmin.benutzerEintragen(benutzer);
            this.setStage("login.fxml", "Login");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            // e.printStackTrace();
            // this.anmeldungsController
            // .showErrorMessage("Der Benutzer ist bereits vorhanden!");
        }
    }

    /**
     * 
     * @param benutzer
     * @throws BenutzerEmptyException
     * @throws BenutzerVergleichException
     */
    void benutzerLogin(Benutzer benutzer) {
        try {
            if (this.benutzerVerwaltungAdmin.benutzerOk(benutzer)) {
                this.setStage("anwendung.fxml", "Anwendung");
            } else {
                // this.loginController
                // .showErrorMessage("Der Benutzer ist NICHT vorhanden!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStage(String file, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            Parent root = (Parent) loader.load();

            switch (file) {
            case "login.xml":
                this.loginController = (LoginController) loader.getController();
                this.loginController.setMainApplication(this);
                break;
            case "anmeldung.fxml":
                this.anmeldungsController = (AnmeldungsController) loader
                        .getController();
                this.anmeldungsController.setMainApplication(this);
                break;
            default:
                break;
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
