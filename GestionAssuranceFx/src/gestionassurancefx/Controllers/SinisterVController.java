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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import gestionassurancefx.Entities.Sinistre;
import gestionassurancefx.Services.Service_Sinistre;
import java.time.LocalDate;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class SinisterVController implements Initializable {
    @FXML
    private TableView<Sinistre> sinistreView;
    @FXML
    private TableColumn<Sinistre, LocalDate> dateDeclarationT;
    @FXML
    private TableColumn<Sinistre, LocalDate> dateSinistreT;
    @FXML
    private TableColumn<Sinistre, String> lieuSinisterT;
    @FXML
    private TableColumn<Sinistre, Integer> numSinistreT;
    @FXML
    private TableColumn<Sinistre, Integer> domMatST;
    @FXML
    private TableColumn<Sinistre, Integer> domCorpST;
    @FXML
    private TableColumn<Sinistre, Integer> ClientT;
    @FXML
    private DatePicker DateSin;
    @FXML
    private JFXTextField LieuSin;
    @FXML
    private JFXTextField NumSin;
    @FXML
    private JFXToggleButton DomMat;
    @FXML
    private JFXToggleButton DomCorp;
    @FXML
    private JFXButton btnsupp;
    @FXML
    private JFXButton btnmodif;
    private Service_Sinistre crud;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXTextField search;
    @FXML
    private Button btnsearch;

    /**
     * Initializes the controller class.
     */
     public void initColumns() {
        
        dateDeclarationT.setCellValueFactory(new PropertyValueFactory<Sinistre, LocalDate>("date_declaration"));
        dateSinistreT.setCellValueFactory(new PropertyValueFactory<Sinistre, LocalDate>("date_sinistre"));
        lieuSinisterT.setCellValueFactory(new PropertyValueFactory<Sinistre, String>("lieu_sinistre"));
        numSinistreT.setCellValueFactory(new PropertyValueFactory<Sinistre, Integer>("Numero_sinistre"));
        domCorpST.setCellValueFactory(new PropertyValueFactory<Sinistre, Integer>("Domage_corps"));
        domMatST.setCellValueFactory(new PropertyValueFactory<Sinistre, Integer>("Domage_mat"));
        ClientT.setCellValueFactory(new PropertyValueFactory<Sinistre, Integer>("Code_assureur"));

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initColumns();
        crud = new Service_Sinistre();
        sinistreView.setItems(crud.getAllSinistre());
        DateSin.setDisable(true);
        desc.setDisable(true);
        btnmodif.setDisable(true);
        btnsupp.setDisable(true);
        DomMat.setDisable(true);
        DomCorp.setDisable(true);
        NumSin.setDisable(true);
        LieuSin.setDisable(true);
      
    }    

      public static Sinistre s=new Sinistre();
    @FXML
    private void ItemSelect(MouseEvent event) {
       
        DateSin.setDisable(false);
        desc.setDisable(false);
        btnmodif.setDisable(false);
        btnsupp.setDisable(false);
        DomMat.setDisable(false);
        DomCorp.setDisable(false);
        NumSin.setDisable(false);
        LieuSin.setDisable(false);
        s=sinistreView.getSelectionModel().getSelectedItem();
        DateSin.setValue(s.getDate_sinistre().toLocalDate());
        if(s.getDomage_corps()==1){
            DomCorp.setSelected(true);
        }else
        {
            DomCorp.setSelected(false);
        }
         if(s.getDomage_mat()==1){
            DomMat.setSelected(true);
        }else
        {
            DomMat.setSelected(false);
        }
        NumSin.setText(""+s.getNumero_sinistre());
        LieuSin.setText(""+s.getLieu_sinistre());
        desc.setText(s.getDescription());
        
    }

    @FXML
    private void suppSinistre(ActionEvent event) {
        crud.supprimerSinistre(sinistreView.getSelectionModel().getSelectedItem().getCode_sinistre());
        sinistreView.getItems().clear();
        sinistreView.setItems(crud.getAllSinistre());
        DateSin.setDisable(true);
        desc.setDisable(true);
        btnmodif.setDisable(true);
        btnsupp.setDisable(true);
        DomMat.setDisable(true);
        DomCorp.setDisable(true);
        NumSin.setDisable(true);
        LieuSin.setDisable(true);
        
    }

    @FXML
    private void modifierSinistre(ActionEvent event) {
        
        s=s=sinistreView.getSelectionModel().getSelectedItem();
        s.setDate_sinistre(java.sql.Date.valueOf(DateSin.getValue()));
        s.setDescription(desc.getText());
        if(DomCorp.isSelected()){
            s.setDomage_corps(1);
        }else
            s.setDomage_corps(0);
        if(DomMat.isSelected()){
            s.setDomage_mat(1);
        }else
            s.setDomage_mat(0);
        s.setLieu_sinistre(LieuSin.getText());
        s.setNumero_sinistre(Integer.parseInt(NumSin.getText()));
        crud.modifierSinistre(s);
        sinistreView.getItems().clear();
        sinistreView.setItems(crud.getAllSinistre());
        DateSin.setDisable(true);
        desc.setDisable(true);
        btnmodif.setDisable(true);
        btnsupp.setDisable(true);
        DomMat.setDisable(true);
        DomCorp.setDisable(true);
        NumSin.setDisable(true);
        LieuSin.setDisable(true);
        
        
        
    }
       @FXML
    private void search(ActionEvent event) {
        String ch = search.getText();
        Service_Sinistre ss=new Service_Sinistre();
        sinistreView.getItems().clear();
        sinistreView.setItems(ss.trouverAll(ch));
    }
    
}
