package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartingTab extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/icon.png")));
            primaryStage.setTitle("Bach Khoa Rental Store");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}