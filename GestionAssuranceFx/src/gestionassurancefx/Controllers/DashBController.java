/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import static gestionassurancefx.Controllers.LoginController.dashBStage;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Button btnGContrat;
    @FXML
    private Button btnGOffre;
    @FXML
    private Button btnGReclamation;
    @FXML
    private Button btnGExpert;
    @FXML
    private Button btnGReparateur;
    @FXML
    private Button btnGestonstat;
    @FXML
    private AnchorPane mainAnchor;
AnchorPane contrat,user,login,AssurePart,ajoutcontrat,constat,offre,expert,reparateur;
AnchorPane stat;
    @FXML
    private Button btnClientPart;
    @FXML
    private Label logoutLabel;
    @FXML
    static public Label agentlabel;
    @FXML
    private Label DashBoardBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            contrat = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ContratFXML.fxml"));
            ajoutcontrat=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ContratFXML.fxml"));
            user=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionUser1.fxml"));
            login=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/Login.fxml"));
            AssurePart=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionAssurePartEntr.fxml"));
            constat=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/sinistre.fxml"));
            offre=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionOffre.fxml"));
            expert=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ExpertFXML.fxml"));
            reparateur=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ReparateurFXML.fxml"));
            stat=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/statistique.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashBController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    
    @FXML
    private void constatView(ActionEvent event) throws IOException{
        setNode(constat);
    }
    
     @FXML
    private void btnConstatClicked(ActionEvent event) throws IOException{
         
         setNode(contrat);
             }

    public void closeDashB() {
        dashBStage = (Stage) dashBStage.getScene().getWindow();
        dashBStage.close();
    }
    
    
   
      
      private void setLoginNode(Node node) {
          
        mainAnchor.getChildren().clear();
        mainAnchor.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
     
    @FXML
    private void dashBoardClicked(MouseEvent event) throws IOException {
           AnchorPane pane =FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/DashB.fxml"));
       mainAnchor.getChildren().setAll(pane);
        	  
    }

    @FXML
    private void AgentClicked(MouseEvent event) {
        setNode(user);
        
    }

    @FXML
    private void btnClientPartClicked(ActionEvent event) {
        setNode(AssurePart);
        
    }

    @FXML
    private void LogoutClicked(MouseEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionassurancefx/Views/Login.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
        ((Node)(event.getSource())).getScene().getWindow().hide();
       stage.show();
       closeDashB();
    }

    @FXML
    private void btnOffreClicked(ActionEvent event) {
        setNode(offre);
    }

    @FXML
    private void btnExpertClicked(ActionEvent event) {
        setNode(expert);
    }

    @FXML
    private void btnReparateurClicked(ActionEvent event) {
        setNode(reparateur);
    }

    @FXML
    private void StatClicked(MouseEvent event) {
        setNode(stat);
        
    }
    
    
    
}
