/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.AssureEntreprise;
import gestionassurancefx.Services.AssureEntrepriseCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class GestionAssureEntrepriseController implements Initializable {
    @FXML
    private TextField nomFIeld;
    @FXML
    private TextField emailField;
    @FXML
    private TextField numtelField;
    @FXML
    private TextField adresseField;
    @FXML
    private Button AjouterBtn;
    private AssureEntrepriseCrud crud;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud=new AssureEntrepriseCrud();
    }    

    @FXML
    private void btnClicked(ActionEvent event) {
        
        AssureEntreprise Ar = new AssureEntreprise();
        Ar.setNomEntr(nomFIeld.getText());
        Ar.setEmailEntr(emailField.getText());
        Ar.setNumTel(numtelField.getText());
        Ar.setAdresseEntr(adresseField.getText());
        crud.ajouterAssureEntreprise(Ar);
        System.out.println("Entreprise Ajoute");
    }
    
}
