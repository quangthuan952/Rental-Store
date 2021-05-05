package controller.rental;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EditRentalController implements Initializable {
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
    private JFXRadioButton rdCD;
    Bill bill = new Bill();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RentalController rentalController = new RentalController();
        tfCodeOrder.setText(rentalController.codeOrder);
        tfCodeOrder.setEditable(false);
        tfNameCustomer.setText(rentalController.nameCustomer);
        tfPhone.setText(rentalController.phoneCustomer);
        tfItem.setText(rentalController.item);
        tfItemID.setText(rentalController.itemID);
        tfDeposit.setText(String.valueOf(rentalController.deposit));
        pRentDateEdit.setPromptText(rentalController.rentDate);
        if(rentalController.loai.equals("Comic")) {
            rdComic.setSelected(true);
        }
        else
            rdCD.setSelected(true);
    }

    public void editRT() {
        String codeOrder;
        String nameCustomer;
        String phoneCustomer;
        String item;
        String itemID;
        String rentDate;
        String kindOfProduct;
        float deposit;
        codeOrder = tfCodeOrder.getText();
        nameCustomer = tfNameCustomer.getText();
        phoneCustomer = tfPhone.getText();
        item = tfItem.getText();
        itemID = tfItemID.getText();
        deposit = Float.parseFloat(tfDeposit.getText());
        if(pRentDateEdit.getValue() == null)
            rentDate = pRentDateEdit.getPromptText();
        else
            rentDate = pRentDateEdit.getValue().toString();
        if(rdComic.isSelected()) {
            kindOfProduct = rdComic.getText();
        }
        else {
            kindOfProduct = rdCD.getText();
        }
        Customer customer = new Customer(nameCustomer, phoneCustomer);
        Bill b;
        if(rdComic.isSelected()) {
            Product product = new Comic(item, itemID);
             b = new Bill(codeOrder, kindOfProduct,product, rentDate, deposit, customer);
        }
        else {
            Product product = new CompactDisc(item, itemID);
            b = new Bill(codeOrder, kindOfProduct,product, rentDate, deposit, customer);
        }
        bill.editRental(b);
    }
}
