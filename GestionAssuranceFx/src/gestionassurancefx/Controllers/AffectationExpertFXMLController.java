/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import static gestionassurancefx.Controllers.ExpertFXMLController.cinexpert;
import gestionassurancefx.Entities.Expert;
import gestionassurancefx.Services.ExpertService;
import gestionassurancefx.Utils.Connexion;
import static gestionassurancefx.Controllers.AffectationExpertFXMLController.cinexpert;
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
public class AffectationExpertFXMLController implements Initializable {
    @FXML
    private TableView<Expert> tableAffecExpert;
    @FXML
    private TableColumn<Expert,Integer> idE;
    @FXML
    private TableColumn<Expert, Integer> cinE;
    @FXML
    private TableColumn<Expert,Integer> faxE;
    @FXML
    private TableColumn<Expert,String> nomE;
    @FXML
    private TableColumn<Expert, String> prenomE;
    @FXML
    private TableColumn<Expert, String> maiE;
    @FXML
    private TableColumn<Expert, Integer> telephoneE;
    @FXML
    private TableColumn<Expert, String> adresseE;
    @FXML
    private TableColumn<Expert, String> etatE;
    @FXML
    private TableColumn<Expert, String> descriptionE;
    @FXML
    private Button affecterE;
    
    
    @FXML
    private AnchorPane mainViewE;
    Connection C = Connexion.getInstance().getCon();
    ExpertService ES=new ExpertService();
        public static int cinexpert=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //affecterE.setVisible(false);
        afficherExpert();
       
    }    

    @FXML
    private void affectationExpert(MouseEvent event) {
          Expert Os =  tableAffecExpert.getSelectionModel().getSelectedItem();
          affecterE.setVisible(true);
    }

    public void afficherExpert() {
        idE.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("idEx"));
        cinE.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("cinEx"));
        faxE.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("faxEx"));
        nomE.setCellValueFactory(new PropertyValueFactory<Expert, String>("nomEx"));
       prenomE.setCellValueFactory(new PropertyValueFactory<Expert, String>("prenomEx"));
        maiE.setCellValueFactory(new PropertyValueFactory<Expert, String>("mailEx"));
        telephoneE.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("numeroEx"));
        adresseE.setCellValueFactory(new PropertyValueFactory<Expert, String>("adresseEx"));
        etatE.setCellValueFactory(new PropertyValueFactory<Expert, String>("etatEx"));
        descriptionE.setCellValueFactory(new PropertyValueFactory<Expert, String>("descriptionEx"));
        
       tableAffecExpert.setItems(ES.afficherExpert());
         tableAffecExpert.setEditable(true);
    }

    @FXML
    private void AffecterEx(ActionEvent event) throws IOException {
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to affecte Expert");
           Optional <ButtonType> action=alert.showAndWait();
           if(action.get()==ButtonType.OK)
                {
        cinexpert=Integer.parseInt(cinE.getText());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/ExpertFXML.fxml"));
        mainViewE.getChildren().setAll(pane);
        
                }
    }

    
}
