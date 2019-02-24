/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.AffectationOffre;
import gestionassurancefx.Entities.OffreFidelite;
import gestionassurancefx.Entities.OffreFidelite;
import gestionassurancefx.Utils.Connexion;
import gestionassurancefx.Utils.Email;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class ServiceOffreFidelite {

    Connection C = Connexion.getInstance().getCon();
    ObservableList<OffreFidelite> listE = FXCollections.observableArrayList();
     List<Integer> listI=new ArrayList<>(); 

    public void ajouterOffreFidelite(OffreFidelite O) {
        try {
            PreparedStatement pst = C.prepareStatement("insert into offrefidelite values (null,?,?,?,?,?,?)");

            pst.setString(1, O.getLibOffre());
            pst.setDate(2, O.getDateDebutOffre());
            pst.setDate(3, (Date) O.getDateFinOffre());
            pst.setInt(4, O.getPourcentageReduction());
            pst.setInt(5, O.getNbrContratMin());
            pst.setString(6, O.getDescripOffre());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<OffreFidelite> afficherOffreFidelite() {
        try {
            listE.removeAll(listE);
            Statement st = C.createStatement();

            String req = "select * from offrefidelite";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                listE.add(new OffreFidelite(rs.getInt("idOffre"), rs.getInt("nbrContratMin"), rs.getString("libOffre"), rs.getDate("dateDebutOffre"), rs.getDate("dateFinOffre"), rs.getInt("pourcentageReduction"), rs.getString("descripOffre")));
            }
            return listE;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void modifierOffreFidelite(OffreFidelite e) {
        try {
            PreparedStatement ps = C.prepareStatement("update offrefidelite set libOffre=?,dateDebutOffre=?,dateFinOffre=?,pourcentageReduction=?,nbrContratMin=?,descripOffre=?  where idOffre=?");
            ps.setString(1, e.getLibOffre());
            ps.setDate(2, e.getDateDebutOffre());
            ps.setDate(3, e.getDateFinOffre());
            ps.setInt(4, e.getPourcentageReduction());
            ps.setInt(5, e.getNbrContratMin());
            ps.setString(6, e.getDescripOffre());
            ps.setInt(7, e.getIdOffre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerOffreFidelite(int id) {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from offrefidelite where idoffre=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void affecterOffreFidelite(OffreFidelite O) {
        try {
            PreparedStatement ps, ps1;
            ServiceAffOffre SAO = new ServiceAffOffre();
            Statement st = C.createStatement();
            String address = "";
            String req = "SELECT id_assure,COUNT(*) from contrat GROUP by id_assure";
            ResultSet rs = st.executeQuery(req);
            System.out.println("" + (float) O.getPourcentageReduction() / 100);
            ps = C.prepareStatement("Update contrat set nvprime=prime-(" + (float) O.getPourcentageReduction() / 100 + "*prime) where id_assure=?");
            ps1 = C.prepareStatement("Select email from assure_particulier where id=?");

            while (rs.next()) {
                if (rs.getInt("COUNT(*)") >= O.getNbrContratMin() && (!SAO.assureExiste(rs.getInt("id_assure")))) {
                    AffectationOffre AO = new AffectationOffre(O.getIdOffre(), rs.getInt("id_assure"));
                    
                    SAO.ajouterAffectation(AO);

                    ps.setInt(1, rs.getInt("id_assure"));
                    ps1.setInt(1, rs.getInt("id_assure"));
                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.first()) {
                        address = rs1.getString("email");
                    }
                    System.out.println(address);
                    try {
                        Email email = new Email();
                        email.sendEmail(address, "Renouvellement Contrat", "Une offre fidélité vous a été affecter vous avez une réduction des primes");
                    } catch (Exception e) {

                    }

                    System.out.println(ps.toString());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void retirerOffreFidelite(OffreFidelite O) {

        try {
            PreparedStatement ps, ps1;
            Statement st = C.createStatement();
            String address = "";
            String req = "SELECT idassure from affectationoffref where idOffre=" + O.getIdOffre();
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
                        email.sendEmail(address, "Renouvellement Contrat", "Une offre fidélité vous a été retirer!!!");
                    } catch (Exception e) {

                    }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getIdLast() {
        int id = 0;
        try {
            Statement st = C.createStatement();

            String req = "select * from offrefidelite";
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
     public ObservableList<OffreFidelite> rechercherOffreFidelite(String lib) {
        Statement ps;
        ObservableList<OffreFidelite> d = FXCollections.observableArrayList();

        try {

            ps = C.createStatement();
            String req = "select * from offrefidelite where libOffre='" + lib + "' ";

            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {

                OffreFidelite c = new OffreFidelite(rs.getInt("idOffre"), rs.getInt("nbrContratMin"), rs.getString("libOffre"), rs.getDate("dateDebutOffre"), rs.getDate("dateFinOffre"), rs.getInt("pourcentageReduction"), rs.getString("descripOffre"));

                d.add(c);

            }
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffreFidelite.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("tiiir");
            return null;
        }

    }
    public List<Integer> OffreExpirerF()
    {
        try {
            Statement st = C.createStatement();
            
            String req = "SELECT idOffre from offrefidelite where dateFinOffre>CURRENT_DATE";
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
