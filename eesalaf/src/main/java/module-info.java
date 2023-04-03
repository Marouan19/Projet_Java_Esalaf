module com.example.eesalaf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires mysql.connector.j;

    opens com.example.eesalaf to javafx.fxml;
    exports com.example.eesalaf;
}