package service;

import model.Pet;
import java.util.List;

public interface PetOperations {
    void tambahPet(Pet pet);
    List<Pet> getDaftar();
    boolean updatePet(Integer id, Pet newPet);
    boolean hapusPet(Integer id);            
    List<Pet> cariPet(String keyword);
}