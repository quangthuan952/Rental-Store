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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Optional;
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
    private Button btnCancel;
    @FXML
    private Button btnAdd;

    ObservableList<String> listPaperSize;
    Comic c = new Comic();
    Alert alert;

    public void initialize(URL location, ResourceBundle resources) {
        listPaperSize = FXCollections.observableArrayList("A3", "A4", "A5");
        cbPaperSize.setItems(listPaperSize);
    }

    public void addComic() {
        if (checkData()) {
            try {
                String ID = tfProductCode.getText();
                String name = tfName.getText();
                String author = tfAuthor.getText();
                String category = tfCategory.getText();
                int pageNumber = Integer.parseInt(tfPageNumber.getText());
                float price = Float.parseFloat(tfPrice.getText());
                String paperSize = cbPaperSize.getSelectionModel().getSelectedItem();
                String language = tfLanguage.getText();
                String year = pDate.getValue().toString();
                Comic comic = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
                c.addProduct(comic);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Comic added successfully!");
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
        if (tfProductCode.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfAuthor.getText().trim().isEmpty() || tfPageNumber.getText().trim().isEmpty() || tfLanguage.getText().trim().isEmpty()
                || tfPrice.getText().trim().isEmpty() || pDate.getValue() == null || cbPaperSize.getSelectionModel().isEmpty() || tfCategory.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
            return false;
        }

        String dir = System.getProperty("user.dir");
        try {
            FileReader fr = new FileReader(dir + "\\src\\data\\ComicData.txt");
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
