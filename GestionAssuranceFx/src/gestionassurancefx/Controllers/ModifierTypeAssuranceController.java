/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import static gestionassurancefx.Controllers.UpdateDeleteContratController.idtypeselected;
import static gestionassurancefx.Controllers.UpdateDeleteContratController.typeselected;
import gestionassurancefx.Entities.Habitation;
import gestionassurancefx.Entities.Marque;
import gestionassurancefx.Entities.Vehicule;
import gestionassurancefx.Entities.Voyage;
import gestionassurancefx.Services.GarantieCrud;
import gestionassurancefx.Services.HabitationCrud;
import gestionassurancefx.Services.LigneGarantieCrud;
import gestionassurancefx.Services.MarqueCrud;
import gestionassurancefx.Services.VehiculeCrud;
import gestionassurancefx.Services.VoyageCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Psico
 */
public class ModifierTypeAssuranceController implements Initializable {
     private VehiculeCrud vc=new VehiculeCrud();
    private VoyageCrud voyc=new VoyageCrud();
    private GarantieCrud gc=new GarantieCrud();
    private LigneGarantieCrud lgc=new LigneGarantieCrud();
    private HabitationCrud hc= new HabitationCrud();
    private MarqueCrud mc=new MarqueCrud();
    public static float primemod=0;
    @FXML
    private Label labass;
    @FXML
    private Button btnmodif;
    @FXML
    private TableView<Vehicule> tbvehicule;
    @FXML
    private TableView<Habitation> tbhabitat;
    @FXML
    private TableColumn<Habitation,Integer> colbv;
    @FXML
    private TableColumn<Habitation,Integer> colnbpiece;
    @FXML
    private TableColumn<Habitation,Float> colvalmob;
    @FXML
    private TableColumn<Habitation,Integer> colsysalrm;
    @FXML
    private TableColumn<Habitation,String> colnatloc;
    @FXML
    private TableView<Voyage> tbvoyage;
    @FXML
    private AnchorPane c1habitat;
    @FXML
    private RadioButton rbbvo;
    @FXML
    private RadioButton rbbvn;
    @FXML
    private TextField txtnbp;
    @FXML
    private TextField txtcapmob;
    @FXML
    private RadioButton rbappart;
    @FXML
    private RadioButton rbvilla;
    @FXML
    private RadioButton rbsysao;
    @FXML
    private RadioButton rbsysan;
    @FXML
    private TableColumn<Voyage,String> tbdest;
    @FXML
    private TableColumn<Voyage,Integer> tbdursej;
    @FXML
    private TableColumn<Voyage,Integer> tbage;
    @FXML
    private AnchorPane c2voy;
    @FXML
    private TextField txtdest;
    @FXML
    private ComboBox<String> cbdursej;
    @FXML
    private TextField txtage;
    @FXML
    private TableColumn<Vehicule,Integer> tbpuiss;
    @FXML
    private TableColumn<Vehicule,Float> tbvalneuf;
    @FXML
    private TableColumn<Vehicule,Float> tbvalven;
    @FXML
    private TableColumn<Vehicule,Integer> tabanneconst;
    @FXML
    private TableColumn<Vehicule,String> tbimmat;
    @FXML
    private AnchorPane c3vehicule;
    @FXML
    private TextField txtpf;
    @FXML
    private TextField txtvalneuf;
    @FXML
    private TextField txtvalven;
    @FXML
    private TextField txtannconst;
    @FXML
    private TextField txtimmat;
    @FXML
    private ComboBox<String> cbmrq;
    @FXML
    private TableView<Marque> tbmarque;
    @FXML
    private TableColumn<Marque,String> tblibmrq;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        ToggleGroup group = new ToggleGroup();
        rbbvo.setToggleGroup(group);
        rbbvn.setToggleGroup(group);
        
        ToggleGroup group1 = new ToggleGroup();
        rbappart.setToggleGroup(group1);
        rbvilla.setToggleGroup(group1);
        
        ToggleGroup group2 = new ToggleGroup();
        rbsysao.setToggleGroup(group2);
        rbsysan.setToggleGroup(group2);
        
        
        for (Marque m : mc.afficherMarque()){
        cbmrq.getItems().add(m.getLib_mrq());
        }    
        
        cbdursej.getItems().addAll("entre 1 et 3 mois","entre 3 et 9 mois","entre 9 et 12 mois","Superieur a 1 an");
        
        //Si c'est une Vehicule
        if (typeselected.equalsIgnoreCase("Vehicule")){
        initColumnsVehicule();
        tbvehicule.setItems(vc.afficherVehiculeparID(idtypeselected));
        c3vehicule.setVisible(true);
        tbvehicule.setVisible(true);
        tbhabitat.setVisible(false);
        c1habitat.setVisible(false);
        c2voy.setVisible(false);
        tbvoyage.setVisible(false);
        tbmarque.setVisible(true);
        labass.setText("Contrat Vehicule");
        for (Vehicule v :vc.afficherVehiculeparID(idtypeselected)){
        txtpf.setText(""+v.getPuiss());
        txtimmat.setText(v.getImmat());
        txtvalneuf.setText(""+v.getVal_neuf());
        txtvalven.setText(""+v.getVal_venale());
        txtannconst.setText(""+v.getAnnne_consruct());
            for (Marque m : mc.afficherMarque()){
                if (m.getId_marque()== v.getId_marque()){
                    cbmrq.getSelectionModel().select(m.getLib_mrq());
                    tbmarque.setItems(mc.afficherMarqueparID(m.getId_marque()));
                    break;
                }
            }
        }
        }
        
         // si c'est un voyage
         if (typeselected.equalsIgnoreCase("Voyage")){
            initColumnsVoyage();
            tbvoyage.setItems(voyc.afficherVoyageparId(idtypeselected));
            c3vehicule.setVisible(false);
            tbvehicule.setVisible(false);
            tbhabitat.setVisible(false);
            c1habitat.setVisible(false);
            c2voy.setVisible(true);
            tbvoyage.setVisible(true);
            tbmarque.setVisible(false);
            labass.setText("Contrat Voyage");
            for (Voyage voy : voyc.afficherVoyageparId(idtypeselected)){
            txtdest.setText(voy.getDest());
            txtage.setText(""+voy.getTranche_age());
            if (voy.getDuree_sej()==3){
                cbdursej.getSelectionModel().select("entre 1 et 3 mois");
            }else if (voy.getDuree_sej()==9){
                cbdursej.getSelectionModel().select("entre 3 et 9 mois");
            }else if (voy.getDuree_sej()==12){
                 cbdursej.getSelectionModel().select("entre 9 et 12 mois");
            }else{
                 cbdursej.getSelectionModel().select("Superieur a 1 an");
            }
            
            
                    
            }
         }
            
            //si c'est une habitation
            if (typeselected.equalsIgnoreCase("Habitation")){
            initColumnsHabitation();
            tbhabitat.setItems(hc.afficherHabitationparId(idtypeselected));
            labass.setText("Contrat Habitation");
            tbhabitat.setVisible(true);
            c1habitat.setVisible(true);
            c3vehicule.setVisible(false);
            tbvehicule.setVisible(false);
            c2voy.setVisible(false);
            tbvoyage.setVisible(false);
            tbmarque.setVisible(false);
            for (Habitation h : hc.afficherHabitationparId(idtypeselected)){
                System.out.println(h);
                txtnbp.setText(""+h.getNb_pieces());
                txtcapmob.setText(""+h.getValeur_mob());
                if (h.getBaie_vitre()==1){
                    rbbvo.setSelected(true);
                }else {
                    rbbvn.setSelected(true);
                }
                if(h.getNature_local().equalsIgnoreCase("Appartement")){
                    rbappart.setSelected(true);
                }else{
                    rbvilla.setSelected(true);
                }
                if (h.getSys_alarm()==1){
                    rbsysao.setSelected(true);
                  
                }else{
                  
                    rbsysan.setSelected(true);
                    
                }
            }
            }
            
            
        
         
    }
    
     public void initColumnsVehicule() {

        tbpuiss.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("puiss"));
        tbvalneuf.setCellValueFactory(new PropertyValueFactory<Vehicule, Float>("val_neuf"));
        tbvalven.setCellValueFactory(new PropertyValueFactory<Vehicule, Float>("val_venale"));
        tbimmat.setCellValueFactory(new PropertyValueFactory<Vehicule, String>("immat"));
        tabanneconst.setCellValueFactory(new PropertyValueFactory<Vehicule, Integer>("annne_consruct"));
        tblibmrq.setCellValueFactory(new PropertyValueFactory<Marque,String>("lib_mrq"));
        
        

    }
       public void initColumnsHabitation() {
          
        
        colbv.setCellValueFactory(new PropertyValueFactory<Habitation, Integer>("baie_vitre"));
        colnatloc.setCellValueFactory(new PropertyValueFactory<Habitation, String>("nature_local"));
        colnbpiece.setCellValueFactory(new PropertyValueFactory<Habitation, Integer>("nb_pieces"));
        colsysalrm.setCellValueFactory(new PropertyValueFactory<Habitation, Integer>("sys_alarm"));
        colvalmob.setCellValueFactory(new PropertyValueFactory<Habitation, Float>("valeur_mob"));
        
        

    }
           public void initColumnsVoyage() {
          
        
        tbdest.setCellValueFactory(new PropertyValueFactory<Voyage, String>("dest"));
        tbage.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("tranche_age"));
        tbdursej.setCellValueFactory(new PropertyValueFactory<Voyage, Integer>("duree_sej"));
        
        
        

    }
    
    

    @FXML
    private void cliqueme(MouseEvent event) {
        
        if (typeselected.equalsIgnoreCase("Vehicule")){
        SelectionModel<Vehicule> v1= tbvehicule.getSelectionModel();
    	Vehicule c=v1.getSelectedItem();
    	
        String idm=""+c.getId_vehicule();
            System.out.println(idm);
        if (idm!=null){
            btnmodif.setVisible(true);
              for (Vehicule v :vc.afficherVehiculeparID(idtypeselected)){
        txtpf.setText(""+v.getPuiss());
        txtimmat.setText(v.getImmat());
        txtvalneuf.setText(""+v.getVal_neuf());
        txtvalven.setText(""+v.getVal_venale());
        txtannconst.setText(""+v.getAnnne_consruct());
            for (Marque m : mc.afficherMarque()){
                if (m.getId_marque()== v.getId_marque()){
                    cbmrq.getSelectionModel().select(m.getLib_mrq());
                    tbmarque.setItems(mc.afficherMarqueparID(m.getId_marque()));
                     
                    break;
                }
            }
        }
    	
        
        }
       
        }
        if (typeselected.equalsIgnoreCase("Habitation")){
        SelectionModel<Habitation> h1= tbhabitat.getSelectionModel();
    	Habitation c=h1.getSelectedItem();
    	
        String idm=""+c.getId_habitat();
         
        if (idm!=null){
                    for (Habitation h : hc.afficherHabitationparId(idtypeselected)){
                System.out.println(h);
                txtnbp.setText(""+h.getNb_pieces());
                txtcapmob.setText(""+h.getValeur_mob());
                if (h.getBaie_vitre()==1){
                    rbbvo.setSelected(true);
                }else {
                    rbbvn.setSelected(true);
                }
                if(h.getNature_local().equalsIgnoreCase("Appartement")){
                    rbappart.setSelected(true);
                }else{
                    rbvilla.setSelected(true);
                }
                if (h.getSys_alarm()==1){
                    rbsysao.setSelected(true);
                  
                }else{
                  
                    rbsysan.setSelected(true);
                    
                }
            }    
    	btnmodif.setVisible(true);
        
        }
        }
        
        if (typeselected.equalsIgnoreCase("Voyage")){
        SelectionModel<Voyage> voy1= tbvoyage.getSelectionModel();
    	Voyage c=voy1.getSelectedItem();
    	
        String idm=""+c.getId_voyage();
         
        if (idm!=null){
          for (Voyage voy : voyc.afficherVoyageparId(idtypeselected)){
            txtdest.setText(voy.getDest());
            txtage.setText(""+voy.getTranche_age());
            if (voy.getDuree_sej()==3){
                cbdursej.getSelectionModel().select("entre 1 et 3 mois");
            }else if (voy.getDuree_sej()==9){
                cbdursej.getSelectionModel().select("entre 3 et 9 mois");
            }else if (voy.getDuree_sej()==12){
                 cbdursej.getSelectionModel().select("entre 9 et 12 mois");
            }else{
                 cbdursej.getSelectionModel().select("Superieur a 1 an");
            }
            
            
                    
            }
    	btnmodif.setVisible(true);
        
        }
        }
        
    	
    	
    }

    @FXML
    private void validerModification(ActionEvent event) {
        
        //Si c'est une Vehicule
        if (typeselected.equalsIgnoreCase("Vehicule")){
        int k=retourneidmrq(event);
        primemod=calculerprime(typeselected);
        for (Vehicule vv : vc.afficherVehiculeparID(idtypeselected)){
           
        Vehicule v=new Vehicule(idtypeselected,txtimmat.getText(), Integer.parseInt(txtpf.getText()),Float.parseFloat(txtvalneuf.getText()),Float.parseFloat( txtvalven.getText()), Integer.parseInt(txtannconst.getText()),k, vv.getId_garantie());
        vc.modifierVehicule(v);
        tbmarque.getItems().clear();
        tbmarque.setItems(mc.afficherMarqueparID(k));
        tbvehicule.getItems().clear();
        tbvehicule.setItems(vc.afficherVehiculeparID(idtypeselected));
        
        }
        btnmodif.setVisible(false);
        }
        
        
         // si c'est un voyage
         if (typeselected.equalsIgnoreCase("Voyage")){
             primemod=calculerprime(typeselected);
        for (Voyage voy : voyc.afficherVoyageparId(idtypeselected) ){
             if( cbdursej.getSelectionModel().getSelectedItem().trim().equals("entre 1 et 3 mois")){
                    
                    Voyage voy1=new Voyage(idtypeselected,txtdest.getText(),3,Integer.parseInt(txtage.getText()));
                    voyc.modifierVoyage(voy1);
                   
             }
             if (cbdursej.getSelectionModel().getSelectedItem().trim().equals("entre 3 et 9 mois")){
                  Voyage voy1=new Voyage(idtypeselected,txtdest.getText(),9,Integer.parseInt(txtage.getText()));
                  System.out.println(voy1);
                  voyc.modifierVoyage(voy1);
                  
                  
                  
             }
             if (cbdursej.getSelectionModel().getSelectedItem().trim().equals("entre 9 et 12 mois")){
                 Voyage voy1=new Voyage(idtypeselected,txtdest.getText(),12,Integer.parseInt(txtage.getText()));
                 voyc.modifierVoyage(voy1);
                 
             }
              if (cbdursej.getSelectionModel().getSelectedItem().trim().equals("Superieur a 1 an")){
                   Voyage voy1=new Voyage(idtypeselected,txtdest.getText(),1,Integer.parseInt(txtage.getText()));
                   voyc.modifierVoyage(voy1);
                   
              }
               tbvoyage.getItems().clear();
               tbvoyage.setItems(voyc.afficherVoyageparId(idtypeselected));
        }
         btnmodif.setVisible(false);
         }
        
        //si c'est une habitation
        if (typeselected.equalsIgnoreCase("Habitation")){
            primemod=calculerprime(typeselected);
        for (Habitation hh: hc.afficherHabitationparId(idtypeselected)){
            
           if (rbvilla.isSelected()){
                    if(rbbvo.isSelected()){
                        if (rbsysao.isSelected()){
                            Habitation h=new Habitation (idtypeselected,1,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),1,rbvilla.getText());
                            hc.modifierHabitation(h);
                        
                        }else{
                            Habitation h=new Habitation (idtypeselected,1,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),0,rbvilla.getText());
                           hc.modifierHabitation(h);
                   
                        }
                    }else{
                        if (rbsysao.isSelected()){
                            Habitation h=new Habitation (idtypeselected,0,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),1,rbvilla.getText());
                           hc.modifierHabitation(h);
                           
                        }else{
                            Habitation h=new Habitation (idtypeselected,0,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),0,rbvilla.getText());
                       hc.modifierHabitation(h);
                      
                        }
                    }
                }else{
                    if(rbbvn.isSelected()){
                        if (rbsysao.isSelected()){
                            Habitation h=new Habitation (idtypeselected,1,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),1,rbappart.getText());
                           hc.modifierHabitation(h);
                          
                        }else{
                            Habitation h=new Habitation (idtypeselected,1,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),0,rbappart.getText());
                          hc.modifierHabitation(h);
                         
                        }
                    }else{
                        if (rbsysao.isSelected()){
                            Habitation h=new Habitation (idtypeselected,0,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),1,rbappart.getText());
                        hc.modifierHabitation(h);
                    
                        }else{
                            Habitation h=new Habitation (idtypeselected,0,Integer.parseInt(txtnbp.getText()),Float.parseFloat(txtcapmob.getText()),0,rbappart.getText());
                          hc.modifierHabitation(h);
                       
                        }  
                    }
                }
            tbhabitat.getItems().clear();
            tbhabitat.setItems(hc.afficherHabitationparId(idtypeselected));
        }
         btnmodif.setVisible(false);
        }
        
    }
    
    public float calculerprime(String type){
     float reprime=0;
    if (type.equalsIgnoreCase("Vehicule")){
         if (Integer.parseInt(txtpf.getText())==4||Integer.parseInt(txtpf.getText())==5){
                    reprime+=500;
                }
         if (Integer.parseInt(txtpf.getText())>=6 && Integer.parseInt(txtpf.getText())<=9){
                    reprime+=1000;
                }
         if (Integer.parseInt(txtpf.getText())>=10 && Integer.parseInt(txtpf.getText())<=12){
                    reprime+=1500;
                }
         if (Integer.parseInt(txtpf.getText())>12){
                    reprime+=2000;
                }
          float s= (Float.parseFloat(txtvalneuf.getText())-Float.parseFloat(txtvalven.getText()))/5000;
          float k=s*200;
          reprime+=k;
          if (Integer.parseInt(txtannconst.getText())<2000){
              reprime+=150;
          }else{
              reprime+=300;
          }
       
    }
      if (type.equalsIgnoreCase("Voyage")){
          
          if( cbdursej.getSelectionModel().getSelectedItem().trim().equals("entre 1 et 3 mois")){
              reprime+=120;
          }
           if( cbdursej.getSelectionModel().getSelectedItem().trim().equals("entre 3 et 9 mois")){
               reprime+=220;
           }
          if( cbdursej.getSelectionModel().getSelectedItem().trim().equals("entre 9 et 12 mois")){
              reprime+=320;
          }
             if( cbdursej.getSelectionModel().getSelectedItem().trim().equals("Superieur a 1 an")){
                 reprime+=400;
             }
          if (Integer.parseInt(txtage.getText())<=25){
              reprime+=40;
          }
          if (Integer.parseInt(txtage.getText())>25 && Integer.parseInt(txtage.getText())<=45){
              reprime+=80;
          }
             if (Integer.parseInt(txtage.getText())>45){
              reprime+=120;
          }
            
      }
      if (type.equalsIgnoreCase("Habitation")){
          
          if (rbbvo.isSelected()){
              reprime+=500;
          }
          if (Integer.parseInt(txtnbp.getText())==1){
              reprime+=100;
          }
           if (Integer.parseInt(txtnbp.getText())==2){
              reprime+=200;
          }
          if (Integer.parseInt(txtnbp.getText())>=3 &&Integer.parseInt(txtnbp.getText())<=5){
              reprime+=400;
          }
           if (Integer.parseInt(txtnbp.getText())>5 &&Integer.parseInt(txtnbp.getText())<=10){
              reprime+=650;
          }
          if (Integer.parseInt(txtnbp.getText())>10){
              reprime+=900;
          }
          
              float valm=Float.parseFloat(txtcapmob.getText())/10000;
              float kj=valm*1000;
              reprime+=kj;
           if (rbsysan.isSelected()){
               reprime+=1200;
           }   
          if (rbvilla.isSelected()){
              reprime+=1500;
          }
          if (rbappart.isSelected()){
              reprime+=450;
          }
          
      }
      
      
         return reprime;
}

    
    
    
    
    
    
      private int retourneidmrq(ActionEvent event) {
        int k=0;
        for (Marque m : mc.afficherMarque()){
           if( cbmrq.getSelectionModel().getSelectedItem().trim().equals(m.getLib_mrq())){
               k=m.getId_marque();
                }
        }
        return k;
    }
}
