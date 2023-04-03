package com.example.eesalaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class AcommandeData {


    private Integer idcredit;
    private String produitnome;
    private double produitprice;
    private String username;

    public AcommandeData(Integer idcredit , String username, String produitnome , double produitprice ){
        this.idcredit=idcredit;
        this.username=username;
        this.produitnome=produitnome;
        this.produitprice=produitprice;

    }
    public Integer getIdproduit(){
        return idcredit;
    }
    public String getUsername() {
        return username;
    }
    public String getProduitnome(){
        return produitnome;
    }
    public double getProduitprice(){
        return produitprice;
    }


}
