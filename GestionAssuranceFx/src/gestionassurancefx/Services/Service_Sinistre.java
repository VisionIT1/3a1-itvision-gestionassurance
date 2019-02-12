/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Utils.Connexion;
import gestionassurancefx.Entities.Sinistre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author youssef
 */
public class Service_Sinistre {
    
    Connection c= Connexion.getInstance().getCon();
    public Service_Sinistre(){}
    public void ajouterReglement(Sinistre R){
       try{
      PreparedStatement pt=c.prepareStatement("insert into sinistre  values (?,?,?,?,?,?,?,?,?)");
           pt.setInt(1,R.getCode_sinistre());
           
//l'indice par rapport a parametrede la requette du prepare
           pt.setDate(2,R.getDate_declaration());
           pt.setDate(3,R.getDate_sinistre());
           pt.setString(4,R.getLieu_sinistre());
           pt.setInt(5,R.getNumero_sinistre());
           pt.setInt(6,R.getDomage_mat());
           pt.setInt(7,R.getDomage_corps());
           pt.setInt(8,R.getCode_assurance());
           pt.setString(9,R.getDescription());
           pt.executeUpdate();
       }catch(SQLException ex){
           System.out.println("Echec d ajouter le Reglement");
       }
             
   }
    public void afficherSinistre(){
		try { PreparedStatement pstmt=c.prepareStatement("select * from sinistre");
		            ResultSet rs=pstmt.executeQuery();
       while(rs.next()){
           System.out.println("Sinistre : "+rs.getInt(1)+"date declaration"+rs.getDate(2)+"date sinistre "+rs.getDate(3)+"lieu sinistre"+rs.getString(4)+"numero sinistre"+rs.getInt(5)+"domage mat"+rs.getInt(6)+"domage corp"+rs.getInt(7)+"code assureur"+rs.getInt(8)+"Description "+rs.getString(9));
       }
		      
       }
       catch(SQLException ex){
           System.out.println("echec affichage");
       }
       
   }
     
        public void modifierSinistre(Sinistre R){
       try{
           PreparedStatement pt=c.prepareStatement("update sinistre set code_sinistre=?,date_declaration=?,date_sinistre=?,lieu_sinistre=?,numero_sinistre=?,dommage_mat=?,dommage_corp=?,code_assureur=?,description=? where code_sinistre=?");
       pt.setInt(1,R.getCode_sinistre());
           
//l'indice par rapport a parametrede la requette du prepare
           pt.setDate(2,R.getDate_declaration());
           pt.setDate(3,R.getDate_sinistre());
           pt.setString(4,R.getLieu_sinistre());
           pt.setInt(5,R.getNumero_sinistre());
           pt.setInt(6,R.getDomage_mat());
           pt.setInt(7,R.getDomage_corps());
           pt.setInt(8,R.getCode_assurance());
           pt.setString(9,R.getDescription());
           pt.setInt(10, R.getCode_sinistre());
           pt.executeUpdate();       
       }catch(SQLException ex){     
       }
   }
        public void supprimerReglement(Sinistre R){
       try{
           PreparedStatement pt=c.prepareStatement("delete from sinistre where code_sinistre=?");
              pt.setInt(1,R.getCode_sinistre());
//l'indice par rapport a parametrede la requette du prepare
           pt.executeUpdate();       
       }catch(SQLException ex){     
       }
   }
    
}
