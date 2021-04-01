package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public void connectToDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName = RentalStore; user=sa; password=123");
            System.out.println("Succesful !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
