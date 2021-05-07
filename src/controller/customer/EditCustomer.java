package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomer implements Initializable {
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
    CustomerController c = new CustomerController();
    Customer customer = new Customer();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfNameCustomer.setText(c.name);
        tfPhoneCustomer.setText(c.phone);
    }
    public void customerModified() {
        String name = tfNameCustomer.getText();
        String phone = tfPhoneCustomer.getText();
        String nameCustomer = c.name;
        String phoneCustomer = c.phone;
        Customer customer1 = new Customer(name, phone);
        customer.editCustomer(customer1, nameCustomer, phoneCustomer);

    }
    @FXML
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
