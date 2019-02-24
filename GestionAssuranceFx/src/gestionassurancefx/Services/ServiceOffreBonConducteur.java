/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import com.sun.jmx.remote.internal.ArrayQueue;
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
import java.util.ArrayList;
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
    List<Integer> listI=new ArrayList<>(); 
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

    public void affecterOffreBC(OffreBonConducteur O) {
        try {
            PreparedStatement ps, ps1;
            ServiceAffOffre SAO = new ServiceAffOffre();
            Statement st = C.createStatement();
            String address = "";
            String req = "SELECT code_assureur,MAX(numero_sinistre) from sinistre WHERE (select YEAR( CURRENT_DATE ))-(SELECT year(date_declaration)) <" + O.getNbrAnnee() + " GROUP by code_assureur";
            ResultSet rs = st.executeQuery(req);

            ps = C.prepareStatement("Update contrat set nvprime=prime-(" + (float) O.getPourcentageReduction() / 100 + "*prime) where id_assure=?");
            ps1 = C.prepareStatement("Select email from assure_particulier where id=?");

            while (rs.next()) {
                if (rs.getInt("MAX(numero_sinistre)") < O.getNumReglement() && (!SAO.assureExiste(rs.getInt("code_assureur")))) {
                    AffectationOffre AO = new AffectationOffre(O.getIdOffre(), rs.getInt("code_assureur"));

                    SAO.ajouterAffectation(AO);

                    ps.setInt(1, rs.getInt("code_assureur"));
                    ps1.setInt(1, rs.getInt("code_assureur"));
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.first()) {
                        address = rs1.getString("email");
                    }
                    System.out.println(address);
                    try {
                        Email email = new Email();
                        email.sendEmail(address, "Renouvellement Contrat", "Une offre Bon conducteur vous a été affecter vous avez une réduction des primes");
                    } catch (Exception e) {

                    }

                    System.out.println(ps.toString());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void retirerOffreBC(OffreBonConducteur O) {

        try {
            PreparedStatement ps, ps1;
            Statement st = C.createStatement();
            String address = "";
            String req = "SELECT idassure from affectationoffre where idOffre=" + O.getIdOffre();
            ResultSet rs = st.executeQuery(req);
            int idAssure = -1;
            if (rs.first()) {
                idAssure = rs.getInt("idassure");
            }

            ps = C.prepareStatement("Update contrat set nvprime=null where id_assure=?");
            ps.setInt(1, idAssure);
            ps.executeUpdate();
            ps1 = C.prepareStatement("Select email from assure_particulier where id=?");
            ps1.setInt(1, idAssure);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.first()) {
                address = rs1.getString("email");
            }
            try {
                Email email = new Email();
                email.sendEmail(address, "Renouvellement Contrat", "Une offre BonConducteur vous a été retirer!!!");
            } catch (Exception e) {

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
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

    public int getIdLast() {
        int id = 0;
        try {
            Statement st = C.createStatement();

            String req = "select * from offrebonconducteur";
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                if (rs.last()) {
                    id = rs.getInt("idOffre");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public ObservableList<OffreBonConducteur> rechercherOffreBonConducteur(String lib) {
        Statement ps;
        ObservableList<OffreBonConducteur> d = FXCollections.observableArrayList();

        try {

            ps = C.createStatement();
            String req = "select * from offrebonconducteur where libOffre='" + lib + "' ";

            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {

                OffreBonConducteur c = new OffreBonConducteur(rs.getInt("idOffre"), rs.getInt("numReglement"), rs.getInt("nbrAnnee"), rs.getString("libOffre"), rs.getDate("dateDebutOffre"), rs.getDate("dateFinOffre"), rs.getInt("pourcentageReduction"), rs.getString("descripOffre"));

                d.add(c);

            }
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("tiiir");
            return null;
        }

    }
    public List<Integer> OffreExpirerBC()
    {
        try {
            Statement st = C.createStatement();
            
            String req = "SELECT idOffre from offrebonconducteur where dateFinOffre>CURRENT_DATE";
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                listI.add(rs.getInt("idOffre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreBonConducteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listI;
    }

}
