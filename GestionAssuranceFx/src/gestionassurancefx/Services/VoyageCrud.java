package gestionassurancefx.Services;



import gestionassurancefx.Entities.Voyage;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Psico
 */
public class VoyageCrud {
    Connection C = Connexion.getInstance().getCon();

    public void ajouterVoyage(Voyage v) {
     
           
        
        try {
            PreparedStatement pstmt=C.prepareStatement("insert into voyage (destination,dureeSejour,trancheAge) values (?,?,?)");
            
            pstmt.setString(1,v.getDest());
            pstmt.setInt(2,v.getDuree_sej());
            pstmt.setInt(3,v.getTranche_age());
            
            pstmt.executeUpdate(); 
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
                        
			
             
       
            
            
            

    }

   


    
     public ObservableList<Voyage> afficherVoyage() {
      
         try {
             ObservableList<Voyage> voyages=FXCollections.observableArrayList();
             PreparedStatement pstmt=C.prepareStatement("select * from voyage");
             ResultSet rs=pstmt.executeQuery();
            
             while (rs.next()){
                 voyages.add(new Voyage((rs.getInt(1)),rs.getString(2),rs.getInt(3),rs.getInt(4)));
             }
             pstmt.close();
             return voyages;
         } catch (SQLException ex) {
             return null;
         }

    }
      public void modifierVoyage(Voyage v) {
       
       
        try {
            PreparedStatement ps = C.prepareStatement("update voyage set destination=?,dureeSejour=?,trancheAge=? where id_voyage=?");
            ps.setString(1,v.getDest());
            ps.setInt(2, v.getDuree_sej());
            ps.setInt(3, v.getTranche_age());
            ps.setInt(4, v.getId_voyage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
           
             
             
       
    

    }
      
    public void SupprimerVoyage(int id) {
       
       
        try {
            PreparedStatement ps = C.prepareStatement("delete from voyage where id_voyage=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
      
}
      public int retourneidvoyage() {
      
         try {
             
             PreparedStatement pstmt=C.prepareStatement("select id_voyage from voyage order by id_voyage desc limit 1");
             ResultSet rs=pstmt.executeQuery();
             int idd=0;
             while (rs.next()){
                 idd=rs.getInt(1);
             }
             pstmt.close();
             return idd;
         } catch (SQLException ex) {
             return 0;
         }

    }
      
           public ObservableList<Voyage> afficherVoyageparId(int id) {
      
         try {
             ObservableList<Voyage> voyages=FXCollections.observableArrayList();
             PreparedStatement pstmt=C.prepareStatement("select * from voyage where id_voyage=?");
             pstmt.setInt(1,id);
             ResultSet rs=pstmt.executeQuery();
            
             while (rs.next()){
                 voyages.add(new Voyage((rs.getInt(1)),rs.getString(2),rs.getInt(3),rs.getInt(4)));
             }
             pstmt.close();
             return voyages;
         } catch (SQLException ex) {
             return null;
         }

    }
}
