/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Services.*;
import gestionassurancefx.Entities.*;
import gestionassurancefx.Utils.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class GestionOffreController implements Initializable {

    @FXML
    private TextField libOffBC;
    @FXML
    private DatePicker DDBC;
    @FXML
    private DatePicker DFBC;
    @FXML
    private TextField pourcentageRed;
    @FXML
    private TextField numReg;
    @FXML
    private TextField nbAnnee;
    @FXML
    private TextArea Descrip;
    ServiceOffreBonConducteur SOB = new ServiceOffreBonConducteur();
    @FXML
    private TableView<OffreBonConducteur> tableOBC;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> idBCT;
    @FXML
    private TableColumn<OffreBonConducteur, String> libelleBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Date> DDBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Date> DFBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> reductionBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> numRegT;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> nbAnneeT;
    @FXML
    private TableColumn<OffreBonConducteur, String> descripT;
    @FXML
    private Button BoutonAjoutOBC;
    @FXML
    private Button btnModifOBC;
    @FXML
    private TextField idBC;
    @FXML
    private Button suppBtn;

    @FXML
    private void ajouterOffreBC(ActionEvent event) {
        String erreur = "";
        if (DDBC.getValue().isAfter(DFBC.getValue())) {
            erreur = erreur + "La date debut doit être après la date fin \n ";
        }
        if (Integer.parseInt(pourcentageRed.getText()) < 0 || Integer.parseInt(pourcentageRed.getText()) > 100) {
            erreur = erreur + "Le pourcentage de réduction n'est pas compris entre [0,100] \n";
        }
        if (Integer.parseInt(nbAnnee.getText()) < 1 || Integer.parseInt(nbAnnee.getText()) > 10) {
            erreur = erreur + "Le nombre d'annee doit etre compris entre [1,10] \n";
        }

        if (!erreur.equals("")) {
            Alerte.desplay("ERREUR:", erreur);
        } else {

            OffreBonConducteur O = new OffreBonConducteur();
            O.setLibOffre(libOffBC.getText());
            O.setDateDebutOffre(java.sql.Date.valueOf(DDBC.getValue()));
            O.setDateFinOffre(java.sql.Date.valueOf(DFBC.getValue()));
            O.setNumReglement(Integer.parseInt(numReg.getText()));
            O.setPourcentageReduction(Integer.parseInt(pourcentageRed.getText()));
            O.setNbrAnnee(Integer.parseInt(nbAnnee.getText()));
            O.setDescripOffre(Descrip.getText());

            SOB.ajouterOffreBonConducteur(O);

            afficherOffreBC();
        }
    }

    public void afficherOffreBC() {
        idBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("idOffre"));
        libelleBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, String>("libOffre"));
        DDBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Date>("dateDebutOffre"));
        DFBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Date>("dateFinOffre"));
        reductionBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("pourcentageReduction"));
        numRegT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("numReglement"));
        nbAnneeT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("nbrAnnee"));
        descripT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, String>("descripOffre"));
        tableOBC.setItems(SOB.afficherOffreBonConducteur());
        tableOBC.setEditable(true);

    }

    public void Rafraichir() {
        idBC.clear();
        libOffBC.clear();
        DDBC.setValue(null);
        DFBC.setValue(null);
        Descrip.clear();
        nbAnnee.clear();
        numReg.clear();
        pourcentageRed.clear();
        BoutonAjoutOBC.setVisible(true);
        btnModifOBC.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idBC.setVisible(false);
        btnModifOBC.setVisible(false);
        afficherOffreBC();
    }

    @FXML
    private void itemClicked(MouseEvent event) {

        OffreBonConducteur Os = tableOBC.getSelectionModel().getSelectedItem();
        libOffBC.setText(Os.getLibOffre());
        DDBC.setValue(Os.getDateDebutOffre().toLocalDate());
        DFBC.setValue(Os.getDateFinOffre().toLocalDate());
        pourcentageRed.setText("" + Os.getPourcentageReduction());
        numReg.setText("" + Os.getNumReglement());
        nbAnnee.setText("" + Os.getNbrAnnee());
        Descrip.setText(Os.getDescripOffre());
        idBC.setText("" + Os.getIdOffre());
        BoutonAjoutOBC.setVisible(false);
        btnModifOBC.setVisible(true);

    }

    @FXML
    private void ModifierOffreBC(ActionEvent event) {
        OffreBonConducteur O = new OffreBonConducteur(Integer.parseInt(idBC.getText()), Integer.parseInt(numReg.getText()), Integer.parseInt(nbAnnee.getText()), libOffBC.getText(), java.sql.Date.valueOf(DDBC.getValue()), java.sql.Date.valueOf(DFBC.getValue()), Integer.parseInt(pourcentageRed.getText()), Descrip.getText());
        SOB.modifierOffreBonConducteur(O);
        BoutonAjoutOBC.setVisible(true);
        btnModifOBC.setVisible(false);
        idBC.clear();
        libOffBC.clear();
        DDBC.setValue(null);
        DFBC.setValue(null);
        Descrip.clear();
        nbAnnee.clear();
        numReg.clear();
        pourcentageRed.clear();

        afficherOffreBC();

    }

    @FXML
    private void suppimerOffBC(ActionEvent event) {
        OffreBonConducteur Os = tableOBC.getSelectionModel().getSelectedItem();
        SOB.supprimerOffreBonConducteur(Os.getIdOffre());
        afficherOffreBC();
        Rafraichir();
    }

}
