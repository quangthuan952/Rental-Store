package controller.compactdisc;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.CompactDisc;

import java.net.URL;
import java.util.Optional;
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
    private Button btnCancel;

    CompactDisc compactDisc = new CompactDisc();
    Alert alert;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addCompactDisc() {
        String ID = tfProductCode.getText();
        String name = tfName.getText();
        String author = tfAuthor.getText();
        String category = tfCategory.getText();
        String resolution = tfResolution.getText();
        String time = tfTime.getText();
        if (ID.isEmpty() || name.isEmpty() || author.isEmpty() || category.isEmpty() || tfPrice.getText().trim().isEmpty()
                || pDate.getValue() == null || tfCapacity.getText().trim().isEmpty() || resolution.isEmpty() || time.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        } else {
            float price = Float.parseFloat(tfPrice.getText());
            double capacity = Double.parseDouble(tfCapacity.getText());
            String year = pDate.getValue().toString();
            CompactDisc cd = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
            compactDisc.addProduct(cd);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Comic edit successfully!");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                cancel();
            }
        }
    }

    public void cancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
