package model;

public class Anjing extends Pet {
    public Anjing(Integer id, String nama, String umur, String pemilik) {
        super(id, nama, umur, pemilik);
    }

    @Override
    public String getJenis() {
        return "Anjing";
    }
}