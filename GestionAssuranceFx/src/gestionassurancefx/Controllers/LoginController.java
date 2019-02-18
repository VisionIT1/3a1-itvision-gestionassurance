/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.User;
import gestionassurancefx.Services.UserCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Ahmed Derbel
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameLoginField;
    @FXML
    private PasswordField mdpLoginField;
    private UserCrud crud;
    @FXML
    private Button connectBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud = new UserCrud();

    }

    public Boolean AuthenticateUser(String username, String password) {
        User u = crud.VerifyUser(username, password);
        if ((username.equals(u.getUsername())) && (password.equals(u.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void ConnectBtnClicked(ActionEvent event) {

        Stage appStage;
        Parent root;
        
        if (AuthenticateUser(usernameLoginField.getText(), mdpLoginField.getText())) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionassurancefx/Views/DashB.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            usernameLoginField.clear();
            mdpLoginField.clear();

        }

    }

}
