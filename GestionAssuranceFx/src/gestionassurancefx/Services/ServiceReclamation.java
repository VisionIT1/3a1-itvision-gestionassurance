/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.Reclamation;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ServiceReclamation {
     Connection C = Connexion.getInstance().getCon();
    public void ajouterReclamation(Reclamation O) {
        try {
            PreparedStatement pst=C.prepareStatement("insert into reclamation values (null,?,?,?,?,?)");
             
             pst.setString(1,O.getTypeRec());
             pst.setString(2, O.getDescRec());
             pst.setDate(3, O.getDateSaisiRec());
             pst.setInt(4,O.getIdCli());
             pst.setInt(5,O.getTraitementRec());
            
             pst.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherReclamation() {
        try {
            Statement st = C.createStatement();

            String req = "select * from reclamation";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                System.out.println("type: " + rs.getString("typeRec") + " description: " + rs.getDate("dateSaisiRec") + " Client: " + rs.getInt("idCli")+ " traitement: " + rs.getString("traitementRec"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void modifierReclamation(Reclamation e) {
        try {
            PreparedStatement ps = C.prepareStatement("update reclamation set typeRec=?,descRec=?,dateSaisiRec=?,idCli=?,traitementRec=?  where idR=?");
            ps.setString(1, e.getTypeRec());
            ps.setString(2, e.getDescRec());
            ps.setDate(3, e.getDateSaisiRec());
            ps.setInt(4,e.getIdCli());
            ps.setInt(5, e.getTraitementRec());
            ps.setInt(6,2);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void supprimerReclamation(int id)
    {
       PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from reclamation where idR=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
