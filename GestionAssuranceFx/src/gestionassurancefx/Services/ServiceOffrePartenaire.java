/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.OffrePartenaire;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ServiceOffrePartenaire {
    Connection C = Connexion.getInstance().getCon();
    public void ajouterOffrePartenaire(OffrePartenaire O) {
        try {
            PreparedStatement pst=C.prepareStatement("insert into offrepartenaire values (null,?,?,?,?,?,?)");
             
             pst.setString(1,O.getLibOffre());
             pst.setDate(2, O.getDateDebutOffre());
             pst.setDate(3, (Date) O.getDateFinOffre());
             pst.setInt(4,O.getPourcentageReduction());
             pst.setString(5,O.getPartenaire());
             pst.setString(6,O.getDescripOffre());
             pst.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffrePartenaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherOffrePartenaire() {
        try {
            Statement st = C.createStatement();

            String req = "select * from offrepartenaire";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                System.out.println("lib: " + rs.getString("libOffre") + " dateDebut: " + rs.getDate("datedebutoffre") + " dateFin: " + rs.getDate("datefinoffre")+ " pourcentage: " + rs.getInt("pourcentagereduction")+" partenaire: " + rs.getString("partenaire")+" description: " + rs.getString("descripoffre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffrePartenaire.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void modifierOffrePartenaire(OffrePartenaire e) {
        try {
            PreparedStatement ps = C.prepareStatement("update offrepartenaire set libOffre=?,dateDebutOffre=?,dateFinOffre=?,pourcentageReduction=?,partenaire=?,descripOffre=?  where idOffre=?");
            ps.setString(1, e.getLibOffre());
            ps.setDate(2, e.getDateDebutOffre());
            ps.setDate(3, e.getDateFinOffre());
            ps.setInt(4,e.getPourcentageReduction());
            ps.setString(5, e.getPartenaire());
            ps.setString(6, e.getDescripOffre());
            ps.setInt(7,2);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffrePartenaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void supprimerOffrePartenaire(int id)
    {
       PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from offrepartenaire where idoffre=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffrePartenaire.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
