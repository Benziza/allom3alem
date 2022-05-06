package com.example.allom3alem;

public class Region {
    private int id;
    private String nom;

    public Region(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    public Region(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

}
