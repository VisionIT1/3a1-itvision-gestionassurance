/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Entities;

/**
 *
 * @author Psico
 */
public class Garantie {
    private int id_garantie;
    private String lib;
    private float prime;
    private String categorie;

    public Garantie() {
    }

    public Garantie(int id_garantie, String lib, float prime, String categorie) {
        this.id_garantie = id_garantie;
        this.lib = lib;
        this.prime = prime;
        this.categorie = categorie;
    }

    public int getId_garantie() {
        return id_garantie;
    }

    public void setId_garantie(int id_garantie) {
        this.id_garantie = id_garantie;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Garantie{" + "id_garantie=" + id_garantie + ", lib=" + lib + ", prime=" + prime + ", categorie=" + categorie + '}';
    }

  
    
}
