/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Utils.Connexion;
import gestionassurancefx.Entities.Reglement;
import gestionassurancefx.Entities.Sinistre;
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
 * @author youssef
 */
public class Service_Sinistre {

    Connection c = Connexion.getInstance().getCon();

    public Service_Sinistre() {
    }

    public int ajouterSinistre(Sinistre R) {
        try {
            PreparedStatement pt = c.prepareStatement("insert into sinistre (date_declaration,date_sinistre,lieu_sinistre,numero_sinistre,dommage_mat,dommage_corp,code_assureur,description)  values (?,?,?,?,?,?,?,?)");
            

//l'indice par rapport a parametrede la requette du prepare
            pt.setDate(1, R.getDate_declaration());
            pt.setDate(2, R.getDate_sinistre());
            pt.setString(3, R.getLieu_sinistre());
            pt.setInt(4, R.getNumero_sinistre());
            pt.setInt(5, R.getDomage_mat());
            pt.setInt(6, R.getDomage_corps());
            pt.setInt(7, R.getCode_assureur());
            pt.setString(8, R.getDescription());
            pt.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            System.out.println("Echec d ajouter le Reglement");
        }
        return 0;
    }

    public void afficherSinistre() {
        try {
            PreparedStatement pstmt = c.prepareStatement("select * from sinistre");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Sinistre : " + rs.getInt(1) + "date declaration" + rs.getDate(2) + "date sinistre " + rs.getDate(3) + "lieu sinistre" + rs.getString(4) + "numero sinistre" + rs.getInt(5) + "domage mat" + rs.getInt(6) + "domage corp" + rs.getInt(7) + "code assureur" + rs.getInt(8) + "Description " + rs.getString(9));
            }

        } catch (SQLException ex) {
            System.out.println("echec affichage");
        }

    }

    public void modifierSinistre(Sinistre R) {
        try {
            PreparedStatement pt = c.prepareStatement("update sinistre set date_sinistre=?,lieu_sinistre=?,numero_sinistre=?,dommage_mat=?,dommage_corp=?,description=? where code_sinistre=?");
           

//l'indice par rapport a parametrede la requette du prepare
            
            pt.setDate(1, R.getDate_sinistre());
            pt.setString(2, R.getLieu_sinistre());
            pt.setInt(3, R.getNumero_sinistre());
            pt.setInt(4, R.getDomage_mat());
            pt.setInt(5, R.getDomage_corps());
            
            pt.setString(6, R.getDescription());
            pt.setInt(7, R.getCode_sinistre());
            pt.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void supprimerSinistre(int code_sinistre) {
        try {
            Statement st = c.createStatement();
            String req = "delete from sinistre where code_sinistre=" + code_sinistre;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Sinistre> getAllSinistre() {
        ObservableList<Sinistre> l = FXCollections.observableArrayList();

        try {
            Statement st = c.createStatement();

            String req = "select * from sinistre";

            ResultSet rs = st.executeQuery(req); //retourne un résulat

            while (rs.next()) {
                Sinistre sn = new Sinistre();
                sn.setCode_sinistre(rs.getInt(1));
                sn.setDate_declaration(rs.getDate(2));
                sn.setDate_sinistre(rs.getDate(3));
                sn.setLieu_sinistre(rs.getString(4));
                sn.setNumero_sinistre(rs.getInt(5));
                sn.setDomage_mat(rs.getInt(6));
                sn.setDomage_corps(rs.getInt(7));
                sn.setCode_assureur(rs.getInt(8));
                sn.setDescription(rs.getString(9));
                l.add(sn);
            }

            return l;
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
            return null;
        }
    }
    
    
    //***************************************************
        public int trouverCin(int cin) {
        try {
            PreparedStatement pstmt = c.prepareStatement("select * from assure_particulier where id ="+cin);
            ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
            return rs.getInt(1);
             }

        } catch (SQLException ex) {
            System.out.println("echec affichage");
        }
            return 0;
    }

         public int idtrouve() {
        try {
            PreparedStatement pstmt = c.prepareStatement("select code_sinistre from sinistre order by code_sinistre desc limit 1");
            ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
            return rs.getInt(1);
             }

        } catch (SQLException ex) {
            System.out.println("echec affichage");
        }
            return 0;
    }

}
