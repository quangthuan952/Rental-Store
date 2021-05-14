package controller.comic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ComicM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/comic/Comic.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Comic");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void CompactDiscM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/compactdisc/CompactDisc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Compact Disc");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RetalM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/rental/Rental.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Rental");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReturnM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/return/ReturnProduct.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Return Product");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void RevenueM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/revenue/Revenue.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Revenue");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CustomerM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/customer/Customer.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Customer");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
