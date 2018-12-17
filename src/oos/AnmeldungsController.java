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
        primaryStage.setTitle("AnwendungsController");
        primaryStage.setScene(new Scene(loader, 430, 300));
        primaryStage.show();
    }
    
    @FXML
    private void onBtAusfuehrenClick() {
        System.out.println("AUSFUEHREN");
        String userID = tfUserID.getText();
        String passWort = pfPasswort.getText();
        String wiederholung = pfWiederholung.getText();
        
        if (passWort.equals(wiederholung)) {
            this.lbHint.setVisible(false);
            pfPasswort.setStyle("-fx-text-fill: black;");
            pfWiederholung.setStyle("-fx-text-fill: black;");
            
            Benutzer benutzer = new Benutzer(userID, passWort.toCharArray());
            System.out.println(benutzer.toString());
        }
        else {
            this.lbHint.setVisible(true);
            pfPasswort.setStyle("-fx-text-fill: red;");
            pfWiederholung.setStyle("-fx-text-fill: red;");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
