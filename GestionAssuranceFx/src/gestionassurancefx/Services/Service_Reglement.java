/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Utils.Connexion;
import gestionassurancefx.Entities.Reglement;
import gestionassurancefx.Entities.Sinistre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author youssef
 */
public class Service_Reglement {
    Connection c= Connexion.getInstance().getCon();
    public Service_Reglement(){}
    public void ajouterReglement(Reglement R){
       try{
      PreparedStatement pt=c.prepareStatement("insert into reglement (date_regl,frais_expert,mode_regl,montant_regl,idEx,cinAssureur,idRep,code_sin)  values (?,?,?,?,?,?,?,?)");
           
           
//l'indice par rapport a parametrede la requette du prepare
           pt.setDate(1,R.getDate_regl());
           pt.setInt(2,R.getFrais_expert());
           pt.setString(3,R.getMode_regl());
           pt.setInt(4,R.getMontant_regl());
           pt.setInt(5,R.getIdEx());
           pt.setInt(6,R.getCinAssureur());
           pt.setInt(7,R.getIdRep());
           pt.setInt(8,R.getCode_sin());
           
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
           PreparedStatement pt=c.prepareStatement("update reglement set date_regl=?,frais_expert=?,mode_regl=?,montant_regl=?,idEx=?,idRep=? where code_regl=?");
              
           
//l'indice par rapport a parametrede la requette du prepare
           pt.setDate(1,R.getDate_regl());
           pt.setInt(2,R.getFrais_expert());
           pt.setString(3,R.getMode_regl());
           pt.setInt(4,R.getMontant_regl());
           pt.setInt(5,R.getIdEx());
           pt.setInt(6,R.getIdRep());
           pt.setInt(7,R.getCode_regl());
           pt.executeUpdate();       
       }catch(SQLException ex){     
       }
   }
        public void supprimerReglement(int r){
       try{
           PreparedStatement pt=c.prepareStatement("delete from reglement where code_regl=?");
              pt.setInt(1,r);
//l'indice par rapport a parametrede la requette du prepare
           pt.executeUpdate();       
       }catch(SQLException ex){     
       }
   }
            public ObservableList<Reglement> getAllReglement() {
        ObservableList<Reglement> l = FXCollections.observableArrayList();

        try {
            Statement st = c.createStatement();

            String req = "select * from reglement";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Reglement Rg = new Reglement();
                Rg.setCode_regl(rs.getInt(1));
                Rg.setDate_regl(rs.getDate(2));
                Rg.setFrais_expert(rs.getInt(3));
                Rg.setMode_regl(rs.getString(4));
                Rg.setMontant_regl(rs.getInt(5));
                Rg.setIdEx(rs.getInt(6));
                Rg.setCinAssureur(rs.getInt(7));
                Rg.setIdRep(rs.getInt(8));
                Rg.setCode_sin(rs.getInt(9));
                l.add(Rg);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
}
