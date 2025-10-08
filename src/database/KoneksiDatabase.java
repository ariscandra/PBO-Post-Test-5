package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/petopia_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi database berhasil, min!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan, min!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal, min!");
            e.printStackTrace();
        }
        return conn;
    }
}