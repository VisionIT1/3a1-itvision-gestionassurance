/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.AssureEntreprise;
import gestionassurancefx.Entities.AssureParticulier;
import gestionassurancefx.Services.AssureEntrepriseCrud;
import gestionassurancefx.Services.AssureParticulierCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class GestionAssureParticulierController implements Initializable {
    @FXML
    public TextField cinField;
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
    private Button AjouterBtn;
    private AssureParticulierCrud crud;
    private AssureEntrepriseCrud crudEntr;

        @FXML
        private AnchorPane assPartPane;
    
    @FXML
    private AnchorPane assEntrPane;
    
   
    public static String cinCont;
    public static String nomEntrCont;
   
    @FXML
    private TextField nomFIeldEntr;
    @FXML
    private TextField emailFieldEntr;
    @FXML
    private TextField numtelFieldEntr;
    @FXML
    private TextField adresseFieldEntr;
    @FXML
    private Button AjouterBtnEntr;
    @FXML   
    private AnchorPane assPartPane1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud=new AssureParticulierCrud();
        crudEntr=new AssureEntrepriseCrud();
    }    

    
    public String getCinFromCont(){
         return cinField.getText();
    }
    public String getNomEntrFromCont(){
        return nomFIeldEntr.getText();
    }
    
    
      private boolean validateNom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nomFIeld.getText());
        if(m.find() && m.group().equals(nomFIeld.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Valider le Nom");
                alert.setHeaderText(null);
                alert.setContentText("Entrer le nom");
                alert.showAndWait();
            
            return false;            
        }
    }
      
      
      private boolean validatePrenom(){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(prenomFIeld.getText());
        if(m.find() && m.group().equals(prenomFIeld.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Valider le Prenom");
                alert.setHeaderText(null);
                alert.setContentText("Entrer le prenom");
                alert.showAndWait();
            
            return false;            
        }
    }
      
       private boolean validateAdresse(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]+");
        Matcher m = p.matcher(adresseField.getText());
        if(m.find() && m.group().equals(adresseField.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Valider le Prenom");
                alert.setHeaderText(null);
                alert.setContentText("Entrer le prenom");
                alert.showAndWait();
            
            return false;            
        }
    }
    
     private boolean validateCin(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(cinField.getText());
        if(m.find() && m.group().equals(cinField.getText())){
            return true;
        }else{
               Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Cin");
                alert.setHeaderText(null);
                alert.setContentText("Entrer un CIN Valide");
                alert.showAndWait();
     
          
            
            return false;            
        }
    }
     
     
      private boolean validateMobileNo(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(numtelField.getText());
        if(m.find() && m.group().equals(numtelField.getText())){
            return true;
        }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Mobile Number");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Mobile Number");
                alert.showAndWait();
           
            
            return false;            
        }
    }
    
    
     public boolean validateEmail(){
       
       Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailField.getText());
        if(m.find() && m.group().equals(emailField.getText())){
            return true;
        }else{
               Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Email");
                alert.setHeaderText(null);
                alert.setContentText("Entrer un email Valide");
                alert.showAndWait();
           
            
            return false;            
        }
     }
    
     private boolean validateFields(){
        if( cinField.getText().isEmpty()){
            
              Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrer le CIN");
                alert.showAndWait();
                
                return false;
        }
        if(nomFIeld.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrer le Nom");
                alert.showAndWait();
                
                return false;
        }
         if(prenomFIeld.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("le prenom ne doit pas etre vide");
                alert.showAndWait();
                
                return false;
        }
        
        if(emailField.getText().isEmpty()){
            
               Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrer l'Email");
                alert.showAndWait();
                
                return false;
        }
        if(numtelField.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrer le Numéro de télephone");
                alert.showAndWait();
                
                return false;
        }
        
          if(adresseField.getText().isEmpty()){
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Entrer l'adresse");
                alert.showAndWait();
                
                return false;
        }
        
        return true;
    }
     
     
     
    @FXML
    private void btnClicked(ActionEvent event) throws IOException {
     
            AssureParticulier ap=new AssureParticulier();
            
            if(validateCin() && validateNom()&& validatePrenom() && validateEmail() && validateMobileNo() && validateAdresse() && validateFields()  ){
            ap.setCin(Integer.parseInt(cinField.getText()));
            ap.setNom(nomFIeld.getText());
            ap.setPrenom(prenomFIeld.getText());
            ap.setEmail(emailField.getText());
            ap.setNumtel(Integer.parseInt(numtelField.getText()));
            ap.setAdresse(adresseField.getText());
            crud.ajouterAssureParticulier(ap);
            crudEntr=new AssureEntrepriseCrud();
            cinCont=getCinFromCont();
            
             
            
            
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionContrat.fxml"));
            
            assPartPane1.getChildren().setAll(pane);
            
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajout avec succés");
                alert.setHeaderText(null);
                alert.setContentText("Assuré particulié ajouté");
                alert.showAndWait();
            }
    }

    @FXML
    private void EntrbtnClicked(ActionEvent event) throws IOException {
         AssureEntreprise Ar = new AssureEntreprise();
        Ar.setNomEntr(nomFIeldEntr.getText());
        Ar.setEmailEntr(emailFieldEntr.getText());
        Ar.setNumTel(numtelFieldEntr.getText());
        Ar.setAdresseEntr(adresseFieldEntr.getText());
        crudEntr.ajouterAssureEntreprise(Ar);
        nomEntrCont=getNomEntrFromCont();
         AnchorPane pane=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionContrat.fxml"));
         assEntrPane.getChildren().setAll(pane);
        System.out.println("Entreprise Ajoute");
    }
    


}
  
    