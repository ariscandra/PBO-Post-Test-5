<a name="top"></a>
    
# PBO | Post-Test 5 | Manajemen Pets Daycare Breakdown

<div align="center">
  
### **🐾 PROFIL 🐾**

| **Atribut** | **Nilai**          |
|---------------|--------------------|
| **Nama**      | Aris Candra Muzaffar        |
| **NIM**       | 2409116088          |
| **Kelas**     | Sistem Informasi C '24 |
| **Mata Kuliah**| Pemrograman Berorientasi Objek |

![Java](https://img.shields.io/badge/Java-Pemula-orange?style=flat&logo=java)
![OOP](https://img.shields.io/badge/OOP-Pemula-blue?style=flat)
![Pets](https://img.shields.io/badge/Pet_Lover-Banget-green?style=flat)

</div>

## 📚 Daftar Isi
- [👥 Deskripsi Program](#-deskripsi-program)
- [📖 Penjelasan Kode](#-penjelasan-kode)
- [🖥️ Penjelasan Alur Program](#️-penjelasan-alur-program)

## 👥 Deskripsi Program
**Manajemen Pets Daycare**

Program ini adalah aplikasi manajemen penitipan hewan (Pets Daycare) yang dikembangkan dengan bahasa pemrograman Java menggunakan pendekatan Model-View-Controller (MVC) dengan integrasi database MySQL. Berikut ini adalah komponen utamanya:

**1. Fitur Utama**

- **Operasi CRUD dengan Database**: Program mampu melakukan operasi Create, Read, Update, dan Delete data peliharaan yang tersimpan secara permanen di database MySQL
- **Manajemen Data Pets**: Mengelola informasi lengkap tentang hewan yang dititipkan termasuk ID, nama, jenis, umur, dan pemilik dengan persistence data
- **Pencarian Data**: Fitur search yang memungkinkan pencarian berdasarkan ID, nama, jenis, atau nama pemilik (case-insensitive)
- **Akses Data Langsung**: Fitur baru untuk menampilkan data langsung dari database menggunakan Statement JDBC

**2. Arsitektur MVC dengan Database Layer**

- **Model**: Class `Pet` dan turunannya (`Kucing`, `Anjing`) yang merepresentasikan struktur data
- **View**: Class `Main` yang menangani tampilan antarmuka pengguna dan input/output  
- **Controller**: Class `PetService` yang menangani logika bisnis dan operasi CRUD ke database
- **Database Layer**: Package `database` yang menangani koneksi dan operasi database

**3. Struktur Package yang Diperbarui**

- **package main**: Berisi class `Main` dengan menu utama dan navigasi pengguna
- **package service**: Menyimpan interface `PetOperations` dan class `PetService` dengan implementasi JDBC
- **package model**: Menyimpan class `Pet`, `Kucing`, dan `Anjing` dengan tipe data Integer untuk ID
- **package util**: Menyimpan class `ValidasiUtil` dengan fungsi validasi input
- **package database**: **BARU** - Menyimpan class `KoneksiDatabase` dan `TampilDataStatement` untuk operasi database

**4. Validasi Input**

- **hanyaHurufDanSpasi()**: Memvalidasi bahwa input hanya mengandung huruf dan spasi
- **hanyaAngka()**: Memvalidasi bahwa input hanya mengandung angka
- **Batas Panjang Input**: Membatasi panjang input untuk setiap field
- **Rentang Nilai**: Memastikan umur hewan berada dalam rentang yang wajar (0-30 tahun)

**5. Access Modifier & Properties yang Diperbarui**

- Menggunakan access modifier private untuk properti class dengan getter dan setter
- **Perubahan**: Tipe data ID dari `String` menjadi `Integer` untuk menyesuaikan dengan auto-increment database
- Tetap terdapat 5 properties dalam class Pet: id, nama, jenis, umur, dan pemilik
- Constructor diperbarui untuk menerima parameter Integer sebagai ID

**6. Alur Program dengan Integrasi Database**

- Program menampilkan menu utama dengan opsi-opsi CRUD, pencarian, dan **fitur baru akses database**
- Semua operasi CRUD sekarang berinteraksi langsung dengan database MySQL
- Data tersimpan secara permanen dan tidak hilang saat program ditutup
- Fitur baru: "Lihat Data (Statement)" untuk akses langsung ke database

**7. Keamanan Data yang Ditingkatkan**

- **PreparedStatement**: Mencegah SQL injection pada operasi CRUD
- Validasi input tetap dipertahankan untuk memastikan kualitas data
- Penanganan exception untuk operasi database
- Koneksi database yang aman dengan proper resource management

**8. Penerapan JDBC (Java Database Connectivity)**

**Lokasi Implementasi:**
- `KoneksiDatabase.java`: Membuat koneksi ke database MySQL
- `PetService.java`: Menggunakan PreparedStatement untuk operasi CRUD
- `TampilDataStatement.java`: Menggunakan Statement untuk query data

**Fitur JDBC yang Diimplementasi:**
- Connection management untuk koneksi database
- PreparedStatement untuk operasi parameterized query
- Statement untuk operasi query langsung
- ResultSet untuk menangani hasil query
- Auto-generated keys untuk ID otomatis

**9. Penerapan ORM (Object-Relational Mapping)**

**Lokasi Implementasi:**
- `PetService.java` pada method `getDaftar()` dan `cariPet()`

**Cara Kerja ORM:**
- Mapping dari ResultSet database ke Object Pet
- Konversi tipe data kolom database ke properti object
- Setiap row dalam tabel pets di-mapping menjadi instance class Pet, Kucing, atau Anjing
- Maintain relationship antara struktur tabel dan hierarchy class

**Contoh Implementasi ORM:**
```java
// Mapping dari database ke object
Integer id = rs.getInt("id");
String nama = rs.getString("nama");
String jenis = rs.getString("jenis");
// ... kemudian dibuat object Pet sesuai jenisnya
```

**10. Inheritance dan Polimorfisme**

- **Superclass**: `Pet` sebagai abstract class
- **Subclass**: `Kucing` dan `Anjing` sebagai turunan
- **Overriding**: Method `getJenis()` di subclass
- **Abstraksi**: Class `Pet` dengan method abstrak
- **Interface**: `PetOperations` untuk kontrak operasi
- **Overloading**: Method `cariPet()` dengan multiple signatures

**11. Pembaruan Versi Post-Test 5**

- **Integrasi Database MySQL**: Data persistence yang reliable
- **Perubahan Tipe Data ID**: dari String ke Integer untuk compatibility database
- **JDBC Implementation**: Koneksi real-time dengan database
- **ORM Pattern**: Mapping object-relational yang konsisten
- **Fitur Baru**: Akses data langsung via Statement
- **Enhanced Security**: PreparedStatement mencegah SQL injection

**12. Kesimpulan**

Program ini telah berkembang menjadi sistem manajemen data yang robust dengan integrasi database MySQL, menerapkan prinsip-prinsip PBO, arsitektur MVC, JDBC untuk database connectivity, dan ORM untuk mapping data, sekaligus menjaga keamanan data melalui berbagai lapisan validasi dan prepared statement.

### **Letak Penerapan JDBC:**
**Package `database` - File `KoneksiDatabase.java`:**
```java
public class KoneksiDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/petopia_db";
    // ... koneksi menggunakan DriverManager JDBC
}
```
**Package `database` - File `TampilDataStatement.java`:**
- Menggunakan `Statement` untuk menjalankan query SQL
- `ResultSet` untuk menampung hasil query

**Package `service` - File `PetService.java`:**
- Menggunakan `PreparedStatement` untuk operasi CRUD
- `Connection` untuk management koneksi database

### **Letak Penerapan ORM (Object-Relational Mapping):**
**Package `service` - File `PetService.java` (Method `getDaftar()`):**
```java
while (rs.next()) {
    Integer id = rs.getInt("id");
    String nama = rs.getString("nama");
    // Mapping dari ResultSet ke Object Pet
    Pet pet = new Kucing(id, nama, umur, pemilik);
    petsList.add(pet);
}
```
Setiap row dari tabel database di-mapping menjadi object Pet dengan properti yang sesuai

## 📖 Penjelasan Kode

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 1</h3></summary>
  <a href="https://github.com/ariscandra/PBO-Post-Test-1?tab=readme-ov-file#-penjelasan-kode">Lihat disini</a>
</details>

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 2</h3></summary>
  <a href="https://github.com/ariscandra/PBO-Post-Test-2?tab=readme-ov-file#-penjelasan-kode">Lihat disini</a>
</details>

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 3</h3></summary>
  <a href="https://github.com/ariscandra/PBO-Post-Test-3?tab=readme-ov-file#-penjelasan-kode">Lihat disini</a>
</details>

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 4</h3></summary>
  <a href="https://github.com/ariscandra/PBO-Post-Test-4?tab=readme-ov-file#-penjelasan-kode">Lihat disini</a>
</details>

<details>
  <summary><h3>Penjelasan Kode Versi Post-Test 5</h3></summary>

  **1. Integrasi Database MySQL**
```java
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
```

- **Package Baru**: `database` berisi koneksi dan operasi database
- **Koneksi Database**: Class `KoneksiDatabase` menangani koneksi ke MySQL
- **Persistence Data**: Data sekarang tersimpan permanen di database

  **2. PERUBAHAN TIPE DATA ID**
  
- **Dari**: `String id` 
- **Menjadi**: `Integer id`
- **Alasan**: Menyesuaikan dengan auto-increment database

  **Implementasi JDBC**
```java
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
```
```java
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
```

- **PreparedStatement**: Untuk operasi CRUD dengan parameterized query
- **Statement**: Untuk menampilkan data dengan query langsung
- **ResultSet**: Untuk menangani hasil query database

  **4. Penerapan ORM**
  
- **Mapping Object-Relational**: Konversi data tabel menjadi object Java
- **Data Consistency**: Memastikan struktur object sesuai dengan tabel database

  **5. Fitur-Fitur Baru**

- **Direct Database Access**: Menu untuk melihat data langsung dari database
- **Real-time Data**: Data selalu ter-update dengan database

### **Perubahan Struktural:**

**Database:**
- `KoneksiDatabase.java`: Majanemen koneksi database
- `TampilDataStatement.java`: Operasi read dengan Statement

**Service Layer Update:**
- `PetService.java`: Semua operasi sekarang menggunakan database
- Method baru `getPetById()` untuk pencarian yg spesisifik

**Model Layer Update:**
- Semua class model menggunakan `Integer` sebagai ID
- Constructor disesuaikan dengan tipe data baru
</details>

## 🖥️ Penjelasan Alur Program

### Versi Post-Test 1

<details>
<summary><h3>Menu Utama</h3></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/160529b6-3faa-4619-a260-b163aa4f6c1e" alt="" width="500px">
</div>

<p align="justify">Gambar di atas merupakan tampilan menu utama program ketika pertama dijalankan.</p>

**1. Jika input tidak valid**
<div align="center">
  <img src="https://github.com/user-attachments/assets/f3498574-ac7e-4051-9a81-2c6808623bb8" alt="" width="500px">
</div>

<p align="justify">Jika pengguna menginput di luar daripada opsi (0-4) di menu utama, maka akan ada dialog teks seperti pada gambar di atas. Menu akan diulang, pengguna diminta untuk menginput lagi.</p>

**2. Jika opsi 0(Keluar) dipilih**
<div align="center">
  <img src="https://github.com/user-attachments/assets/17ec4145-6c43-4fd0-a33c-2ef6d7d46657" alt="" width="500px">
</div>

<p align="justify">Program akan berhenti berjalan jika pengguna menginput opsi untuk keluar (0). Menu akan berhenti berulang, program selesai.</p>

</details>

<details>
<summary><h3>Menambah Data Pet</h3></summary>

**1. Validasi input dan jika berupa selain huruf dan spasi**
<div align="center">
  <img src="https://github.com/user-attachments/assets/b80c4e30-3f77-4224-8042-927d36d062fb" alt="" width="500px">
</div>

<p align="justify">Jika pengguna mengisi field input nama pet, jenis, dan nama pemilik dengan angka. Maka, akan muncul dialog teks di atas. Pengguna diminta mengulang inputnya.</p>

**2. Validasi input dan jika lebih dari jumlah karakter yang ditentukan**
<div align="center">
  <img src="https://github.com/user-attachments/assets/0e9d08cc-c6d7-4a1b-90a5-4ec632b2fc40" alt="" width="500px">
</div>

<p align="justify">Jika pengguna mengisi field input nama pet dengan karakter lebih dari 30, jenis lebih dari 20, dan nama pemilik lebih dari 40 karakter. Maka, akan muncul dialog teks di atas. Pengguna diminta mengulang inputnya hingga validasi sukses.</p>

**3. Validasi input dan jika umur lebih dari 2 digit atau berupa selain angka**
<div align="center">
  <img src="https://github.com/user-attachments/assets/3042bf6c-fc79-41ad-b6d0-3c27d1fee06a" alt="" width="500px">
</div>

<p align="justify">Jika pengguna menginput lebih dari 3 digit angka atau memasukkan huruf pada field input umur. Maka, akan muncul dialog teks seperti pada gambar di atas.</p>

**4. Validasi input dan jika umur di luar rentang 0-30 tahun**
<div align="center">
  <img src="https://github.com/user-attachments/assets/dcd1329d-d8ca-4a34-9b85-ef5ca8b74d51" alt="" width="500px">
</div>

<p align="justify">Jika pengguna memasukkan umur pet di bawah 0 atau lebih dari 30 tahun, maka akan diminta input ulang.</p>

**5. Berhasil menambah data**
<div align="center">
  <img src="https://github.com/user-attachments/assets/4805e553-e2fa-4d30-9019-6c85aad5afa3" alt="" width="500px">
</div>

<p align="justify">Pada gambar di atas merupakan tampilan apabila proses penambahan data pet berhasil.</p>

</details>

<details>
<summary><h3>Melihat Data Pet</h3></summary>

**1. Jika data pet pada ArrayList masih kosong**
<div align="center">
  <img src="https://github.com/user-attachments/assets/bf990e63-b24d-4c18-82a5-d06b7f12f08e" alt="" width="500px">
</div>

<p align="justify">Akan muncul teks seperti pada gambar di atas jika ArrayList masih kosong.</p>

**2. Tampilan daftar pet jika memiliki data**
<div align="center">
  <img src="https://github.com/user-attachments/assets/dee21aad-ba92-4776-8112-85424dfece3e" alt="" width="500px">
</div>

</details>

<details>
<summary><h3>Memperbarui Data Pet</h3></summary>

**1. Validasi ID dan jika gagal**
<div align="center">
  <img src="https://github.com/user-attachments/assets/64e5fc8c-6b99-430b-80b2-d836fca3e4ca" alt="" width="500px">
</div>

<p align="justify">Jika pengguna memasukkan id yang tidak ada atau tidak cocok dengan yang ada pada ArrayList. Maka, akan muncul teks seperti pada gambar.</p>

**2. Tampilan pembaruan data pet jika berhasil**
<div align="center">
  <img src="https://github.com/user-attachments/assets/e7fa40c6-8ef8-467d-a361-3efef7c821ca" alt="" width="500px">
</div>

<p align="justify">Perlu diketahui, bahwa logika dan proses validasi input seperti batas karakter, rentang umur, dll. pada bagian update ini kurang lebih sama dengan yang ada pada proses penambahan data pet. Bedanya, hanya di cara penyimpanannya di ArrayList menggunakan variabel khusus untuk bagian update. Pada gambar di atas merupakan tampilan jika pembaruan data pet berhasil.</p>

</details>

<details>

<summary><h3>Menghapus Data Pet</h3></summary>

**1. Validasi ID dan jika gagal**
<div align="center">
  <img src="https://github.com/user-attachments/assets/ae6042d6-9e36-43b4-aec3-7a0077e32df5" alt="" width="500px">
</div>

<p align="justify">Sama seperti di bagian update, pengguna diminta memasukkan ID pet, dan jika proses validasi gagal. Maka akan diminta input ulang.</p>

**2. Jika data pet berhasil dihapus**
<div align="center">
  <img src="https://github.com/user-attachments/assets/2bfe34c2-344e-42b7-bf99-91809a0ea644" alt="" width="500px">
</div>

<p align="justify">Jika proses validasi berhasil (ID cocok dengan data dalam ArrayList). Maka, data berhasil dihapus.</p>

</details>

---

### Versi Post-Test 2

<details>
<summary><h3>Pilihan Pencarian Pada Menu Utama</h3></summary>
  
<div align="center">
  <img src="https://github.com/user-attachments/assets/5c00c6ec-5139-45fd-97c1-7bf9080f5abc" alt="" width="500px">
</div>

<p align="justify">Melanjutkan dari Post Test pertama, disini saya menambahkan fitur pencarian data pet sebagai opsi di menu utama.</p>

</details>

<details>
<summary><h3>Melakukan Pencarian</h3></summary>

**1. Jika keyword yang dimasukkan tidak ditemukan**
<div align="center">
  <img src="https://github.com/user-attachments/assets/79030d13-3b2c-4c88-a48c-23f6902309f9" alt="" width="500px">
</div>

<p align="justify">Untuk mencari data pet yang diinginkan, pengguna diminta memasukkan kata kunci yang berupa ID/nama/jenis/umur/pemilik dari pet. Jika setelah proses pencocokan keyword yang dimasukkan tidak terdapat pada daftar pet yang ada, maka pengguna diberikan teks yang memberitahukan bahwa keyword yang diinput tidak ditemukan.</p>

**2. Jika keyword berhasil ditemukan**
<div align="center">
  <img src="https://github.com/user-attachments/assets/285a1513-951f-4c3c-a991-9624a15a205b" alt="" width="500px">
</div>

<p align="justify">Gambar di atas dapat terlihat memanggil data pet yang ditemukan jika keyword yang dimasukkan pengguna cocok dengan daftar pet yang ada.</p>

</details>

---

### Versi Post-Test 3

<details>
<summary><h3>Perubahan Operasi Tambah Pet</h3></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/49cb2f99-6edf-4dfc-9c86-306a45f0c8e7" alt="" width="500px">
</div>

<p align="justify">Pembaruan yang saya lakukan pada kali ini ada ketika pengguna menambahkan data pet, sekarang pengguna tidak perlu menginput jenis dengan mengetik jenis dan spesies pet, namun cukup dengan memilih opsi 1 atau 2.</p>

<div align="center">
  <img src="https://github.com/user-attachments/assets/4ce44180-2005-4eb2-a8a1-59088c6fb841" alt="" width="500px">
</div>

<p align="justify">Setelah pengguna menginput pilihannya, maka data berhasil ditambahkan. Perlu diketahui pula bahwa proses validasi juda terdapat pada saat pengguna memilih jenis, jika pengguna memasukkan di luar dari opsi (1&2), maka pengguna diminta menginput kembali.</p>

</details>

<details>
<summary><h3>Perubahan Operasi Update Pet</h3></summary>
  
<div align="center">
  <img src="https://github.com/user-attachments/assets/101e4770-a8a0-44a6-852c-07546efdc3ae" alt="" width="500px">
</div>

<p align="justify">Pada bagian ini update data pet, pengguna sekarang tidak bisa mengubah jenis pet yang ada.</p>

</details>

---

### Versi Post-Test 4

<details>
<summary><h3>Hanya Backend</h3></summary>

<p align="justify">Pada versi ini, pengguna tidak akan merasakan perbedaan. Menu, logika bisnis, dan alur jalannya program masih sama, karena pembaruan di versi ini bersifat struktural dalam pada bagian backend program.</p>

</details>

---

### Versi Post-Test 5

<details>
<summary><h3>Menu Utama dengan Fitur Baru</h3></summary>
<div align="center">
  <img src="https://github.com/user-attachments/assets/d370a329-d576-4105-b734-20dd11dbb51e" alt="" width="500px">
</div>

<p align="justify">Penambahan menu baru untuk akses data langsung dari database menggunakan Statement.</p>

</details>

<details>
<summary><h3>Fitur Tampil Data dengan Statement</h3></summary>
<div align="center">
  <img src="https://github.com/user-attachments/assets/32d17f48-5245-493c-b85f-8bf100beffca" alt="" width="500px">
</div>
<div align="center">
  <img src="https://github.com/user-attachments/assets/c2bdcb8e-d447-4034-9761-ace5280c9d89" alt="" width="500px">
</div>

<p align="justify">Fitur baru yang menampilkan data langsung dari database MySQL dengan koneksi real-time.</p>

</details>

<details>
<summary><h3>Proses Tambah, Update, dan Hapus Data dari Database</h3></summary>
<div align="center">
  <img src="https://github.com/user-attachments/assets/1890c1c3-2191-41e4-b116-2f294486a238" alt="" width="500px">
</div>
<div align="center">
  <img src="https://github.com/user-attachments/assets/1a82eec9-5ff5-4437-a2e5-90d8e3bd35f1" alt="" width="500px">
</div>
<div align="center">
  <img src="https://github.com/user-attachments/assets/8c5c64a5-a30f-419e-a0ed-1f4888b0a93b" alt="" width="500px">
</div>

<p align="justify">Data sekarang tersimpan secara permanen di database MySQL, bukan hanya di memory program.</p>

</details>

---
> [!NOTE]
> **🎉 Terimakasih! 🎉**
> Petit à petit, l'oiseau fait son nid 🙏

---
[⬆️ Kembali ke Atas](#top)
