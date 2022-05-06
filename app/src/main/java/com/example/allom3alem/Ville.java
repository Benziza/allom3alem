package com.example.allom3alem;

public class Ville {
    private int id;
    private String nom;
    private int ref;

    public Ville(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Ville(int id, String nom,int ref) {
        this.id = id;
        this.nom = nom;
        this.ref = ref;
    }

    public Ville(String nom,int ref) {
        this.nom = nom;
        this.ref = ref;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getRef() {
        return ref;
    }
}
