package com.example.allom3alem;

public class Metiers {
    private int id;
    private String nom;
    private byte[] logo;

    public Metiers(int id, String nom, byte[] logo) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
    }


    public Metiers(String nom) {
        this.nom = nom;
    }


    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
