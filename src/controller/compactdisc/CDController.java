package controller.compactdisc;

import data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CompactDisc;
import model.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CDController implements Initializable {
    @FXML
    private TextField tfSearchCD;

    @FXML
    private ComboBox<String> cbSearchBy;

    @FXML
    private Button refesh;
    @FXML
    private TableColumn<CompactDisc, String> IDCol;

    @FXML
    private TableColumn<CompactDisc, String> nameCol;

    @FXML
    private TableColumn<CompactDisc, String> authorCol;

    @FXML
    private TableColumn<CompactDisc, String> yearOfPublicationCol;

    @FXML
    private TableColumn<CompactDisc, String> categoryCol;

    @FXML
    private TableColumn<CompactDisc, Float> priceCol;

    @FXML
    private TableColumn<CompactDisc, Double> capacityCol;

    @FXML
    private TableColumn<CompactDisc, String> resolutionCol;
    @FXML
    private TableView<CompactDisc> CDTable;
    @FXML
    private TableColumn<CompactDisc, String> timeCol;
    ObservableList<CompactDisc> compactDiscsList;
    ObservableList<String> listSearchBy;
    CompactDisc compactDisc = new CompactDisc();
    public static String ID;
    public static String name;
    public  static String author;
    public static float price;
    public static String category;
    public static String resolution;
    public static double capacity;
    public static String time;
    public static String year;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listSearchBy = FXCollections.observableArrayList("By Name", "By Author", "By Category");
        cbSearchBy.setItems(listSearchBy);
        Data getComicData = new Data();
        compactDiscsList = getComicData.getDataCD();
        updateTable(compactDiscsList);
        handle();
    }
    public void handle() {
        CDTable.setOnMouseClicked( event -> {
            if( event.getClickCount() == 2 ) {
                compactDisc = CDTable.getSelectionModel().getSelectedItem();
                ID = compactDisc.getId();
                name = compactDisc.getName();
                author = compactDisc.getAuthor();
                price = compactDisc.getPrice();
                category = compactDisc.getCategory();
                time = compactDisc.getTime();
                resolution = compactDisc.getResolution();
                capacity = compactDisc.getCapacity();
                year = compactDisc.getYearOfPublication();
                DetailComic();
            }});
    }
    public void DetailComic() {

        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/compactdisc/DetailCD.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Detail Compact Disc");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateTable(ObservableList<CompactDisc> compactDiscsList) {
        //comicList = FXCollections.observableArrayList();
        IDCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("Name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("Author"));
        capacityCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, Double>("Capacity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<CompactDisc,Float>("Price"));
        yearOfPublicationCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("YearOfPublication"));
        timeCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("Time"));
        resolutionCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("Resolution"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<CompactDisc, String>("Category"));
        CDTable.setItems(compactDiscsList);
    }
    public void addCompactDisc(ActionEvent actionEvent) {

        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/compactdisc/AddCompactDisc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Compact Disc");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void search() {
        String key = tfSearchCD.getText();
        int crition = -1;
        String output = cbSearchBy.getSelectionModel().getSelectedItem();
        ObservableList<CompactDisc> compactDiscObservableList = FXCollections.observableArrayList();
        if(output == null || key.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Keyword or search criteria is empty.");
            alert.show();
        }
        else {
            if(output.equals("By Author")) {
                crition = 1;
            }
            else if(output.equals("By Name")) {
                crition = 0;
            }
            else if(output.equals("By Category")) {
                crition = 2;
            }
            List<Product> l = compactDisc.searchProduct(key, crition);
            for (int i = 0; i < l.size(); i++) {
                compactDiscObservableList.add((CompactDisc) l.get(i));
            }
            updateTable(compactDiscObservableList );
        }
    }
    public void delete() {
        CompactDisc cp = CDTable.getSelectionModel().getSelectedItem();
        compactDisc.deleteProduct(cp);
    }

    public void edit() {
        CompactDisc cp = CDTable.getSelectionModel().getSelectedItem();
        ID = cp.getId();
        name = cp.getName();
        category = cp.getCategory();
        author = cp.getAuthor();
        price = cp.getPrice();
        time = cp.getTime();
        capacity = cp.getCapacity();
        resolution = cp.getResolution();
        year = cp.getYearOfPublication();
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/compactdisc/EditCompactDisc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Edit Compact Disc");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refesh() {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/compactdisc/CompactDisc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Compact Disc");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) refesh.getScene().getWindow();
        stage.close();
    }

}
