/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Services.ExpertService;
import gestionassurancefx.Utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.print.Paper.C;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatistiqueExpertController implements Initializable {
    @FXML
    private BarChart<String, Double> bar;

    /**
     * Initializes the controller class.
     */
        Connection C = Connexion.getInstance().getCon();
    ExpertService ES=new ExpertService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    load();
       
    }
    
    private void load() {
        try {
            String querry="select expert.adresseex,count(*) from expert group by adresseex ";
            XYChart.Series<String,Double> serie=new XYChart.Series<>();
            
            ResultSet rs=C.createStatement().executeQuery(querry);
            while(rs.next()){
                serie.getData().add(new XYChart.Data<>(rs.getString(1),rs.getDouble(2)));}
            bar.getData().add(serie);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueExpertController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    
}
