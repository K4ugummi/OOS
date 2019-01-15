package prak4client;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prak4gemklassen.Benutzer;

/**
 * Controller to register a new Benutzer.
 * 
 * @author stephan
 *
 */
public class AnmeldungsController extends Application {

    /**
     * Stores if the form has input errors.
     */
    private boolean hasErrors = false;

    /**
     * Stores all error messages to display via a labels text.
     */
    private String hintString = "";

    private Client client;

    @FXML
    private Button btAusfuehren;

    @FXML
    private Label lbNeuAnmeldung;

    @FXML
    private Label lbUserID;

    @FXML
    private Label lbPasswort;

    @FXML
    private Label lbWiederholung;

    @FXML
    private Label lbHint;

    @FXML
    private TextField tfUserID;

    @FXML
    private PasswordField pfPasswort;

    @FXML
    private PasswordField pfWiederholung;

    void setClient(Client client) {
        this.client = client;
    }

    /**
     * Application start function. Initialize the form.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader
                .load(getClass().getResource("anmeldung.fxml"));
        // loader.get
        primaryStage.setTitle("AnmeldungsController");
        primaryStage.setScene(new Scene(loader, 430, 300));
        primaryStage.show();
    }

    /**
     * Check if no input errors exist. Returns true if every input field is
     * valid.
     * 
     * @return
     */
    private boolean checkOkay() {
        this.resetErrors();
        this.checkEmptyID();
        this.checkEmptyPasswort();
        this.checkEmptyWiederholung();
        this.checkPasswortMatch();

        if (this.hasErrors) {
            this.lbHint.setText(this.hintString);
            this.lbHint.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Reset all errors on this form.
     */
    private void resetErrors() {
        this.hasErrors = false;
        this.lbHint.setVisible(false);
        this.hintString = "Error:";

        pfPasswort.setStyle("-fx-text-fill: black;");
        pfWiederholung.setStyle("-fx-text-fill: black;");

        tfUserID.setStyle("-fx-border-color: black;");
        pfPasswort.setStyle("-fx-border-color: black;");
        pfWiederholung.setStyle("-fx-border-color: black;");
    }

    /**
     * Button Ausfuehren is clicked.
     */
    @FXML
    private void onBtAusfuehrenClick() {
        if (this.checkOkay()) {
            String userID = tfUserID.getText();
            String passWort = pfPasswort.getText();

            Benutzer benutzer = new Benutzer(userID, passWort.toCharArray());
            this.client.neuerBenutzer(benutzer);
            System.out.println(benutzer.toString());
        }
    }

    /**
     * Check if UserID field is empty. Set error to true if it is.
     */
    private void checkEmptyID() {
        if (this.tfUserID.getText().isEmpty()) {
            this.hasErrors = true;
            this.hintString += " empty ID;";
            tfUserID.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Check if Passwort field is empty. Set error to true if it is.
     */
    private void checkEmptyPasswort() {
        if (this.tfUserID.getText().isEmpty()) {
            this.hasErrors = true;
            this.hintString += " empty Passwort;";
            pfPasswort.setStyle("-fx-border-color: red;");

        }
    }

    /**
     * Check if Wiederholung field is empty. Set error to true if it is.
     */
    private void checkEmptyWiederholung() {
        if (this.tfUserID.getText().isEmpty()) {
            this.hasErrors = true;
            this.hintString += " empty Wiederholung;";
            pfWiederholung.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Check if Passwort and Wiederholung are equal. Set error to true if they
     * are not.
     */
    private void checkPasswortMatch() {
        if (!this.pfPasswort.getText().equals(this.pfWiederholung.getText())) {
            this.hasErrors = true;
            this.hintString += " Passwort != Wiederholung;";
            this.lbHint.setVisible(true);
            pfPasswort.setStyle("-fx-text-fill: red;");
            pfWiederholung.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Start this form.
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
