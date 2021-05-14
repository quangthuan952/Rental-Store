package controller.comic;


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
import model.Comic;
import model.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ComicController implements Initializable {
    @FXML
    private TableView<Comic> ComicTable;
    @FXML
    private TableColumn<Comic, String> IDCol;

    @FXML
    private TableColumn<Comic, String> NameCol;

    @FXML
    private TableColumn<Comic, String> AuthorCol;

    @FXML
    private TableColumn<Comic, String> CategoryCol;

    @FXML
    private TableColumn<Comic, Integer> PageNumberCol;

    @FXML
    private TableColumn<Comic, Float> PriceCol;

    @FXML
    private TableColumn<Comic, String> LanguageCol;

    @FXML
    private TableColumn<Comic, String> PaperSizeCol;

    @FXML
    private TableColumn<Comic, String> YearOfPublicationCol;
    @FXML
    private javafx.scene.control.ComboBox<String> ComboBox;
    @FXML
    private Button refesh;
    @FXML
    private TextField tfSearch;

    ObservableList<String> listComboBoxSearchBy;
    ObservableList<Comic> comicList;
    Comic c = new Comic();
    Data getComicData = new Data();
    Comic comic = new Comic();
    public static String ID;
    public static String name;
    public static String author;
    public static float price;
    public static String category;
    public static String paperSize;
    public static int pageNumber;
    public static String language;
    public static String year;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listComboBoxSearchBy = FXCollections.observableArrayList("By Name", "By Author", "By Category");
        ComboBox.setItems(listComboBoxSearchBy);
        comicList = getComicData.getDataComic();
        updateTable(comicList);
        handle();
    }

    public void handle() {
        ComicTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                comic = ComicTable.getSelectionModel().getSelectedItem();
                ID = comic.getId();
                name = comic.getName();
                author = comic.getAuthor();
                price = comic.getPrice();
                category = comic.getCategory();
                paperSize = comic.getPaperSize();
                pageNumber = comic.getPageNumber();
                language = comic.getLanguage();
                year = comic.getYearOfPublication();
                detail();
            }
        });
    }

    public void updateTable(ObservableList<Comic> comicList) {
        //comicList = FXCollections.observableArrayList();
        IDCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("Name"));
        AuthorCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("Author"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("Category"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<Comic, Float>("Price"));
        PageNumberCol.setCellValueFactory(new PropertyValueFactory<Comic, Integer>("PageNumber"));
        PaperSizeCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("PaperSize"));
        LanguageCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("Language"));
        YearOfPublicationCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("YearOfPublication"));
        ComicTable.setItems(comicList);
    }

    public void add(ActionEvent actionEvent) {

        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/comic/AddComic.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Comic");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detail() {

        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/comic/DetailComic.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Detail Comic");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit() {
        Comic comic1 = ComicTable.getSelectionModel().getSelectedItem();
        ID = comic1.getId();
        name = comic1.getName();
        category = comic1.getCategory();
        author = comic1.getAuthor();
        price = comic1.getPrice();
        pageNumber = comic1.getPageNumber();
        paperSize = comic1.getPaperSize();
        language = comic1.getLanguage();
        year = comic1.getYearOfPublication();
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/comic/EditComic.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Edit Comic");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search() {
        String key = tfSearch.getText();
        int crition = -1;
        String output = ComboBox.getSelectionModel().getSelectedItem();
        ObservableList<Comic> comicObservableList = FXCollections.observableArrayList();
        if (output == null || key.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please check again!");
            alert.setContentText("Keyword or search criteria is empty.");
            alert.show();
        }
        else {
            if (output.equals("By Author")) {
                crition = 1;
            } else if (output.equals("By Name")) {
                crition = 0;
            } else if (output.equals("By Category")) {
                crition = 2;
            }
            List<Product> l = c.searchProduct(key, crition);
            for (int i = 0; i < l.size(); i++) {
                comicObservableList.add((Comic) l.get(i));
            }
            updateTable(comicObservableList);
        }
    }

    public void refesh() {
        try {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/comic/Comic.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setTitle("Comic");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) refesh.getScene().getWindow();
        stage.close();
    }

    public void delete() {
        Comic comic1 = ComicTable.getSelectionModel().getSelectedItem();
        c.deleteProduct(comic1);
        // System.out.println(comic1.toString());

    }

}
