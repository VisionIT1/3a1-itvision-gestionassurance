/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionassurancefx.Utils;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class Alerte {
    public static void desplay(String title, String messeg) {
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(200);
        
        Label lab= new Label();
        lab.setText(messeg);
        Button btn = new Button("close the wondow");
        btn.setOnAction(e -> window.close());
        
        
        VBox layout = new VBox();
        layout.getChildren().addAll(lab,btn);
        layout.setAlignment(Pos.CENTER);
        
        Scene  ss = new Scene(layout);
        window.setScene(ss);
        window.showAndWait();
                
        
    }
}
