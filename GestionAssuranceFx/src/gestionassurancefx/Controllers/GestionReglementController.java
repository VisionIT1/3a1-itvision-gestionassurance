/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionassurancefx.Controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import static gestionassurancefx.Controllers.SinistreController.cinAssu;
import static gestionassurancefx.Controllers.SinistreController.cinExx;
import static gestionassurancefx.Controllers.SinistreController.cinRepp;
import gestionassurancefx.Entities.AssureParticulier;
import gestionassurancefx.Entities.Expert;
import gestionassurancefx.Entities.Reglement;
import gestionassurancefx.Entities.Sinistre;
import gestionassurancefx.Services.Service_Reglement;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author youssef
 */
public class GestionReglementController implements Initializable {
    @FXML
    private DatePicker dateReglement;
    @FXML
    private JFXTextField fraisExpert;
    @FXML
    private JFXToggleButton modeReg;
    @FXML
    private JFXTextField montantTotale;
    @FXML
    private JFXButton btnAjouterReg;
  
    private Service_Reglement Crud;
    @FXML
    private TableColumn<Reglement,LocalDate> DateRegl;
    @FXML
    private TableColumn<Reglement, Integer> FraisEx;
    @FXML
    private TableColumn<Reglement, String> ModeRegl;
    @FXML
    private TableColumn<Reglement,Integer> MontantRegl;
    @FXML
    private TableView<Reglement> reglementView;
    @FXML
    private JFXTextField idRperateur;
    @FXML
    private JFXTextField idExperet;
    @FXML
    private JFXButton btnSuppReg;
    @FXML
    private JFXButton btnModiferReg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initColumns();
        Crud=new Service_Reglement();
      
        
    }    
          public void initColumns() {
         DateRegl.setCellValueFactory(new PropertyValueFactory<Reglement, LocalDate>("Date_regl"));
        FraisEx.setCellValueFactory(new PropertyValueFactory<Reglement, Integer>("Frais_expert"));
        ModeRegl.setCellValueFactory(new PropertyValueFactory<Reglement, String>("Mode_regl"));
        MontantRegl.setCellValueFactory(new PropertyValueFactory<Reglement, Integer>("Montant_regl"));
        dateReglement.setDisable(false);
        fraisExpert.setDisable(false);
        montantTotale.setDisable(false);
        modeReg.setDisable(false);
        btnAjouterReg.setDisable(false);
       
        }

    @FXML
    private void ajouterReg(ActionEvent event) {
        
         Date dateReg = java.sql.Date.valueOf(dateReglement.getValue());
         Reglement Rg=new Reglement();
        Rg.setCinAssureur(cinAssu);
        Rg.setIdRep(cinRepp);
        Rg.setIdEx(cinExx);
        Rg.setCode_sin(SinistreController.cconstat);
        Rg.setDate_regl(dateReg);
        Rg.setFrais_expert(Integer.parseInt(fraisExpert.getText()));
        if (modeReg.isSelected()) {
           Rg.setMode_regl("Cheque");
        } else {
            Rg.setMode_regl("Espece");
        }
        Rg.setMontant_regl(Integer.parseInt(montantTotale.getText()));
        Crud.ajouterReglement(Rg);
        reglementView.getItems().clear();
        reglementView.setItems(Crud.getAllReglement());
        dateReglement.setDisable(true);
        fraisExpert.setDisable(true);
        montantTotale.setDisable(true);
        modeReg.setDisable(true);
        btnAjouterReg.setDisable(true);
    }
 public static Reglement reg=new Reglement();
    @FXML
    private void selectItem(MouseEvent event) {
        reg=reglementView.getSelectionModel().getSelectedItem();
        
        fraisExpert.setText(Integer.toString(reg.getFrais_expert()));
        dateReglement.setValue(reg.getDate_regl().toLocalDate());
        if(reg.getMode_regl().equals("Cheque")){
            modeReg.setSelected(true);
        }else
        if(reg.getMode_regl().equals("Espece")){
            modeReg.setSelected(false);
        }
        montantTotale.setText(Integer.toString(reg.getMontant_regl()));
        idExperet.setText(Integer.toString(reg.getIdEx()));
        idRperateur.setText(Integer.toString(reg.getIdRep()));
        
        dateReglement.setDisable(false);
        fraisExpert.setDisable(false);
        montantTotale.setDisable(false);
        modeReg.setDisable(false);
        btnModiferReg.setDisable(false);
        btnSuppReg.setDisable(false);
        idExperet.setDisable(false);
        idRperateur.setDisable(false);
        idExperet.setVisible(true);
        idRperateur.setVisible(true);
        btnAjouterReg.setVisible(false);
        btnModiferReg.setVisible(true);
        btnSuppReg.setVisible(true);
    }

    @FXML
    private void supprimerReg(ActionEvent event) {
        Crud.supprimerReglement(reglementView.getSelectionModel().getSelectedItem().getCode_regl());
        dateReglement.setDisable(true);
        fraisExpert.setDisable(true);
        montantTotale.setDisable(true);
        modeReg.setDisable(true);
        btnModiferReg.setDisable(true);
        btnSuppReg.setDisable(true);
        idExperet.setDisable(true);
        idRperateur.setDisable(true);
        
        reglementView.getItems().clear();
        reglementView.setItems(Crud.getAllReglement());
    }

    @FXML
    private void modifierReg(ActionEvent event) {
        reg=reglementView.getSelectionModel().getSelectedItem();
        Date dateReg = java.sql.Date.valueOf(dateReglement.getValue());
        
        reg.setIdRep(Integer.parseInt(idRperateur.getText()));
        reg.setIdEx(Integer.parseInt(idExperet.getText()));
        reg.setDate_regl(dateReg);
        reg.setFrais_expert(Integer.parseInt(fraisExpert.getText()));
        reg.setMontant_regl(Integer.parseInt(montantTotale.getText()));
        if (modeReg.isSelected()) {
           reg.setMode_regl("Cheque");
        } else {
            reg.setMode_regl("Espece");
        }
            Crud.modifierReglement(reg);
         reglementView.getItems().clear();
        reglementView.setItems(Crud.getAllReglement());
         dateReglement.setDisable(true);
        fraisExpert.setDisable(true);
        montantTotale.setDisable(true);
        modeReg.setDisable(true);
        btnModiferReg.setDisable(true);
        btnSuppReg.setDisable(true);
        idExperet.setDisable(true);
        idRperateur.setDisable(true);
    }

    @FXML
    private void pdf(ActionEvent event) {
        String file =Crud.getAllR();
        Paragraph par=new Paragraph(file);
        Document doc=new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("holla.pdf"));
            doc.open();
           
            doc.add(par);
            doc.close();
        } catch (Exception e) {
            System.out.println("lol");
        }
        
    }
        
}
