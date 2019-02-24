/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Entities;

/**
 *
 * @author ADMIN
 */
public class AffectationOffre {
    private int idOffre;
    private int idAssure;

    public AffectationOffre(int idOffre, int idAssure) {
        this.idOffre = idOffre;
        this.idAssure = idAssure;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public int getIdAssure() {
        return idAssure;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public void setIdAssure(int idAssure) {
        this.idAssure = idAssure;
    }
   
    
}
