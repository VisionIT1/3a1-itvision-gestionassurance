/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahmed Derbel
 */
public class ContratCrud {

    Connection Cn = Connexion.getInstance().getCon();

    public void ajouterContrat(Contrat C) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            //  String req = "insert into assurance values('" + C.getNom()+ "','" + C.getDescription()+ "','" + C.getId_client()+ "','" + C.getType()+ "','" + C.getDate_debut() + "','" + C.getDate_Echeance()+"')";
            String req = "insert into contrat ( nom, description, cin_assure, type, date_debut, date_echeance,etat,prime) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, C.getNom());
            ste.setString(2, C.getDescription());
            ste.setInt(3, C.getCin_assure());
            ste.setString(4, C.getType());
            ste.setDate(5, C.getDate_debut());
            ste.setDate(6, C.getDate_Echeance());
            ste.setInt(7, C.getEtat());
            ste.setFloat(8, C.getPrime());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }

    public void SupprimerContrat(int id) {
        try {
            Statement st = Cn.createStatement();
            String req = "delete from contrat where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Contrat> getAllContrat() {
        ObservableList<Contrat> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "select * from contrat";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Contrat c = new Contrat();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setCin_assure(rs.getInt(4));
                c.setType(rs.getString(5));
                c.setDate_debut(rs.getDate(6));
                c.setDate_Echeance(rs.getDate(7));
                c.setEtat(rs.getInt(8));
                c.setPrime(rs.getFloat(9));
                l.add(c);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }

    public void modifierContrat(Contrat c) {
        try {
            PreparedStatement ps = Cn.prepareStatement("update contrat set nom=?,description=?,id_assure=?,type=?,date_debut=?,date_echeance=?,etat=?,prime=? where id=?");
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getCin_assure());
            ps.setString(4, c.getType());
            ps.setDate(5, c.getDate_debut());
            ps.setDate(6, c.getDate_Echeance());
            ps.setInt(7, c.getEtat());
            ps.setFloat(8, c.getPrime());
            ps.setInt(9, c.getId());
            ps.executeUpdate();
            System.out.println(c.getNom());
        } catch (SQLException ex) {
            System.out.println("error : "+ex.getCause());
        }

    }
    
    public ObservableList<String> getNomAss(){
        ObservableList<String> listid = FXCollections.observableArrayList();
        try {
            Statement st = Cn.createStatement();
            String req = "select a.id,a.nom , a.prenom from  assure_particulier a ;";
            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
               
               
                listid.add(rs.getInt("id")+" "+ rs.getString("nom")+" "+rs.getString("prenom"));
            }

            return listid;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
   public int getIdAss(int id){
        try {
            Statement st = Cn.createStatement();
            String req = "select a.id from  assure_particulier a where a.id ="+ id;
            ResultSet rs = st.executeQuery(req); //retourne un résulat
                int idass=0;
            while (rs.next()) {
               
               idass=rs.getInt("id");
            }

            return idass;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
           return 0;
        }
        
   }
   
  
   
   

}
