package com.example.project.controller.simulationflow;

import com.example.project.controller.SimulationFlowController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlPanelController {
    private SimulationFlowController simulationFlowController;

    @FXML
    private Button nextStepButton;
    @FXML
    public Button previousStepButton;
    @FXML
    private Button skipToEndButton;
    @FXML
    private Button saveImageButton;

    public void nextStep(){
        simulationFlowController.nextStep();
    }

    public void previousStep(){
        simulationFlowController.previousStep();
    }

    public void skipToTheEnd(){
        simulationFlowController.skipToTheEnd();
    }

    public void saveImage(){
        simulationFlowController.saveImage();
    }

    public void injectSimulationFlowController(SimulationFlowController controller){
        this.simulationFlowController = controller;
    }

}
