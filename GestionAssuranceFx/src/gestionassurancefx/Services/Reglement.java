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
    private String Mode_regl;
    private int Montant_regl;
    private int cinAssureur;
    private int idRep;
    private int code_sin;
    private int idEx;

    public Reglement() {
    }

    public Reglement(int Code_regl, Date Date_regl, int Frais_expert, String Mode_regl, int Montant_regl, int cinAssureur, int idRep, int code_sin, int idEx) {
        this.Code_regl = Code_regl;
        this.Date_regl = Date_regl;
        this.Frais_expert = Frais_expert;
        this.Mode_regl = Mode_regl;
        this.Montant_regl = Montant_regl;
        this.cinAssureur = cinAssureur;
        this.idRep = idRep;
        this.code_sin = code_sin;
        this.idEx = idEx;
    }

    public int getCode_regl() {
        return Code_regl;
    }

    public void setCode_regl(int Code_regl) {
        this.Code_regl = Code_regl;
    }

    public Date getDate_regl() {
        return Date_regl;
    }

    public void setDate_regl(Date Date_regl) {
        this.Date_regl = Date_regl;
    }

    public int getFrais_expert() {
        return Frais_expert;
    }

    public void setFrais_expert(int Frais_expert) {
        this.Frais_expert = Frais_expert;
    }

    public String getMode_regl() {
        return Mode_regl;
    }

    public void setMode_regl(String Mode_regl) {
        this.Mode_regl = Mode_regl;
    }

    public int getMontant_regl() {
        return Montant_regl;
    }

    public void setMontant_regl(int Montant_regl) {
        this.Montant_regl = Montant_regl;
    }

    public int getCinAssureur() {
        return cinAssureur;
    }

    public void setCinAssureur(int cinAssureur) {
        this.cinAssureur = cinAssureur;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public int getCode_sin() {
        return code_sin;
    }

    public void setCode_sin(int code_sin) {
        this.code_sin = code_sin;
    }

    public int getIdEx() {
        return idEx;
    }

    public void setIdEx(int idEx) {
        this.idEx = idEx;
    }
    

    
    
    
}
