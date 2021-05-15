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

import java.io.BufferedReader;
import java.io.FileReader;
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
        if (checkData()) {
            try {
                String ID = tfProductCode.getText();
                String name = tfName.getText();
                String author = tfAuthor.getText();
                String category = tfCategory.getText();
                String resolution = tfResolution.getText();
                String time = tfTime.getText();
                float price = Float.parseFloat(tfPrice.getText());
                double capacity = Double.parseDouble(tfCapacity.getText());
                String year = pDate.getValue().toString();
                CompactDisc cd = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
                compactDisc.addProduct(cd);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Add Compact Disc successfully!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    cancel();
                }
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please check again!");
                alert.setContentText("Wrong data format.");
                alert.show();
            }
        }
    }

    public boolean checkData() {
        if (tfProductCode.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfAuthor.getText().trim().isEmpty()
                || tfPrice.getText().trim().isEmpty() || pDate.getValue() == null || tfCategory.getText().trim().isEmpty()
                || tfCapacity.getText().trim().isEmpty() || tfResolution.getText().trim().isEmpty() || tfTime.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
            return false;
        }
        String dir = System.getProperty("user.dir");
        try {
            FileReader fr = new FileReader(dir + "\\src\\data\\CompactDiscData.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String ID = txt[0];
                if(tfProductCode.getText().equalsIgnoreCase(ID)) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please check again!");
                    alert.setContentText("ID already exists. Please use another ID.");
                    alert.show();
                    return false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void cancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
