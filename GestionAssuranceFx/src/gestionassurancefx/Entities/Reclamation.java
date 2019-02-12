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
public class Reclamation {
    private int idR;
    private String typeRec;
    private String descRec;
    private Date dateSaisiRec;
    private int idCli;
    private int traitementRec; // {1,0}

    public Reclamation(String typeRec, String descRec, Date dateSaisiRec, int idCli, int traitementRec) {
        
        this.typeRec = typeRec;
        this.descRec = descRec;
        this.dateSaisiRec = dateSaisiRec;
        this.idCli = idCli;
        this.traitementRec = traitementRec;
    }
    
    
    public Date getDateSaisiRec() {
        return dateSaisiRec;
    }

   

    public String getDescRec() {
        return descRec;
    }

    public int getIdCli() {
        return idCli;
    }
    
    
    public int getIdR() {
        return idR;
    }

    public int getTraitementRec() {
        return traitementRec;
    }

    public String getTypeRec() {
        return typeRec;
    }

    public void setDateSaisiRec(Date dateSaisiRec) {
        this.dateSaisiRec = dateSaisiRec;
    }

    

    public void setDescRec(String descRec) {
        this.descRec = descRec;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public void setTraitementRec(int traitementRec) {
        this.traitementRec = traitementRec;
    }

    public void setTypeRec(String typeRec) {
        this.typeRec = typeRec;
    }
}
