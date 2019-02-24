/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Services;

import gestionassurancefx.Entities.Reclamation;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.Date;
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
 * @author ADMIN
 */
public class ServiceReclamation {

    Connection C = Connexion.getInstance().getCon();
    ObservableList<Reclamation> listE = FXCollections.observableArrayList();
    ObservableList<String> ListAssureP = FXCollections.observableArrayList();
    ObservableList<String> ListAssureE = FXCollections.observableArrayList();

    public void ajouterReclamation(Reclamation O) {
        try {
            PreparedStatement pst = C.prepareStatement("insert into reclamation values (null,?,?,?,?,?,?)");

            pst.setString(1, O.getTypeRec());
            pst.setString(2, O.getDescRec());
            pst.setDate(3, O.getDateSaisiRec());
            pst.setString(4,O.getTypeAssure());
            pst.setInt(5, O.getIdCli());
            pst.setInt(6, O.getTraitementRec());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Reclamation> afficherReclamation() {
        try {
            listE.removeAll(listE);;
            Statement st = C.createStatement();

            String req = "select * from reclamation";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                listE.add(new Reclamation(rs.getInt("idR"), rs.getString("typeRec"), rs.getString("descrec"), rs.getDate("dateSaisiRec"), rs.getString("typeAssure"), rs.getInt("idCli"), rs.getInt("traitementRec")));
            }
            return listE;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void modifierReclamation(Reclamation e) {
        try {
            PreparedStatement ps = C.prepareStatement("update reclamation set typeRec=?,descRec=?,dateSaisiRec=?,idCli=?,traitementRec=?  where idR=?");
            ps.setString(1, e.getTypeRec());
            ps.setString(2, e.getDescRec());
            ps.setDate(3, e.getDateSaisiRec());
            ps.setInt(4, e.getIdCli());
            ps.setInt(5, e.getTraitementRec());
            ps.setInt(6, e.getIdR());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerReclamation(int id) {
        PreparedStatement ps;
        try {
            ps = C.prepareStatement("delete from reclamation where idR=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<String> getAssureP() {
        try {
            ListAssureP.removeAll(ListAssureP);
            Statement st = C.createStatement();

            String req = "SELECT CONCAT(nom,' ',prenom) as \"Nom Prenom\" FROM assure_particulier";

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ListAssureP.add(rs.getString("Nom Prenom"));
            }
            return ListAssureP;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ObservableList<String> getAssureE() {
        try {
            ListAssureE.removeAll(ListAssureE);
            Statement st = C.createStatement();

            String req = "select nomEntr from assure_entreprise";

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ListAssureE.add(rs.getString("nomEntr"));
            }
            return ListAssureE;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int getIdAP(String assure) {
        try {
            Statement st = C.createStatement();

            String req = "SELECT id FROM `assure_particulier` WHERE CONCAT(nom,' ',prenom)='" + assure+"'";
           

            ResultSet rs = st.executeQuery(req);
            if(rs.first())
                rs.getInt("id");

            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int getIdAE(String assure) {
        try { Statement st = C.createStatement();

            String req = "SELECT id FROM `assure_entreprise` WHERE nomEntr='" + assure+"'";
            

            ResultSet rs = st.executeQuery(req);
            if(rs.first())
                rs.getInt("id");
            
           
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }
    public ResultSet statistiqueRec()
    {
        try {
            String req="select reclamation.typeRec,count(*) from reclamation group by typeRec";
            ResultSet rs=C.createStatement().executeQuery(req);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ObservableList<Reclamation> rechercherReclamation(String lib) {
        Statement ps;
        ObservableList<Reclamation> d = FXCollections.observableArrayList();

        try {

            ps = C.createStatement();
            String req = "select * from reclamation where typeRec='" + lib + "' ";

            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {

                Reclamation c = new Reclamation(rs.getInt("idR"), rs.getString("typeRec"), rs.getString("descrec"), rs.getDate("dateSaisiRec"), rs.getString("typeAssure"), rs.getInt("idCli"), rs.getInt("traitementRec"));

                d.add(c);

            }
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("tiiir");
            return null;
        }

    }
}
