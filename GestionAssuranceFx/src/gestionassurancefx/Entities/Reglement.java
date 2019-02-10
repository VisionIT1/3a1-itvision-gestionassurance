/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Entities;

import java.sql.Date;

/**
 *
 * @author youssef
 */
public class Reglement {
    private int Code_regl;
    private Date Date_regl;
    private int Frais_expert;
    private int Mode_regl;
    private int Montant_regl;

    public Reglement(int Code_regl, Date Date_regl, int Frais_expert, int Mode_regl, int Montant_regl) {
        this.Code_regl = Code_regl;
        this.Date_regl = Date_regl;
        this.Frais_expert = Frais_expert;
        this.Mode_regl = Mode_regl;
        this.Montant_regl = Montant_regl;
    }

    public int getCode_regl() {
        return Code_regl;
    }

    public Date getDate_regl() {
        return Date_regl;
    }

    public int getFrais_expert() {
        return Frais_expert;
    }

    public int getMode_regl() {
        return Mode_regl;
    }

    public int getMontant_regl() {
        return Montant_regl;
    }

    public void setCode_regl(int Code_regl) {
        this.Code_regl = Code_regl;
    }

    public void setDate_regl(Date Date_regl) {
        this.Date_regl = Date_regl;
    }

    public void setFrais_expert(int Frais_expert) {
        this.Frais_expert = Frais_expert;
    }

    public void setMode_regl(int Mode_regl) {
        this.Mode_regl = Mode_regl;
    }

    public void setMontant_regl(int Montant_regl) {
        this.Montant_regl = Montant_regl;
    }

    @Override
    public String toString() {
        return "Reglement{" + "Code_regl=" + Code_regl + ", Date_regl=" + Date_regl + ", Frais_expert=" + Frais_expert + ", Mode_regl=" + Mode_regl + ", Montant_regl=" + Montant_regl + '}';
    }
    
    
}
