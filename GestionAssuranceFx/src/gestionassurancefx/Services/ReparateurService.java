/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;


import Entities.Reparateur;
import Utiles.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ReparateurService {
    
    Connection C = Connexion.getInstance().getCon();

    public void ajouterReparateur(Reparateur E) {
        try {
            Statement st = C.createStatement(); //l'element qui va éxécuter la requete sql

            String req = "insert into reparateur values('"+ E.getIdRep() + "','" + E.getCinRep() + "','" + E.getFaxRep() + "','" + E.getNomRep() + "','" + E.getPrenomRep() + "','" + E.getMailRep() + "','"  + E.getNumeroRep() +"','" + E.getAdresseRep() +"','"+ E.getEtatRep() +"','"+E.getDescriptionRep() +"')";

     st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

}
      public void afficherReparateur() {
        try {
            Statement st = C.createStatement();

            String req = "select * from reparateur";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                System.out.println("id:" + rs.getInt(1)+"Cin:" + rs.getInt(2) + " Fax: " + rs.getString(3) + " Nom: " + rs.getString(4)+ " Prenom: " + rs.getString(5)+" Mail: " + rs.getString(6)+" numero: " + rs.getString(7)+" adresse: " + rs.getString(8)+" Etat: " + rs.getString(9)+" Description: " + rs.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void modifierReparateur(Reparateur R) {
        try {
            PreparedStatement ps = C.prepareStatement("update reparateur set mailrep=?,numerorep=?,adresserep=?,etatrep=?,descriptionrep=? where idrep=?");
            ps.setString(1, R.getMailRep());
            ps.setInt(2, R.getNumeroRep());
             ps.setString(3, R.getAdresseRep());
              ps.setString(4, R.getEtatRep());
                ps.setString(5, R.getDescriptionRep());
                 ps.setInt(6, R.getIdRep());
         ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public void SupprimerReparateur(int id) {
     
            
             try {
             Statement st = C.createStatement();
             String req = "delete from reparateur WHERE idrep="+id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ReparateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    
}
