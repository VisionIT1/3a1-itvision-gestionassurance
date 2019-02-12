/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.LigneGarantie;
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
public class LigneGarantieCrud {
    Connection C = Connexion.getInstance().getCon();

    public void ajouterLigneGarantie (LigneGarantie lg) {
     
            
      
        try {
            PreparedStatement pstmt=C.prepareStatement("insert into ligne_garantie (id_type,id_garantie,prime) values (?,?,?)");
            
            pstmt.setInt(1,lg.getId_voyage());
            pstmt.setInt(2,lg.getId_garantie());
            pstmt.setFloat(3,lg.getPrime());
            
            
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(LigneGarantieCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
      
                 

    }

     public List<LigneGarantie> afficherListeGarantie() {
      
         try {
             List<LigneGarantie> LigneGaranties= new ArrayList<LigneGarantie>();
             PreparedStatement pstmt=C.prepareStatement("select * from ligne_garantie");
             ResultSet rs=pstmt.executeQuery();
            
             while (rs.next()){
                 LigneGaranties.add(new LigneGarantie((rs.getInt(1)),(rs.getInt(2)),rs.getInt(3),rs.getFloat(4)));
             }
             pstmt.close();
             return LigneGaranties;
         } catch (SQLException ex) {
             return null;
         }

    }
      public void modifierLigneGarantie(LigneGarantie lg) {
       
       
      
      
        try {
            PreparedStatement ps = C.prepareStatement("update ligne_garantie set id_type=?,id_garantie=?,prime=? where idgh=?");
            ps.setInt(1,lg.getId_voyage());
            ps.setInt(2,lg.getId_garantie());
            ps.setFloat(3,lg.getPrime());
            ps.setInt(4,lg.getId_ligne());
        } catch (SQLException ex) {
            Logger.getLogger(LigneGarantieCrud.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
      
    public void SupprimerLigneGarantie (int id) {
      
        try {
            PreparedStatement ps = C.prepareStatement("delete from ligne_garantie where idgh=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LigneGarantieCrud.class.getName()).log(Level.SEVERE, null, ex);
        }          
      
}
}
