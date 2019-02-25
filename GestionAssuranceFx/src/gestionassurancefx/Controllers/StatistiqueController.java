/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class StatistiqueController implements Initializable {
    @FXML
    private AnchorPane statPane;
    @FXML
    private FontAwesomeIcon btnStatReparateur;
    @FXML
    private Button btnStatContrat;
    @FXML
    private Button btnStatConstat;
    @FXML
    private Button btnStatOffre;
    @FXML
    private Button btnStatExpert;
    @FXML
    private Button btnGReparateur;
    @FXML
    private Button btnStatRec;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    
     
    
    @FXML
    private void btnStatReparateurClicked(MouseEvent event) {
        
    }

    @FXML
    private void btnStatConstatClicked(ActionEvent event) {
    }

    @FXML
    private void btnStatOffreClicked(ActionEvent event) {
    }

    @FXML
    private void btnStatExpertClicked(ActionEvent event) throws IOException {
        
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionassurancefx/Views/statistiqueExpert.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
      stage.show();
      
//        setNode(statexpert);
    }

    @FXML
    private void btnReparateurClicked(ActionEvent event) {
    }

    @FXML
    private void btnStatRecClicked(ActionEvent event) {
    }
    
}
