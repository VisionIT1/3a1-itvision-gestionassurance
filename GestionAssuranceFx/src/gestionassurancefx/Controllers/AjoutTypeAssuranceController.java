/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Garantie;
import gestionassurancefx.Entities.Habitation;
import gestionassurancefx.Entities.LigneGarantie;
import gestionassurancefx.Entities.Marque;
import gestionassurancefx.Entities.Vehicule;
import gestionassurancefx.Entities.Voyage;
import gestionassurancefx.Services.GarantieCrud;
import gestionassurancefx.Services.HabitationCrud;
import gestionassurancefx.Services.LigneGarantieCrud;
import gestionassurancefx.Services.MarqueCrud;
import gestionassurancefx.Services.VehiculeCrud;
import gestionassurancefx.Services.VoyageCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Psico
 */
public class AjoutTypeAssuranceController implements Initializable {
    
    private VehiculeCrud vc=new VehiculeCrud();
    private VoyageCrud voyc=new VoyageCrud();
    private GarantieCrud gc=new GarantieCrud();
    private LigneGarantieCrud lgc=new LigneGarantieCrud();
    private HabitationCrud hc= new HabitationCrud();
    private MarqueCrud mc=new MarqueCrud();
    
    private static int idm=0;
    public static int idtype;
    public static String nomtype;
    private static int maxgaran=1;
    private static int maxgar=1;
   
    private CheckBox[] chekb=new CheckBox[100];
    private RadioButton[] rb1=new RadioButton[100];
    @FXML
    private ComboBox<String> cbtypea;
    @FXML
    private AnchorPane c1;
    @FXML
    private TextField txtnbplace;
    @FXML
    private TextField txtpuiss;
    @FXML
    private TextField txtvalneuf;
    @FXML
    private TextField txtannconst;
    @FXML
    private TextField txtvalven;
    @FXML
    private VBox c1radio;
    @FXML
    private AnchorPane c2;
    @FXML
    private TextField txtnbpiece;
    @FXML
    private TextField txtcapass;
    @FXML
    private RadioButton rapp;
    @FXML
    private RadioButton rvilla;
    @FXML
    private RadioButton rbvo;
    @FXML
    private RadioButton rbvn;
    @FXML
    private RadioButton rsysao;
    @FXML
    private RadioButton rsysan;
    @FXML
    private Button btnsuiv;
    @FXML
    private AnchorPane c3;
    @FXML
    private TextField txtage;
    @FXML
    private TextField txtdest;
    @FXML
    private ComboBox<String> cbdsej;
    @FXML
    private VBox c31;
    @FXML
    private ComboBox<String> cbmrqv;
    @FXML
    private TextField txtimat;
    @FXML
    private AnchorPane ctypeass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        cbtypea.getItems().addAll("Vehicule", "Habitation","Voyage");
        cbdsej.getItems().addAll("entre 1 et 3 mois","entre 3 et 9 mois","entre 9 et 12 mois","Superieur a 1 an");
           c1.setVisible(false);
           c2.setVisible(false);
           c3.setVisible(false);
        for (Marque m : mc.afficherMarque()){
        cbmrqv.getItems().add(m.getLib_mrq());
        }

        int gg=1;
        ToggleGroup group = new ToggleGroup();
        for (Garantie g : gc.afficherGarantie()){
            if (g.getCategorie().trim().equalsIgnoreCase("Vehicule")){
                
                rb1[gg] =new RadioButton(g.getLib());
                
                c1radio.getChildren().add(rb1[gg]); 
                rb1[gg].setToggleGroup(group);
                gg++;
                maxgar++;
            
            }
        }
        
        int kk=1;
        for (Garantie g : gc.afficherGarantie()){
            if (g.getCategorie().trim().equalsIgnoreCase("Voyage")){
                chekb[kk]=new CheckBox(g.getLib());
                c31.getChildren().add(chekb[kk]);
                maxgaran++;
                kk++;
                
            }
            }
       
    }    

    @FXML
    private void suivantAjout(ActionEvent event) {
        
        
        if (cbtypea.getSelectionModel().getSelectedItem().equals("Vehicule")){  
            try {
                nomtype="Vehicule";
                int c=retourneidmrq(null);
                int s=retourneidbtnradio();
                Vehicule v=new Vehicule(txtimat.getText(),Integer.parseInt(txtpuiss.getText()),Float.parseFloat(txtvalneuf.getText()),Float.parseFloat(txtvalven.getText()),Integer.parseInt(txtannconst.getText()),c,s);
                vc.ajouterVehicule(v);
                idtype=vc.retourneidvehicule();
                AnchorPane pane=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionContrat.fxml"));
                ctypeass.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(AjoutTypeAssuranceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
        }else if (cbtypea.getSelectionModel().getSelectedItem().equals("Habitation")){
            nomtype="Habitation";
          
            try {
                if (rvilla.isSelected()){
                    if(rbvo.isSelected()){
                        if (rsysao.isSelected()){
                            Habitation h=new Habitation (1,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),1,rvilla.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }else{
                            Habitation h=new Habitation (1,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),0,rvilla.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }
                    }else{
                        if (rsysao.isSelected()){
                            Habitation h=new Habitation (0,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),1,rvilla.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }else{
                            Habitation h=new Habitation (0,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),0,rvilla.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }
                    }
                }else{
                    if(rbvo.isSelected()){
                        if (rsysao.isSelected()){
                            Habitation h=new Habitation (1,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),1,rapp.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }else{
                            Habitation h=new Habitation (1,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),0,rapp.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }
                    }else{
                        if (rsysao.isSelected()){
                            Habitation h=new Habitation (0,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),1,rapp.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }else{
                            Habitation h=new Habitation (0,Integer.parseInt(txtnbpiece.getText()),Float.parseFloat(txtcapass.getText()),0,rapp.getText());
                            hc.ajouterHabitation(h);
                            idtype=hc.retourneidHabitation();
                        }  
                    }
                }
                AnchorPane pane=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionContrat.fxml"));
                ctypeass.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(AjoutTypeAssuranceController.class.getName()).log(Level.SEVERE, null, ex);
            }
    } else if (cbtypea.getSelectionModel().getSelectedItem().equals("Voyage")){
            try {
                nomtype="Voyage";
               
                if( cbdsej.getSelectionModel().getSelectedItem().trim().equals("entre 1 et 3 mois")){
                    Voyage v=new Voyage(txtdest.getText(),3,Integer.parseInt(txtage.getText()));
                    voyc.ajouterVoyage(v);
                    idtype=voyc.retourneidvoyage();
                    for (Garantie g : retournegarantvoyage()){
                        
                        LigneGarantie lgm=new LigneGarantie(g.getId_garantie(),idtype, g.getPrime());
                        lgc.ajouterLigneGarantie(lgm);
                    }
                }
                if( cbdsej.getSelectionModel().getSelectedItem().trim().equals("entre 3 et 9 mois")){
                    Voyage v=new Voyage(txtdest.getText(),9,Integer.parseInt(txtage.getText()));
                    voyc.ajouterVoyage(v);
                     idtype=voyc.retourneidvoyage();
                    for (Garantie g : retournegarantvoyage()){
                       
                        LigneGarantie lgm=new LigneGarantie(g.getId_garantie(),idtype, g.getPrime());
                        lgc.ajouterLigneGarantie(lgm);
                    }
                }
                if( cbdsej.getSelectionModel().getSelectedItem().trim().equals("entre 9 et 12 mois")){
                    Voyage v=new Voyage(txtdest.getText(),12,Integer.parseInt(txtage.getText()));
                    voyc.ajouterVoyage(v);
                     idtype=voyc.retourneidvoyage();
                    for (Garantie g : retournegarantvoyage()){
                        
                        LigneGarantie lgm=new LigneGarantie(g.getId_garantie(),idtype, g.getPrime());
                        lgc.ajouterLigneGarantie(lgm);
                    }
                }
                if( cbdsej.getSelectionModel().getSelectedItem().trim().equals("Superieur a 1 an")){
                    Voyage v=new Voyage(txtdest.getText(),1,Integer.parseInt(txtage.getText()));
                    voyc.ajouterVoyage(v);
                     idtype=voyc.retourneidvoyage();
                    for (Garantie g : retournegarantvoyage()){
                     
                        LigneGarantie lgm=new LigneGarantie(g.getId_garantie(),idtype, g.getPrime());
                        lgc.ajouterLigneGarantie(lgm);
                    }
                }
                
         
        
                AnchorPane pane=FXMLLoader.load(getClass().getResource("/gestionassurancefx/Views/GestionContrat.fxml"));
                ctypeass.getChildren().setAll(pane);
            } catch (IOException ex) {
                Logger.getLogger(AjoutTypeAssuranceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
    }
            
        }
        
        
        
    

    @FXML
    private void choisirtypeassur(ActionEvent event) {
       if (cbtypea.getSelectionModel().getSelectedItem().equals("Vehicule")){
           c1.setVisible(true);
           c2.setVisible(false);
           c3.setVisible(false);
       }else if (cbtypea.getSelectionModel().getSelectedItem().equals("Habitation")){
           c1.setVisible(false);
           c2.setVisible(true);
           c3.setVisible(false);
       }else if (cbtypea.getSelectionModel().getSelectedItem().equals("Voyage")){
           c1.setVisible(false);
           c2.setVisible(false);
           c3.setVisible(true);
       }
    }

    @FXML
    private int retourneidmrq(ActionEvent event) {
        int k=0;
        for (Marque m : mc.afficherMarque()){
           if( cbmrqv.getSelectionModel().getSelectedItem().trim().equals(m.getLib_mrq())){
               k=m.getId_marque();
                }
        }
        return k;
    }
    
    private int retourneidbtnradio(){
        int d=0;
       
        for (Garantie g: gc.afficherGarantie()){
          for (int ff=1;ff<maxgar;ff++){
            if (rb1[ff].isSelected()){    
            if(g.getLib().trim().equalsIgnoreCase(rb1[ff].getText().trim())){
                d=g.getId_garantie();
            }
            }
          }
          
        }
        return d;
    }
    
    private List<Garantie> retournegarantvoyage(){
        
        List<Garantie> l=new ArrayList<Garantie>();
        
       
  
        for (Garantie g: gc.afficherGarantie()){
          
           for (int ff=1;ff<maxgaran;ff++){
               if (chekb[ff].isSelected()){   
            if(g.getLib().trim().equalsIgnoreCase(chekb[ff].getText().trim())){
                  
                l.add(g);
            }
               }
            
           }
            }
        
        
        
        
        return l;
    }

    @FXML
    private void retourduresej(ActionEvent event) {
    }
    
}
