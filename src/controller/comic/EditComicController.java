package controller.comic;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Comic;

import java.net.URL;
import java.util.Optional;
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
    private JFXComboBox<String> cbPaperSizeEdit;
    @FXML
    private JFXTextField tfProductCode;

    @FXML
    private JFXTextField tfLanguage;
    @FXML
    private JFXDatePicker pDateEdit;
    @FXML
    private Button btnExit;

    ObservableList<String> listPaperSize;
    Comic c = new Comic();
    ComicController comicController = new ComicController();
    Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listPaperSize = FXCollections.observableArrayList("A3", "A4", "A5");
        cbPaperSizeEdit.setItems(listPaperSize);
        tfProductCode.setText(comicController.ID);
        tfProductCode.setEditable(false);
        tfName.setText(comicController.name);
        tfAuthor.setText(comicController.author);
        tfPrice.setText(String.valueOf(comicController.price));
        tfCategory.setText(comicController.category);
        tfPageNumber.setText(String.valueOf(comicController.pageNumber));
        tfLanguage.setText(comicController.language);
        pDateEdit.setPromptText(comicController.year);
        if ((comicController.paperSize).equals("A3")) {
            cbPaperSizeEdit.setPromptText("A3");
        }
        if ((comicController.paperSize).equals("A4")) {
            cbPaperSizeEdit.setPromptText("A4");
        } else {
            cbPaperSizeEdit.setPromptText("A5");
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
        language = tfLanguage.getText();
        if (pDateEdit.getValue() == null)
            year = pDateEdit.getPromptText();
        else
            year = pDateEdit.getValue().toString();
        if (cbPaperSizeEdit.getSelectionModel().isEmpty()) {
            paperSize = cbPaperSizeEdit.getPromptText();
        } else
            paperSize = cbPaperSizeEdit.getSelectionModel().getSelectedItem();
        category = tfCategory.getText();
        if (tfProductCode.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfAuthor.getText().trim().isEmpty() || tfPageNumber.getText().trim().isEmpty() || tfLanguage.getText().trim().isEmpty()
                || tfPrice.getText().trim().isEmpty() || tfCategory.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        } else {
            price = Float.parseFloat(tfPrice.getText());
            pageNumber = Integer.parseInt(tfPageNumber.getText());
            Comic comic = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
            c.editProduct(comic);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Comic edited successfully!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                handleExit();
            }
        }
    }

    @FXML
    public void handleExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
