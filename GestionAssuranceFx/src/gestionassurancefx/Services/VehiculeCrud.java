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
			pstmt.setInt(2,v.getVal_neuf());
			pstmt.setInt(3,v.getVal_venale());
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

   


    
     public List<Vehicule> afficherVehicule() {
      
         try {
             List<Vehicule> vehicules= new ArrayList<Vehicule>();
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
             ps.setInt(2, v.getVal_neuf());
             ps.setInt(3, v.getVal_venale());
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
}
