package controller.compactdisc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.CompactDisc;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCDController implements Initializable {
    @FXML
    private JFXTextField tfName;

    @FXML
    private JFXTextField tfAuthor;

    @FXML
    private JFXTextField tfCategory;

    @FXML
    private JFXTextField tfTime;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXTextField tfCapacity;

    @FXML
    private JFXTextField tfResolution;

    @FXML
    private JFXDatePicker pDate;
    @FXML
    private JFXTextField tfProductCode;
    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;

    CompactDisc compactDisc = new CompactDisc();
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static boolean check = false;
    public void addCompactDisc() {
        String ID = tfProductCode.getText();
        String name = tfName.getText();
        String author = tfAuthor.getText();
        String category = tfCategory.getText();
        float price = Float.parseFloat(tfPrice.getText());
        String year = pDate.getValue().toString();
        double capacity = Double.parseDouble(tfCapacity.getText());
        String resolution = tfResolution.getText();
        String time = tfTime.getText();
        CompactDisc cd = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
        compactDisc.addProduct(cd);
        check = true;
    }
}
