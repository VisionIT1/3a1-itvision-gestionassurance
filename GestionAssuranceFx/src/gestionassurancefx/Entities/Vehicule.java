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
    private int id_marque;
    private int id_garantie;

    public Vehicule() {
    }

    public Vehicule(int id_vehicule, String immat, int puiss, int val_neuf, int val_venale, int annne_consruct, int id_marque, int id_garantie) {
        this.id_vehicule = id_vehicule;
        this.immat = immat;
        this.puiss = puiss;
        this.val_neuf = val_neuf;
        this.val_venale = val_venale;
        this.annne_consruct = annne_consruct;
        this.id_marque = id_marque;
        this.id_garantie = id_garantie;
    }

    public int getId_marque() {
        return id_marque;
    }

    public void setId_marque(int id_marque) {
        this.id_marque = id_marque;
    }

    public int getId_garantie() {
        return id_garantie;
    }

    public void setId_garantie(int id_garantie) {
        this.id_garantie = id_garantie;
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

    @Override
    public String toString() {
        return "Vehicule{" + "id_vehicule=" + id_vehicule + ", immat=" + immat + ", puiss=" + puiss + ", val_neuf=" + val_neuf + ", val_venale=" + val_venale + ", annne_consruct=" + annne_consruct + ", id_marque=" + id_marque + ", id_garantie=" + id_garantie + '}';
    }

  

    

  
    
}
