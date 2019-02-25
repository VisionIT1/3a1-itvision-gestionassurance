/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import Utils.BCrypt;
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

            String req = "insert into fos_user ( username, username_canonical, email, email_canonical, enabled, salt,password,last_login,confirmation_token,password_requested_at,roles) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, U.getUsername());
            ste.setString(2, U.getUsername_canonical());
            ste.setString(3, U.getEmail());
            ste.setString(4, U.getEmail_canonical());
            ste.setBoolean(5,U.getEnabled());
            ste.setString(6, U.getSalt());
            ste.setString(7, BCrypt.hashpw(U.getPassword(), BCrypt.gensalt(12)));
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
                User U = new User(rs.getInt(1),rs.getString(2),rs.getString(4),true,rs.getString(8),rs.getString(12));
              
                l.add(U);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur juhyhyhyhununuyyh" + ex.getMessage());
            return null;
        }
    }

    public void modifierUser(User u){
        try {
            PreparedStatement ps = Cn.prepareStatement("update fos_user set username=?,email=?,enabled=?,password=?,roles=? where id=?");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setBoolean(3, u.getEnabled());
            ps.setString(4, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));
            ps.setString(5, u.getRoles());            
            ps.setInt(6, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error : "+ex.getCause());
        }
    }
    
    
    
    public User VerifyUser(String username, String password) {
		User u = new User();
		Boolean found = false;
		
		String query = "select * from fos_user where username = '" + username + "'";
		try {
			Statement st = Cn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
                            
                            u.setUsername(rs.getString(2));
                            u.setPassword(rs.getString(8));
                            u.setRoles(rs.getString(12));
			
			}
			return u;
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
            return null;
                }
		
	}
    
   
}
