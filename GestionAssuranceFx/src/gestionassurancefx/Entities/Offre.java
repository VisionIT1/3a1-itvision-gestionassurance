/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Entities;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Offre {
    private int idOffre;
    private String libOffre;
    private Date dateDebutOffre;
    private Date dateFinOffre;
    private int pourcentageReduction;
    private String descripOffre;

    public Offre(String libOffre, Date dateDebutOffre, Date dateFinOffre, int pourcentageReduction, String descripOffre) {
        
        this.libOffre = libOffre;
        this.dateDebutOffre = dateDebutOffre;
        this.dateFinOffre = dateFinOffre;
        this.pourcentageReduction = pourcentageReduction;
        this.descripOffre = descripOffre;
    }
    
    
    public String getDescripOffre() {
        return descripOffre;
    }

    public void setDescripOffre(String descripOffre) {
        this.descripOffre = descripOffre;
    }

    
    public Date getDateDebutOffre() {
        return dateDebutOffre;
    }

    public Date getDateFinOffre() {
        return dateFinOffre;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public String getLibOffre() {
        return libOffre;
    }

    public int getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setDateDebutOffre(Date dateDebutOffre) {
        this.dateDebutOffre = dateDebutOffre;
    }

    public void setDateFinOffre(Date dateFinOffre) {
        this.dateFinOffre = dateFinOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public void setLibOffre(String libOffre) {
        this.libOffre = libOffre;
    }

    public void setPourcentageReduction(int pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }
    
}
