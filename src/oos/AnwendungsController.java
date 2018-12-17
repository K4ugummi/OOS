package oos;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AnwendungsController extends Application {
      
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("anwendung.fxml"));
        //loader.get
        primaryStage.setTitle("AnwendungsController");
        primaryStage.setScene(new Scene(loader, 500, 200));
        primaryStage.show();
    }
    
    @FXML
    public void onCancelClick() {
        System.out.println("Closing the application!");
        Platform.exit();
    }
}
