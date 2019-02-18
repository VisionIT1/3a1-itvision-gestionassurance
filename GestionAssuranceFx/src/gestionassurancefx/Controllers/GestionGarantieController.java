/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Garantie;
import gestionassurancefx.Services.GarantieCrud;
import gestionassurancefx.Utils.Alerte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Psico
 */
public class GestionGarantieController implements Initializable {
    private GarantieCrud gc=new GarantieCrud();
    @FXML
    private Button btnvalid;
    @FXML
    private TextField txtlib;
    @FXML
    private Button btnajoutm;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableView<Garantie> tbgarantie;
    @FXML
    private TableColumn<Garantie,Integer> tbid;
    @FXML
    private TableColumn<Garantie,String> tblib;
    @FXML
    private TableColumn<Garantie,Float> tbprime;
    @FXML
    private TableColumn<Garantie,String> tbcateg;
    @FXML
    private TextField txtprime;
    @FXML
    private ComboBox<String> cbcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        tbgarantie.setItems(gc.afficherGarantie());
        cbcat.getItems().addAll("Vehicule","Voyage");
        
    }    
    
     public void initColumns() {

        tbid.setCellValueFactory(new PropertyValueFactory<Garantie, Integer>("id_garantie"));
        tblib.setCellValueFactory(new PropertyValueFactory<Garantie, String>("lib"));
        tbprime.setCellValueFactory(new PropertyValueFactory<Garantie, Float>("prime"));
        tbcateg.setCellValueFactory(new PropertyValueFactory<Garantie, String>("categorie"));

    }
    @FXML
    private void validermodif(ActionEvent event) {
        while (!txtlib.getText().trim().equals("")){
         int idd=cliqueme(null);
          if(cbcat.getSelectionModel().getSelectedItem().trim().equals("Vehicule")){
        Garantie g = new Garantie(idd,txtlib.getText(),Float.parseFloat(txtprime.getText()),"vh");
        gc.modifierGarantie(g);
          }else{
        Garantie g = new Garantie(idd,txtlib.getText(),Float.parseFloat(txtprime.getText()),"voy");
        gc.modifierGarantie(g);
          }
        
        btnajoutm.setVisible(true);
        btnsupp.setVisible(false);
        btnmodifier.setVisible(false);
        btnvalid.setVisible(false);
        txtlib.setText("");
        txtprime.setText("");
        cbcat.getSelectionModel().select("--Choisissez Une Categorie--");
        
        tbgarantie.getItems().clear();
        tbgarantie.setItems(gc.afficherGarantie());
        }
    }


    @FXML
    private int cliqueme(MouseEvent event) {
        SelectionModel<Garantie> g= tbgarantie.getSelectionModel();
    	Garantie c=g.getSelectedItem();
    	
        String idm=""+c.getId_garantie(); 
         
        if (idm!=null){
    	btnsupp.setVisible(true);
        btnmodifier.setVisible(true);
        
        }
    	
    	return c.getId_garantie();
    }

    @FXML
    private void ajouterGarantie(ActionEvent event) {
         if (!(txtlib.getText().trim().equals(""))){
          if(cbcat.getSelectionModel().getSelectedItem().trim().equals("Vehicule")){
        Garantie g = new Garantie(txtlib.getText(),Float.parseFloat(txtprime.getText()),"vh");
        gc.ajouterGarantie(g);
          }else{
        Garantie g = new Garantie(txtlib.getText(),Float.parseFloat(txtprime.getText()),"voy");
        gc.ajouterGarantie(g);
          }
        
        txtlib.setText("");
        txtprime.setText("");
        cbcat.getSelectionModel().select("--Choisissez Une Categorie--");
        tbgarantie.getItems().clear();
        tbgarantie.setItems(gc.afficherGarantie());
       
        Alerte.desplay("Succées d'ajout", "Vehicule ajouté avec succées");
        
        }
    }

    @FXML
    private void supprimerGarantie(ActionEvent event) {
         int idd=cliqueme(null);
        gc.SupprimerGarantie(idd);
        btnsupp.setVisible(false);
        btnmodifier.setVisible(false);
          tbgarantie.getItems().clear();
        tbgarantie.setItems(gc.afficherGarantie());
    }

    @FXML
    private void modifierGarantie(ActionEvent event) {
          btnajoutm.setVisible(false);
        btnsupp.setVisible(false);
        btnmodifier.setVisible(false);
        btnvalid.setVisible(true);
        SelectionModel<Garantie> g= tbgarantie.getSelectionModel();
    	Garantie c=g.getSelectedItem(); 
       	txtlib.setText(c.getLib());
        txtprime.setText(""+c.getPrime());
        cbcat.getSelectionModel().select(c.getCategorie());
    }
    
}
