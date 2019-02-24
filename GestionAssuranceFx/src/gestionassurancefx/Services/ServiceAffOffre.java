/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.AffectationOffre;
import gestionassurancefx.Entities.OffreBonConducteur;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ServiceAffOffre {
    Connection C = Connexion.getInstance().getCon();
    public void ajouterAffectation(AffectationOffre A) {
        try {
            PreparedStatement pst = C.prepareStatement("insert into AffectationOffre values (?,?)");

            pst.setInt(1,A.getIdOffre());
            pst.setInt(2,A.getIdAssure());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void supprimerAffectation(int id) {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from affectationoffre where idoffre=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public boolean assureExiste(int id)
    {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("select idAssure from affectationoffre");
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("idAssure")==id)
                    return true;
            }
            return false;
           
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
