package controller.returnproduct;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import data.Data;
import javafx.beans.property.SimpleObjectProperty;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ReturnProductController implements Initializable {
    @FXML
    private JFXTextField tfCodeOrder;

    @FXML
    private JFXDatePicker pReturnDate;

    @FXML
    private TableView<Bill> ReturnTable;

    @FXML
    private TableColumn<Bill, String> codeOrderCol;

    @FXML
    private TableColumn<Bill, String> rentDateCol;

    @FXML
    private TableColumn<Bill, String> returnDateCol;

    @FXML
    private TableColumn<Bill, Float> depositCol;
    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<Bill, Float> rentalFreeCol;
    @FXML
    private TableColumn<Bill, Float> paymentCol;
    ObservableList<Bill> billList;
    Data data = new Data();
    Bill b = new Bill();
    public  static String codeOrder;
    public static String nameCustomer;
    public static String phoneCustomer;
    public static String itemID;
    public static String item;
    public static String rentDate;
    public static float deposit;
    public static String returnDate;
    public static float rentalFee;
    public static float payment;
    Alert alert;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        billList = data.getDataReturn();
        updateTable(billList);

    }
    public void updateTable(ObservableList<Bill> billList) {
        //comicList = FXCollections.observableArrayList();
        codeOrderCol.setCellValueFactory(new PropertyValueFactory<Bill,String>("codeOrder"));
        rentDateCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("rentDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<Bill, String>("returnDate"));
        depositCol.setCellValueFactory(new PropertyValueFactory<Bill, Float>("deposit"));
        rentalFreeCol.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().hireCharge()));
        paymentCol.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().calculateAmountPay()));
        ReturnTable.setItems(billList);
        handle();
    }
    public void addReturn() {
        String orderID = tfCodeOrder.getText();
        List<Bill> list = b.searchBill(orderID, 0);
        if(orderID.isEmpty() || pReturnDate.getValue() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        }
        else {
            if(list.size() == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please check again!");
                alert.setContentText("Could not find Order ID.");
                alert.show();
            }
            else {
                String returnDate = pReturnDate.getValue().toString();
                list.get(0).setReturnDate(returnDate);
                String itemID = list.get(0).getProduct().getId();
                float priceItem = data.getPriceProduct(itemID);
                list.get(0).getProduct().setPrice(priceItem);
                b.returnProduct(list.get(0));
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Return successfully!");
                alert.setContentText("Amount to be paid is: " +  list.get(0).calculateAmountPay());
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    try {
                        Stage primaryStage = new Stage();
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("/view/return/ReturnProduct.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.setMaximized(true);
                        primaryStage.setTitle("Return Product");
                        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image/add_icon.png")));
                        primaryStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Stage stage = (Stage) btnAdd.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    public void handle() {
        ReturnTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                b = ReturnTable.getSelectionModel().getSelectedItem();
                codeOrder = b.getCodeOrder();
                nameCustomer = b.getCustomer().getName();
                phoneCustomer = b.getCustomer().getPhone();
                rentDate = b.getRentDate();
                deposit = b.getDeposit();
                itemID = b.getProduct().getId();
                item = b.getProduct().getName();
                returnDate = b.getReturnDate();
                rentalFee = b.hireCharge();
                payment = b.calculateAmountPay();
                detail();
            }
        });
    }

    public void detail() {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/return/DetailReturn.fxml"));
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
    public void cancel() {
        tfCodeOrder.setText("");
        pReturnDate.setValue(null);
    }
}
