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
import gestionassurancefx.Entities.Sinistre;
import gestionassurancefx.Services.Service_Sinistre;
import static gestionassurancefx.Services.Service_Sinistre.c;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
         
 ProgressBar p2 = new ProgressBar();
 p2.setProgress(0.25F);
        
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
        
        s=sinistreView.getSelectionModel().getSelectedItem();
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
    private void searchon3(KeyEvent event) {
        String ch = search.getText();
        Service_Sinistre ss=new Service_Sinistre();
        sinistreView.getItems().clear();
        sinistreView.setItems(ss.trouverAll(ch));
    }

    @FXML
    private void export(ActionEvent event) throws FileNotFoundException, IOException {
        
        ObservableList<Sinistre> listeone=FXCollections.observableArrayList();
        
        try {
            Statement pstmt =c.createStatement();
           String req = "select *from sinistre where code_assureur="+s.getCode_assureur();
            ResultSet rs= pstmt.executeQuery(req);
            
            
            
             XSSFWorkbook wb= new XSSFWorkbook();
        
        XSSFSheet sheet =wb.createSheet("User details");
        XSSFRow head=sheet.createRow(0);
        head.createCell(0).setCellValue("Cin");
        head.createCell(1).setCellValue("Date declaration");
        head.createCell(2).setCellValue("Date sinistre");
        head.createCell(3).setCellValue("Lieu sinistre");
        head.createCell(4).setCellValue("Numero sinistre");
        head.createCell(5).setCellValue("Domage materielle");
        head.createCell(6).setCellValue("Domage corporelle");
        head.createCell(7).setCellValue("Description");
        int i=1;
         while (rs.next())
         {
            XSSFRow row =sheet.createRow(i);
            
            row.createCell(0).setCellValue(rs.getInt(8));
            row.createCell(1).setCellValue(rs.getDate(2));
            row.createCell(2).setCellValue(rs.getDate(3));
            row.createCell(3).setCellValue(rs.getString(4));
            row.createCell(4).setCellValue(rs.getInt(5));
            row.createCell(5).setCellValue(rs.getInt(6));
            row.createCell(6).setCellValue(rs.getInt(7));
            row.createCell(7).setCellValue(rs.getString(9));
            i++;
         }
       
        
        FileOutputStream fileout=new FileOutputStream("Userdetails.xlsx");
        wb.write(fileout);
        fileout.close();
            
        } catch (Exception e) {
            System.out.println("lol");
        }
        
        
        
      /*  XSSFWorkbook wb= new XSSFWorkbook();
        
        XSSFSheet sheet =wb.createSheet("User details");
        XSSFRow head=sheet.createRow(0);
        head.createCell(0).setCellValue("Cin");
        head.createCell(1).setCellValue("Date declaration");
        head.createCell(2).setCellValue("Date sinistre");
        head.createCell(3).setCellValue("Lieu sinistre");
        head.createCell(4).setCellValue("Numero sinistre");
        head.createCell(5).setCellValue("Domage materielle");
        head.createCell(6).setCellValue("Domage corporelle");
        head.createCell(7).setCellValue("Description");
        listeone=crud.selectone(s.getCode_assureur());
        sinistreView.getItems().clear();
        sinistreView.setItems(listeone);
        Sinistre q=new Sinistre();
        for (int i =0 ;i<listeone.size();i++)
        {
            q=sinistreView.getSelectionModel().getSelectedItem();
            XSSFRow row =sheet.createRow(i+1);
            System.out.println(q.getCode_assureur());
            row.createCell(0).setCellValue(q.getCode_assureur());
            row.createCell(1).setCellValue(q.getDate_declaration());
            row.createCell(2).setCellValue(q.getDate_sinistre());
            row.createCell(3).setCellValue(q.getLieu_sinistre());
            row.createCell(4).setCellValue(q.getNumero_sinistre());
            row.createCell(5).setCellValue(q.getDomage_mat());
            row.createCell(6).setCellValue(q.getDomage_corps());
            row.createCell(7).setCellValue(q.getDescription());
        }
        FileOutputStream fileout=new FileOutputStream("Userdetails.xlsx");
        wb.write(fileout);
        fileout.close();*/
        
            
      
        
    }

    
}
