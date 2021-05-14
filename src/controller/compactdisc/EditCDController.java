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
    private Button btnExit;

    @FXML
    private JFXTextField tfCapacity;
    CompactDisc compactDisc = new CompactDisc();
    CDController cdController = new CDController();
    Alert alert;
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
        time = tfTime.getText();
        resolution = tfResolution.getText();
        if(pDateEdit.getValue() == null)
            year = pDateEdit.getPromptText();
        else
            year = pDateEdit.getValue().toString();
        if(ID.isEmpty() || name.isEmpty() || author.isEmpty() || category.isEmpty() || tfPrice.getText().trim().isEmpty()
                || tfCapacity.getText().trim().isEmpty() || resolution.isEmpty() || time.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            //alert.setContentText("We override the style classes of the dialog");
            alert.show();
        }
        else {
            System.out.println("OK");
            price = Float.parseFloat(tfPrice.getText());
            capacity = Double.parseDouble(tfCapacity.getText());
            CompactDisc cp = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
            compactDisc.editProduct(cp);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Compact Disc edited successfully!");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
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
