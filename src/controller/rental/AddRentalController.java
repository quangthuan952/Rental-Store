package controller.rental;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRentalController implements Initializable {
    @FXML
    private JFXTextField tfCodeOrder;

    @FXML
    private JFXTextField tfNameCustomer;

    @FXML
    private JFXTextField tfPhoneCustomer;

    @FXML
    private JFXTextField tfItem;

    @FXML
    private JFXTextField tfDeposit;

    @FXML
    private JFXRadioButton rdComic;

    @FXML
    private ToggleGroup item;

    @FXML
    private JFXRadioButton rdCD;
    @FXML
    private JFXTextField tfID;
    @FXML
    private Button btnCancel;

    @FXML
    private JFXDatePicker pRentDate;
    Bill bill = new Bill();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void add(ActionEvent actionEvent) {
        String ID = tfID.getText();
        String codeOrder = tfCodeOrder.getText();
        String nameCustomer = tfNameCustomer.getText();
        String phoneCustomer = tfPhoneCustomer.getText();
        String kindOfProduct;
        if(rdComic.isSelected()) {
            kindOfProduct = rdComic.getText();
        }
        else {
            kindOfProduct = rdCD.getText();
        }

        String item = tfItem.getText();
        float deposit = Float.parseFloat(tfDeposit.getText());
        String rentDate = pRentDate.getValue().toString();
        Customer customer = new Customer(nameCustomer, phoneCustomer);
        Product product;
        if(kindOfProduct.equals("Comic")) {
            product = new Comic(item, ID);
            Bill b = new Bill(codeOrder, kindOfProduct, product, rentDate, deposit, customer);
            bill.addBill(b);
        }
        else {
            product = new CompactDisc(item, ID);
            Bill b = new Bill(codeOrder, kindOfProduct, product, rentDate, deposit, customer);
            bill.addBill(b);
        }
    }
    public void cancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
