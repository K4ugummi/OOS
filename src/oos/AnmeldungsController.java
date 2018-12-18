package oos;

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

public class AnmeldungsController extends Application {
    private boolean hasErrors = false;
    private String hintString = "";

    @FXML private Button btAusfuehren;
    @FXML private Label lbNeuAnmeldung;
    @FXML private Label lbUserID;
    @FXML private Label lbPasswort;
    @FXML private Label lbWiederholung;
    @FXML private Label lbHint;
    @FXML private TextField tfUserID;
    @FXML private PasswordField pfPasswort;
    @FXML private PasswordField pfWiederholung;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("anmeldung.fxml"));
        //loader.get
        primaryStage.setTitle("AnmeldungsController");
        primaryStage.setScene(new Scene(loader, 430, 300));
        primaryStage.show();
    }
    
    @FXML
    private void onBtAusfuehrenClick() {
        if (this.checkOkay()) {
            String userID = tfUserID.getText();
            String passWort = pfPasswort.getText();
            
            Benutzer benutzer = new Benutzer(userID, passWort.toCharArray());
            System.out.println(benutzer.toString());
        }
    }
    
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
        }
        else {
            return true;
        }
    }
    
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
    
    private void checkEmptyID() {
        if (this.tfUserID.getText().length() == 0) {
            this.hasErrors = true;
            this.hintString += " empty ID;";
            tfUserID.setStyle("-fx-border-color: red;");
        }
    }
    
    private void checkEmptyPasswort() {
        if (this.tfUserID.getText().length() == 0) {
            this.hasErrors = true;
            this.hintString += " empty Passwort;";
            pfPasswort.setStyle("-fx-border-color: red;");
            
        }
    }
    
    private void checkEmptyWiederholung() {
        if (this.tfUserID.getText().length() == 0) {
            this.hasErrors = true;
            this.hintString += " empty Wiederholung;";
            pfWiederholung.setStyle("-fx-border-color: red;");
        }
    }
    
    private void checkPasswortMatch() {
        if (!this.pfPasswort.getText().equals(this.pfWiederholung.getText())) {
            this.hasErrors = true;
            this.hintString += " Passwort != Wiederholung;";
            this.lbHint.setVisible(true);
            pfPasswort.setStyle("-fx-text-fill: red;");
            pfWiederholung.setStyle("-fx-text-fill: red;");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
