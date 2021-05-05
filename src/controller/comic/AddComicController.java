package controller.comic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Comic;

import java.net.URL;
import java.util.ResourceBundle;

public class AddComicController implements Initializable {
    @FXML
    private JFXTextField tfName;

    @FXML
    private JFXTextField tfAuthor;

    @FXML
    private JFXTextField tfCategory;

    @FXML
    private JFXTextField tfPageNumber;
    @FXML
    private JFXTextField tfProductCode;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXComboBox<String> cbPaperSize;

    @FXML
    private JFXTextField tfLanguage;

    @FXML
    private JFXDatePicker pDate;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;
    ObservableList<String> listPaperSize ;
    Comic c = new Comic();
    public void initialize(URL location, ResourceBundle resources) {
        listPaperSize = FXCollections.observableArrayList("A3","A4","A5");
        cbPaperSize.setItems(listPaperSize);
    }
    public void addComic() {
        String ID = tfProductCode.getText();
        String name = tfName.getText();
        String author = tfAuthor.getText();
        String category = tfCategory.getText();
        int pageNumber = Integer.parseInt(tfPageNumber.getText());
        float price = Float.parseFloat(tfPrice.getText());
        String paperSize = cbPaperSize.getSelectionModel().getSelectedItem();
        String language = tfLanguage.getText();
        String year = pDate.getValue().toString();
        String productCode = tfProductCode.getText();
        //name, author, year, category, price, pageNumber, paperSize, language
        //Comic comic = new Comic(name, author, year, category, price, pageNumber, paperSize, language);
        Comic comic = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
        c.addProduct(comic);
    }
}
