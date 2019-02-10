/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Entities;

import java.sql.Date;

/**
 *
 * @author youssef
 */
public class Sinistre {
    private int Code_sinistre;
    private Date Date_declaration;
    private Date Date_sinistre;
    private String Lieu_sinistre ;
    private int Numero_sinistre;
    private int Domage_mat;
    private int Domage_corps;
    private int Code_assurance;
    private String Description;

    public Sinistre(int Code_sinistre, Date Date_declaration, Date Date_sinistre, String Lieu_sinistre, int Numero_sinistre, int Domage_mat, int Domage_corps, int Code_assurance, String Description) {
        this.Code_sinistre = Code_sinistre;
        this.Date_declaration = Date_declaration;
        this.Date_sinistre = Date_sinistre;
        this.Lieu_sinistre = Lieu_sinistre;
        this.Numero_sinistre = Numero_sinistre;
        this.Domage_mat = Domage_mat;
        this.Domage_corps = Domage_corps;
        this.Code_assurance = Code_assurance;
        this.Description = Description;
    }

    public int getCode_sinistre() {
        return Code_sinistre;
    }

    public Date getDate_declaration() {
        return Date_declaration;
    }

    public Date getDate_sinistre() {
        return Date_sinistre;
    }

    public String getLieu_sinistre() {
        return Lieu_sinistre;
    }

    public int getNumero_sinistre() {
        return Numero_sinistre;
    }

    public int getDomage_mat() {
        return Domage_mat;
    }

    public int getDomage_corps() {
        return Domage_corps;
    }

    public int getCode_assurance() {
        return Code_assurance;
    }

    public String getDescription() {
        return Description;
    }

    public void setCode_sinistre(int Code_sinistre) {
        this.Code_sinistre = Code_sinistre;
    }

    public void setDate_declaration(Date Date_declaration) {
        this.Date_declaration = Date_declaration;
    }

    public void setDate_sinistre(Date Date_sinistre) {
        this.Date_sinistre = Date_sinistre;
    }

    public void setLieu_sinistre(String Lieu_sinistre) {
        this.Lieu_sinistre = Lieu_sinistre;
    }

    public void setNumero_sinistre(int Numero_sinistre) {
        this.Numero_sinistre = Numero_sinistre;
    }

    public void setDomage_mat(int Domage_mat) {
        this.Domage_mat = Domage_mat;
    }

    public void setDomage_corps(int Domage_corps) {
        this.Domage_corps = Domage_corps;
    }

    public void setCode_assurance(int Code_assurance) {
        this.Code_assurance = Code_assurance;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Sinistre{" + "Code_sinistre=" + Code_sinistre + 
                ", Date_declaration=" + Date_declaration + 
                ", Date_sinistre=" + Date_sinistre + 
                ", Lieu_sinistre=" + Lieu_sinistre +
                ", Numero_sinistre=" + Numero_sinistre +
                ", Domage_mat=" + Domage_mat + 
                ", Domage_corps=" + Domage_corps +
                ", Code_assurance=" + Code_assurance +
                ", Description=" + Description + '}';
    }

   
    
    
    
    
    
    
}
