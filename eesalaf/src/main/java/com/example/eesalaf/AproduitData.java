package com.example.eesalaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class AproduitData {


    private Integer idproduit;
    private String produitnome;
    private double produitprice;
    private String image;

    public AproduitData(Integer idproduit , String produitnome , double produitprice , String image){
        this.idproduit=idproduit;
        this.produitnome=produitnome;
        this.produitprice=produitprice;
        this.image=image;
    }
    public Integer getIdproduit(){
        return idproduit;
    }
    public String getProduitnome(){
        return produitnome;
    }
    public double getProduitprice(){
        return produitprice;
    }

    public String getImage() {
        return image;
    }
}
