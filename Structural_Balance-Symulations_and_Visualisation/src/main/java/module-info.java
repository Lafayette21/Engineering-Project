module com.example.structural_balancesymulations_and_visualisation {
    requires com.dlsc.formsfx;
    requires com.google.common;
    requires jakarta.persistence;
    requires java.desktop;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.graphics;
    requires javafx.base;
    requires org.controlsfx.controls;
    requires org.hibernate.orm.core;

    exports com.example.project;
    opens com.example.project;
    exports com.example.project.controller;
    opens com.example.project.controller;
    exports com.example.project.controller.parameters;
    opens com.example.project.controller.parameters;
    exports com.example.project.controller.simulationflow;
    opens com.example.project.controller.simulationflow;

    exports com.example.project.database.model;
    opens com.example.project.database.model;
    exports com.example.project.visualisation.model;
    opens com.example.project.visualisation.model;
}