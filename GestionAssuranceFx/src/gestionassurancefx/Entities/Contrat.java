/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Entities;

import java.sql.Date;

/**
 *
 * @author Ahmed Derbel
 */
public class Contrat {
    int id;
    String nom;
    String description;
    int id_assure;
    String type;
    Date date_debut;
    Date date_Echeance;
    int etat;
    float prime;
    public Contrat(){
        
    }

    public Contrat(int id, String nom, String description, int id_assure, String type, Date date_debut, Date date_Echeance, int etat, float prime) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.id_assure = id_assure;
        this.type = type;
        this.date_debut = date_debut;
        this.date_Echeance = date_Echeance;
        this.etat = etat;
        this.prime = prime;
    }

    public int getId_assure() {
        return id_assure;
    }

    public void setId_assure(int id_assure) {
        this.id_assure = id_assure;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_Echeance() {
        return date_Echeance;
    }

    public void setDate_Echeance(Date date_Echeance) {
        this.date_Echeance = date_Echeance;
    }

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", id_assure=" + id_assure + ", type=" + type + ", date_debut=" + date_debut + ", date_Echeance=" + date_Echeance + ", etat=" + etat + ", prime=" + prime + '}';
    }

   
    
    
}
