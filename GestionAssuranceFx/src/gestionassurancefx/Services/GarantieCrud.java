/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;


import gestionassurancefx.Entities.Garantie;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Psico
 */
public class GarantieCrud {
        Connection C = Connexion.getInstance().getCon();

    public void ajouterGarantie(Garantie g) {
     
           
        
      
            try {
                PreparedStatement pstmt=C.prepareStatement("insert into garantie (lib,prime,categorie) values (?,?,?)");
                
                pstmt.setString(1,g.getLib());
                pstmt.setFloat(2,g.getPrime());
                pstmt.setString(3,g.getCategorie());
                
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(GarantieCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
       
                        
                        
			
             
       
            
            
            

    }

   


    
     public ObservableList<Garantie> afficherGarantie() {
      
         try {
             ObservableList<Garantie> garanties= FXCollections.observableArrayList();
             PreparedStatement pstmt=C.prepareStatement("select * from garantie");
             ResultSet rs=pstmt.executeQuery();
            
             while (rs.next()){
                 if (rs.getString(4).trim().equals("voy")){
                 garanties.add(new Garantie((rs.getInt(1)),rs.getString(2),rs.getFloat(3),"Voyage"));
                 }else{
                    garanties.add(new Garantie((rs.getInt(1)),rs.getString(2),rs.getFloat(3),"Vehicule")); 
                 }
             }
             pstmt.close();
             return garanties;
         } catch (SQLException ex) {
             return null;
         }

    }
      public void modifierGarantie(Garantie g) {
       
       
      
            try {
                PreparedStatement ps = C.prepareStatement("update garantie set lib=?,prime=?,categorie=? where id_garantie=?");
                ps.setString(1,g.getLib());
                ps.setFloat(2, g.getPrime());
                ps.setString(3,g.getCategorie());
                ps.setInt(4,g.getId_garantie());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(GarantieCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
      
           
             
             
       
    

    }
      
    public void SupprimerGarantie(int id) {
       
       
      
            try {
                PreparedStatement ps = C.prepareStatement("delete from garantie where id_garantie=?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(GarantieCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
       
       
           
      
}
}
