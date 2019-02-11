/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;
import Entities.Expert;
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
public class ExpertService {
    
     Connection C = Connexion.getInstance().getCon();

    public void ajouterExpert(Expert E) {
        try {
            Statement st = C.createStatement(); //l'element qui va éxécuter la requete sql

            String req = "insert into expert values('"+ E.getIdEx() + "','" + E.getCinEx() + "','" + E.getFaxEx() + "','" + E.getNomEx() + "','" + E.getPrenomEx() + "','" + E.getMailEx() + "','"  + E.getNumeroEx() +"','" + E.getAdresseEx() +"','"+ E.getEtatEx() +"','"+E.getDescriptionEx() +"')";

     st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    
     public void afficherExpert() {
        try {
            Statement st = C.createStatement();

            String req = "select * from expert";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                System.out.println("id:" + rs.getInt(1)+"Cin:" + rs.getInt(2) + " Fax: " + rs.getString(3) + " Nom: " + rs.getString(4)+ " Prenom: " + rs.getString(5)+" Mail: " + rs.getString(6)+" numero: " + rs.getString(7)+" adresse: " + rs.getString(8)+" Etat: " + rs.getString(9)+" Description: " + rs.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      public void modifierExpert(Expert e) {
        try {
            PreparedStatement ps = C.prepareStatement("update expert set mailex=?,numeroex=?,adresseex=?,etatex=?,descriptionex=? where idex=?");
            ps.setString(1, e.getMailEx());
            ps.setInt(2, e.getNumeroEx());
             ps.setString(3, e.getAdresseEx());
              ps.setString(4, e.getEtatEx());
                ps.setString(5, e.getDescriptionEx());
                 ps.setInt(6, e.getIdEx());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void SupprimerExpert(int id) {
        try {
             Statement st = C.createStatement();
             String req = "delete from expert WHERE idex="+id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        } catch (SQLException ex) {
            Logger.getLogger(ExpertService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    
    
}