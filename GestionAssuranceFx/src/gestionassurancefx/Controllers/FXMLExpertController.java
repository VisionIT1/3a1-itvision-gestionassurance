/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Expert;
import gestionassurancefx.Services.ExpertService;
import gestionassurancefx.Utils.Alerte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ASUS
 */
public class FXMLExpertController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField idT;
    @FXML
    private TextField cinT;
    @FXML
    private TextField faxT;
    @FXML
    private TextField nomT;
    @FXML
    private TextField prenomT;
    @FXML
    private TextField mailT;
    @FXML
    private TextField numeroT;
    @FXML
    private TextField adresseT;
    @FXML
    private TextField etatT;
    @FXML
    private TextField descriptionT;
    @FXML private ComboBox<String> comboBox ;
     
    
    @FXML private TableView<Expert> table;
     @FXML  private TableColumn<Expert,Integer> id;
     @FXML  private TableColumn<Expert,Integer> cin;
      @FXML  private TableColumn<Expert,Integer> fax; 
     @FXML  private TableColumn<Expert,String> nom;   
      @FXML  private TableColumn<Expert,String> prenom;
      @FXML  private TableColumn<Expert,String> mail;
     @FXML  private TableColumn<Expert,Integer> numero;
      @FXML  private TableColumn<Expert,String> adresse;
     @FXML  private TableColumn<Expert,String> etat;
      @FXML  private TableColumn<Expert,String> description;

    
         
      ExpertService ES=new ExpertService();
    @FXML
    private Button ajouter;
    @FXML
    private Button afficher;
      @FXML
    private Button modifier;
           @FXML
    private Button supprimer;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       modifier.setVisible(false);
       idT.setVisible(false);
       afficherExpert();
       comboBox.getItems().addAll("oui","non");
    }    

    @FXML
    public void ajouterEx(ActionEvent event) {
    
     
     
     String erreur="";
     if(numeroT.getText().length()!=8)
         erreur= erreur+ "verifier la longueur du téléphone";
    if(!mailT.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) 
           erreur=erreur+"vérifier votre adresseMail";

    
    if (!erreur.equals("")) {
            Alerte.desplay("erreur", erreur);
        } else {
       
     Expert e= new Expert();
        e.setCinEx(Integer.parseInt( cinT.getText()));
        e.setFaxEx(Integer.parseInt(faxT.getText()));
       e.setNomEx(nomT.getText());
        e.setPrenomEx(prenomT.getText());
          e.setMailEx(mailT.getText());
      e.setNumeroEx(Integer.parseInt( numeroT.getText()));
         e.setAdresseEx(adresseT.getText()); 
        e.setEtatEx( comboBox.getSelectionModel().getSelectedItem());
        // e.setEtatEx(etatT.getText());
          e.setDescriptionEx(descriptionT.getText());

        ES.ajouterExpert(e);
        afficherExpert();
    }

    }
      
    @FXML
        public void afficherExpert() {
        id.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("idEx"));
       cin.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("cinEx"));
        fax.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("faxEx"));
        nom.setCellValueFactory(new PropertyValueFactory<Expert, String>("nomEx"));
       prenom.setCellValueFactory(new PropertyValueFactory<Expert, String>("prenomEx"));
        mail.setCellValueFactory(new PropertyValueFactory<Expert, String>("mailEx"));
        numero.setCellValueFactory(new PropertyValueFactory<Expert, Integer>("numeroEx"));
        adresse.setCellValueFactory(new PropertyValueFactory<Expert, String>("adresseEx"));
        etat.setCellValueFactory(new PropertyValueFactory<Expert, String>("etatEx"));
        description.setCellValueFactory(new PropertyValueFactory<Expert, String>("descriptionEx"));
        
        table.setItems(ES.afficherExpert());
        table.setEditable(true);

       

    }

    @FXML
    private void itemClicked(MouseEvent event) {
        
        

        Expert Os = table.getSelectionModel().getSelectedItem();
         idT.setText(""+Os.getIdEx());
        cinT.setText(""+Os.getCinEx());
        faxT.setText(""+Os.getFaxEx());
       nomT.setText(Os.getNomEx());
         prenomT.setText(Os.getPrenomEx());
         mailT.setText(Os.getMailEx());
         numeroT.setText(""+Os.getNumeroEx());
         adresseT.setText(Os.getAdresseEx());
      
            comboBox.getSelectionModel().select(Os.getEtatEx());
          
      //  etatT.setText(Os.getEtatEx());
           descriptionT.setText(Os.getDescriptionEx());
        
     
        ajouter.setVisible(false);
     modifier.setVisible(true);


    }
    
    

    @FXML
    private void modifierExpert(ActionEvent event) {
        
          String erreur="";
     if(numeroT.getText().length()!=8)
         erreur= erreur+ "verifier la longueur du téléphone";
    if(!mailT.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) 
           erreur=erreur+"vérifier votre adresseMail";

    
    if (!erreur.equals("")) {
            Alerte.desplay("erreur", erreur);
        } else {
        Expert O = new Expert(Integer.parseInt(idT.getText()), Integer.parseInt(cinT.getText()), Integer.parseInt(faxT.getText()), nomT.getText(), prenomT.getText(),mailT.getText(), Integer.parseInt(numeroT.getText()),adresseT.getText(),comboBox.getSelectionModel().getSelectedItem(),descriptionT.getText());
        ES.modifierExpert(O);
      ajouter.setVisible(true);
        modifier.setVisible(false);
        idT.clear();
       cinT.clear();
       faxT.clear();
      nomT.clear();
        prenomT.clear();
        mailT.clear();
        numeroT.clear();
        adresseT.clear();
        
        //comboBox.disableProperty();
       // etatT.clear();
        descriptionT.clear();

        afficherExpert();

    }
    }
     public void Rafraichir()
    {
       
        idT.clear();
        cinT.clear();
       faxT.clear();
       nomT.clear();
       prenomT.clear();
       mailT.clear();
        numeroT.clear();
        adresseT.clear();
        etatT.clear();
        descriptionT.clear();
        
      
        ajouter.setVisible(true);
        modifier.setVisible(false);
    }


     @FXML
    private void supprimerExpert(ActionEvent event) {
        
        Expert Os = table.getSelectionModel().getSelectedItem();
        ES.supprimerExpert(Os.getIdEx());
        afficherExpert();
       Rafraichir();
    }

    
    
}
