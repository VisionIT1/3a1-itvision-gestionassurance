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
public class OffreFidelite extends Offre {
     private int nbrContratMin;
     

    public OffreFidelite(int idOffre,int nbrContratMin, String libOffre, Date dateDebutOffre, Date dateFinOffre, int pourcentageReduction, String descripOffre) {
        super(idOffre,libOffre, dateDebutOffre, dateFinOffre, pourcentageReduction, descripOffre);
        this.nbrContratMin = nbrContratMin;
    }

    public OffreFidelite() {
        super(null, null, null, 0, null);
    }
    

    
    

    public int getNbrContratMin() {
        return nbrContratMin;
    }

    public void setNbrContratMin(int nbrContratMin) {
        this.nbrContratMin = nbrContratMin;
    }
}
