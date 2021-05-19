package controller;
/*
 * author: Hoàng Quang Thuận + Trịnh Bá Thắng
 * */

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // Mở giao diện quản lý truyện
    public void ComicM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/comic/Comic.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Comic");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/book_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mở giao diện quản lý đĩa phim
    public void CompactDiscM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/compactdisc/CompactDisc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Compact Disc");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/cd_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mở giao diện quản lý đơn hàng
    public void RetalM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/rental/Rental.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Rental");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/rental_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mở giao diện quản lý đơn trả hàng
    public void ReturnM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/return/ReturnProduct.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Return Product");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/return_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mở giao diện quản lý doanh thu
    public void RevenueM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/revenue/Revenue.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Revenue");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/revenue_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mở giao diện quản lý khách hàng
    public void CustomerM(ActionEvent actionEvent) {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/customer/Customer.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Customer");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/customer_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
