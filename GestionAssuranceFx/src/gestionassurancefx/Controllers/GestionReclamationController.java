/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import gestionassurancefx.Entities.*;
import gestionassurancefx.Services.*;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class GestionReclamationController implements Initializable {

    @FXML
    private DatePicker DSRec;
    @FXML
    private TextArea descRec;
    @FXML
    private Button BtnAjoutRec;
    @FXML
    private Button btnModifRec;
    @FXML
    private TextField idR;
    @FXML
    private ComboBox<String> typeRec;
    @FXML
    private ComboBox<String> client;
    @FXML
    private Button btnSuppRec;
    @FXML
    private Button btnRafraichir;
    AnchorPane reclamation;

    @FXML
    private ComboBox<String> traitementRec;
    @FXML
    private ComboBox<String> typeAssure;
    ServiceReclamation SR = new ServiceReclamation();
    @FXML
    private TableView<Reclamation> tableR;
    @FXML
    private TableColumn<Reclamation, String> typeAT;
    @FXML
    private TableColumn<Reclamation, Integer> idAT;
    @FXML
    private TableColumn<Reclamation, String> descAT;
    @FXML
    private TableColumn<Reclamation, Integer> idRT;
    @FXML
    private TableColumn<Reclamation, String> typeRecT;
    @FXML
    private TableColumn<Reclamation, Date> DSRecT;
    @FXML
    private TableColumn<Reclamation, Integer> etatRT;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnrechercheR;
    @FXML
    private Button btnGoogleDrive;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeRec.getItems().addAll("rembourser un objet cassé", "montant d'indemnisation insuffisant", "contester le refus d'indemniser pour déclaration tardive.", "non-prise en charge d'un objet suite à un vol ");
        traitementRec.getItems().addAll("En cours de traitement", "Reclamation traitée");
        typeAssure.getItems().addAll("Assuré Particulier", "Assuré entreprise");
        BtnAjoutRec.setVisible(true);
        idR.setVisible(false);
        btnModifRec.setVisible(false);
        traitementRec.getSelectionModel().select("En cours de traitement");
        typeAssure.getSelectionModel().select("Assuré Particulier");
        typeRec.getSelectionModel().select("rembourser un objet cassé");
        try {
            reclamation=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/statistiqueReclamation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherReclamation();
    }

    @FXML
    private void ajouterRec(ActionEvent event) {
        int etat, id = 0;
        if (traitementRec.getSelectionModel().getSelectedItem().equals("En cours de traitement")) {
            etat = 0;
        } else {
            etat = 1;
        }
        if (typeAssure.getSelectionModel().getSelectedItem().equals("Assuré Particulier")) {
            id = SR.getIdAP(client.getSelectionModel().getSelectedItem());
        } else {
            id = SR.getIdAE(client.getSelectionModel().getSelectedItem());
        }

        Reclamation R = new Reclamation(typeRec.getSelectionModel().getSelectedItem(), descRec.getText(), java.sql.Date.valueOf(DSRec.getValue()), typeAssure.getSelectionModel().getSelectedItem(), id, etat);
        SR.ajouterReclamation(R);
        afficherReclamation();
        RafraichirR();
    }

    public void afficherReclamation() {
        idRT.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("idR"));
        typeRecT.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("typeRec"));
        descAT.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("descRec"));
        DSRecT.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("dateSaisiRec"));
        typeAT.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("typeAssure"));
        idAT.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("idCli"));
        etatRT.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("traitementRec"));

        tableR.setItems(SR.afficherReclamation());
        tableR.setEditable(true);

    }

    @FXML
    private void ModifierRec(ActionEvent event) {
        int etat, id = 0;
        if (traitementRec.getSelectionModel().getSelectedItem().equals("En cours de traitement")) {
            etat = 0;
        } else {
            etat = 1;
        }
        if (typeAssure.getSelectionModel().getSelectedItem().equals("Assuré Particulier")) {
            id = SR.getIdAP(client.getSelectionModel().getSelectedItem());
        } else {
            id = SR.getIdAE(client.getSelectionModel().getSelectedItem());
        }

        Reclamation R = new Reclamation(Integer.parseInt(idR.getText()), typeRec.getSelectionModel().getSelectedItem(), descRec.getText(), java.sql.Date.valueOf(DSRec.getValue()), typeAssure.getSelectionModel().getSelectedItem(), id, etat);
        SR.modifierReclamation(R);
        BtnAjoutRec.setVisible(true);
        btnModifRec.setVisible(false);
        idR.clear();
        descRec.clear();
        DSRec.setValue(null);
        
        afficherReclamation();

    }
     public void RafraichirR() {
        BtnAjoutRec.setVisible(true);
        btnModifRec.setVisible(false);
        idR.clear();
        descRec.clear();
        DSRec.setValue(null);
        
    }

    @FXML
    private void itemClicked(MouseEvent event) {
        Reclamation R = tableR.getSelectionModel().getSelectedItem();
        idR.setText("" + R.getIdR());
        typeRec.getSelectionModel().select(R.getTypeRec());
        DSRec.setValue(R.getDateSaisiRec().toLocalDate());
        typeAssure.getSelectionModel().select(R.getTypeAssure());
        client.getSelectionModel().select(R.getIdCli());
        String etat;
        if (R.getTraitementRec() == 0) {
            etat = "En cours de traitement";
        } else {
            etat = "Reclamation traitée";
        }

        traitementRec.getSelectionModel().select(etat);
        descRec.setText(R.getDescRec());
        BtnAjoutRec.setVisible(false);
        btnModifRec.setVisible(true);
    }

    @FXML
    private void supprimerRec(ActionEvent event) {
        Reclamation R=tableR.getSelectionModel().getSelectedItem();
        SR.supprimerReclamation(R.getIdR());
        afficherReclamation();
        RafraichirR();
    }

    @FXML
    private void rafraichirRec(ActionEvent event) {
        RafraichirR();
    }

    @FXML
    private void itemSelected(ActionEvent event) {
        if (typeAssure.getSelectionModel().getSelectedItem().equals("Assuré Particulier")) {
            client.setItems(SR.getAssureP());
            
        }
        if (typeAssure.getSelectionModel().getSelectedItem().equals("Assuré entreprise")) {
            client.setItems(SR.getAssureE());
        }
    }
    

    @FXML
    private void rechercherR(ActionEvent event) {
        tableR.setItems(SR.rechercherReclamation(recherche.getText()));
        tableR.setEditable(true);
    }

    @FXML
    private void refresh(MouseEvent event) { 
        afficherReclamation();
       
    }

    

}
