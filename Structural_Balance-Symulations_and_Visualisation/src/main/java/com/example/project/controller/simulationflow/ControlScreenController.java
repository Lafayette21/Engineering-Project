package com.example.project.controller.simulationflow;

import com.example.project.controller.SimulationFlowController2;
import javafx.scene.control.Button;

public class ControlScreenController {
    private SimulationFlowController2 parentController;

    public void injectScreenController(SimulationFlowController2 simulationFlowController2){
        this.parentController = simulationFlowController2;
    }

    public Button nextStepButton;

    public void nextStep(){
        parentController.nextStep();
    }
}
