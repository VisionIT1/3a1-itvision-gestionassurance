/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Marque;
import gestionassurancefx.Services.MarqueCrud;
import gestionassurancefx.Utils.Alerte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class GestionMarqueController implements Initializable {

    private MarqueCrud mc = new MarqueCrud();
    @FXML
    private TableView<Marque> tbmarque;

    @FXML
    private TextField txtlib;
    @FXML
    private Button btnajoutm;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableColumn<Marque, Integer> tbid;
    @FXML
    private TableColumn<Marque, String> tblib;
    @FXML
    private Button btnvalid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        tbmarque.setItems(mc.afficherMarque());
        

    }

    public void initColumns() {

        tbid.setCellValueFactory(new PropertyValueFactory<Marque, Integer>("id_marque"));
        tblib.setCellValueFactory(new PropertyValueFactory<Marque, String>("lib_mrq"));

    }

    @FXML
    private void ajouterMarque(ActionEvent event) {
        
        if (!(txtlib.getText().trim().equals(""))){
            System.out.println(txtlib.getText());
        Marque m = new Marque(txtlib.getText());

        mc.ajouterMarque(m);
        txtlib.setText("");
        tbmarque.getItems().clear();
        tbmarque.setItems(mc.afficherMarque());
       
        Alerte.desplay("Succées d'ajout", "Marque ajouté avec succées");
        
        }
        
       
    }

    @FXML
    private void supprimerMarque(ActionEvent event) {
        int idd=cliqueme(null);
        mc.SupprimerMarque(idd);
        btnsupp.setVisible(false);
        btnmodifier.setVisible(false);
          tbmarque.getItems().clear();
        tbmarque.setItems(mc.afficherMarque());
        
    }

    @FXML
    private void modifierMarque(ActionEvent event) {
        btnajoutm.setVisible(false);
        btnsupp.setVisible(false);
        btnmodifier.setVisible(false);
        btnvalid.setVisible(true);
        SelectionModel<Marque> m= tbmarque.getSelectionModel();
    	Marque c=m.getSelectedItem();
        String lib=""+c.getLib_mrq();  
       	txtlib.setText(lib);
    }

    @FXML
    private int cliqueme(MouseEvent event) {
        SelectionModel<Marque> m= tbmarque.getSelectionModel();
    	Marque c=m.getSelectedItem();
    	
        String idm=""+c.getId_marque(); 
         System.out.println(idm);
        if (idm!=null){
    	btnsupp.setVisible(true);
        btnmodifier.setVisible(true);
        
        }
    	
    	return c.getId_marque();
        
    }

    @FXML
    private void validermodif(ActionEvent event) {
        
        while (!txtlib.getText().trim().equals("")){
         int idd=cliqueme(null);
        Marque m = new Marque(idd,txtlib.getText());
        mc.modifierMarque(m);
        btnajoutm.setVisible(true);
        btnsupp.setVisible(false);
        btnmodifier.setVisible(false);
        btnvalid.setVisible(false);
        txtlib.setText("");
        tbmarque.getItems().clear();
        tbmarque.setItems(mc.afficherMarque());
        }
    }

}
