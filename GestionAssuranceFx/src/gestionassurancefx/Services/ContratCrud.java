/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import com.teknikindustries.bulksms.SMS;
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
            String req = "insert into contrat ( nom, description, cin_assure,nomEntr, type,id_type, date_debut, date_echeance,etat,prime) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, C.getNom());
            ste.setString(2, C.getDescription());
            ste.setInt(3, C.getCin_assure());
            ste.setString(4, C.getNomEntr());
            ste.setString(5, C.getType());
            ste.setInt(6, C.getId_type());
            ste.setDate(7, C.getDate_debut());
            ste.setDate(8, C.getDate_Echeance());
            ste.setInt(9, C.getEtat());
            ste.setFloat(10, C.getPrime());
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
                c.setNomEntr(rs.getString(5));
                c.setType(rs.getString(6));
                c.setId_type(rs.getInt(7));
                c.setDate_debut(rs.getDate(8));
                c.setDate_Echeance(rs.getDate(9));
                c.setEtat(rs.getInt(10));
                c.setPrime(rs.getFloat(11));
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
            PreparedStatement ps = Cn.prepareStatement("update contrat set nom=?,description=?,cin_assure=?,nomEntr=?,type=?,id_type=?,date_debut=?,date_echeance=?,etat=?,prime=? where id=?");
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
            ps.setInt(3, c.getCin_assure());
            ps.setString(4, c.getNomEntr());
            ps.setString(5, c.getType());
            ps.setInt(6, c.getId_type());
            ps.setDate(7, c.getDate_debut());
            ps.setDate(8, c.getDate_Echeance());
            ps.setInt(9, c.getEtat());
            ps.setFloat(10, c.getPrime());
            ps.setInt(11, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }

    public ObservableList<String> getNomAss() {
        ObservableList<String> listid = FXCollections.observableArrayList();
        try {
            Statement st = Cn.createStatement();
            String req = "select a.id,a.nom , a.prenom from  assure_particulier a ;";
            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {

                listid.add(rs.getInt("id") + " " + rs.getString("nom") + " " + rs.getString("prenom"));
            }

            return listid;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }

    public int getIdAss(int id) {
        try {
            Statement st = Cn.createStatement();
            String req = "select a.id from  assure_particulier a where a.id =" + id;
            ResultSet rs = st.executeQuery(req); //retourne un résulat
            int idass = 0;
            while (rs.next()) {

                idass = rs.getInt("id");
            }

            return idass;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return 0;
        }

    }

    public void getExpiredContrat() throws SQLException {

        int numtel = 0;
        String nom = "";
        String type = "";
        SMS smstut = new SMS();
        Statement st = Cn.createStatement();
        String req = "select c.cin_assure , c.type , c.date_echeance , e.numtel,e.nom from  contrat c,assure_particulier e where date_echeance = CURRENT_DATE -4 and c.cin_assure=e.cin ";
        ResultSet rs = st.executeQuery(req); //retourne un résulat
        while (rs.next()) {

            numtel = rs.getInt("e.numtel");
            nom = rs.getString("e.nom");
            type = rs.getString("c.type");
        }

        //  smstut.SendSMS("imentouati","imentouati1","Bienvneue Mr "+nom+" Votre contrat d'assurance de type "+type+" va éxpirer dans 3 jours ","216"+Integer.toString(numtel),"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    }

}
