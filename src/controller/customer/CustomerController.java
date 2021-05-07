package controller.customer;

import data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Comic;
import model.Customer;
import model.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private TextField tfSearch;

    @FXML
    private ComboBox<String> cbSearchBy;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, String> customerPhoneCol;

    @FXML
    private MenuItem ctxDelete;
    ObservableList<Customer> list;
    ObservableList<String> searchByList;
    Data data = new Data();
    Customer customer = new Customer();
    public static String name;
    public static String phone;
    @FXML
    private MenuItem ctxEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchByList = FXCollections.observableArrayList("By Name", "By Phone");
        cbSearchBy.setItems(searchByList);
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        list = data.getDataCustomer();
        CustomerTable.setItems(list);
    }

    public void updateTable(ObservableList<Customer> list) {
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        CustomerTable.setItems(list);
    }

    public void search() {
        String key = tfSearch.getText();
        if (key == null) {
            list = data.getDataCustomer();
            updateTable(list);
        }
        int crition = -1;
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        String output = cbSearchBy.getSelectionModel().getSelectedItem();
        if (output == null) {
            System.out.println("NULL");
        } else {
            if (output.equals("By Name")) {
                crition = 0;
            } else if (output.equals("By Phone")) {
                crition = 1;
            }
            Customer c = new Customer();
            Customer customer = c.searchCustomer(key, crition);
            customerObservableList.add((customer));
            updateTable(customerObservableList);
        }
    }

    public void edit() {
        customer = CustomerTable.getSelectionModel().getSelectedItem();
        name = customer.getName();
        phone = customer.getPhone();
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/customer/EditCustomer.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
