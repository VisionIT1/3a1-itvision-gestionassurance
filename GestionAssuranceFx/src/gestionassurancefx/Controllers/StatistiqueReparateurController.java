/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Services.ReparateurService;
import gestionassurancefx.Utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatistiqueReparateurController implements Initializable {
    @FXML
    private BarChart<String, Double> barRep;

    /**
     * Initializes the controller class.
     */
        ReparateurService ES=new ReparateurService();
     Connection C = Connexion.getInstance().getCon();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }
       private void load() {
        try {
            String querry="select reparateur.adresserep,count(*) from reparateur group by adresserep ";
            XYChart.Series<String,Double> serie=new XYChart.Series<>();
            
            ResultSet rs=C.createStatement().executeQuery(querry);
            while(rs.next()){
                serie.getData().add(new XYChart.Data<>(rs.getString(1),rs.getDouble(2)));}
            barRep.getData().add(serie);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueReparateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
