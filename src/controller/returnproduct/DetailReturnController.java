package controller.returnproduct;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailReturnController implements Initializable {
    @FXML
    private Label lbCodeOrder;

    @FXML
    private Label lbNameCustomer;

    @FXML
    private Label lbPhoneCustomer;

    @FXML
    private Label lbItem;

    @FXML
    private Label lbItemID;

    @FXML
    private Label lbDeposit;

    @FXML
    private Label lbRentDate;

    @FXML
    private Label lbRentalFee;

    @FXML
    private Label lbReturnDate;

    @FXML
    private Label lbPayment;
        ReturnProductController returnProductController = new ReturnProductController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbCodeOrder.setText(returnProductController.codeOrder);
        lbNameCustomer.setText((returnProductController.nameCustomer));
        lbPhoneCustomer.setText(returnProductController.phoneCustomer);
        lbDeposit.setText(String.valueOf(returnProductController.deposit));
        lbRentDate.setText(returnProductController.rentDate);
        lbReturnDate.setText(returnProductController.returnDate);
        lbPayment.setText(String.valueOf(returnProductController.payment));
        lbRentalFee.setText(String.valueOf(returnProductController.rentalFee));
        lbItemID.setText(returnProductController.itemID);
        lbItem.setText(returnProductController.item);
    }
}
