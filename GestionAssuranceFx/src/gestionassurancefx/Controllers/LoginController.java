/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Controllers;

import gestionassurancefx.Entities.User;
import static gestionassurancefx.GestionAssuranceFx.LoginStage;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public static Stage dashBStage = null;
    @FXML
    private TextField usernameLoginField;
    @FXML
    private PasswordField mdpLoginField;
    private UserCrud crud;
    @FXML
    public Button connectBtn;
    private User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud = new UserCrud();

        u = new User();
    }

    public Boolean AuthenticateUser(String username, String password) {
        u = crud.VerifyUser(username, password);
        User u = crud.VerifyUser(username, password);
        if ((username.equals(u.getUsername())) && (password.equals(u.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean VerifyEnable(User u) {
        if (u.getEnabled() == true) {
            return true;
        }
        return false;
    }

    public void closeLogin() {
        LoginStage = (Stage) LoginStage.getScene().getWindow();
        LoginStage.close();
    }

    public void Login() {
        Stage appStage;
        Parent root;

        if (AuthenticateUser(usernameLoginField.getText(), mdpLoginField.getText())) {

            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionassurancefx/Views/DashB.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root1));
                dashBStage = stage;
                stage.show();
               
                closeLogin();
                /*  if(u.getRoles().equals("Agent")){
                 agentlabel.setVisible(false);
                 }*/

            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            usernameLoginField.clear();
            mdpLoginField.clear();

        }
    }

    @FXML
    private void ConnectBtnClicked(ActionEvent event) {

        Login();

    }

    @FXML
    private void EntrKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Login();
        }
    }

}
