/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Services.ServiceReclamation;
import java.net.URL;
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
 * @author ADMIN
 */
public class StatistiqueReclamationController implements Initializable {

    @FXML
    private BarChart<String, Double> barRec;
    ServiceReclamation SR = new ServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           
        
        try {
            XYChart.Series<String,Double> serie=new XYChart.Series<>();
            ResultSet rs=SR.statistiqueRec();
            String ch="";
            
            while(rs.next()){
                if(rs.getString(1).equals("rembourser un objet cassé"))
                    ch="Type 1";
                if(rs.getString(1).equals("montant d'indemnisation insuffisant"))
                    ch="Type 2";
                if(rs.getString(1).equals("contester le refus d'indemniser pour déclaration tardive."))
                    ch="Type 3";
                if(rs.getString(1).equals("non-prise en charge d'un objet suite à un vol "))
                    ch="Type 4";
                serie.getData().add(new XYChart.Data<>(ch,rs.getDouble(2)));}
            barRec.getData().add(serie);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
                }
                    
             
        
}
        
        
    

