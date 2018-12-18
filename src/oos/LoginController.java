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
    
    private boolean neuAnmeldung = false;
    private boolean hasErrors = false;

    @FXML private Button btAusfuehren;
    @FXML private Button btAbbrechen;
    @FXML private CheckBox cbNeuAnmeldung;
    @FXML private Label lbUserID;
    @FXML private Label lbPasswort;
    @FXML private TextField tfUserID;
    @FXML private PasswordField pfPasswort;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("login.fxml"));
        //loader.get
        primaryStage.setTitle("LoginController");
        primaryStage.setScene(new Scene(loader, 350, 310));
        primaryStage.show();
    }
    
    @FXML
    private void onCbNeuAnmeldungClick() {
        this.neuAnmeldung = this.cbNeuAnmeldung.isSelected();
        System.out.println("neuAnmeldung = " + this.neuAnmeldung);
    }
    
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
    
    @FXML
    private void btAbbrechenClick() {
        System.out.println("Abbruch");
        Platform.exit();
    }
    
    private void resetError() {
        this.hasErrors = false;
        tfUserID.setStyle("-fx-border-color: black;");
        pfPasswort.setStyle("-fx-border-color: black;");
    }
    
    private void checkEmptyID() {
        if (this.tfUserID.getText().length() == 0) {
            this.hasErrors = true;
            tfUserID.setStyle("-fx-border-color: red;");
        }
    }
    
    private void checkEmptyPasswort() {
        if (this.pfPasswort.getText().length() == 0) {
            this.hasErrors = true;
            pfPasswort.setStyle("-fx-border-color: red;");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
