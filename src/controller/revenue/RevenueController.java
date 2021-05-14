package controller.revenue;

import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Bill;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;

public class RevenueController implements Initializable {
    @FXML
    private JFXDatePicker pDateFrom;

    @FXML
    private JFXDatePicker pDateTo;
    @FXML
    private Label lbRenvenueComic;

    @FXML
    private Label lbRenvenueCD;

    @FXML
    private Label lbRevenue;
    @FXML
    private Label lbTotalRevenue;
    @FXML
    private PieChart c1;


    Bill b = new Bill();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Float> list = b.calculateTotalRevenue();
        lbTotalRevenue.setText(String.valueOf(list.get(0)));
        lbRenvenueCD.setText(String.valueOf(list.get(2)));
        lbRenvenueComic.setText(String.valueOf(list.get(1)));
        NumberFormat numberFormat = NumberFormat.getCompactNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        float comicValue = Integer.parseInt(numberFormat.format(list.get(1) / list.get(0) * 100));
        float cdValue = Integer.parseInt(numberFormat.format(list.get(2) / list.get(0) * 100));
        ObservableList<PieChart.Data> pieChart1 = FXCollections.observableArrayList(
                new PieChart.Data("Comic", comicValue),
                new PieChart.Data("CD", cdValue)
        );
        c1.setData(pieChart1);
        pieChart1.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " %")));
    }

    public void caclulate() {

        if (pDateTo.getValue() == null || pDateFrom.getValue() == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Please fill in all the required fields.");
            alert.show();
        } else {
            String from = pDateFrom.getValue().toString();
            String to = pDateTo.getValue().toString();
            lbRevenue.setText(String.valueOf(b.calculateRevenue(from, to)));
        }
    }

    public void cancel() {
        pDateFrom.setValue(null);
        pDateTo.setValue(null);
    }
}
