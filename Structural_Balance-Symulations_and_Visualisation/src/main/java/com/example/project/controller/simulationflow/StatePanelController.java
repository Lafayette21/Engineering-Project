package com.example.project.controller.simulationflow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatePanelController {
    @FXML
    public Label stepNumberLabel;

    public void updateStepNumber(int currentStepNumber){
        stepNumberLabel.setText(String.valueOf(currentStepNumber));
    }
}
