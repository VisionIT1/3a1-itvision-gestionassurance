/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.Contrat;
import gestionassurancefx.Entities.User;
import gestionassurancefx.Services.ContratCrud;
import gestionassurancefx.Services.UserCrud;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class GestionUserController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField nomUserField;
    @FXML
    private TableView<User> userView;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> activation;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private ComboBox<String> roleField;
    @FXML
    private TextField emailUserField;
    @FXML
    private RadioButton activeUserField;
    @FXML
    private RadioButton desactiveField;
    @FXML
    private TextField mdpUserField;
    @FXML
    private Button ajouterBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;
    private UserCrud crud;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        crud = new UserCrud();
        roleField.getItems().addAll("Admin", "Utilisateur");
        userView.setItems(crud.getAllUser());
       //System.out.println(crud.getAllUser());

    }

    public void initColumns() {
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        activation.setCellValueFactory(new PropertyValueFactory<User, String>("enabled"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("roles"));

    }
       private User u = new User();
    @FXML
    private void ItemSelected(MouseEvent event) {
        u=userView.getSelectionModel().getSelectedItem();
        if(u != null){
            String active="";
            nomUserField.setText(u.getUsername());
            emailUserField.setText(u.getEmail());
            mdpUserField.setText(u.getPassword());
            roleField.getSelectionModel().select(u.getRoles());
            if(u.getEnabled().booleanValue()){
                activeUserField.setSelected(true);
            }else{
                desactiveField.setSelected(true);
            }
        }
    }

    @FXML
    private void ajouterClicked(ActionEvent event) {
        User u = new User();
        u.setUsername(nomUserField.getText());
        u.setUsername_canonical(nomUserField.getText());
        u.setEmail(emailUserField.getText());
        u.setEmail_canonical(emailUserField.getText());
        
        if (activeUserField.isSelected()) {
            u.setEnabled(true);
        } else {
            u.setEnabled(false);
        }

        u.setPassword(mdpUserField.getText());
        u.setRoles(roleField.getSelectionModel().getSelectedItem());

        u.setSalt(null);
        u.setLast_login(null);
        u.setPassword_requested_at(null);
        u.setConfirmation_token(null);
        crud.ajouterUser(u);

        userView.getItems().clear();
        userView.setItems(crud.getAllUser());
        System.out.println("User Ajouter");
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        crud.SupprimerUser(userView.getSelectionModel().getSelectedItem().getId());
        nomUserField.setText("");
        emailUserField.setText("");
        activeUserField.setText("");
        //typeContratField.setItems(typeliste);
        mdpUserField.setText("");
        roleField.getSelectionModel().select("");
        userView.getItems().clear();
        userView.setItems(crud.getAllUser());
        System.out.println("deleted");
    }

 

    @FXML
    private void updateClicked(ActionEvent event) {
        if (u != null) {

            u.setUsername(nomUserField.getText());
            u.setEmail(emailUserField.getText());
            u.setPassword(mdpUserField.getText());
            u.setRoles(roleField.getSelectionModel().getSelectedItem());
            if (activeUserField.getText().equals("Activé")) {
                u.setEnabled(true);
            } else {
                u.setEnabled(false);
            }
            crud.modifierUser(u);
            userView.getItems().clear();
            userView.setItems(crud.getAllUser());
        } else {
            System.out.println("clicker sur un object");
        }
    }
    
  

}
