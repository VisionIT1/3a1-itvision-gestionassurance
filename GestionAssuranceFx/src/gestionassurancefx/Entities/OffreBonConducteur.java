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
public class OffreBonConducteur extends Offre {
    private int numReglement;
    private int nbrAnnee;

    public OffreBonConducteur() {
        super(null, null, null, 0, null);
    }
    
    public OffreBonConducteur(int numReglement, int nbrAnnee,String libOffre, Date dateDebutOffre, Date dateFinOffre, int pourcentageReduction, String descripOffre) {
        super(libOffre, dateDebutOffre, dateFinOffre, pourcentageReduction, descripOffre);
        this.numReglement = numReglement;
        this.nbrAnnee = nbrAnnee;
    }
    public OffreBonConducteur(int idOffre,int numReglement, int nbrAnnee,String libOffre, Date dateDebutOffre, Date dateFinOffre, int pourcentageReduction, String descripOffre) {
        super(idOffre,libOffre, dateDebutOffre, dateFinOffre, pourcentageReduction, descripOffre);
        this.numReglement = numReglement;
        this.nbrAnnee = nbrAnnee;
    }

    

    public int getNumReglement() {
        return numReglement;
    }

    public int getNbrAnnee() {
        return nbrAnnee;
    }

    public void setNbrAnnee(int nbrAnnee) {
        this.nbrAnnee = nbrAnnee;
    }

    public void setNumReglement(int numReglement) {
        this.numReglement = numReglement;
    }

    
}
