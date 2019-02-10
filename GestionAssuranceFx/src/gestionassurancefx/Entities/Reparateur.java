/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

/**
 *
 * @author ASUS
 */
public class Reparateur {
     private int idRep;
      private int cinRep;
    private int faxRep;
    private String nomRep;
    private String prenomRep;
    private String mailRep;
    private int numeroRep;
    private String adresseRep;
    private String etatRep;
    private String descriptionRep;
    
    public Reparateur()
    {
    
    }

    public Reparateur(int idRep,int cinRep, int faxRep, String nomRep, String prenomRep, String mailRep, int numeroRep, String adresseRep, String etatRep, String descriptionRep) {
       this.idRep=idRep;
        this.cinRep = cinRep;
        this.faxRep = faxRep;
        this.nomRep = nomRep;
        this.prenomRep = prenomRep;
        this.mailRep = mailRep;
        this.numeroRep = numeroRep;
        this.adresseRep = adresseRep;
        this.etatRep = etatRep;
        this.descriptionRep = descriptionRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }
    

    public int getIdRep() {
        return idRep;
    }

    public int getCinRep() {
        return cinRep;
    }

    public int getFaxRep() {
        return faxRep;
    }

    public String getNomRep() {
        return nomRep;
    }

    public String getPrenomRep() {
        return prenomRep;
    }

    public String getMailRep() {
        return mailRep;
    }

    public int getNumeroRep() {
        return numeroRep;
    }

    public String getAdresseRep() {
        return adresseRep;
    }

    public String getEtatRep() {
        return etatRep;
    }

    public String getDescriptionRep() {
        return descriptionRep;
    }

    public void setCinRep(int cinRep) {
        this.cinRep = cinRep;
    }

    public void setFaxRep(int faxRep) {
        this.faxRep = faxRep;
    }

    public void setNomRep(String nomRep) {
        this.nomRep = nomRep;
    }

    public void setPrenomRep(String prenomRep) {
        this.prenomRep = prenomRep;
    }

    public void setMailRep(String mailRep) {
        this.mailRep = mailRep;
    }

    public void setNumeroRep(int numeroRep) {
        this.numeroRep = numeroRep;
    }

    public void setAdresseRep(String adresseRep) {
        this.adresseRep = adresseRep;
    }

    public void setEtatRep(String etatRep) {
        this.etatRep = etatRep;
    }

    public void setDescriptionRep(String descriptionRep) {
        this.descriptionRep = descriptionRep;
    }
    
    
    
}
