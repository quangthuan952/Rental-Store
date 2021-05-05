package controller.returnproduct;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import data.Data;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bill;

import java.net.URL;
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
    ObservableList<Bill> billList;
    Data data = new Data();
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
        rentalFreeCol.setCellValueFactory(new PropertyValueFactory<Bill, Float>("hireCharge"));
        ReturnTable.setItems(billList);
    }
}
