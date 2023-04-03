package com.example.eesalaf;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMenuController implements Initializable{
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button commande_btn;

    @FXML
    private Button credit_btn;

    @FXML
    private Button produit_btn;

    @FXML
    private Button home_btn;

    @Override
    public void initialize(URL url,ResourceBundle rb){
        displayUsername();

    }
    @FXML
    private void home(MouseEvent event){
        bp.setCenter(ap);
    }
    @FXML
    private void produit(MouseEvent event){
        loadPage("Aproduit");
    }
    @FXML
    private void credit(MouseEvent event){
        loadPage("Acredit");
    }
    @FXML
    private void commande(MouseEvent event){
        loadPage("Acommande");
    }


    private void loadPage(String page){
        Parent root = null;

        try {
            root= FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE,null,e);

        }
        bp.setCenter(root);
    }
    @FXML
    private Label username;
    public void displayUsername() {
        String user = getData.username;
        // TO SET THE FIRST LETTER TO BIG LETTER
        username.setText(user);

    }

}
