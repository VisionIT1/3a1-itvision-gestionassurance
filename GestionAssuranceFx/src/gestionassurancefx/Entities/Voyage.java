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
public class Voyage {
    private int id_voyage;
    private String dest;
    private int duree_sej;
    private int tranche_age;

    public Voyage() {
    }

    public Voyage(int id_voyage, String dest, int duree_sej, int tranche_age) {
        this.id_voyage = id_voyage;
        this.dest = dest;
        this.duree_sej = duree_sej;
        this.tranche_age = tranche_age;
    }
   
      public Voyage(String dest, int duree_sej, int tranche_age) {
        this.id_voyage = id_voyage;
        this.dest = dest;
        this.duree_sej = duree_sej;
        this.tranche_age = tranche_age;
    }
    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getDuree_sej() {
        return duree_sej;
    }

    public void setDuree_sej(int duree_sej) {
        this.duree_sej = duree_sej;
    }

    public int getTranche_age() {
        return tranche_age;
    }

    public void setTranche_age(int tranche_age) {
        this.tranche_age = tranche_age;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id_voyage=" + id_voyage + ", dest=" + dest + ", duree_sej=" + duree_sej + ", tranche_age=" + tranche_age + '}';
    }
    
    
}
