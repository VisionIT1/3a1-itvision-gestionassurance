/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Utils.Connexion;
import gestionassurancefx.Entities.Reglement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author youssef
 */
public class Service_Reglement {
      Connection c= Connexion.getInstance().getCon();
    public Service_Reglement(){}
    public void ajouterReglement(Reglement R){
       try{
      PreparedStatement pt=c.prepareStatement("insert into reglement  values (?,?,?,?,?)");
           pt.setInt(1,R.getCode_regl());
           
//l'indice par rapport a parametrede la requette du prepare
           pt.setDate(2,R.getDate_regl());
           pt.setInt(3,R.getFrais_expert());
           pt.setInt(4,R.getMode_regl());
           pt.setInt(5,R.getMontant_regl());
           pt.executeUpdate();
       }catch(SQLException ex){
           System.out.println("Echec d ajouter le Reglement");
       }
             
   }
    public void afficherReglement(){
		try { PreparedStatement pstmt=c.prepareStatement("select * from reglement");
		            ResultSet rs=pstmt.executeQuery();
       while(rs.next()){
           System.out.println("Reglent : "+rs.getInt(1)+"code "+rs.getString(2)+"frais expert"+rs.getInt(3)+"mode"+rs.getInt(4)+"montant"+rs.getInt(5));
       }
		      
       }
       catch(SQLException ex){
           System.out.println("echec affichage");
       }
       
   }
     
        public void modifierReglement(Reglement R){
       try{
           PreparedStatement pt=c.prepareStatement("update reglement set code_regl=?,date_regl=?,frais_expert=?,mode_regl=?,montant_regl=? where code_regl=?");
              pt.setInt(1,R.getCode_regl());
           
//l'indice par rapport a parametrede la requette du prepare
           pt.setDate(2,R.getDate_regl());
           pt.setInt(3,R.getFrais_expert());
           pt.setInt(4,R.getMode_regl());
           pt.setInt(5,R.getMontant_regl());
           pt.setInt(6,R.getCode_regl());
           pt.executeUpdate();       
       }catch(SQLException ex){     
       }
   }
        public void supprimerReglement(Reglement R){
       try{
           PreparedStatement pt=c.prepareStatement("delete from reglement where code_regl=?");
              pt.setInt(1,R.getCode_regl());
//l'indice par rapport a parametrede la requette du prepare
           pt.executeUpdate();       
       }catch(SQLException ex){     
       }
   }
}
