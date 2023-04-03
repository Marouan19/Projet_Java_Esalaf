package com.example.eesalaf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EventListener;
import java.util.Optional;
import java.util.ResourceBundle;


public  class AproduitController implements Initializable {

    @FXML
    private TextField Produitname;

    @FXML
    private TextField Produitprice;

    @FXML
    private AnchorPane ap;

    @FXML
    private Button clearBtn;
    @FXML
    private Button produit_btn;
    @FXML
    private TableColumn<AproduitData, String> col_produitname;

    @FXML
    private TableColumn<AproduitData, String> col_produitprice;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    @FXML
    private Button deleteBtn;

    @FXML
    private ImageView imageView;

    @FXML
    private Button importBtn;

    @FXML
    private Button insertBtn;

    @FXML
    private TableView<AproduitData> tableView;


    private Image image;

    public void produitAdd() {

        String sql = "INSERT INTO produit (produitnome, produitprice, image) VALUES(?,?,?) ;";
        connect = DatabaseConnection.getConnection();

        try {
            Alert alert;
            if (Produitname.getText().isEmpty() || Produitprice.getText().isEmpty() || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                availableAproduitShowListData();
            } else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, Produitname.getText());
                prepare.setString(2, Produitprice.getText());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");

                prepare.setString(3, uri);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added");
                alert.showAndWait();

                availableAproduitShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Documents", "*.jpg", "*.png","*.jpeg","*public.image"));

        File file = open.showOpenDialog(ap.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 164, 108, false, true);
            imageView.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        availableAproduitShowListData();
    }

    public ObservableList<AproduitData> availableAproduitListData() {

        ObservableList<AproduitData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM  produit";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            AproduitData AproduitD;

            while (result.next()) {
                AproduitD = new AproduitData(result.getInt("idproduit")
                        , result.getString("produitnome")
                        , result.getDouble("produitprice"),
                        result.getString("image"));
                listData.add(AproduitD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AproduitData> availableAproduitList;

    public void availableAproduitShowListData() {
        availableAproduitList = availableAproduitListData();

        col_produitname.setCellValueFactory(new PropertyValueFactory<>("produitnome"));
        col_produitprice.setCellValueFactory(new PropertyValueFactory<>("produitprice"));

        tableView.setItems(availableAproduitList);
    }

    public void availableAproduitSelect() {
        AproduitData AproduitD = tableView.getSelectionModel().getSelectedItem();
        int num = tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        Produitname.setText(String.valueOf(AproduitD.getProduitnome()));
        Produitprice.setText(String.valueOf(AproduitD.getProduitprice()));

        getData.path = AproduitD.getImage();

        String uri = "file:" + AproduitD.getImage();

        image = new Image(uri, 164, 108, false, true);
        imageView.setImage(image);


    }


    public void Delete() {

        String sql = "DELETE FROM produit WHERE produitnome = '" + Produitname.getText() + "';";

        connect = DatabaseConnection.getConnection();

        try {
            Alert alert;
            if (Produitname.getText().isEmpty() || Produitprice.getText().isEmpty() || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE produit: " + Produitname.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableAproduitShowListData();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
