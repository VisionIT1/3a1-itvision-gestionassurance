/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;


import gestionassurancefx.Entities.Reparateur;
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
 * @author ASUS
 */
public class ReparateurService {
    
        Connection C = Connexion.getInstance().getCon();
     
    public ObservableList<Reparateur> data=FXCollections.observableArrayList();
    
     public void ajouterReparateur(Reparateur E) {
        try {
            PreparedStatement pst = C.prepareStatement("insert into reparateur values (null,?,?,?,?,?,?,?,?,?)");

           
            pst.setInt(1, E.getCinRep());
            pst.setInt(2, E.getFaxRep());
            pst.setString(3, E.getNomRep());
            pst.setString(4, E.getPrenomRep());
            pst.setString(5, E.getMailRep());
            pst.setInt(6, E.getNumeroRep());
            pst.setString(7, E.getAdresseRep());
            pst.setString(8, E.getEtatRep());
            pst.setString(9, E.getDescriptionRep());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      public ObservableList<Reparateur> afficherReparateur() {

        try {
             data.removeAll(data);
            Statement st = C.createStatement();
            String req = "Select * from reparateur";
            ResultSet rs = st.executeQuery(req);
            
           

            while (rs.next()) {
               // data.add(new Expert(rs.getInt("idex"), rs.getInt("cinex"), rs.getInt("faxex"), rs.getString("nomex"), rs.getString("prenomex"), rs.getString("mailex"), rs.getInt("numeroex"), rs.getString("adresseex"), rs.getString("etatex"), rs.getString("descriptionex")));

                
                Reparateur c = new Reparateur();
                c.setIdRep(rs.getInt(1));
                c.setCinRep(rs.getInt(2));
                c.setFaxRep(rs.getInt(3));
                c.setNomRep(rs.getString(4));
                c.setPrenomRep(rs.getString(5));
                c.setMailRep(rs.getString(6));
                c.setNumeroRep(rs.getInt(7));
                c.setAdresseRep(rs.getString(8));
               c.setEtatRep(rs.getString(9));
                    c.setDescriptionRep(rs.getString(10));
                data.add(c);


            }
            return data;     
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       public void modifierReparateur(Reparateur e) {
        try {
            PreparedStatement ps = C.prepareStatement("update reparateur set cinrep=?,faxrep=?,nomrep=?,prenomrep=?,mailrep=?,numerorep=?,adresserep=?,etatrep=?,descriptionrep=?  where idrep=?");
        
            ps.setInt(1, e.getCinRep());
            ps.setInt(2, e.getFaxRep());
            ps.setString(3, e.getNomRep());
            ps.setString(4, e.getPrenomRep());
            ps.setString(5, e.getMailRep());
            ps.setInt(6, e.getNumeroRep());
            ps.setString(7, e.getAdresseRep());
            ps.setString(8, e.getEtatRep());
            ps.setString(9, e.getDescriptionRep());
            ps.setInt(10, e.getIdRep());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void supprimerReparateur(int id) {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from reparateur where idrep=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
