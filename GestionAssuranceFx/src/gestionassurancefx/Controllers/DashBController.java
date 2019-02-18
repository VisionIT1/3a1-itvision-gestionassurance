/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
