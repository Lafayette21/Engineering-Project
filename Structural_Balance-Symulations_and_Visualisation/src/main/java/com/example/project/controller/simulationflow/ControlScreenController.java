package com.example.project.controller.simulationflow;

import com.example.project.controller.SimulationFlowController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlScreenController {
    private static final String BUTTON_START_TEXT = "Rozpocznij";
    private static final String BUTTON_PAUSE_TEXT = "Zatrzymaj";

    @FXML
    private Button previousStepButton;
    @FXML
    private Button nextStepButton;
    @FXML
    private Button runButton;
    @FXML
    private Button saveChartButton;
    @FXML
    private Button saveNetButton;

    private SimulationFlowController parentController;

    public void injectScreenController(SimulationFlowController simulationFlowController){
        this.parentController = simulationFlowController;
    }

    public void nextStep(){
        parentController.nextStep();
    }

    public void previousStep(){
        parentController.previousStep();
    }

    public void start(){
        parentController.start();
        runButton.setText(BUTTON_PAUSE_TEXT);
        runButton.setOnAction(event -> pause());
    }

    public void pause(){
        parentController.pause();
        runButton.setText(BUTTON_START_TEXT);
        runButton.setOnAction(event -> start());
    }

    public void saveNetImage(){
        parentController.saveImage("Net");
    }
    public void saveChartImage(){
        parentController.saveImage("Chart");
    }

}
