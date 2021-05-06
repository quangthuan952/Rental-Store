package controller.rental;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailRentalController implements Initializable {
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
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RentalController rentalController = new RentalController();
        lbCodeOrder.setText(rentalController.codeOrder);
        lbNameCustomer.setText(rentalController.nameCustomer);
        lbPhoneCustomer.setText(rentalController.phoneCustomer);
        lbItem.setText(rentalController.item);
        lbItemID.setText(rentalController.itemID);
        lbDeposit.setText(String.valueOf(rentalController.deposit));
        lbRentDate.setText(rentalController.rentDate);

    }
    @FXML
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}
