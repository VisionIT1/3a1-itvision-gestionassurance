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
import java.sql.SQLException;
import java.sql.Statement;

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
}


