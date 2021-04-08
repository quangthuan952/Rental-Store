package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Comic;

import java.sql.*;

public class DBConnect {


    Statement statement = null;
    public Connection connectToDB() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName = RentalStore; user=sa; password=123");
            System.out.println("Succesful !");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
      return con;
    }
    public boolean insertData(String sql) {
        try {

            Connection con = this.connectToDB();
            statement = con.createStatement();
            statement.execute(sql);
            System.out.println("OK");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ObservableList<Comic> getDataComics() {
        Connection con = connectToDB();
        ObservableList<Comic> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = con.prepareStatement("select * from Comic");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println("OK la");
                Comic comic = new Comic(rs.getString("Name"), rs.getString("Author"), rs.getDate("YearOfPublication"), rs.getString("Category"), rs.getFloat("Price"),rs.getInt("PaperNumber"), rs.getString("PaperSize"), rs.getString("Language"));
                list.add(comic);
                System.out.println(comic.getPageNumber());
            }
        }

        catch (Exception e) {

        }
        return list;
    }
}
