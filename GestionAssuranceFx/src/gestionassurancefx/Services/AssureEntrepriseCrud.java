/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.AssureEntreprise;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahmed Derbel
 */
public class AssureEntrepriseCrud {

    Connection Cn = Connexion.getInstance().getCon();

    public void ajouterAssureEntreprise(AssureEntreprise Ar) {

        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql
            String req = "insert into assure_entreprise (nomEntr, emailEntr, numtelEntr, adresseEntr) VALUES (?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, Ar.getNomEntr());
            ste.setString(2, Ar.getEmailEntr());
            ste.setString(3, Ar.getNumTel());
            ste.setString(4, Ar.getAdresseEntr());
             ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
   
public ObservableList<AssureEntreprise> getAllAssEntr() {
        ObservableList<AssureEntreprise> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "select * from assure_entreprise";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                AssureEntreprise aEntr = new AssureEntreprise();
               aEntr.setNomEntr(rs.getString(1));
               aEntr.setEmailEntr(rs.getString(2));
               aEntr.setNumTel(rs.getString(3));
               aEntr.setAdresseEntr(rs.getString(4));
               l.add(aEntr);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }



public void modifierEntreprise(AssureEntreprise aEntr) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update assure_entreprise set nomEntr=?,emailEntr=?,numtelEntr=?,adresseEntr=? where nomEntr=?");
            ps.setString(1,aEntr.getNomEntr());
            ps.setString(2, aEntr.getEmailEntr());
            ps.setString(3, aEntr.getNumTel());
            ps.setString(4, aEntr.getAdresseEntr());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : "+ex.getMessage());
        }

    }


public void supprimerAssureEntreprise(String nom){
          try {
            Statement st = Cn.createStatement();
            String req = "delete from assure_entreprise where nomEntr=" + nom;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(AssureEntreprise.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}


