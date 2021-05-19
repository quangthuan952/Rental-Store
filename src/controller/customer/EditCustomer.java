package controller.customer;
/*
 * author: Hoàng Quang Thuận
 * */

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Customer;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditCustomer implements Initializable {
    CustomerController c = new CustomerController();
    Customer customer = new Customer();
    @FXML
    private TableView<Customer> CustomerTable;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> customerPhoneCol;
    @FXML
    private JFXTextField tfNameCustomer;
    @FXML
    private JFXTextField tfPhoneCustomer;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfNameCustomer.setText(CustomerController.name);
        tfPhoneCustomer.setText(CustomerController.phone);
    }

    // chỉnh sửa thông tin khách hàng
    public void editCustomer() {
        String name = tfNameCustomer.getText();
        String phone = tfPhoneCustomer.getText();
        if (name.isEmpty() || phone.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        } else {
            String nameCustomer = CustomerController.name;
            String phoneCustomer = CustomerController.phone;
            Customer customer1 = new Customer(name, phone);
            customer.editCustomer(customer1, nameCustomer, phoneCustomer);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Customer edited successfully!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                cancel();
            }
        }
    }

    public void cancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
