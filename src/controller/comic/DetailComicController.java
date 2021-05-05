package controller.comic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailComicController implements Initializable {
    @FXML
    private Label lbName;

    @FXML
    private Label lbAuthor;

    @FXML
    private Label lbCategory;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbPageNumber;
    @FXML
    private Label lbPaperSize;

    @FXML
    private Label lbLanguage;

    @FXML
    private Label lbYearOfPublication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComicController comicController = new ComicController();
        lbName.setText(comicController.name);
        lbAuthor.setText(comicController.author);
        lbPrice.setText(String.valueOf(comicController.price));
        lbCategory.setText(comicController.category);
        lbPageNumber.setText(String.valueOf(comicController.pageNumber));
        lbPaperSize.setText(comicController.paperSize);
        lbYearOfPublication.setText(comicController.year);
        lbLanguage.setText(comicController.language);
    }
}
