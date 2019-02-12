/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;


import gestionassurancefx.Entities.Marque;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Psico
 */
public class MarqueCrud {
    Connection C = Connexion.getInstance().getCon();

    public void ajouterMarque(Marque m) {
     
            
        try {
            PreparedStatement pstmt=C.prepareStatement("insert into marque (libelle) values (?)");
            
            pstmt.setString(1,m.getLib_mrq());
            
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarqueCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
                 

    }

   


    
     public List<Marque> afficherMarque() {
      
         try {
             List<Marque> marques= new ArrayList<Marque>();
             PreparedStatement pstmt=C.prepareStatement("select * from marque");
             ResultSet rs=pstmt.executeQuery();
            
             while (rs.next()){
                 marques.add(new Marque((rs.getInt(1)),rs.getString(2)));
             }
             pstmt.close();
             return marques;
         } catch (SQLException ex) {
             return null;
         }

    }
      public void modifierMarque(Marque m) {
       
       
      
        try {
            PreparedStatement ps = C.prepareStatement("update marque set libelle=? where id_marque=?");
            ps.setString(1,m.getLib_mrq());
            
            ps.setInt(2,m.getId_marque());
        } catch (SQLException ex) {
            Logger.getLogger(MarqueCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
            
      
           
             
             
       
    

    }
      
    public void SupprimerVoyage(int id) {
       
       
      
           
        try {
            PreparedStatement ps = C.prepareStatement("delete from marque where id_marque=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarqueCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       
       
           
      
}
    
}
