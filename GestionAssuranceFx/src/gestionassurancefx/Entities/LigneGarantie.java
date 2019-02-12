/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Entities;

import sun.security.provider.PolicyParser;

/**
 *
 * @author Psico
 */
public class LigneGarantie {
    private Garantie garantie;
    private Voyage  voyage;
    private float prime;
    

    public LigneGarantie(Garantie garantie, Voyage voyage, float prime) {
        this.garantie = garantie;
        this.voyage = voyage;
        this.prime = prime;
    }

    public LigneGarantie() {
    }

    public Garantie getGarantie() {
        return garantie;
    }

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }

    @Override
    public String toString() {
        return "LigneGarantie{" + "garantie=" + garantie + ", voyage=" + voyage + ", prime=" + prime + '}';
    }
    
}
