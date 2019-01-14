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
    public BenutzerVerwaltung Adm;
    
    private Stage stage;
    
    private LoginController loginController;
    
    private AnmeldungsController anmeldungsController;
    
    private boolean lokal;
    
    public Client() throws ClassNotFoundException, IOException {
        this.Adm = new BenutzerVerwaltungAdmin();
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Erzeugung eines Objekts der Klasse BenutzerVerwaltungAdmin
        this.Adm = new BenutzerVerwaltungAdmin();
        
        //Anfrage an den Benutzer, ob die Datenhaltung initialisiert werden soll
        System.out.println("Soll die Datenhaltung initialisiert werden (0/1)?");
        int dbInitialisieren = -1;
        boolean check = true;
        do {
            try {
                if (check == false) {
                    System.out.println("Ungültige Eingabe!");
                }
                BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
                dbInitialisieren = Integer.parseInt(din.readLine());
                if (dbInitialisieren == 0 || dbInitialisieren == 1) {
                    check = true;
                }
                else {
                    check = false;
                }
            }catch(NumberFormatException | IOException f) {
                f.printStackTrace();
            }
        }while(dbInitialisieren != 0 && dbInitialisieren != 1);
        if (dbInitialisieren == 1) {
            ((BenutzerVerwaltungAdmin) Adm).dbInitialisieren();
            System.out.println("Die Datenhaltung wurde initialisiert!");
        }
        else {
            System.out.println("Die Datenhaltung wurde NICHT initialisiert!");
        }
        
        //Erzeugung einer LoginScene mit Übergabe der eigenen Referenz an deren Controller
        this.stage = primaryStage;
        
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
             Parent root = (Parent)loader.load();

             this.loginController = (LoginController)loader.getController();
             this.loginController.setORB(this);
             
             this.stage.setTitle("Benutzerverwaltung");
             this.stage.setScene(new Scene(root));
             this.stage.show();
          } catch(IOException e) {
                e.printStackTrace();
          }
        
    }
    
    /**
     * 
     */
    void neuAnmeldungLokal() {
        this.lokal = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anmeldung.fxml"));
            Parent root = (Parent) loader.load();
            
            this.anmeldungsController = (AnmeldungsController)loader.getController();
            this.anmeldungsController.setORB(this);
            
            this.stage.setTitle("Neu-Anmeldung");
            this.stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     */
    void neuAnmeldungRemote() {
        this.lokal = false;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anmeldung.fxml"));
            Parent root = (Parent) loader.load();
            
            this.anmeldungsController = (AnmeldungsController)loader.getController();
            this.anmeldungsController.setORB(this);
            
            this.stage.setTitle("Neu-Anmeldung");
            this.stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    void neuerBenutzer(Benutzer benutzer) throws BenutzerEmptyException {
        try {
            if (lokal) {
                Adm = new BenutzerVerwaltungAdmin();
                Adm.benutzerEintragen(benutzer);
            }
            else {
                Adm = new ClientOrb();
                Adm.benutzerEintragen(benutzer);
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = (Parent) loader.load();
                
                this.loginController = (LoginController) loader.getController();
                this.loginController.setORB(this);
                
                this.stage.setTitle("BenutzerVerwaltung");
                this.stage.setScene(new Scene(root));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (BenutzerVergleichException e) {
            this.anmeldungsController.showErrorMessage("Der Benutzer ist bereits vorhanden!");
        }
    }
    
    void benutzerLoginLokal(Benutzer benutzer) {
        try {
            Adm = new BenutzerVerwaltungAdmin();
            if (Adm.benutzerOk(benutzer)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("anwendung.FXML"));
                    Parent root = (Parent) loader.load();
                    
                    this.stage.setTitle("Anwendung");
                    this.stage.setScene(new Scene(root));
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                this.loginController.showErrorMessage("Der Benutzer ist NICHT vorhanden!");
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    void benutzerLoginRemote(Benutzer benutzer) {
        try {
            Adm = new ClientOrb();
            if (Adm.benutzerOk(benutzer)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("anwendung.FXML"));
                    Parent root = (Parent) loader.load();
                    
                    this.stage.setTitle("Anwendung");
                    this.stage.setScene(new Scene(root));
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                this.loginController.showErrorMessage("Der Benutzer ist NICHT vorhanden!");
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}