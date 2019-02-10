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
public class Expert {
    private int idEx;
    private int cinEx;
    private int faxEx;
    private String nomEx;
    private String prenomEx;
    private String mailEx;
    private int numeroEx;
     private String adresseEx;
    private String etatEx;
    private String descriptionEx;
    
    public Expert()
    {
    
    
    }

    public Expert(int idEx,int cinEx, int faxEx, String nomEx, String prenomEx, String mailEx, int numeroEx, String adresseEx,String etatEx, String descriptionEx) {
          this.idEx = idEx;
        this.cinEx = cinEx;
        this.faxEx = faxEx;
        this.nomEx = nomEx;
        this.prenomEx = prenomEx;
        this.mailEx = mailEx;
        this.numeroEx = numeroEx;
        this.adresseEx = adresseEx;
        this.etatEx = etatEx;
        this.descriptionEx = descriptionEx;
    }

    public void setIdEx(int idEx) {
        this.idEx = idEx;
    }

    public int getIdEx() {
        return idEx;
    }

    public int getCinEx() {
        return cinEx;
    }

    public int getFaxEx() {
        return faxEx;
    }

    public String getNomEx() {
        return nomEx;
    }

    public String getPrenomEx() {
        return prenomEx;
    }

    public String getMailEx() {
        return mailEx;
    }

    public int getNumeroEx() {
        return numeroEx;
    }

    public String getAdresseEx() {
        return adresseEx;
    }

  

    public String getEtatEx() {
        return etatEx;
    }

    public String getDescriptionEx() {
        return descriptionEx;
    }

    public void setCinEx(int cinEx) {
        this.cinEx = cinEx;
    }

    public void setFaxEx(int faxEx) {
        this.faxEx = faxEx;
    }

    public void setNomEx(String nomEx) {
        this.nomEx = nomEx;
    }

    public void setPrenomEx(String prenomEx) {
        this.prenomEx = prenomEx;
    }

    public void setMailEx(String mailEx) {
        this.mailEx = mailEx;
    }

    public void setNumeroEx(int numeroEx) {
        this.numeroEx = numeroEx;
    }

    public void setAdresseEx(String adresseEx) {
        this.adresseEx = adresseEx;
    }

   
    public void setEtatEx(String etatEx) {
        this.etatEx = etatEx;
    }

    public void setDescriptionEx(String descriptionEx) {
        this.descriptionEx = descriptionEx;
    }
    

    
    
    
    
    
}
