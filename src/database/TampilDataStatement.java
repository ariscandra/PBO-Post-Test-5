package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TampilDataStatement {
    
    // Method buat nampilkan pets pakai Statement
    public static void tampilkanSemuaPets() {
        System.out.println("\n=== DATA PETS DARI DATABASE ===");
        System.out.println("ID | Nama | Jenis | Umur | Pemilik");
        System.out.println("--------------------------------------------------------");
        
        Connection conn = KoneksiDatabase.getKoneksi();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            String query = "SELECT * FROM pets";
            rs = stmt.executeQuery(query);
            
            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                System.out.printf("%d | %s | %s | %s Tahun | %s\n",
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("jenis"),
                    rs.getString("umur"),
                    rs.getString("pemilik")
                );
            }
            
            if (!adaData) {
                System.out.println("Belum ada data pets di database, Min!");
            }
            System.out.println("--------------------------------------------------------");
            
        } catch (SQLException e) {
            System.out.println("Error menampilkan data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Method nampilkan pets berdasarkan jenis
    public static void tampilkanPetsBerdasarkanJenis(String jenis) {
        System.out.println("\n=== DATA PETS JENIS: " + jenis.toUpperCase() + " ===");
        System.out.println("ID | Nama | Umur | Pemilik");
        System.out.println("------------------------------------");
        
        Connection conn = KoneksiDatabase.getKoneksi();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            String query = "SELECT * FROM pets WHERE jenis = '" + jenis + "'";
            rs = stmt.executeQuery(query);
            
            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                System.out.printf("%d | %s | %s Tahun | %s\n",
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("umur"),
                    rs.getString("pemilik")
                );
            }
            
            if (!adaData) {
                System.out.println("Tidak ada pets dengan jenis " + jenis + "Min!");
            }
            System.out.println("------------------------------------");
            
        } catch (SQLException e) {
            System.out.println("Error menampilkan data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}