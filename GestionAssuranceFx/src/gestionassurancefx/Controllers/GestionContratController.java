/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Services.ContratCrud;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class GestionContratController implements Initializable {
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField nomContratField;
    @FXML
    private TextArea descriptionContratField;
    @FXML
    private TextField idClientField;
    @FXML
    private ComboBox<String> typeContratField;
    @FXML
    private DatePicker dateDebutField;
    @FXML
    private DatePicker dateEcheanceField;
    private ContratCrud crud;
    @FXML
    private Button ajouterBtn;
    @FXML
    private Button afficherBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.crud = new ContratCrud();
        typeContratField.getItems().addAll("Habitation","Vie","Voiture");
    }    

    @FXML
    private void ajouterClicked(ActionEvent event) {
        Date date_debut = java.sql.Date.valueOf(dateDebutField.getValue());
        Date date_echeance = java.sql.Date.valueOf(dateEcheanceField.getValue());
        System.out.println(date_debut);
        System.out.println(date_echeance);
        System.out.println("Nom : "+nomContratField.getText());
        System.out.println("Description : "+descriptionContratField.getText());
        System.out.println("Id Client: "+idClientField.getText());
        System.out.println("type contrat"+typeContratField.getSelectionModel().getSelectedItem());
     //  Contrat cr = new Contrat(1,nomContratField.getText(),descriptionContratField.getText(),Integer.getInteger(idClientField.getText()),typeContratField.getSelectionModel().getSelectedItem(),date_debut,date_echeance);
       Contrat cr = new Contrat();
       cr.setDate_Echeance(date_echeance);
       cr.setDate_debut(date_debut);
       cr.setNom(nomContratField.getText());
       cr.setDescription(descriptionContratField.getText());
       cr.setId_client(2);
       cr.setType(typeContratField.getSelectionModel().getSelectedItem());
       crud.ajouterContrat(cr);
        System.out.println("Contrat Ajouter");
    
    }

    @FXML
    private void afficherClicked(ActionEvent event) {
        System.out.println(crud.getAllContrat());
    }
    
}
