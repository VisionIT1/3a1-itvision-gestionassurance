/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Services;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Derbel
 */
public class ContratCrud {
    Connection Cn = Connexion.getInstance().getCon();

    public void ajouterContrat(Contrat C) {
        try {
            Statement st = Cn.createStatement(); //l'element qui va éxécuter la requete sql

          //  String req = "insert into assurance values('" + C.getNom()+ "','" + C.getDescription()+ "','" + C.getId_client()+ "','" + C.getType()+ "','" + C.getDate_debut() + "','" + C.getDate_Echeance()+"')";
            String req = "insert into contrat ( nom, description, id_client, type, date_debut, date_echeance) VALUES (?,?,?,?,?,?)";
            PreparedStatement ste = Cn.prepareStatement(req);
            ste.setString(1, C.getNom());
            ste.setString(2, C.getDescription());
            ste.setInt(3, 1);
            ste.setString(4,C.getType());
            ste.setDate(5, C.getDate_debut());
            ste.setDate(6, C.getDate_Echeance());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error sql : "+ex.getMessage());
        }

    }

    public List<Contrat> getAllContrat(){
        List<Contrat> l = new ArrayList<>();
        Contrat c = new Contrat();
        try {
            Statement st = Cn.createStatement();

            String req = "select * from contrat";

            ResultSet rs = st.executeQuery(req); //retourne un résulat
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setId_client(rs.getInt(4));
                c.setType(rs.getString(5));
                c.setDate_debut(rs.getDate(6));
                c.setDate_Echeance(rs.getDate(7));
                
                l.add(c);
            }
            return l;
        } catch (SQLException ex) {
                System.out.println("erreur"+ex.getMessage());
                return null;
        }
    }
    
}
