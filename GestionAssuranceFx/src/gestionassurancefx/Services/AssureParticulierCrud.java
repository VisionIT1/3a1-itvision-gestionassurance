/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Entities.AssureParticulier;
import gestionassurancefx.Entities.Contrat;
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
public class AssureParticulierCrud {
    
            Connection Cn = Connexion.getInstance().getCon();
            
public void ajouterAssureParticulier(AssureParticulier Ap) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            String req = "insert into assure_particulier ( cin, nom, prenom, email, numtel, adresse) VALUES (?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setInt(1, Ap.getCin());
            ste.setString(2, Ap.getNom());
            ste.setString(3,Ap.getPrenom());
            ste.setString(4, Ap.getEmail());
            ste.setInt(5, Ap.getNumtel());
            ste.setString(6, Ap.getAdresse());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }
        

    }


public ObservableList<AssureParticulier> getAllAssPart() {
        ObservableList<AssureParticulier> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "select * from assure_particulier";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                AssureParticulier ap = new AssureParticulier();
               ap.setCin(rs.getInt(1));
               ap.setNom(rs.getString(2));
               ap.setPrenom(rs.getString(3));
               ap.setEmail(rs.getString(4));
               ap.setNumtel(rs.getInt(5));
               ap.setAdresse(rs.getString(6));
                l.add(ap);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
     public void modifierParticulier(AssureParticulier ap) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update assure_particulier set cin=?,nom=?,prenom=?,email=?,numtel=?,adresse=? where cin=?");
            ps.setInt(1,ap.getCin());
            ps.setString(2, ap.getNom());
            ps.setString(3, ap.getPrenom());
            ps.setString(4, ap.getEmail());
            ps.setInt(5,ap.getNumtel());
            ps.setString(6, ap.getAdresse());
            ps.setInt(7, ap.getCin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : "+ex.getMessage());
        }

    }
     
     public void supprimerAssureParticulier(int cin){
          try {
            Statement st = Cn.createStatement();
            String req = "delete from assure_particulier where cin=" + cin;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(AssureParticulier.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
