package com.example.eesalaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class produitController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
     cnx=DatabaseConnection.getConnection();
        remplirCombo();
    }
    private Connection cnx;
    private PreparedStatement st;
    private ResultSet result;
    @FXML
    private Button Acheter;

    @FXML
    private ComboBox <String> Combo_searchProduit;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView imagelog;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_produit;



    @FXML
    void search_produit() {
        String sql="SELECT produitnome ,produitprice, image FROM produit WHERE produitnome ='"+Combo_searchProduit.getValue()+"';";
        String log ;
        byte byteimg[];
        Blob blob;
        try {
            st =cnx.prepareStatement(sql);
            result=st.executeQuery();
            if (result.next()){
                log=result.getString("produitnome");
                txt_produit.setText(String.valueOf(log));
                txt_price.setText(result.getString("produitprice"));

                blob=result.getBlob("image");
                byteimg=blob.getBytes(1, (int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byteimg));
                imagelog.setImage(img);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void remplirCombo(){
        String sql="SELECT produitnome FROM produit";
        List<String> list=new ArrayList<String>();
        try{
            st=cnx.prepareStatement(sql);
            result=st.executeQuery();
            while (result.next()){
                list.add(result.getString("produitnome"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        Combo_searchProduit.setItems(FXCollections.observableArrayList(list));
    }


    public void produitAdd() {

        String sql = "INSERT INTO credit (username,produitnome, produitprice) VALUES(?,?,?) ;";


        try {
            Alert alert;
            if (txt_produit.getText().isEmpty() || txt_price.getText().isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                st = cnx.prepareStatement(sql);
                st.setString(1,getData.username);
                st.setString(2, txt_produit.getText());
                st.setString(3, txt_price.getText());

                st.executeUpdate();


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added");
                alert.showAndWait();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}