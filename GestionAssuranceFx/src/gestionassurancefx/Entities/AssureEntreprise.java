/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Entities;

/**
 *
 * @author Ahmed Derbel
 */
public class AssureEntreprise {
    String nomEntr;
    String emailEntr;
    String numTel;
    String adresseEntr;
    
    public AssureEntreprise(){}
    
    
    public AssureEntreprise(String nomEntr, String emailEntr, String numTel, String adresseEntr) {
        this.nomEntr = nomEntr;
        this.emailEntr = emailEntr;
        this.numTel = numTel;
        this.adresseEntr = adresseEntr;
    }

    public String getNomEntr() {
        return nomEntr;
    }

    public void setNomEntr(String nomEntr) {
        this.nomEntr = nomEntr;
    }

    public String getEmailEntr() {
        return emailEntr;
    }

    public void setEmailEntr(String emailEntr) {
        this.emailEntr = emailEntr;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresseEntr() {
        return adresseEntr;
    }

    public void setAdresseEntr(String adresseEntr) {
        this.adresseEntr = adresseEntr;
    }
    
    
    
}
