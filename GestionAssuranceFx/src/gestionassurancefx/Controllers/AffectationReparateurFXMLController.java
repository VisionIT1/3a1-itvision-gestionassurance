/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;


import static gestionassurancefx.Controllers.AffectationExpertFXMLController.cinexpert;
import gestionassurancefx.Entities.Reparateur;
import gestionassurancefx.Services.ReparateurService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffectationReparateurFXMLController implements Initializable {
    @FXML
    private AnchorPane mainViewR;
    @FXML
    private TableView<Reparateur> tableAffectRep;
    @FXML
    private TableColumn<Reparateur, Integer> idR;
    @FXML
    private TableColumn<Reparateur, Integer> cinR;
    @FXML
    private TableColumn<Reparateur, Integer> faxR;
    @FXML
    private TableColumn<Reparateur, String> nomR;
    @FXML
    private TableColumn<Reparateur, String>  prenomR;
    @FXML
    private TableColumn<Reparateur, String>  mailR;
    @FXML
    private TableColumn<Reparateur, Integer>  numeroR;
    @FXML
    private TableColumn<Reparateur, String>  adresseR;
    @FXML
    private TableColumn<Reparateur, String>  etatR;
    @FXML
    private TableColumn<Reparateur, String>  descR;
    @FXML
    private Button affecterRep;
        ReparateurService ES=new ReparateurService();
     public static int cr=0;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    afficherReparateur();
    }    

    @FXML
    private void affectationRep(MouseEvent event) {
          Reparateur Os =    tableAffectRep.getSelectionModel().getSelectedItem();
   
    }

    @FXML
    private void affecterRep(ActionEvent event) throws IOException {
        Reparateur r=tableAffectRep.getSelectionModel().getSelectedItem();
        
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to affecte Reparateur");
            
           Optional <ButtonType> action=alert.showAndWait();
           if(action.get()==ButtonType.OK)
                {
        cr=r.getCinRep();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/DashB.fxml"));
        mainViewR.getChildren().setAll(pane);
                }
    }
       public void afficherReparateur() {
        idR.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("idRep"));
       cinR.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("cinRep"));
        faxR.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("faxRep"));
        nomR.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("nomRep"));
       prenomR.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("prenomRep"));
        mailR.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("mailRep"));
        numeroR.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("numeroRep"));
        adresseR.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("adresseRep"));
        etatR.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("etatRep"));
       etatR.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("descriptionRep"));
        
        tableAffectRep.setItems(ES.afficherReparateur());
       tableAffectRep.setEditable(true);
    
}
    
}
