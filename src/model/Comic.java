package model;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Comic extends Products implements Initializable {
    private int pageNumber;
    private String paperSize;
    private String language;

    public int getPageNumber() {

        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {

        this.pageNumber = pageNumber;
    }

    public String getPaperSize() {

        return paperSize;
    }

    public void setPaperSize(String paperSize) {

        this.paperSize = paperSize;
    }

    public String getLanguage() {

        return language;
    }

    public void setLanguage(String language) {

        this.language = language;
    }

    // Construct

    public Comic(String name, String author, Date yearOfPublication, String category, float price, int pageNumber, String paperSize, String language) {
        super(name, author, yearOfPublication, category, price);
        this.pageNumber = pageNumber;
        this.paperSize = paperSize;
        this.language = language;
    }

    public Comic(int pageNumber, String paperSize, String language) {
        this.pageNumber = pageNumber;
        this.paperSize = paperSize;
        this.language = language;
    }

    public Comic() {

    }

    // Các thuộc tính của giao diện
    @FXML
    private JFXTextField tfComicName;

    @FXML
    private JFXTextField tfAuthor;

    @FXML
    private JFXTextField tfCategory;

    @FXML
    private JFXTextField tfPageNumber;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXTextField tfLanguage;

    @FXML
    JFXComboBox<String> cbPapersize = new JFXComboBox<String>();
    ComboBox<String> cbSearch = new ComboBox<String>();

    @FXML
    private JFXDatePicker dpYearOfPublication;
    // Xét giá trị cho comboBox
    ObservableList<String> list = FXCollections.observableArrayList("A5", "A4", "A2", "A3");
    ObservableList<String> searchBy = FXCollections.observableArrayList("Name", "Author", "Category");

    // TableComic
    @FXML
    private TableView<Comic> ComicTable;
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
    private TableColumn<Comic, Integer> STTCol;

    @FXML
    private TableColumn<Comic, Date> YearOfPublicationCol;
    ObservableList<Comic> listComic;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbPapersize.getItems().addAll(list);
        cbSearch.getItems().addAll(searchBy);
        //STTCol.setCellValueFactory(new PropertyValueFactory<Comic, Integer>("STT"));
        NameCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("Name"));
        AuthorCol.setCellValueFactory(new PropertyValueFactory<Comic,String>("Author"));
        CategoryCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("Category"));
        PageNumberCol.setCellValueFactory(new PropertyValueFactory<Comic, Integer>("PageNumber"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<Comic,Float>("Price"));
        LanguageCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("language"));
        PaperSizeCol.setCellValueFactory(new PropertyValueFactory<Comic, String>("PaperSize"));
        YearOfPublicationCol.setCellValueFactory(new PropertyValueFactory<Comic,Date>("YearOfPublication"));
        DBConnect dbConnect = new DBConnect();
        listComic = dbConnect.getDataComics();
        ComicTable.setItems(listComic);
    }


    // Update table
    @Override
    public void addProduct() {
        Comic comic = new Comic();
        //Set giá trị thuộc tính cho đối tượng comics từ dữ liệu do người dùng nhập
        comic.setName(tfComicName.getText().toString());
        comic.setAuthor(tfAuthor.getText().toString());
        comic.setCategory(tfCategory.getText().toString());
        comic.setPrice(Float.parseFloat(tfPrice.getText().toString()));
        comic.setPageNumber(Integer.parseInt(tfPageNumber.getText().toString()));
        comic.setLanguage(tfLanguage.getText().toString());
        comic.setPaperSize(cbPapersize.getSelectionModel().getSelectedItem().toString());
        comic.setYearOfPublication(java.sql.Date.valueOf(dpYearOfPublication.getValue()));
        listComic.add(comic);
        // Insert vào Database
        String sql = "insert into comic values(N'" + comic.getName() + "', N'" + comic.getAuthor() + "', N'" + comic.getCategory() + "', " +
                "'" + comic.getPageNumber() + "', '" + comic.getPrice() + "', N'" + comic.getLanguage() + "', N'" + comic.getPaperSize() + "', '" + comic.getYearOfPublication() + "')";
        DBConnect dbConnect = new DBConnect();
        if (dbConnect.insertData(sql)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Insert data in Database successful!");
            alert.show();
            dbConnect.getDataComics();
        }
    }

    public void testButton(ActionEvent actionEvent) {
        try {

            Parent root;
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("/view/ComicList.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(true);
            DBConnect db = new DBConnect();
            db.connectToDB();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
