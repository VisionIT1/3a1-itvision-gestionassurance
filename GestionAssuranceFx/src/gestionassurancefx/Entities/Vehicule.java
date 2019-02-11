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
public class Vehicule {
    private int id_vehicule;
    private String immat;
    private int puiss;
    private int val_neuf;
    private int val_venale;
    private int annne_consruct;
    private Marque marque;
    private Garantie garantie;

    public Vehicule() {
    }

    public Vehicule(int id_vehicule,String immat, int puiss, int val_neuf, int val_venale, int annne_consruct, Marque marque, Garantie garantie) {
        this.id_vehicule = id_vehicule;
        this.immat = immat;
        this.puiss = puiss;
        this.val_neuf = val_neuf;
        this.val_venale = val_venale;
        this.annne_consruct = annne_consruct;
        this.marque = marque;
        this.garantie = garantie;
    }

    public Garantie getGarantie() {
        return garantie;
    }

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

 

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }
    
    

  

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public int getPuiss() {
        return puiss;
    }

    public void setPuiss(int puiss) {
        this.puiss = puiss;
    }

    public int getVal_neuf() {
        return val_neuf;
    }

    public void setVal_neuf(int val_neuf) {
        this.val_neuf = val_neuf;
    }

    public int getVal_venale() {
        return val_venale;
    }

    public void setVal_venale(int val_venale) {
        this.val_venale = val_venale;
    }

    public int getAnnne_consruct() {
        return annne_consruct;
    }

    public void setAnnne_consruct(int annne_consruct) {
        this.annne_consruct = annne_consruct;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    

  
    
}
