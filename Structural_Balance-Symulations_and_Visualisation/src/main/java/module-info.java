module com.example.structural_balancesymulations_and_visualisation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires javafx.swing;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.controller;
    opens com.example.project.controller to javafx.fxml;
    exports com.example.project.controller.parameters;
    opens com.example.project.controller.parameters to javafx.fxml;
    exports com.example.project.database.model;
    opens com.example.project.database.model;
}