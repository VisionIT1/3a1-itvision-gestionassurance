/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Entities.User;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahmed Derbel
 */
public class UserCrud {

    Connection Cn = Connexion.getInstance().getCon();

    public void ajouterUser(User U) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

            //  String req = "insert into assurance values('" + C.getNom()+ "','" + C.getDescription()+ "','" + C.getId_client()+ "','" + C.getType()+ "','" + C.getDate_debut() + "','" + C.getDate_Echeance()+"')";
            String req = "insert into fos_user ( username, username_canonical, email, email_canonical, enabled, salt,password,last_login,confirmation_token,password_requested_at,roles) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, U.getUsername());
            ste.setString(2, U.getUsername_canonical());
            ste.setString(3, U.getEmail());
            ste.setString(4, U.getEmail_canonical());
            ste.setBoolean(5, U.getEnabled());
            ste.setString(6, U.getSalt());
            ste.setString(7, U.getPassword());
            ste.setDate(8, U.getLast_login());
            ste.setString(9, U.getConfirmation_token());
            ste.setDate(10, U.getPassword_requested_at());
            ste.setString(11, U.getRoles());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : " + ex.getMessage());
        }

    }
    
    public void SupprimerUser(int id) {
        try {
            Statement st = Cn.createStatement();
            String req = "delete from fos_user where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    public ObservableList<User> getAllUser() {
        ObservableList<User> l = FXCollections.observableArrayList();

        try {
            Statement st = Cn.createStatement();

            String req = "select * from fos_user";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                User U = new User();
                U.setId(rs.getInt(1));
                U.setUsername(rs.getString(2));
                U.setUsername_canonical(rs.getString(3));
                U.setEmail(rs.getString(4));
                U.setEmail_canonical(rs.getString(5));
                U.setEnabled(rs.getBoolean(6));
                U.setSalt(rs.getString(7));
                U.setPassword(rs.getString(8));
                U.setLast_login(rs.getDate(9));
                U.setConfirmation_token(rs.getString(10));
                U.setPassword_requested_at(rs.getDate(11));
                U.setRoles(rs.getString(12));
                l.add(U);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    
    
}
