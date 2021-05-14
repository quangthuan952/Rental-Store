package controller.compactdisc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailCDController implements Initializable {
    @FXML
    private Label lbName;

    @FXML
    private Label lbAuthor;

    @FXML
    private Label lbCategory;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbTime;

    @FXML
    private Label lbCapacity;

    @FXML
    private Label lbResolution;

    @FXML
    private Label lbYearOfPublication;
    @FXML
    private Button btnExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CDController cdController = new CDController();
        lbName.setText(cdController.name);
        lbAuthor.setText(cdController.author);
        lbPrice.setText(String.valueOf(cdController.price));
        lbCategory.setText(cdController.category);
        lbTime.setText(String.valueOf(cdController.time));
        lbCapacity.setText(String.valueOf(cdController.capacity));
        lbYearOfPublication.setText(String.valueOf(cdController.year));
        lbResolution.setText(cdController.resolution);
    }

    @FXML
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
}
