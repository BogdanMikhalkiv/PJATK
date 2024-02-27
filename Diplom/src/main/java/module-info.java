module com.example.diplom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires fontawesomefx;
    //requires charm.glisten;
//    requires org.apache.poi.poi;


    opens com.example.diplom to javafx.fxml;
    exports com.example.diplom;
    opens com.example.diplom.Models to javafx.fxml;
    exports com.example.diplom.Models;


}