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
public class OffrePartenaire extends Offre {
     private String partenaire;

    public OffrePartenaire(String partenaire,String libOffre, Date dateDebutOffre, Date dateFinOffre, int pourcentageReduction, String descripOffre) {
        super(libOffre, dateDebutOffre, dateFinOffre, pourcentageReduction, descripOffre);
        this.partenaire = partenaire;
    }

    
    
    

    public String getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(String partenaire) {
        this.partenaire = partenaire;
    }
}
