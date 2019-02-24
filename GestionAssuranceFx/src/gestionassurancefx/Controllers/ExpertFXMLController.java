/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Expert;
import gestionassurancefx.Services.ExpertService;
import gestionassurancefx.Utils.Connexion;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ExpertFXMLController implements Initializable {
    @FXML
    private AnchorPane mainViewExpert;
    @FXML
    private Label label;
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
    private TextField descriptionT;
    @FXML
    private Button ajouter;
  
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

    
    @FXML
    private TextField idT;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Button affecter;
    @FXML
    private TextField rechercherT;
    @FXML
    private Button rechercher;
    @FXML
    private Button raf;
    @FXML
    private Button affi;
    @FXML
    private Button imprimer;
    
    public static int cinexpert;
     Connection C = Connexion.getInstance().getCon();
    ExpertService ES=new ExpertService();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        supprimer.setVisible(false);
           
             affecter.setVisible(false);
       modifier.setVisible(false);
       idT.setVisible(false);
       afficherExpert();
       comboBox.getItems().addAll("oui","non");
       raf.setVisible(false);
       
             Pattern intPattern = Pattern.compile("-?\\d*");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (intPattern.matcher(change.getControlNewText()).matches()) {
                return change;
            }
            return null;
        };
        TextFormatter textFormatter = new TextFormatter(filter);
        TextFormatter tF1 = new TextFormatter(filter);
        TextFormatter tF2 = new TextFormatter(filter);
        TextFormatter tF3 = new TextFormatter(filter);
     
        
        idT.setTextFormatter(textFormatter);
        cinT.setTextFormatter(tF1);
        faxT.setTextFormatter(tF2);
    
    
        numeroT.setTextFormatter(tF3);
     
    


    }    

    @FXML
    private void ajouterEx(ActionEvent event) {
        
        
     String erreur="";
     if(numeroT.getText().length()!=8)
         erreur= erreur+ "verifier la longueur du telephone";
    if(!mailT.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) 
           erreur=erreur+"verifier votre adresseMail";

    
    if (!erreur.equals("")) {
  Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText(erreur);
                alert.showAndWait();
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

      
          e.setDescriptionEx(descriptionT.getText());

        ES.ajouterExpert(e);
        afficherExpert();
    }

    }
    
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
          
    
           descriptionT.setText(Os.getDescriptionEx());
        
     
        ajouter.setVisible(false);
     modifier.setVisible(true);
     affecter.setVisible(true);
raf.setVisible(true);

     supprimer.setVisible(true);


    }

    @FXML
    private void modifierExpert(ActionEvent event) {
        
        
        
          String erreur="";
     if(numeroT.getText().length()!=8)
         erreur= erreur+ "verifier la longueur du telephone";
    if(!mailT.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) 
           erreur=erreur+"verifier votre adresseMail";

    
    if (!erreur.equals("")) {
       Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText(erreur);
                alert.showAndWait();
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
      
        descriptionT.clear();
        
      
        ajouter.setVisible(true);
        modifier.setVisible(false);
        supprimer.setVisible(false);
        affecter.setVisible(false);
   

    }

    @FXML
    private void supprimerExpert(ActionEvent event) {
       
          supprimer.setOnAction(e->{
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to delete");
           Optional <ButtonType> action=alert.showAndWait();
           if(action.get()==ButtonType.OK)
                {
        
        Expert Os = table.getSelectionModel().getSelectedItem();
       
        ES.supprimerExpert(Os.getIdEx());
        afficherExpert();
       Rafraichir();
                  }
                   });

    }
    

    @FXML
    private void affecter(ActionEvent event) throws IOException {
        //String message="";
        
        cinexpert=Integer.parseInt(cinT.getText());
     AnchorPane paneExpert=FXMLLoader.load(getClass().getResource("gestionassurancefx/Views/ReparateurFXML.fxml"));
      mainViewExpert.getChildren().setAll(paneExpert);
       //Alert.desplay("affectation","vous avec affecter l'expert avec le cin: /n"+cinexpert);
       
       
       

    }

    @FXML
    private void rechercher(ActionEvent event) {
                  table.setItems(ES.rechercherExpert(rechercherT.getText()));
      table.setEditable(true);
        affi.setVisible(true);

    }

    @FXML
    private void raf(ActionEvent event) {
        Rafraichir();

    }

    @FXML
    private void affi(ActionEvent event) {
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
    
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout
        = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX
        = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
    double scaleY
        = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    node.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, node);
      if (success) {
        job.endJob();

      }
    }
    node.getTransforms().remove(scale);
  }



    @FXML
    private void imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(table);

    }
    
}
