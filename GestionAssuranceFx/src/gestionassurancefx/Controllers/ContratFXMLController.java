/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class ContratFXMLController implements Initializable {
    @FXML
    private AnchorPane ContratPane;
    @FXML
    private Button btnAddContrat;
    @FXML
    private Button btnOtherContratClicked;
    AnchorPane ajoutcontrat,modifiercontrat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ajoutcontrat = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionAssureParticulier.fxml"));
            modifiercontrat=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/UpdateDeleteContrat.fxml"));
            
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(ContratFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
     private void setNode(Node node) {
        ContratPane.getChildren().clear();
        ContratPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    @FXML
    private void AddContratClicked(ActionEvent event) {
        setNode(ajoutcontrat);
        
    }

    @FXML
    private void OtherContratClicked(ActionEvent event) {
         setNode(modifiercontrat);
    }
    
}
