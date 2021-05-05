package controller.compactdisc;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.comic.ComicController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Comic;
import model.CompactDisc;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCDController implements Initializable {
    @FXML
    private JFXTextField tfName;

    @FXML
    private JFXTextField tfAuthor;

    @FXML
    private JFXTextField tfCategory;

    @FXML
    private JFXTextField tfPrice;
    @FXML
    private JFXTextField tfProductCode;

    @FXML
    private JFXTextField tfTime;

    @FXML
    private JFXTextField tfResolution;

    @FXML
    private JFXDatePicker pDateEdit;

    @FXML
    private JFXTextField tfCapacity;
    CompactDisc compactDisc = new CompactDisc();
    CDController cdController = new CDController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfProductCode.setText(cdController.ID);
        tfProductCode.setEditable(false);
        tfName.setText(cdController.name);
        tfAuthor.setText(cdController.author);
        tfPrice.setText(String.valueOf(cdController.price));
        tfCategory.setText(cdController.category);
        tfTime.setText(cdController.time);
        tfCapacity.setText(String.valueOf(cdController.capacity));
        tfResolution.setText(cdController.resolution);
        pDateEdit.setPromptText(cdController.year);
    }

    public void editCD() {
        String name;
        String ID;
        String author;
        String category;
        float price;
        String time;
        double capacity;
        String resolution;
        String year;
        ID = tfProductCode.getText();
        name = tfName.getText();
        author = tfAuthor.getText();
        category = tfCategory.getText();
        price = Float.parseFloat(tfPrice.getText());
        time = tfTime.getText();
        capacity = Double.parseDouble(tfCapacity.getText());
        resolution = tfResolution.getText();
        if(pDateEdit.getValue() == null)
            year = pDateEdit.getPromptText();
        else
            year = pDateEdit.getValue().toString();
        CompactDisc cp = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
        compactDisc.editProduct(cp);
    }
}
