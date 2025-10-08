package service;

import model.Pet;
import model.Kucing;
import model.Anjing;
import database.KoneksiDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetService implements PetOperations {

    @Override
    public void tambahPet(Pet pet) {
        Connection conn = KoneksiDatabase.getKoneksi();
        PreparedStatement pstmt = null;
        
        try {
            String query = "INSERT INTO pets (nama, jenis, umur, pemilik) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, pet.getNama());
            pstmt.setString(2, pet.getJenis());
            pstmt.setString(3, pet.getUmur());
            pstmt.setString(4, pet.getPemilik());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                // Ambil ID yang baru dibuat
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    pet.setId(rs.getInt(1));
                }
                System.out.println("Pet berhasil disimpan ke database!");
            }
            
        } catch (SQLException e) {
            System.out.println("Error menambah pet: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Pet> getDaftar() {
        List<Pet> petsList = new ArrayList<>();
        Connection conn = KoneksiDatabase.getKoneksi();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM pets";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nama = rs.getString("nama");
                String jenis = rs.getString("jenis");
                String umur = rs.getString("umur");
                String pemilik = rs.getString("pemilik");
                
                Pet pet;
                if (jenis.equals("Kucing")) {
                    pet = new Kucing(id, nama, umur, pemilik);
                } else {
                    pet = new Anjing(id, nama, umur, pemilik);
                }
                petsList.add(pet);
            }
            
        } catch (SQLException e) {
            System.out.println("Error mengambil daftar pet: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return petsList;
    }

    @Override
    public boolean updatePet(Integer id, Pet newPet) {
        Connection conn = KoneksiDatabase.getKoneksi();
        PreparedStatement pstmt = null;
        boolean berhasil = false;
        
        try {
            String query = "UPDATE pets SET nama = ?, umur = ?, pemilik = ? WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, newPet.getNama());
            pstmt.setString(2, newPet.getUmur());
            pstmt.setString(3, newPet.getPemilik());
            pstmt.setInt(4, id);
            
            int result = pstmt.executeUpdate();
            berhasil = (result > 0);
            
        } catch (SQLException e) {
            System.out.println("Error update pet: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return berhasil;
    }

    @Override
    public boolean hapusPet(Integer id) {
        Connection conn = KoneksiDatabase.getKoneksi();
        PreparedStatement pstmt = null;
        boolean berhasil = false;
        
        try {
            String query = "DELETE FROM pets WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            
            int result = pstmt.executeUpdate();
            berhasil = (result > 0);
            
        } catch (SQLException e) {
            System.out.println("Error hapus pet: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return berhasil;
    }

    @Override
    public List<Pet> cariPet(String keyword) {
        List<Pet> petsList = new ArrayList<>();
        Connection conn = KoneksiDatabase.getKoneksi();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM pets WHERE " +
                          "id LIKE ? OR " +
                          "nama LIKE ? OR " +
                          "jenis LIKE ? OR " +
                          "umur LIKE ? OR " +
                          "pemilik LIKE ?";
            
            pstmt = conn.prepareStatement(query);
            String searchKeyword = "%" + keyword + "%";
            
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);
            pstmt.setString(3, searchKeyword);
            pstmt.setString(4, searchKeyword);
            pstmt.setString(5, searchKeyword);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nama = rs.getString("nama");
                String jenis = rs.getString("jenis");
                String umur = rs.getString("umur");
                String pemilik = rs.getString("pemilik");
                
                Pet pet;
                if (jenis.equals("Kucing")) {
                    pet = new Kucing(id, nama, umur, pemilik);
                } else {
                    pet = new Anjing(id, nama, umur, pemilik);
                }
                petsList.add(pet);
            }
            
        } catch (SQLException e) {
            System.out.println("Error mencari pet: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return petsList;
    }

    public List<Pet> cariPet() {
        return getDaftar();
    }
    
    public Pet getPetById(Integer id) {
        Pet pet = null;
        Connection conn = KoneksiDatabase.getKoneksi();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM pets WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String nama = rs.getString("nama");
                String jenis = rs.getString("jenis");
                String umur = rs.getString("umur");
                String pemilik = rs.getString("pemilik");
                
                if (jenis.equals("Kucing")) {
                    pet = new Kucing(id, nama, umur, pemilik);
                } else {
                    pet = new Anjing(id, nama, umur, pemilik);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error mengambil pet: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return pet;
    }
}