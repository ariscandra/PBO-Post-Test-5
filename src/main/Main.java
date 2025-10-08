package main;

import model.Pet;
import model.Kucing;
import model.Anjing;
import service.PetService;
import util.ValidasiUtil;
import database.TampilDataStatement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PetService petService = new PetService();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("""
                       _____     _              _       
                      |  __ \\   | |            (_)      
                      | |__) |__| |_ ___  _ __  _  __ _ 
                      |  ___/ _ \\ __/ _ \\| '_ \\| |/ _` |
                      | |  |  __/ || (_) | |_) | | (_| |
                      |_|   \\___|\\__\\___/| .__/|_|\\__,_|
                                         | |            
                                         |_|            
                    """);

            System.out.println("\n=== Haloooo Admin Aris, Good to see you back! Moga sehat selalu! ===");
            System.out.println("\n+==== Petopia Pets Daycare ====+");
            System.out.println("| [1] Tambah Data Pets         |");
            System.out.println("| [2] Lihat Semua Data Pets    |");
            System.out.println("| [3] Update Data Pets         |");
            System.out.println("| [4] Hapus Data Pets          |");
            System.out.println("| [5] Cari Data Pets           |");
            System.out.println("| [6] Lihat Data (Statement)   |");
            System.out.println("| [0] Keluar                   |");
            System.out.println("+==============================+");
            System.out.print("Pilih menu (0-6): ");

            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahPet(scanner, petService);
                case 2 -> lihatSemuaPets(petService);
                case 3 -> updatePet(scanner, petService);
                case 4 -> hapusPet(scanner, petService);
                case 5 -> cariPet(scanner, petService);
                case 6 -> tampilDataDenganStatement(scanner);
                case 0 -> System.out.println("Program selesai. See you soon Mimin!");
                default -> System.out.println("Pilihan tidak valid, min! Silakan pilih 0-6.");
            }
        } while (pilihan != 0);

        scanner.close();
    }

    // METHOD BARU VERSI POST TEST 5 - Nampilkan data pake Statement
    private static void tampilDataDenganStatement(Scanner scanner) {
        System.out.println("\n--- Menampilkan Data Menggunakan Statement ---");
        System.out.println("1. Tampilkan semua pets");
        System.out.println("2. Tampilkan pets Kucing saja");
        System.out.println("3. Tampilkan pets Anjing saja");
        System.out.print("Pilihan: ");
        
        int pilih = scanner.nextInt();
        scanner.nextLine();
        
        switch (pilih) {
            case 1 -> TampilDataStatement.tampilkanSemuaPets();
            case 2 -> TampilDataStatement.tampilkanPetsBerdasarkanJenis("Kucing");
            case 3 -> TampilDataStatement.tampilkanPetsBerdasarkanJenis("Anjing");
            default -> System.out.println("Pilihan tidak valid!");
        }
    }

    private static void tambahPet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Tambah Data Pet ---");
        
        String name = validasiInput(scanner, "Nama pet: ", "nama", 30);
        String umur = validasiUmur(scanner);
        String pemilik = validasiInput(scanner, "Nama Pemilik: ", "pemilik", 40);
        
        int jenisPilihan;
        while (true) {
            System.out.println("Pilih jenis hewan:");
            System.out.println("1. Kucing");
            System.out.println("2. Anjing");
            System.out.print("Pilihan (1-2): ");
            
            if (scanner.hasNextInt()) {
                jenisPilihan = scanner.nextInt();
                scanner.nextLine();
                
                if (jenisPilihan == 1 || jenisPilihan == 2) {
                    break;
                } else {
                    System.out.println("Error: Pilihan harus 1 atau 2!");
                }
            } else {
                System.out.println("Error: Masukkan angka yang valid!");
                scanner.nextLine();
            }
        }

        Pet newPet;
        if (jenisPilihan == 1) {
            newPet = new Kucing(null, name, umur, pemilik);
        } else {
            newPet = new Anjing(null, name, umur, pemilik);
        }
        
        petService.tambahPet(newPet);
        System.out.println(newPet.getNama() + " (" + newPet.getJenis() + ") berhasil ditambahkan dengan ID: " + newPet.getId());
    }

    private static void lihatSemuaPets(PetService petService) {
        System.out.println("\n--- Daftar Pets di Petopia ---");
        List<Pet> pets = petService.cariPet();
        if (pets.isEmpty()) {
            System.out.println("Belum ada pets yang dititipkan :(\n");
        } else {
            System.out.println("ID | Nama | Jenis | Umur | Pemilik");
            System.out.println("------------------------------------");
            for (Pet pet : pets) {
                System.out.printf("%d | %s | %s | %s Tahun | %s\n",
                        pet.getId(), pet.getNama(), pet.getJenis(), pet.getUmur(), pet.getPemilik());
                System.out.println("------------------------------------");
            }
        }
    }

    private static void updatePet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Update Data Pet ---");
        System.out.print("Masukkan ID pet yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pet petCocok = petService.getPetById(id);

        if (petCocok == null) {
            System.out.println("Pet dengan ID " + id + " tidak ada, min!");
            return;
        }

        System.out.println("Data saat ini:");
        System.out.println("Nama: " + petCocok.getNama());
        System.out.println("Jenis: " + petCocok.getJenis());
        System.out.println("Umur: " + petCocok.getUmur());
        System.out.println("Pemilik: " + petCocok.getPemilik());

        System.out.println("\nMasukkan data pet yang baru:");
        String name = validasiInput(scanner, "Nama Baru: ", "nama", 30);
        String umur = validasiUmur(scanner);
        String pemilik = validasiInput(scanner, "Pemilik Baru: ", "pemilik", 40);

        Pet petBaru;
        if (petCocok.getJenis().equals("Kucing")) {
            petBaru = new Kucing(id, name, umur, pemilik);
        } else {
            petBaru = new Anjing(id, name, umur, pemilik);
        }

        if (petService.updatePet(id, petBaru)) {
            System.out.println("Data pet berhasil diperbarui!");
        } else {
            System.out.println("Gagal memperbarui data pet!");
        }
    }

    private static void hapusPet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Hapus Data Pet ---");
        System.out.print("Masukkan ID pet yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (petService.hapusPet(id)) {
            System.out.println("Pet dengan ID " + id + " berhasil dihapus!");
        } else {
            System.out.println("Pet dengan ID " + id + " tidak ada, min!");
        }
    }

    private static void cariPet(Scanner scanner, PetService petService) {
        System.out.println("\n--- Cari Data Pet ---");
        System.out.print("Masukkan keyword (ID/Nama/Jenis/Umur/Pemilik): ");
        String keyword = scanner.nextLine();

        List<Pet> hasil = petService.cariPet(keyword);
        if (hasil.isEmpty()) {
            System.out.println("Tidak ditemukan pet dengan kata kunci '" + keyword + "'");
        } else {
            System.out.println("Hasil pencarian:");
            System.out.println("ID | Nama | Jenis | Umur | Pemilik");
            System.out.println("------------------------------------");
            for (Pet pet : hasil) {
                System.out.printf("%d | %s | %s | %s Tahun | %s\n",
                        pet.getId(), pet.getNama(), pet.getJenis(), pet.getUmur(), pet.getPemilik());
                System.out.println("------------------------------------");
            }
        }
    }

    private static String validasiInput(Scanner scanner, String prompt, String field, int maxLength) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            if (input.length() > maxLength) {
                System.out.printf("Error: %s tidak boleh lebih dari %d karakter.\n", field, maxLength);
                continue;
            }
            
            if (ValidasiUtil.hanyaHurufDanSpasi(input)) {
                return input;
            } else {
                System.out.printf("Error: %s hanya boleh mengandung huruf dan spasi.\n", field);
            }
        }
    }

    private static String validasiUmur(Scanner scanner) {
        while (true) {
            System.out.print("Umur: ");
            String input = scanner.nextLine();
            
            if (input.length() > 2) {
                System.out.println("Error: Umur tidak boleh lebih dari 2 digit.");
                continue;
            }
            
            if (ValidasiUtil.hanyaAngka(input)) {
                try {
                    int umur = Integer.parseInt(input);
                    if (umur < 0 || umur > 30) {
                        System.out.println("Error: Umur harus antara 0 dan 30 tahun.");
                        continue;
                    }
                    return input;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Format angka tidak valid.");
                }
            } else {
                System.out.println("Error: Umur hanya boleh mengandung angka.");
            }
        }
    }
}