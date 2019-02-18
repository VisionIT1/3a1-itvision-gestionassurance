/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Entities.AssureParticulier;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
    
}
