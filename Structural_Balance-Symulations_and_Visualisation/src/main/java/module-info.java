module com.example.structural_balancesymulations_and_visualisation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.structural_balancesymulations_and_visualisation to javafx.fxml;
    exports com.example.structural_balancesymulations_and_visualisation;
}