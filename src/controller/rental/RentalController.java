package controller.rental;
/*
 * author: Hoàng Quang Thuận
 * */

import data.Data;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Bill;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RentalController implements Initializable {
    @FXML
    private TableView<Bill> RentalTable;

    @FXML
    private TableColumn<Bill, String> codeOrderCol;

    @FXML
    private TableColumn<Bill, String> nameCustomerCol;

    @FXML
    private TableColumn<Bill, String> phoneCol;

    @FXML
    private TableColumn<Bill, String> itemCol;

    @FXML
    private TableColumn<Bill, String> rentDateCol;

    @FXML
    private TableColumn<Bill, Float> depositCol;
    @FXML
    private TableColumn<Bill, String> idItemCol;

    @FXML
    private javafx.scene.control.ComboBox<String> ComboBox;
    @FXML
    private Button refesh;
    @FXML
    private TextField tfSearch;
    ObservableList<String> listComboBoxSearchBy;
    ObservableList<Bill> billList;
    Data data = new Data();
    Bill b = new Bill();
    public static String codeOrder;
    public static String nameCustomer;
    public static String phoneCustomer;
    public static String item;
    public static String itemID;
    public static String rentDate;
    public static float deposit;
    public static String loai;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listComboBoxSearchBy = FXCollections.observableArrayList("By Code Order", "By Name", "By Phone", "By Date");
        ComboBox.setItems(listComboBoxSearchBy);
        billList = data.getDataBill();
        updateTable(billList);
    }

    public void updateTable(ObservableList<Bill> billList) {
        codeOrderCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("CodeOrder"));
        nameCustomerCol.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getCustomer().getName()));
        phoneCol.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getCustomer().getPhone()));
        itemCol.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getProduct().getName()));
        idItemCol.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getProduct().getId()));
        rentDateCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("RentDate"));
        depositCol.setCellValueFactory(new PropertyValueFactory<Bill, Float>("Deposit"));
        RentalTable.setItems(billList);
        handle();
    }

    // thêm mới đơn hàng
    public void add() {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/rental/AddRental.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Rental");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/add_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lấy dữ liệu 1 hàng khi nhấn đúp chuột vào hàng đó
    public void handle() {
        RentalTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                b = RentalTable.getSelectionModel().getSelectedItem();
                codeOrder = b.getCodeOrder();
                nameCustomer = b.getCustomer().getName();
                phoneCustomer = b.getCustomer().getPhone();
                rentDate = b.getRentDate();
                deposit = b.getDeposit();
                item = b.getProduct().getName();
                itemID = b.getProduct().getId();
                char tmp = itemID.charAt(1);
                if (tmp == 'C') {
                    loai = "Comic";
                } else {
                    loai = "CD";
                }
                detail();
            }
        });
    }

    // xem thông tin chi tiết 1 đơn hàng
    public void detail() {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/rental/DetailRental.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Detail Order");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/detail_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // chỉnh sửa thông tin 1 đơn hàng
    public void edit() {
        Bill b1 = RentalTable.getSelectionModel().getSelectedItem();
        codeOrder = b1.getCodeOrder();
        nameCustomer = b1.getCustomer().getName();
        phoneCustomer = b1.getCustomer().getPhone();
        rentDate = b1.getRentDate();
        deposit = b1.getDeposit();
        item = b1.getProduct().getName();
        itemID = b1.getProduct().getId();
        int tmp = itemID.indexOf("M");
        if (tmp == 1) {
            loai = "Comic";
        } else {
            loai = "CD";
        }
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/rental/EditRental.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Edit Order");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/edit_icon.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa 1 đơn hàng
    public void delete() {
        Bill bill1 = RentalTable.getSelectionModel().getSelectedItem();
        b.deleteBill(bill1);
        refesh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Deleted successfully!");
        alert.show();
    }

    // tìm kiếm đơn hàng
    public void search() {
        ObservableList<Bill> billObservableList = FXCollections.observableArrayList();
        Data data = new Data();
        String key = tfSearch.getText();
        int criterion = -1;
        String output = ComboBox.getSelectionModel().getSelectedItem();
        if (key.isEmpty() || output == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Keyword or search criteria is empty.");
            alert.show();
        } else {
            if (output.equals("By Code Order")) {
                criterion = 0;
            } else if (output.equals("By Name")) {
                criterion = 1;
            } else if (output.equals("By Phone")) {
                criterion = 2;
            } else if (output.equals("By Date")) {
                criterion = 3;
            }
            List<Bill> l = b.searchBill(key, criterion);
            for (int i = 0; i < l.size(); i++) {
                billObservableList.add(l.get(i));
            }
            updateTable(billObservableList);
        }
    }

    //Refesh lại dữ liệu
    public void refesh() {
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
        Stage stage = (Stage) refesh.getScene().getWindow();
        stage.close();
    }

    // trả đơn hàng
    public void returnProduct() {
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
}
