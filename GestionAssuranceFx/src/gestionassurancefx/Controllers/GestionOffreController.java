/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import doryan.windowsnotificationapi.fr.Notification;
import gestionassurancefx.Services.*;
import gestionassurancefx.Entities.*;
import gestionassurancefx.Utils.*;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class GestionOffreController implements Initializable {

    @FXML
    private TextField libOffBC;
    @FXML
    private DatePicker DDBC;
    @FXML
    private DatePicker DFBC;
    @FXML
    private TextField pourcentageRed;
    @FXML
    private TextField numReg;
    @FXML
    private TextField nbAnnee;
    @FXML
    private TextArea Descrip;
    ServiceOffreBonConducteur SOB = new ServiceOffreBonConducteur();
    ServiceOffreFidelite SOF = new ServiceOffreFidelite();
    ServiceOffrePartenaire SOP = new ServiceOffrePartenaire();
    ServiceAffOffre SAF = new ServiceAffOffre();
    @FXML
    private TableView<OffreBonConducteur> tableOBC;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> idBCT;
    @FXML
    private TableColumn<OffreBonConducteur, String> libelleBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Date> DDBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Date> DFBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> reductionBCT;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> numRegT;
    @FXML
    private TableColumn<OffreBonConducteur, Integer> nbAnneeT;
    @FXML
    private TableColumn<OffreBonConducteur, String> descripT;
    @FXML
    private Button BoutonAjoutOBC;
    @FXML
    private Button btnModifOBC;
    @FXML
    private TextField idBC;
    @FXML
    private Button suppBtn;
    @FXML
    private TextField libOffBC1;
    @FXML
    private DatePicker DDBC1;
    @FXML
    private DatePicker DFBC1;
    @FXML
    private TextField pourcentageRed1;
    @FXML
    private TextField nbContMin;
    @FXML
    private TextArea Descrip1;
    @FXML
    private Button BoutonAjoutOBC1;
    @FXML
    private Button btnModifOBC1;
    @FXML
    private TextField idBC1;
    @FXML
    private TableView<OffreFidelite> tableOBC1;
    @FXML
    private TableColumn<OffreFidelite, Integer> idBCT1;
    @FXML
    private TableColumn<OffreFidelite, String> libelleBCT1;
    @FXML
    private TableColumn<OffreFidelite, Date> DDBCT1;
    @FXML
    private TableColumn<OffreFidelite, Date> DFBCT1;
    @FXML
    private TableColumn<OffreFidelite, Integer> reductionBCT1;
    @FXML
    private TableColumn<OffreFidelite, Integer> nbContMinT;
    @FXML
    private TableColumn<OffreFidelite, String> descripT1;
    @FXML
    private Button suppBtn1;
    @FXML
    private Button rafraichir;
    @FXML
    private Button rafraichir1;
    @FXML
    private TextField libOffBC11;
    @FXML
    private DatePicker DDBC11;
    @FXML
    private DatePicker DFBC11;
    @FXML
    private TextField pourcentageRed11;
    @FXML
    private ComboBox<String> partenaire;
    @FXML
    private TextArea Descrip11;
    @FXML
    private Button BoutonAjoutP;
    @FXML
    private Button btnModifP;
    @FXML
    private TextField idBC11;
    @FXML
    private TableView<OffrePartenaire> tableOBC11;
    @FXML
    private TableColumn<OffrePartenaire, Integer> idBCT11;
    @FXML
    private TableColumn<OffrePartenaire, String> libelleBCT11;
    @FXML
    private TableColumn<OffrePartenaire, Date> DDBCT11;
    @FXML
    private TableColumn<OffrePartenaire, Date> DFBCT11;
    @FXML
    private TableColumn<OffrePartenaire, Integer> reductionBCT11;
    @FXML
    private TableColumn<OffrePartenaire, String> partenaireT;
    @FXML
    private TableColumn<OffrePartenaire, String> descripT11;
    @FXML
    private Button suppBtnP;
    @FXML
    private Button rafraichirP;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnRechercher;
    @FXML
    private TextField rechercheF;
    @FXML
    private Button btnRechercherF;
    @FXML
    private TextField rechercheP;
    @FXML
    private Button btnRechercherP;
     List<Integer> l=new ArrayList<>();

    @FXML
    private void ajouterOffreBC(ActionEvent event) {
        String erreur = "";
        if (DDBC.getValue().isAfter(DFBC.getValue())) {
            erreur = erreur + "La date debut doit être après la date fin \n ";
        }
        if (Integer.parseInt(pourcentageRed.getText()) < 0 || Integer.parseInt(pourcentageRed.getText()) > 100) {
            erreur = erreur + "Le pourcentage de réduction n'est pas compris entre [0,100] \n";
        }
        if (Integer.parseInt(nbAnnee.getText()) < 1 || Integer.parseInt(nbAnnee.getText()) > 10) {
            erreur = erreur + "Le nombre d'annee doit etre compris entre [1,10] \n";
        }

        if (!erreur.equals("")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText(erreur);
                alert.showAndWait();
            
        } else {

            OffreBonConducteur O = new OffreBonConducteur();
            O.setLibOffre(libOffBC.getText());
            O.setDateDebutOffre(java.sql.Date.valueOf(DDBC.getValue()));
            O.setDateFinOffre(java.sql.Date.valueOf(DFBC.getValue()));
            O.setNumReglement(Integer.parseInt(numReg.getText()));
            O.setPourcentageReduction(Integer.parseInt(pourcentageRed.getText()));
            O.setNbrAnnee(Integer.parseInt(nbAnnee.getText()));
            O.setDescripOffre(Descrip.getText());

            SOB.ajouterOffreBonConducteur(O);
            O.setIdOffre(SOB.getIdLast());
            SOB.affecterOffreBC(O);
            try {
                Notification.sendNotification("Nouvelle Offre","Une nouvelle offre a été ajouté veuillez verifier la table d'affectation des offres.",MessageType.INFO,"Views/gift.png");
            } catch (AWTException ex) {
                Logger.getLogger(GestionOffreController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(GestionOffreController.class.getName()).log(Level.SEVERE, null, ex);
            }

            afficherOffreBC();
            RafraichirBC();
        }
    }

    public void afficherOffreBC() {
        idBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("idOffre"));
        libelleBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, String>("libOffre"));
        DDBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Date>("dateDebutOffre"));
        DFBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Date>("dateFinOffre"));
        reductionBCT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("pourcentageReduction"));
        numRegT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("numReglement"));
        nbAnneeT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, Integer>("nbrAnnee"));
        descripT.setCellValueFactory(new PropertyValueFactory<OffreBonConducteur, String>("descripOffre"));
        tableOBC.setItems(SOB.afficherOffreBonConducteur());
        tableOBC.setEditable(true);

    }

    public void afficherOffreF() {
        idBCT1.setCellValueFactory(new PropertyValueFactory<OffreFidelite, Integer>("idOffre"));
        libelleBCT1.setCellValueFactory(new PropertyValueFactory<OffreFidelite, String>("libOffre"));
        DDBCT1.setCellValueFactory(new PropertyValueFactory<OffreFidelite, Date>("dateDebutOffre"));
        DFBCT1.setCellValueFactory(new PropertyValueFactory<OffreFidelite, Date>("dateFinOffre"));
        reductionBCT1.setCellValueFactory(new PropertyValueFactory<OffreFidelite, Integer>("pourcentageReduction"));

        nbContMinT.setCellValueFactory(new PropertyValueFactory<OffreFidelite, Integer>("nbrContratMin"));
        descripT1.setCellValueFactory(new PropertyValueFactory<OffreFidelite, String>("descripOffre"));
        tableOBC1.setItems(SOF.afficherOffreFidelite());
        tableOBC1.setEditable(true);

    }

    public void afficherOffreP() {
        idBCT11.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, Integer>("idOffre"));
        libelleBCT11.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, String>("libOffre"));
        DDBCT11.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, Date>("dateDebutOffre"));
        DFBCT11.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, Date>("dateFinOffre"));
        reductionBCT11.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, Integer>("pourcentageReduction"));

        partenaireT.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, String>("partenaire"));
        descripT11.setCellValueFactory(new PropertyValueFactory<OffrePartenaire, String>("descripOffre"));
        tableOBC11.setItems(SOP.afficherOffrePartenaire());
        tableOBC11.setEditable(true);

    }

    public void RafraichirBC() {
        idBC.clear();
        libOffBC.clear();
        DDBC.setValue(null);
        DFBC.setValue(null);
        Descrip.clear();
        nbAnnee.clear();
        numReg.clear();
        pourcentageRed.clear();
        BoutonAjoutOBC.setVisible(true);
        btnModifOBC.setVisible(false);
    }

    public void RafraichirF() {
        idBC1.clear();
        libOffBC1.clear();
        DDBC1.setValue(null);
        DFBC1.setValue(null);
        Descrip1.clear();

        nbContMin.clear();
        pourcentageRed1.clear();
        BoutonAjoutOBC1.setVisible(true);
        btnModifOBC1.setVisible(false);
    }

    public void RafraichirP() {
        idBC11.clear();
        libOffBC11.clear();
        DDBC11.setValue(null);
        DFBC11.setValue(null);
        Descrip11.clear();

        pourcentageRed11.clear();
        BoutonAjoutP.setVisible(true);
        btnModifP.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        TextFormatter tF4 = new TextFormatter(filter);
        TextFormatter tF5 = new TextFormatter(filter);
        
        nbAnnee.setTextFormatter(textFormatter);
        numReg.setTextFormatter(tF1);
        nbContMin.setTextFormatter(tF2);
        pourcentageRed.setTextFormatter(tF3);
        pourcentageRed1.setTextFormatter(tF4);
        pourcentageRed11.setTextFormatter(tF5);
       
        
        
        idBC.setVisible(false);
        idBC1.setVisible(false);
        idBC11.setVisible(false);
        btnModifOBC.setVisible(false);
        btnModifOBC1.setVisible(false);
        btnModifP.setVisible(false);
        afficherOffreBC();
        afficherOffreF();

        partenaire.setItems(SOP.getNomMarque());
        afficherOffreP();
        String ch="";
        l=SOB.OffreExpirerBC();
        if(!l.isEmpty())
        {    for(Integer i:l)
            {
                ch+=i+",";}
            try {
                Notification.sendNotification("Offres Expirés","Les offres Bons conducteurs n°:"+ch+" sont expirés vueillez les désactiver !",MessageType.INFO,"Views/gift.png");
            } catch (AWTException ex) {
                Logger.getLogger(GestionOffreController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(GestionOffreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        l.clear();
        ch="";
        l=SOF.OffreExpirerF();
        if(!l.isEmpty())
        {    for(Integer i:l)
            {
                ch+=i+",";}
            try {
                Notification.sendNotification("Offres Expirés","Les offres Fidélités n°:"+ch+" sont expirés vueillez les désactiver !",MessageType.INFO,"Views/gift.png");
            } catch (AWTException ex) {
                Logger.getLogger(GestionOffreController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(GestionOffreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }

    @FXML
    private void itemClicked(MouseEvent event) {

        OffreBonConducteur Os = tableOBC.getSelectionModel().getSelectedItem();
        libOffBC.setText(Os.getLibOffre());
        DDBC.setValue(Os.getDateDebutOffre().toLocalDate());
        DFBC.setValue(Os.getDateFinOffre().toLocalDate());
        pourcentageRed.setText("" + Os.getPourcentageReduction());
        numReg.setText("" + Os.getNumReglement());
        nbAnnee.setText("" + Os.getNbrAnnee());
        Descrip.setText(Os.getDescripOffre());
        idBC.setText("" + Os.getIdOffre());
        BoutonAjoutOBC.setVisible(false);
        btnModifOBC.setVisible(true);

    }

    @FXML
    private void ModifierOffreBC(ActionEvent event) {
        OffreBonConducteur O = new OffreBonConducteur(Integer.parseInt(idBC.getText()), Integer.parseInt(numReg.getText()), Integer.parseInt(nbAnnee.getText()), libOffBC.getText(), java.sql.Date.valueOf(DDBC.getValue()), java.sql.Date.valueOf(DFBC.getValue()), Integer.parseInt(pourcentageRed.getText()), Descrip.getText());
        SOB.modifierOffreBonConducteur(O);
        BoutonAjoutOBC.setVisible(true);
        btnModifOBC.setVisible(false);
        idBC.clear();
        libOffBC.clear();
        DDBC.setValue(null);
        DFBC.setValue(null);
        Descrip.clear();
        nbAnnee.clear();
        numReg.clear();
        pourcentageRed.clear();

        afficherOffreBC();

    }

    @FXML
    private void suppimerOffBC(ActionEvent event) {
        OffreBonConducteur Os = tableOBC.getSelectionModel().getSelectedItem();
        SOB.retirerOffreBC(Os);
        SAF.supprimerAffectation(Os.getIdOffre());
        SOB.supprimerOffreBonConducteur(Os.getIdOffre());
        afficherOffreBC();
        RafraichirBC();
    }

    @FXML
    private void ajouterOffreF(ActionEvent event) {
        String erreur = "";
        if (DDBC1.getValue().isAfter(DFBC1.getValue()) || DDBC1.getValue().isBefore(LocalDate.now())) {
            erreur = erreur + "La date debut doit être après la date fin et pas avant la date courante ";
        }
        if (Integer.parseInt(pourcentageRed1.getText()) < 0 || Integer.parseInt(pourcentageRed1.getText()) > 100) {
            erreur = erreur + "Le pourcentage de réduction n'est pas compris entre [0,100] \n";
        }
        if (Integer.parseInt(nbContMin.getText()) < 2) {
            erreur = erreur + "Le nombre d'annee doit au moins egale à 3 \n";
        }

        if (!erreur.equals("")) {
            Alerte.desplay("ERREUR:", erreur);
        } else {
            OffreFidelite O = new OffreFidelite();
            O.setLibOffre(libOffBC1.getText());
            O.setDateDebutOffre(java.sql.Date.valueOf(DDBC1.getValue()));
            O.setDateFinOffre(java.sql.Date.valueOf(DFBC1.getValue()));

            O.setPourcentageReduction(Integer.parseInt(pourcentageRed1.getText()));
            O.setNbrContratMin(Integer.parseInt(nbContMin.getText()));
            O.setDescripOffre(Descrip1.getText());
            SOF.ajouterOffreFidelite(O);
            O.setIdOffre(SOF.getIdLast());
            SOF.affecterOffreFidelite(O);

            afficherOffreF();
            RafraichirF();
        }

    }

    @FXML
    private void ModifierOffreF(ActionEvent event) {
        OffreFidelite O = new OffreFidelite(Integer.parseInt(idBC1.getText()), Integer.parseInt(nbContMin.getText()), libOffBC1.getText(), java.sql.Date.valueOf(DDBC1.getValue()), java.sql.Date.valueOf(DFBC1.getValue()), Integer.parseInt(pourcentageRed1.getText()), Descrip1.getText());
        SOF.modifierOffreFidelite(O);
        BoutonAjoutOBC1.setVisible(true);
        btnModifOBC1.setVisible(false);
        idBC1.clear();
        libOffBC1.clear();
        DDBC1.setValue(null);
        DFBC1.setValue(null);
        Descrip1.clear();

        nbContMin.clear();
        pourcentageRed1.clear();

        afficherOffreF();

    }

    @FXML
    private void suppimerOffF(ActionEvent event) {
        OffreFidelite Os = tableOBC1.getSelectionModel().getSelectedItem();
        SOF.retirerOffreFidelite(Os);
        SAF.supprimerAffectation(Os.getIdOffre());
        SOF.supprimerOffreFidelite(Os.getIdOffre());

        afficherOffreF();
        RafraichirF();
    }

    @FXML
    private void itemClickedF(MouseEvent event) {
        OffreFidelite Os = tableOBC1.getSelectionModel().getSelectedItem();
        libOffBC1.setText(Os.getLibOffre());
        DDBC1.setValue(Os.getDateDebutOffre().toLocalDate());
        DFBC1.setValue(Os.getDateFinOffre().toLocalDate());
        pourcentageRed1.setText("" + Os.getPourcentageReduction());

        nbContMin.setText("" + Os.getNbrContratMin());
        Descrip1.setText(Os.getDescripOffre());
        idBC1.setText("" + Os.getIdOffre());
        BoutonAjoutOBC1.setVisible(false);
        btnModifOBC1.setVisible(true);

    }

    @FXML
    private void btnRafraichir(ActionEvent event) {
        RafraichirBC();
    }

    @FXML
    private void btnRafraichir1(ActionEvent event) {
        RafraichirF();
    }

    @FXML
    private void ajouterOffreP(ActionEvent event) {
        String erreur = "";
        if (DDBC11.getValue().isAfter(DFBC11.getValue())) {
            erreur = erreur + "La date debut doit être après la date fin \n ";
        }
        if (Integer.parseInt(pourcentageRed11.getText()) < 0 || Integer.parseInt(pourcentageRed11.getText()) > 100) {
            erreur = erreur + "Le pourcentage de réduction n'est pas compris entre [0,100] \n";
        }

        if (!erreur.equals("")) {
            Alerte.desplay("ERREUR:", erreur);
        } else {
            OffrePartenaire O = new OffrePartenaire();
            O.setLibOffre(libOffBC11.getText());
            O.setDateDebutOffre(java.sql.Date.valueOf(DDBC11.getValue()));
            O.setDateFinOffre(java.sql.Date.valueOf(DFBC11.getValue()));

            O.setPourcentageReduction(Integer.parseInt(pourcentageRed11.getText()));
            O.setPartenaire(partenaire.getSelectionModel().getSelectedItem());
            O.setDescripOffre(Descrip11.getText());

            SOP.ajouterOffrePartenaire(O);

            afficherOffreP();
        }
    }

    @FXML
    private void ModifierOffreP(ActionEvent event) {
        OffrePartenaire O = new OffrePartenaire(Integer.parseInt(idBC11.getText()), partenaire.getSelectionModel().getSelectedItem(), libOffBC11.getText(), java.sql.Date.valueOf(DDBC11.getValue()), java.sql.Date.valueOf(DFBC11.getValue()), Integer.parseInt(pourcentageRed11.getText()), Descrip11.getText());
        SOP.modifierOffrePartenaire(O);
        BoutonAjoutP.setVisible(true);
        btnModifP.setVisible(false);
        idBC11.clear();
        libOffBC11.clear();
        DDBC11.setValue(null);
        DFBC11.setValue(null);
        Descrip11.clear();

        pourcentageRed11.clear();

        afficherOffreP();
    }

    @FXML
    private void suppimerOffP(ActionEvent event) {
        OffrePartenaire Os = tableOBC11.getSelectionModel().getSelectedItem();
        SOP.supprimerOffrePartenaire(Os.getIdOffre());
        afficherOffreP();
        RafraichirP();
    }

    @FXML
    private void btnRafraichir11(ActionEvent event) {
        RafraichirP();
    }

    @FXML
    private void itemClickedP(MouseEvent event) {
        OffrePartenaire Os = tableOBC11.getSelectionModel().getSelectedItem();
        libOffBC11.setText(Os.getLibOffre());
        DDBC11.setValue(Os.getDateDebutOffre().toLocalDate());
        DFBC11.setValue(Os.getDateFinOffre().toLocalDate());
        pourcentageRed11.setText("" + Os.getPourcentageReduction());

        partenaire.getSelectionModel().select(Os.getPartenaire());
        Descrip11.setText(Os.getDescripOffre());
        idBC11.setText("" + Os.getIdOffre());
        BoutonAjoutP.setVisible(false);
        btnModifP.setVisible(true);
    }

    @FXML
    private void rechercher(ActionEvent event) {
        tableOBC.setItems(SOB.rechercherOffreBonConducteur(recherche.getText()));
      tableOBC.setEditable(true);
        
    }

    @FXML
    private void refresh(MouseEvent event) 
    {
        afficherOffreBC();
    }

    @FXML
    private void rechercherF(ActionEvent event) 
    { tableOBC1.setItems(SOF.rechercherOffreFidelite(rechercheF.getText()));
      tableOBC1.setEditable(true);
    }

    @FXML
    private void rechercherP(ActionEvent event) {
        tableOBC11.setItems(SOP.rechercherOffrePartenaire(rechercheP.getText()));
        tableOBC11.setEditable(true);
    }

    @FXML
    private void refrechF(MouseEvent event) 
    {
        afficherOffreF();
    }

    @FXML
    private void refreshP(MouseEvent event) {
        afficherOffreP();
    }

}
