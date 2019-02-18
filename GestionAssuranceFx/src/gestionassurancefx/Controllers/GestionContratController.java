/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import static gestionassurancefx.Controllers.GestionAssureParticulierController.cinCont;
import static gestionassurancefx.Controllers.GestionAssureParticulierController.nomEntrCont;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Services.ContratCrud;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TableView<Contrat> contratview;
    @FXML
    private TableColumn<Contrat, Integer> id;
    @FXML
    private TableColumn<Contrat, String> nom;
    @FXML
    private TableColumn<Contrat, String> desc;
    @FXML
    private TableColumn<Contrat, Integer> idassure;
    @FXML
    private TableColumn<Contrat, String> type;
    @FXML
    private TableColumn<Contrat, LocalDate> datedebut;
    @FXML
    private TableColumn<Contrat, LocalDate> dateecheance;
    @FXML
    private TableColumn<Contrat, Integer> etat;
    @FXML
    private TableColumn<Contrat, Float> prime;
    @FXML
    private TextField primeField;
    @FXML
    private ComboBox<String> etatField;

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;

    Contrat updateContrat;
   
    @FXML
    private Button printBtn;
   
    @FXML
    private TextField cinAssureField;
    private GestionAssureParticulierController ApCont; 
    @FXML
    private Label nomAssLabel;
    @FXML
    private TextField nomAssField;
    @FXML
    private Label cinAssLabel;
    
    public void initialize(URL url, ResourceBundle rb) {
       
        if(nomEntrCont != null ){
            nomAssLabel.setVisible(true);
            nomAssField.setVisible(true);
            cinAssLabel.setVisible(false);
            cinAssureField.setVisible(false);
        }
        
        initColumns();
        crud = new ContratCrud();
        typeContratField.getItems().addAll("Habitation", "Voyage", "Voiture");
        etatField.getItems().addAll("Paye", "Non paye");
        cinAssureField.setText(cinCont);
        //cinAssureField.setText(nomEntrCont);
//        contratview.setItems(crud.getAllContrat());
    //        System.out.println(crud.getNomAss());              
    }

    
    
   
    public void initColumns() {
        id.setCellValueFactory(new PropertyValueFactory<Contrat, Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Contrat, String>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<Contrat, String>("Description"));
        idassure.setCellValueFactory(new PropertyValueFactory<Contrat, Integer>("cin_assure"));
        type.setCellValueFactory(new PropertyValueFactory<Contrat, String>("type"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Contrat, LocalDate>("date_debut"));
        dateecheance.setCellValueFactory(new PropertyValueFactory<Contrat, LocalDate>("date_Echeance"));
        etat.setCellValueFactory(new PropertyValueFactory<Contrat, Integer>("Etat"));
        prime.setCellValueFactory(new PropertyValueFactory<Contrat, Float>("prime"));

    }

    
    
    
    @FXML
    private void ajouterClicked(ActionEvent event) {
        //contratview.getItems().clear();
        
        Date date_debut = java.sql.Date.valueOf(dateDebutField.getValue());
        Date date_echeance = java.sql.Date.valueOf(dateEcheanceField.getValue());
        int prime = Integer.parseInt(primeField.getText());
        int etat = 0;
      //  int idass = Integer.parseInt(etatField.getSelectionModel().getSelectedItem());
        if (etatField.getSelectionModel().getSelectedItem().equalsIgnoreCase("Paye")) {
            etat = 1;
        } else {
            etat = 0;
        }
        Contrat cr = new Contrat();
        cr.setDate_Echeance(date_echeance);
        cr.setDate_debut(date_debut);
        cr.setNom(nomContratField.getText());
        cr.setDescription(descriptionContratField.getText());
        
       //String[ ] arr=idAssureField.getSelectionModel().getSelectedItem().split(" ");
       cr.setCin_assure(Integer.parseInt(cinAssureField.getText()));
       cr.setType(typeContratField.getSelectionModel().getSelectedItem());
        cr.setEtat(etat);
        cr.setPrime(prime);
        
        crud.ajouterContrat(cr);
        contratview.getItems().clear();
        contratview.setItems(crud.getAllContrat());
        System.out.println("Contrat Ajouter");
       contratview.setItems(crud.getAllContrat());

    }

    private void AfficherClicked(Event event) {
        System.out.println(crud.getAllContrat());
    }

    @FXML
    private void deleteContrat(ActionEvent event) {
        crud.SupprimerContrat(contratview.getSelectionModel().getSelectedItem().getId());
        nomContratField.setText("");
        descriptionContratField.setText("");
        idClientField.setText("");
        //typeContratField.setItems(typeliste);
        typeContratField.getSelectionModel().select("");
        dateDebutField.setValue(null);
        dateEcheanceField.setValue(null);
        etatField.getSelectionModel().select("");
        primeField.setText("");
        contratview.getItems().clear();
        contratview.setItems(crud.getAllContrat());
        System.out.println("deleted");
    }

    private Contrat cr = new Contrat();

    @FXML
    private void ItemSelected(MouseEvent event) {
        cr = contratview.getSelectionModel().getSelectedItem();
        if (cr != null) {
            String etat = "";
            String prime = "";
            prime += cr.getPrime();
           
            if (cr.getEtat() == 0) {
                etat = "non paye";
            } else {
                etat = "Paye";
            }

            nomContratField.setText(cr.getNom());
            descriptionContratField.setText(cr.getDescription());
            cinAssureField.setText(Integer.toString(cr.getCin_assure()));
            typeContratField.getSelectionModel().select(cr.getType());
            dateDebutField.setValue(cr.getDate_debut().toLocalDate());
            dateEcheanceField.setValue(cr.getDate_Echeance().toLocalDate());
            etatField.getSelectionModel().select(etat);
            primeField.setText(prime);

        }
    }

    @FXML
    private void updateClicked(ActionEvent event) {
        if (cr != null) {
            Date date_debut = java.sql.Date.valueOf(dateDebutField.getValue());
            Date date_echeance = java.sql.Date.valueOf(dateEcheanceField.getValue());
            cr.setDate_Echeance(date_echeance);
            cr.setDate_debut(date_debut);
            cr.setNom(nomContratField.getText());
            cr.setDescription(descriptionContratField.getText());
            
          //String[ ] arr=cinAssureField.getSelectionModel().getSelectedItem().split(" ");
          //cr.setId_assure(Integer.parseInt(arr[0]));
           
          cr.setType(typeContratField.getSelectionModel().getSelectedItem());
            cr.setPrime(Integer.parseInt(primeField.getText()));
            if (etatField.getSelectionModel().getSelectedItem().equals("Non paye")) {
                cr.setEtat(0);
            } else {
                cr.setEtat(1);
            }
            crud.modifierContrat(cr);
            contratview.getItems().clear();
            contratview.setItems(crud.getAllContrat());
        }else {
            System.out.println("clicker sur un object");
        }
    }

    @FXML
    private void printClicked(ActionEvent event) {
        /* PrinterJob printerJob = PrinterJob.createPrinterJob();
   if(printerJob.showPrintDialog(primaryStage.getOwner()) && printerJob.printPage(yourNode))
       printerJob.endJob();*/
    }

}
