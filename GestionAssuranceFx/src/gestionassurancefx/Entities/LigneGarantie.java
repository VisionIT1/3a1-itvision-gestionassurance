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
public class LigneGarantie {
    private int id_ligne;
    private int id_garantie;
    private int  id_voyage;
    private float prime;
    

  

    public LigneGarantie() {
    }

    public LigneGarantie(int id_ligne, int id_garantie, int id_voyage, float prime) {
        this.id_ligne = id_ligne;
        this.id_garantie = id_garantie;
        this.id_voyage = id_voyage;
        this.prime = prime;
    }

   
    public int getId_garantie() {
        return id_garantie;
    }

    public void setId_garantie(int id_garantie) {
        this.id_garantie = id_garantie;
    }

    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }



    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }

    public int getId_ligne() {
        return id_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    @Override
    public String toString() {
        return "LigneGarantie{" + "id_ligne=" + id_ligne + ", id_garantie=" + id_garantie + ", id_voyage=" + id_voyage + ", prime=" + prime + '}';
    }

   

   
    
}
