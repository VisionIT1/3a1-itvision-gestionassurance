/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package  gestionassurancefx.Controllers;


import gestionassurancefx.Entities.Reparateur;
import gestionassurancefx.Services.ReparateurService;
import  gestionassurancefx.Utils.Alerte;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReparateurFXMLController implements Initializable {
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
    private ComboBox<String> etatT;
    @FXML
    private TextField descriptionT;
    @FXML
    private Button ajouter;
    
    
       ReparateurService ES=new ReparateurService();
    
    
     @FXML private TableView<Reparateur> tableRep;
     @FXML  private TableColumn<Reparateur,Integer> idRep;
     @FXML  private TableColumn<Reparateur, Integer> cinRep;
      @FXML  private TableColumn<Reparateur, Integer> faxRep; 
     @FXML  private TableColumn<Reparateur, String> nomRep;   
      @FXML  private TableColumn<Reparateur, String> prenomRep;
      @FXML  private TableColumn<Reparateur, String> mailRep;
     @FXML  private TableColumn<Reparateur, Integer> numeroRep;
      @FXML  private TableColumn<Reparateur, String> adresseRep;
     @FXML  private TableColumn<Reparateur, String> etatRep;
      @FXML  private TableColumn<Reparateur, String> descriptionRep;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          modifier.setVisible(false);
       idT.setVisible(false);
       afficherReparateur();
       etatT.getItems().addAll("oui","non");
    }    

    @FXML
    private void ajouterReparateur(ActionEvent event) {
        
        
         String erreur="";
     if(numeroT.getText().length()!=8)
         erreur= erreur+ "verifier la longueur du téléphone";
    if(!mailT.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) 
           erreur=erreur+"vérifier votre adresseMail";

    
    if (!erreur.equals("")) {
            Alerte.desplay("erreur", erreur);
        } else {
       
     Reparateur e= new Reparateur();
        e.setCinRep(Integer.parseInt( cinT.getText()));
        e.setFaxRep(Integer.parseInt(faxT.getText()));
       e.setNomRep(nomT.getText());
        e.setPrenomRep(prenomT.getText());
          e.setMailRep(mailT.getText());
      e.setNumeroRep(Integer.parseInt( numeroT.getText()));
         e.setAdresseRep(adresseT.getText()); 
        e.setEtatRep( etatT.getSelectionModel().getSelectedItem());
        // e.setEtatEx(etatT.getText());
          e.setDescriptionRep(descriptionT.getText());

        ES.ajouterReparateur(e);
        afficherReparateur();
    }
    }
    
    
      public void afficherReparateur() {
        idRep.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("idRep"));
       cinRep.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("cinRep"));
        faxRep.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("faxRep"));
        nomRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("nomRep"));
       prenomRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("prenomRep"));
        mailRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("mailRep"));
        numeroRep.setCellValueFactory(new PropertyValueFactory<Reparateur, Integer>("numeroRep"));
        adresseRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("adresseRep"));
        etatRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("etatRep"));
        descriptionRep.setCellValueFactory(new PropertyValueFactory<Reparateur, String>("descriptionRep"));
        
        tableRep.setItems(ES.afficherReparateur());
        tableRep.setEditable(true);
    
}

    @FXML
    private void itemClickedR(MouseEvent event) {
        
            Reparateur Os = tableRep.getSelectionModel().getSelectedItem();
         idT.setText(""+Os.getIdRep());
        cinT.setText(""+Os.getCinRep());
        faxT.setText(""+Os.getFaxRep());
       nomT.setText(Os.getNomRep());
         prenomT.setText(Os.getPrenomRep());
         mailT.setText(Os.getMailRep());
         numeroT.setText(""+Os.getNumeroRep());
         adresseT.setText(Os.getAdresseRep());
      
            etatT.getSelectionModel().select(Os.getEtatRep());
          
 
           descriptionT.setText(Os.getDescriptionRep());
        
     
        ajouter.setVisible(false);
     modifier.setVisible(true);

        
        
    }

    @FXML
    private void modifierReparateur(ActionEvent event) {
        
        
         
         String erreur="";
     if(numeroT.getText().length()!=8)
         erreur= erreur+ "verifier la longueur du téléphone";
    if(!mailT.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) 
           erreur=erreur+"vérifier votre adresseMail";

    
    if (!erreur.equals("")) {
            Alerte.desplay("erreur", erreur);
        } else {
       
        
             Reparateur O = new Reparateur(Integer.parseInt(idT.getText()), Integer.parseInt(cinT.getText()), Integer.parseInt(faxT.getText()), nomT.getText(), prenomT.getText(),mailT.getText(), Integer.parseInt(numeroT.getText()),adresseT.getText(),etatT.getSelectionModel().getSelectedItem(),descriptionT.getText());
        ES.modifierReparateur(O);
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
        
      
        descriptionT.clear();

        afficherReparateur();
        //Rafraichir();
        
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
      
        descriptionT.clear();
        
      
        ajouter.setVisible(true);
        modifier.setVisible(false);
        
    }

    @FXML
    private void supprimerReparateur(ActionEvent event) {
        Reparateur Os = tableRep.getSelectionModel().getSelectedItem();
        ES.supprimerReparateur(Os.getIdRep());
        afficherReparateur();
       Rafraichir();
    }

}