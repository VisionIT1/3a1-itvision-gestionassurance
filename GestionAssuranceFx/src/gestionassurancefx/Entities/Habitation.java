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
public class Habitation {
    private int id_habitat;
    private int baie_vitre;
    private int nb_pieces;
    private float valeur_mob;
    private int sys_alarm;
    private String nature_local;

    public Habitation() {
    }

    public Habitation(int id_habitat, int baie_vitre, int nb_pieces, float valeur_mob, int sys_alarm, String nature_local) {
        this.id_habitat = id_habitat;
        this.baie_vitre = baie_vitre;
        this.nb_pieces = nb_pieces;
        this.valeur_mob = valeur_mob;
        this.sys_alarm = sys_alarm;
        this.nature_local = nature_local;
    }
        public Habitation( int baie_vitre, int nb_pieces, float valeur_mob, int sys_alarm, String nature_local) {
        this.id_habitat = id_habitat;
        this.baie_vitre = baie_vitre;
        this.nb_pieces = nb_pieces;
        this.valeur_mob = valeur_mob;
        this.sys_alarm = sys_alarm;
        this.nature_local = nature_local;
    }

    public int getId_habitat() {
        return id_habitat;
    }

    public void setId_habitat(int id_habitat) {
        this.id_habitat = id_habitat;
    }

 

  

 

    public int getBaie_vitre() {
        return baie_vitre;
    }

    public void setBaie_vitre(int baie_vitre) {
        this.baie_vitre = baie_vitre;
    }

    public int getNb_pieces() {
        return nb_pieces;
    }

    public void setNb_pieces(int nb_pieces) {
        this.nb_pieces = nb_pieces;
    }

    public float getValeur_mob() {
        return valeur_mob;
    }

    public void setValeur_mob(float valeur_mob) {
        this.valeur_mob = valeur_mob;
    }

    public int getSys_alarm() {
        return sys_alarm;
    }

    public void setSys_alarm(int sys_alarm) {
        this.sys_alarm = sys_alarm;
    }

    public String getNature_local() {
        return nature_local;
    }

    public void setNature_local(String nature_local) {
        this.nature_local = nature_local;
    }

    @Override
    public String toString() {
        return "Habitation{" + "id_habitat=" + id_habitat + ", baie_vitre=" + baie_vitre + ", nb_pieces=" + nb_pieces + ", valeur_mob=" + valeur_mob + ", sys_alarm=" + sys_alarm + ", nature_local=" + nature_local + '}';
    }

  
    
    
    
}
