/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestionassurancefx.Utils.*;
import gestionassurancefx.Entities.*;
import java.sql.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class ServiceOffreBonConducteur {

    Connection C = Connexion.getInstance().getCon();
    ObservableList<OffreBonConducteur> listE = FXCollections.observableArrayList();

    public void ajouterOffreBonConducteur(OffreBonConducteur O) {
        try {
            PreparedStatement pst = C.prepareStatement("insert into offrebonconducteur values (null,?,?,?,?,?,?,?)");

            pst.setString(1, O.getLibOffre());
            pst.setDate(2, O.getDateDebutOffre());
            pst.setDate(3, (Date) O.getDateFinOffre());
            pst.setInt(4, O.getPourcentageReduction());
            pst.setInt(5, O.getNumReglement());
            pst.setInt(6, O.getNbrAnnee());
            pst.setString(7, O.getDescripOffre());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<OffreBonConducteur> afficherOffreBonConducteur() {

        try {
             listE.removeAll(listE);
            Statement st = C.createStatement();
            String req = "Select * from offrebonconducteur";
            ResultSet rs = st.executeQuery(req); //retourne un résulat
           

            while (rs.next()) {
                listE.add(new OffreBonConducteur(rs.getInt("idOffre"), rs.getInt("numReglement"), rs.getInt("nbrAnnee"), rs.getString("libOffre"), rs.getDate("dateDebutOffre"), rs.getDate("dateFinOffre"), rs.getInt("pourcentageReduction"), rs.getString("descripOffre")));

                

            }
            return listE;     
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void modifierOffreBonConducteur(OffreBonConducteur e) {
        try {
            PreparedStatement ps = C.prepareStatement("update offrebonconducteur set libOffre=?,dateDebutOffre=?,dateFinOffre=?,pourcentageReduction=?,numreglement=?,nbrAnnee=?,descripOffre=?  where idOffre=?");
            ps.setString(1, e.getLibOffre());
            ps.setDate(2, e.getDateDebutOffre());
            ps.setDate(3, e.getDateFinOffre());
            ps.setInt(4, e.getPourcentageReduction());
            ps.setInt(5, e.getNumReglement());
            ps.setInt(6, e.getNbrAnnee());
            ps.setString(7, e.getDescripOffre());
            ps.setInt(8, e.getIdOffre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerOffreBonConducteur(int id) {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from offrebonconducteur where idoffre=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
