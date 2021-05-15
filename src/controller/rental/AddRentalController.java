package controller.rental;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Optional;
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
    Alert alert;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void add(ActionEvent actionEvent) {
        String ID = tfID.getText();
        String codeOrder = tfCodeOrder.getText();
        String nameCustomer = tfNameCustomer.getText();
        String phoneCustomer = tfPhoneCustomer.getText();
        String kindOfProduct = "";
        if(rdComic.isSelected()) {
            kindOfProduct = rdComic.getText();
        }
        else if (rdCD.isSelected()){
            kindOfProduct = rdCD.getText();
        }
        String item = tfItem.getText();
        Customer customer = new Customer(nameCustomer, phoneCustomer);
        Product product;
        if(ID.isEmpty() || codeOrder.isEmpty() || nameCustomer.isEmpty() || phoneCustomer.isEmpty() || kindOfProduct.isEmpty()
        || item.isEmpty() || tfDeposit.getText().trim().isEmpty() || pRentDate.getValue() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        }


        if (checkProduct()) {
            float deposit = Float.parseFloat(tfDeposit.getText());
            String rentDate = pRentDate.getValue().toString();
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
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Successfully added to database!");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                cancel();
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Product not exist");
            alert.show();
        }
    }
    public boolean checkProduct(){
        boolean status=false;
        String dir = System.getProperty("user.dir");
        if(rdComic.isSelected()){
            try {
                FileReader fr = new FileReader(dir + "\\src\\data\\ComicData.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    String txt[] = line.split(";");
                    String ID = txt[0];
                    String name = txt[1];
                    if(tfID.getText().equalsIgnoreCase(ID) && tfItem.getText().equalsIgnoreCase(name)) {
                        status=true;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(rdCD.isSelected()){
            try {
                FileReader fr = new FileReader(dir + "\\src\\data\\CompactDiscData.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    String txt[] = line.split(";");
                    String ID = txt[0];
                    String name = txt[1];
                    if(tfID.getText().equalsIgnoreCase(ID) && tfItem.getText().equalsIgnoreCase(name)) {
                        status=true;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }
    public void cancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
