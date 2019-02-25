/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import gestionassurancefx.Entities.Expert;
import gestionassurancefx.Entities.Sinistre;
import gestionassurancefx.Services.ExpertService;
import gestionassurancefx.Services.Service_Sinistre;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class SinistreController implements Initializable {
    public static int cinAssu;
    public static int cinExx;
    public static int cinRepp;
    public static int cconstat;
    @FXML
    private Button btnRechercheCin;
    private Service_Sinistre crud;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXToggleButton DomageMaterelle;
    @FXML
    private DatePicker dateDeclaration;
    @FXML
    private DatePicker dateSinistre;
    @FXML
    private JFXTextField lieuSinistre;
    @FXML
    private JFXTextField numeroSinistre;
    @FXML
    private JFXToggleButton DomageCorporelle;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton btnAjouterSinistre;
    @FXML
    private Label Aexpret;
    @FXML
    private Label Areparateur;
    @FXML
    private Button btnAjouterReparateurS;
    @FXML
    private Button btnAjouterExpertS;
    
    @FXML
    private AnchorPane mainView;
    @FXML
    private Label RecD;
    @FXML
    private Label Note;
    @FXML
    private Label Note1;
    @FXML
    private Label recN;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud = new Service_Sinistre();
    }    

    
      public static LocalDate asLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    
  }
    @FXML
    private void rechercheCin(ActionEvent event) {
        dateDeclaration.setDisable(true);
        dateSinistre.setDisable(true);
        lieuSinistre.setDisable(true);
        numeroSinistre.setDisable(true);
        DomageCorporelle.setDisable(true);
        DomageMaterelle.setDisable(true);
        description.setDisable(true);
        btnAjouterSinistre.setDisable(true);
        Service_Sinistre sn=new Service_Sinistre();
        String k=cin.getText();
        System.out.println(k);
        int c=Integer.parseInt(k.trim());
        int b;
        b=sn.trouverCin(c);
        if(b==c){
        dateDeclaration.setDisable(false);
        dateSinistre.setDisable(false);
        lieuSinistre.setDisable(false);
        numeroSinistre.setDisable(true);
        DomageCorporelle.setDisable(false);
        DomageMaterelle.setDisable(false);
        description.setDisable(false);
        btnAjouterSinistre.setDisable(false);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    //get current date time with Date()
    Date date = new Date();
    System.out.println(dateFormat.format(date));
    //get current date time with Calendar()
    Calendar cal = Calendar.getInstance();
    dateDeclaration.setValue(asLocalDate(date)); 
    int cinas=0;
        cinas=crud.countass(c);
        numeroSinistre.setText(""+cinas);
        
        } 
    }

    @FXML
    private void AjouterSinistre(ActionEvent event) {
        int ajout;
        int d=0,num=0;
        
        Sinistre sn = new Sinistre();
        int code=Integer.parseInt(cin.getText());
        
        if(code%2==0){
        code=(code+2)/2;
        }else{
          code=(code+1)/2;  
        }
        sn.setCode_sinistre(code);
        
        java.sql.Date date_declaration = java.sql.Date.valueOf(dateDeclaration.getValue());
        java.sql.Date date_sinistre = java.sql.Date.valueOf(dateSinistre.getValue());
        
        sn.setDate_declaration(date_declaration);
        sn.setDate_sinistre(date_sinistre);
        if(sn.getDate_declaration().after(sn.getDate_sinistre()))
        {
            d=1;
            RecD.setVisible(true);
            Note.setVisible(true);
            date_sinistre=null;
        }else{
            RecD.setVisible(false);
            Note.setVisible(false);
        }
        sn.setLieu_sinistre(lieuSinistre.getText());
        if(!validateNumber()){
             num=1;
        }else{
            sn.setNumero_sinistre(Integer.parseInt(numeroSinistre.getText()));
            Note1.setVisible(false);
            recN.setVisible(false);
        }
       
        if (DomageMaterelle.isSelected()) {
            sn.setDomage_mat(1);
        } else {
            sn.setDomage_mat(0);
        }
        if (DomageCorporelle.isSelected()) {
            sn.setDomage_corps(1);
        } else {
            sn.setDomage_corps(0);
        }
        sn.setCode_assureur(Integer.parseInt(cin.getText()));
        sn.setDescription(description.getText());
        int k;
        
        if(d==0 & num==0){
            ajout=crud.ajouterSinistre(sn);
            RecD.setVisible(false);
            Note.setVisible(false);
            Note1.setVisible(false);
            recN.setVisible(false);
        Aexpret.setDisable(true);
           Areparateur.setDisable(true);
           btnAjouterExpertS.setDisable(true);
           btnAjouterReparateurS.setDisable(true);
        if(ajout==1)
        {
           Aexpret.setDisable(false);
           Areparateur.setDisable(false);
           btnAjouterExpertS.setDisable(false);
           btnAjouterReparateurS.setDisable(false);
           ajout=0;
        }
        }
        
        
        
        
       /* sinistreView.getItems().clear();
        sinistreView.setItems(crud.getAllSinistre());*/
        
        
        
    }
    private boolean validateNumber(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(numeroSinistre.getText());
        if(m.find() && m.group().equals(numeroSinistre.getText())){
            return true;
        }else{
            System.out.println("erreur");
             Note1.setVisible(true);
            recN.setVisible(true);
            numeroSinistre.setText("");
            return false;            
        }
    }
    
   
    
    
    
    @FXML
    private void AjouterExS(ActionEvent event) throws IOException {
        cconstat=crud.idtrouve();
        cinAssu=Integer.parseInt(cin.getText());
        cinExx=100;
        cinRepp=100;
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionReglement.fxml"));
        // System.out.println(getClass().getResource("/gestionassurancefx/Views/GestionConstat.fxml"));
        mainView.getChildren().setAll(pane);
        
    }

    @FXML
    private void AjouterRepS(ActionEvent event) {
        
    }
    
    
}
