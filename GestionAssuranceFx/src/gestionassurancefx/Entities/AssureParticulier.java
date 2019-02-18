/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Entities;

/**
 *
 * @author Ahmed Derbel
 */
public class AssureParticulier {

    int cin;
    String nom;
    String prenom;
    String email;
    int numtel;
    String adresse;

    public AssureParticulier() {
    }

    public AssureParticulier(int cin, String nom, String prenom, String email, int numtel, String adresse) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.adresse = adresse;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "AssureParticulier{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", numtel=" + numtel + ", adresse=" + adresse + '}';
    }

}
