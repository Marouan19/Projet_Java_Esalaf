package com.example.eesalaf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {
 @FXML
    private Button cancelButtom ;
 @FXML
 private Label loginMessageLabel;
 @FXML
 private TextField usernameTextField;
 @FXML
 private PasswordField passwordPasswordField;

 public void loginButtonOnAction(ActionEvent e){
     getData.username=usernameTextField.getText();
     if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
         loginMessageLabel.setText("bienvenue");
         validateLogin();
         validateLogin1();

     }else {
         loginMessageLabel.setText("Enter your username and password");
     }

 }
 public void cancelButtomOnAction(ActionEvent e){
     Stage stage = (Stage) cancelButtom.getScene().getWindow();
     createAccountForm();
 }
 public void validateLogin(){
     DatabaseConnection connectNow = new DatabaseConnection();
     Connection connectDB = connectNow.getConnection();

     String verifyLogin = "SELECT count(1) FROM client WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";


     try {
         Statement statement = connectDB.createStatement();
         ResultSet queryResult = statement.executeQuery(verifyLogin);

         while (queryResult.next()){
             if (queryResult.getInt(1)==1){
                 createAccountForm1();

             }else{
                 loginMessageLabel.setText("Username or password incorrect");
             }
         }


     }catch (Exception e){
         e.printStackTrace();
     }

 }
    public void validateLogin1(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM admin WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1)==1){
                    createAccountForm2();
                } else{
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }


 public void createAccountForm(){
     try {

         FXMLLoader fxmlLoader = new FXMLLoader(login.class.getResource("register.fxml"));
         Stage registerstage =new Stage();
         Scene scene = new Scene(fxmlLoader.load(), 700, 500);
         registerstage.setTitle("Esalaf");
         registerstage.setScene(scene);
         registerstage.show();

     }catch (Exception e){
         e.printStackTrace();
         e.getCause();
     }
 }
    public void createAccountForm1(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(login.class.getResource("Menu.fxml"));
            Stage registerstage =new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            registerstage.setTitle("Esalaf");
            registerstage.setScene(scene);
            registerstage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAccountForm2(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(login.class.getResource("AdminMenu.fxml"));
            Stage registerstage =new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            registerstage.setTitle("Esalaf");
            registerstage.setScene(scene);
            registerstage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}