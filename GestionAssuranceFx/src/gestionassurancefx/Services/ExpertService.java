/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Entities.Expert;
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
public class ExpertService {
    
     Connection C = Connexion.getInstance().getCon();
     
    public ObservableList<Expert> data=FXCollections.observableArrayList();
    
     public void ajouterExpert(Expert E) {
        try {
            PreparedStatement pst = C.prepareStatement("insert into expert values (null,?,?,?,?,?,?,?,?,?)");

           
            pst.setInt(1, E.getCinEx());
            pst.setInt(2, E.getFaxEx());
            pst.setString(3, E.getNomEx());
            pst.setString(4, E.getPrenomEx());
            pst.setString(5, E.getMailEx());
            pst.setInt(6, E.getNumeroEx());
            pst.setString(7, E.getAdresseEx());
            pst.setString(8, E.getEtatEx());
            pst.setString(9, E.getDescriptionEx());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     

    public ObservableList<Expert> afficherExpert() {

        try {
             data.removeAll(data);
            Statement st = C.createStatement();
            String req = "Select * from expert";
            ResultSet rs = st.executeQuery(req);
            
           

            while (rs.next()) {
               // data.add(new Expert(rs.getInt("idex"), rs.getInt("cinex"), rs.getInt("faxex"), rs.getString("nomex"), rs.getString("prenomex"), rs.getString("mailex"), rs.getInt("numeroex"), rs.getString("adresseex"), rs.getString("etatex"), rs.getString("descriptionex")));

                
                Expert c = new Expert();
                c.setIdEx(rs.getInt(1));
                c.setCinEx(rs.getInt(2));
                c.setFaxEx(rs.getInt(3));
                c.setNomEx(rs.getString(4));
                c.setPrenomEx(rs.getString(5));
                c.setMailEx(rs.getString(6));
                c.setNumeroEx(rs.getInt(7));
                c.setAdresseEx(rs.getString(8));
               c.setEtatEx(rs.getString(9));
                    c.setDescriptionEx(rs.getString(10));
                data.add(c);


            }
            return data;     
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    

    public void modifierExpert(Expert e) {
        try {
            PreparedStatement ps = C.prepareStatement("update expert set cinex=?,faxex=?,nomex=?,prenomex=?,mailex=?,numeroex=?,adresseex=?,etatex=?,descriptionex=?  where idex=?");
        
            ps.setInt(1, e.getCinEx());
            ps.setInt(2, e.getFaxEx());
            ps.setString(3, e.getNomEx());
            ps.setString(4, e.getPrenomEx());
            ps.setString(5, e.getMailEx());
            ps.setInt(6, e.getNumeroEx());
            ps.setString(7, e.getAdresseEx());
            ps.setString(8, e.getEtatEx());
            ps.setString(9, e.getDescriptionEx());
            ps.setInt(10, e.getIdEx());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    


    public void supprimerExpert(int id) {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from expert where idex=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
