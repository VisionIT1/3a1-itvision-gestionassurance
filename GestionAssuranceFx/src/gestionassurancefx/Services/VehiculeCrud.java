/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;


import gestionassurancefx.Entities.Vehicule;
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

/**
 *
 * @author Psico
 */
public class VehiculeCrud {
     Connection C = Connexion.getInstance().getCon();

    public void ajouterVehicule(Vehicule v) {
     
           
         try {
                    PreparedStatement pstmt=C.prepareStatement("insert into vehicule (puissance,valNeuf,valVenale,anneConst,id_marque,immat,id_garantie) values (?,?,?,?,?,?,?)");
                       
			pstmt.setInt(1, v.getPuiss());
			pstmt.setFloat(2,v.getVal_neuf());
			pstmt.setFloat(3,v.getVal_venale());
			pstmt.setInt(4,v.getAnnne_consruct());
			pstmt.setInt(5,v.getId_marque());
			pstmt.setString(6,v.getImmat());
			pstmt.setInt(7,v.getId_garantie());
                        pstmt.executeUpdate();
                        pstmt.close(); 
                        
                        
			
             
         } catch (SQLException ex) {
             Logger.getLogger(VehiculeCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            
            

    }

   


    
     public ObservableList<Vehicule> afficherVehicule() {
      
         try {
             ObservableList<Vehicule> vehicules= FXCollections.observableArrayList();
             PreparedStatement pstmt=C.prepareStatement("select * from vehicule");
             ResultSet rs=pstmt.executeQuery();
           
             while (rs.next()){
                 vehicules.add(new Vehicule((rs.getInt(1)),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8)));
             }
             pstmt.close();
             return vehicules;
         } catch (SQLException ex) {
             return null;
         }

    }
      public void modifierVehicule(Vehicule v) {
       
         try {
             PreparedStatement ps = C.prepareStatement("update vehicule set puissance=?,valNeuf=?,valVenale=?,anneConst=?,id_marque=?,immat=?,id_garantie=? where id_vehicule=?");
             ps.setInt(1,v.getPuiss());
             ps.setFloat(2, v.getVal_neuf());
             ps.setFloat(3, v.getVal_venale());
             ps.setInt(4, v.getAnnne_consruct());
             ps.setInt(5, v.getId_marque());
             ps.setString(6, v.getImmat());
             ps.setInt(7,v.getId_garantie());
             ps.setInt(8,v.getId_vehicule());
             
             
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(VehiculeCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
    

    }
      
    public void SupprimerVehicule(int id) {
       
         try {
             PreparedStatement ps = C.prepareStatement("delete from vehicule where id_vehicule=?");
             ps.setInt(1, id);
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(VehiculeCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
           
      
}
    
     public int retourneidvehicule() {
      
         try {
             
             PreparedStatement pstmt=C.prepareStatement("select id_vehicule from vehicule order by id_vehicule desc  limit 1");
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
     public ObservableList<Vehicule> afficherVehiculeparID(int id) {
      
         try {
             ObservableList<Vehicule> vehicules= FXCollections.observableArrayList();
             PreparedStatement pstmt=C.prepareStatement("select * from vehicule where id_vehicule=?");
             pstmt.setInt(1, id);
             ResultSet rs=pstmt.executeQuery();
           
             while (rs.next()){
                 vehicules.add(new Vehicule(rs.getString(7),rs.getInt(2),rs.getFloat(3),rs.getFloat(4),rs.getInt(5),rs.getInt(6),rs.getInt(8)));
             }
             pstmt.close();
             return vehicules;
         } catch (SQLException ex) {
             return null;
         }

    }
}
