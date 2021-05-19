package controller.rental;
/*
 * author: Hoàng Quang Thuận
 * */

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditRentalController implements Initializable {
    Bill bill = new Bill();
    RentalController rentalController = new RentalController();
    Alert alert;
    @FXML
    private JFXTextField tfCodeOrder;
    @FXML
    private JFXTextField tfNameCustomer;
    @FXML
    private JFXTextField tfItem;
    @FXML
    private JFXTextField tfItemID;
    @FXML
    private JFXTextField tfDeposit;
    @FXML
    private JFXDatePicker pRentDateEdit;
    @FXML
    private JFXTextField tfPhone;
    @FXML
    private JFXRadioButton rdComic;
    @FXML
    private ToggleGroup item;
    @FXML
    private Button btnExit;
    @FXML
    private JFXRadioButton rdCD;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCodeOrder.setText(RentalController.codeOrder);
        tfCodeOrder.setEditable(false);
        tfNameCustomer.setText(RentalController.nameCustomer);
        tfPhone.setText(RentalController.phoneCustomer);
        tfItem.setText(RentalController.item);
        tfItemID.setText(RentalController.itemID);
        tfDeposit.setText(String.valueOf(RentalController.deposit));
        pRentDateEdit.setPromptText(RentalController.rentDate);
        if (RentalController.loai.equals("Comic")) {
            rdComic.setSelected(true);
        } else
            rdCD.setSelected(true);
    }

    // chỉnh sửa thông tin đơn hàng
    public void editRental() {
        String codeOrder;
        String nameCustomer;
        String phoneCustomer;
        String item;
        String itemID;
        String rentDate;
        String kindOfProduct = "";
        String name = RentalController.nameCustomer;
        String phone = RentalController.phoneCustomer;
        float deposit;
        codeOrder = tfCodeOrder.getText();
        nameCustomer = tfNameCustomer.getText();
        phoneCustomer = tfPhone.getText();
        item = tfItem.getText();
        itemID = tfItemID.getText();
        deposit = Float.parseFloat(tfDeposit.getText());
        if (pRentDateEdit.getValue() == null)
            rentDate = pRentDateEdit.getPromptText();
        else
            rentDate = pRentDateEdit.getValue().toString();
        if (rdComic.isSelected()) {
            kindOfProduct = rdComic.getText();
        } else if (rdCD.isSelected()) {
            kindOfProduct = rdCD.getText();
        }
        if (itemID.isEmpty() || codeOrder.isEmpty() || nameCustomer.isEmpty() || phoneCustomer.isEmpty() || kindOfProduct.isEmpty()
                || item.isEmpty() || tfDeposit.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        } else {
            Customer customer = new Customer(nameCustomer, phoneCustomer);
            Bill b;
            if (rdComic.isSelected()) {
                Product product = new Comic(item, itemID);
                b = new Bill(codeOrder, kindOfProduct, product, rentDate, deposit, customer);
            } else {
                Product product = new CompactDisc(item, itemID);
                b = new Bill(codeOrder, kindOfProduct, product, rentDate, deposit, customer);
            }
            bill.editBill(b, name, phone);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Update successfully!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cancel();
            }
        }
    }

    public void cancel() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
