/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.AssureEntreprise;
import gestionassurancefx.Entities.AssureParticulier;
import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Services.AssureEntrepriseCrud;
import gestionassurancefx.Services.AssureParticulierCrud;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Ahmed Derbel
 */
public class GestionAssureController implements Initializable {

    @FXML
    private AnchorPane assPartPane1;
    @FXML
    private AnchorPane assPartPane;
    @FXML
    private TextField cinField;
    @FXML
    private TextField nomFIeld;
    @FXML
    private TextField prenomFIeld;
    @FXML
    private TextField emailField;
    @FXML
    private TextField numtelField;
    @FXML
    private TextField adresseField;
    @FXML
    private Button ModifierPartBtn;
    @FXML
    private TableView<AssureParticulier> ClientPartTabView;
    @FXML
    private AnchorPane assEntrPane;
    @FXML
    private TextField nomFIeldEntr;
    @FXML
    private TextField emailFieldEntr;
    @FXML
    private TextField numtelFieldEntr;
    @FXML
    private TextField adresseFieldEntr;
    private AssureParticulierCrud crud;
    private AssureEntrepriseCrud crudEntr;
    @FXML
    private TableColumn<AssureParticulier, Integer> cin;
    @FXML
    private TableColumn<AssureParticulier, String> nom;
    @FXML
    private TableColumn<AssureParticulier, String> prenom;
    @FXML
    private TableColumn<AssureParticulier, String> email;
    @FXML
    private TableColumn<AssureParticulier, Integer> numtel;
    @FXML
    private TableColumn<AssureParticulier, String> adresse;
    @FXML
    private Button SupprimerPartBtn;
    @FXML
    private TableView<AssureEntreprise> ClientEntrTabView;
    @FXML
    private TableColumn<AssureEntreprise, String> nomEntr;
    @FXML
    private TableColumn<AssureEntreprise, String> emailEntr;
    @FXML
    private TableColumn<AssureEntreprise, String> numtelEntr;
    @FXML
    private TableColumn<AssureEntreprise, String> adresseEntr;
    @FXML
    private Button ModifierEntrBtn;
    @FXML
    private Button SupprimerEntrBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud = new AssureParticulierCrud();
        crudEntr = new AssureEntrepriseCrud();
        initColumnsParticulier();
        initColumnsEntreprise();
        ClientPartTabView.setItems(crud.getAllAssPart());
        ClientEntrTabView.setItems(crudEntr.getAllAssEntr());

    }

    public void initColumnsParticulier() {

        cin.setCellValueFactory(new PropertyValueFactory<AssureParticulier, Integer>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<AssureParticulier, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AssureParticulier, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<AssureParticulier, String>("email"));
        numtel.setCellValueFactory(new PropertyValueFactory<AssureParticulier, Integer>("numtel"));
        adresse.setCellValueFactory(new PropertyValueFactory<AssureParticulier, String>("adresse"));

    }

    public void initColumnsEntreprise() {

        nomEntr.setCellValueFactory(new PropertyValueFactory<AssureEntreprise, String>("nomEntr"));
        emailEntr.setCellValueFactory(new PropertyValueFactory<AssureEntreprise, String>("emailEntr"));
        emailEntr.setCellValueFactory(new PropertyValueFactory<AssureEntreprise, String>("numTel"));
        adresseEntr.setCellValueFactory(new PropertyValueFactory<AssureEntreprise, String>("adresseEntr"));

    }

    private AssureParticulier ap = new AssureParticulier();
    private AssureEntreprise ar = new AssureEntreprise();

    @FXML
    private void ItemParticulierSelected(MouseEvent event) {

        ap = ClientPartTabView.getSelectionModel().getSelectedItem();
        if (ap != null) {
            cinField.setText(Integer.toString(ap.getCin()));
            nomFIeld.setText(ap.getNom());
            prenomFIeld.setText(ap.getPrenom());
            emailField.setText(ap.getEmail());
            numtelField.setText(Integer.toString(ap.getNumtel()));
            adresseField.setText(ap.getAdresse());
            cinField.setDisable(false);
            nomFIeld.setDisable(false);
            prenomFIeld.setDisable(false);
            emailField.setDisable(false);
            numtelField.setDisable(false);
            adresseField.setDisable(false);

        }
    }

    @FXML
    private void ModifierPartBtnClicked(ActionEvent event) {
        if (ap != null) {
            ap.setCin(Integer.parseInt(cinField.getText()));
            ap.setNom(nomFIeld.getText());
            ap.setPrenom(prenomFIeld.getText());
            ap.setEmail(emailField.getText());
            ap.setNumtel(Integer.parseInt(numtelField.getText()));
            ap.setAdresse(adresseField.getText());
            crud.modifierParticulier(ap);
            ClientPartTabView.getItems().clear();
            ClientPartTabView.setItems(crud.getAllAssPart());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modification succés");
            alert.setHeaderText(null);
            alert.setContentText("Assuré Particulier Modifier");
            alert.showAndWait();
        } else {
            System.out.println("clicker sur un object");
        }
    }

    @FXML
    private void SupprimerPartBtnClicked(ActionEvent event) {
        crud.supprimerAssureParticulier(ClientPartTabView.getSelectionModel().getSelectedItem().getCin());
        cinField.setText("");
        nomFIeld.setText("");
        prenomFIeld.setText("");
        emailField.setText("");
        numtelField.setText("");
        adresseField.setText("");
        ClientPartTabView.getItems().clear();
        ClientPartTabView.setItems(crud.getAllAssPart());
    }

    @FXML
    private void ModifierEntrBtnClicked(ActionEvent event) {

        if (ar != null) {
            ar.setNomEntr(nomEntr.getText());
            ar.setEmailEntr(emailFieldEntr.getText());
            ar.setNumTel(numtelFieldEntr.getText());
            ar.setAdresseEntr(adresseFieldEntr.getText());
            crudEntr.modifierEntreprise(ar);
            ClientEntrTabView.getItems().clear();
            ClientEntrTabView.setItems(crudEntr.getAllAssEntr());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modification succés");
            alert.setHeaderText(null);
            alert.setContentText("Assuré Entreprise Modifier");
            alert.showAndWait();
        } else {
            System.out.println("clicker sur un object");
        }

    }

    @FXML
    private void SupprimerEntrBtnClicked(ActionEvent event) {
        crudEntr.supprimerAssureEntreprise(ClientEntrTabView.getSelectionModel().getSelectedItem().getNomEntr());
        nomEntr.setText("");
        emailFieldEntr.setText("");
        numtelFieldEntr.setText("");
        adresseEntr.setText("");
        ClientEntrTabView.getItems().clear();
        ClientEntrTabView.setItems(crudEntr.getAllAssEntr());
    }

    @FXML
    private void ItemEntrepriseSelected(MouseEvent event) {

        ar = ClientEntrTabView.getSelectionModel().getSelectedItem();
        if (ar != null) {
            nomFIeldEntr.setText(ar.getNomEntr());
            emailFieldEntr.setText(ar.getEmailEntr());
            numtelFieldEntr.setText(ar.getNumTel());
            adresseFieldEntr.setText(ar.getAdresseEntr());
            nomFIeldEntr.setDisable(false);
            emailFieldEntr.setDisable(false);
            numtelFieldEntr.setDisable(false);
            adresseFieldEntr.setDisable(false);

        }
    }

}
