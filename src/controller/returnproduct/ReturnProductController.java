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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bill;

import java.net.URL;
import java.util.List;
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
        String returnDate = pReturnDate.getValue().toString();
        list.get(0).setReturnDate(returnDate);
        String itemID = list.get(0).getProduct().getId();
        float priceItem = data.getPriceProduct(itemID);
        list.get(0).getProduct().setPrice(priceItem);
        b.returnProduct(list.get(0));
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
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
