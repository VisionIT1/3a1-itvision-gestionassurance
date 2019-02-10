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
    int id_client;
    String type;
    Date date_debut;
    Date date_Echeance;

    public Contrat(){
        
    }
    
    public Contrat(int id, String nom, String description, int id_client, String type, Date date_debut, Date date_Echeance) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.id_client = id_client;
        this.type = type;
        this.date_debut = date_debut;
        this.date_Echeance = date_Echeance;
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

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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
        return "Contrat{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", id_client=" + id_client + ", type=" + type + ", date_debut=" + date_debut + ", date_Echeance=" + date_Echeance + '}';
    }
    
    
    
}
