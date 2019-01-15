package prak4client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * First test of handling JavaFX and SceneBuilder
 * 
 * @author stephan
 *
 */
public class AnwendungsController extends Application {

    private Client client;

    void setClient(Client client) {
        this.client = client;
    }

    /**
     * Initialize and show this form.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader
                .load(getClass().getResource("anwendung.fxml"));
        // loader.get
        primaryStage.setTitle("AnwendungsController");
        primaryStage.setScene(new Scene(loader, 500, 200));
        primaryStage.show();
    }

    /**
     * Executed if button Abbrechen is clicked.
     */
    @FXML
    public void onCancelClick() {
        System.out.println("Closing the application!");
        Platform.exit();
    }

    /**
     * Starts this form.
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
