package com.example.project.controller.parameters;

import javafx.scene.control.Alert;

public class SuccesAlertFactory {
    private SuccesAlertFactory() {
        throw new RuntimeException("Class SuccesAlertFactory cannot be instantiated");
    }

    static void createAndShow(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Zapisywanie się powiodło");
        alert.showAndWait();
    }
}
