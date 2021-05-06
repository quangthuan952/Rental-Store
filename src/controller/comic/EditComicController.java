package controller.comic;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Comic;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class EditComicController implements Initializable {
    @FXML
    private JFXTextField tfName;

    @FXML
    private JFXTextField tfAuthor;

    @FXML
    private JFXTextField tfCategory;

    @FXML
    private JFXTextField tfPrice;
    @FXML
    private JFXTextField tfPageNumber;

    @FXML
    private JFXComboBox<String> cbPaperSize;
    @FXML
    private JFXTextField tfProductCode;

    @FXML
    private JFXTextField tfLanguage;
    @FXML
    private JFXDatePicker pDateEdit;
    @FXML
    private Button btnExit;

    ObservableList<String> listPaperSize ;
    Comic c = new Comic();
    ComicController comicController = new ComicController();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listPaperSize = FXCollections.observableArrayList("A3","A4","A5");
        cbPaperSize.setItems(listPaperSize);
        tfProductCode.setText(comicController.ID);
        tfProductCode.setEditable(false);
        tfName.setText(comicController.name);
        tfAuthor.setText(comicController.author);
        tfPrice.setText(String.valueOf(comicController.price));
        tfCategory.setText(comicController.category);
        tfPageNumber.setText(String.valueOf(comicController.pageNumber));
        tfLanguage.setText(comicController.language);
        pDateEdit.setPromptText(comicController.year);
        if((comicController.paperSize).equals("A3")) {
            cbPaperSize.setPromptText("A3");
        }
        if((comicController.paperSize).equals("A4")) {
            cbPaperSize.setPromptText("A4");
        }
        else {
            cbPaperSize.setPromptText("A5");
        }
    }

    public void editComic() {
        String ID;
        String name;
        String author;
        String category;
        float price;
        String paperSize = null;
        int pageNumber;
        String language;
        String year;
        ID = tfProductCode.getText();
        name = tfName.getText();
        author = tfAuthor.getText();
        price = Float.parseFloat(tfPrice.getText());
        pageNumber = Integer.parseInt(tfPageNumber.getText());
        language = tfLanguage.getText();
        if(pDateEdit.getValue() == null)
            year = pDateEdit.getPromptText();
        else
            year = pDateEdit.getValue().toString();
        if(cbPaperSize.getSelectionModel().isEmpty()) {
            paperSize = cbPaperSize.getPromptText();
        }
        else
            paperSize = cbPaperSize.getSelectionModel().getSelectedItem();
        category = tfCategory.getText();
        //String yearOfPublication, String category, float price, int pageNumber, String paperSize, String
        Comic comic = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
        c.editProduct(comic);
    }
    @FXML
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
