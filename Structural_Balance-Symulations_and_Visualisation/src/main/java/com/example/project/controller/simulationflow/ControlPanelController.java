package com.example.project.controller.simulationflow;

import com.example.project.controller.SimulationFlowController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlPanelController {
    private SimulationFlowController simulationFlow2Controller;

    @FXML
    private Button nextStepButton;
    @FXML
    public Button previousStepButton;
    @FXML
    private Button skipToEndButton;

    public void nextStep(){
        simulationFlow2Controller.nextStep();
    }

    public void previousStep(){
        simulationFlow2Controller.previousStep();
    }

    public void skipToTheEnd(){
        simulationFlow2Controller.skipToTheEnd();
    }

    public void injectSimulationFlowController(SimulationFlowController controller){
        this.simulationFlow2Controller = controller;
    }

}
