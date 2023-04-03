package com.example.eesalaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AcreditController implements Initializable {

    @FXML
    private AnchorPane ap;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    @FXML
    private TableColumn<AcommandeData, String> col_produitname;

    @FXML
    private TableColumn<AcommandeData, String> col_produitprice;

    @FXML
    private TableColumn<AcommandeData, String> col_nom;


    @FXML
    private TableView<AcommandeData> tableView;

    @FXML
    void availableAproduitSelect(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        availableAproduitShowListData();
        connect = DatabaseConnection.getConnection();
    }

    public ObservableList<AcommandeData> availableAproduitListData() {

        ObservableList<AcommandeData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM  credit ";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            AcommandeData AproduitD;

            while (result.next()) {
                AproduitD = new AcommandeData(result.getInt("idcredit")
                        , result.getString("username")
                        , result.getString("produitnome")
                        , result.getDouble("produitprice")
                        );
                listData.add(AproduitD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AcommandeData> availableAproduitList;

    public void availableAproduitShowListData() {
        availableAproduitList = availableAproduitListData();

        col_nom.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_produitname.setCellValueFactory(new PropertyValueFactory<>("produitnome"));
        col_produitprice.setCellValueFactory(new PropertyValueFactory<>("produitprice"));

        tableView.setItems(availableAproduitList);
    }

}
