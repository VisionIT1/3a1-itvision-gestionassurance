/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author youssef
 */
public class DashBController  implements Initializable  {
    
    @FXML
    private VBox id2;
    @FXML
    private VBox id1;
    @FXML
    private AnchorPane mainView;
    @FXML
    private Label projectBtn;
    @FXML
    private Button btnGConstat;
    @FXML
    private Button btnGContrat;
    @FXML
    private Button btnGOffre;
    @FXML
    private Button btnGReclamation;
    @FXML
    private Button btnGExpert;
    @FXML
    private Button btnGReparateur;
	
    private AnchorPane mainAnchor;
AnchorPane contrat;
AnchorPane expert,reparateur;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            contrat = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionAssureParticulier.fxml"));
            expert=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ExpertFXML.fxml"));
            reparateur=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ReparateurFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    @FXML
    private void constatView(ActionEvent event) throws IOException{
       AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/sinistre.fxml"));
       // System.out.println(getClass().getResource("/gestionassurancefx/Views/GestionConstat.fxml"));
        mainView.getChildren().setAll(pane);
    }

    @FXML
    private void afficheSinistre(MouseEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/sin.fxml"));
        System.out.println(getClass().getResource("/gestionassurancefx/Views/sin.fxml"));
       mainView.getChildren().setAll(pane);
    }
	
	
    private void btnConstatClicked(ActionEvent event) throws IOException{
   
         setNode(contrat);
             }

      private void setNode(Node node) {
        mainView.getChildren().clear();
        mainView.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
     
    private void dashBoardClicked(MouseEvent event) throws IOException {
            AnchorPane pane =FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/DashB.fxml"));
       mainAnchor.getChildren().setAll(pane);
    }

    @FXML
    private void btnExpertClicked(ActionEvent event) {
                setNode(expert);

    }

    @FXML
    private void btnReparteurClicked(ActionEvent event) {
                setNode(reparateur);

    }
    
    
}
