package oos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Application {
    
    /**
     * This boolean will be set from the checkbox.
     */
    private boolean neuAnmeldung = false;
    /**
     * This boolean keeps track of input errors.
     */
    private boolean hasErrors = false;
    
    private MainApplication application;

    @FXML private Button btAusfuehren;
    @FXML private Button btAbbrechen;
    @FXML private CheckBox cbNeuAnmeldung;
    @FXML private Label lbUserID;
    @FXML private Label lbPasswort;
    @FXML private TextField tfUserID;
    @FXML private PasswordField pfPasswort;

    /**
     * Start function to initialize and show this form.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("LoginController");
        primaryStage.setScene(new Scene(loader));
        primaryStage.show();
        
    }
    
    void setMainApplication(MainApplication application) { this.application = application; }
    
    /**
     * Called if checkbox 'Neu Anmeldung' is clicked.
     */
    @FXML
    private void onCbNeuAnmeldungClick() {
        this.neuAnmeldung = this.cbNeuAnmeldung.isSelected();
        System.out.println("neuAnmeldung = " + this.neuAnmeldung);
    }
    
    /**
     * Calles if button Ausfuehren is clicked.
     */
    @FXML
    private void btAusfuehrenClick() {
        String userID = this.tfUserID.getText();
        String passWort = this.pfPasswort.getText();
        Benutzer benutzer = new Benutzer(userID, passWort.toCharArray());
        
        this.resetError();
        this.checkEmptyID();
        this.checkEmptyPasswort();
        
        if (!this.hasErrors) {
            System.out.println(benutzer.toString());
            Platform.exit();
        }
    }
    
    /**
     * Called if button Abbrechen is clicked.
     */
    @FXML
    private void btAbbrechenClick() {
        System.out.println("Abbruch");
        Platform.exit();
    }
    
    /**
     * Reset all errors for front- and backend.
     */
    private void resetError() {
        this.hasErrors = false;
        tfUserID.setStyle("-fx-border-color: black;");
        pfPasswort.setStyle("-fx-border-color: black;");
    }
    
    /**
     * Check if UserID field is empty. Sets error to true if it is.
     */
    private void checkEmptyID() {
        if (this.tfUserID.getText().length() == 0) {
            this.hasErrors = true;
            tfUserID.setStyle("-fx-border-color: red;");
        }
    }
    
    /**
     * Check if the Passwort field is empty. Sets error to true if it is.
     */
    private void checkEmptyPasswort() {
        if (this.pfPasswort.getText().length() == 0) {
            this.hasErrors = true;
            pfPasswort.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Start this form.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
