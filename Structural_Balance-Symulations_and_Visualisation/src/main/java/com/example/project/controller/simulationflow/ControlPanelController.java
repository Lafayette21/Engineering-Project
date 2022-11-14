package com.example.project.controller.simulationflow;

import com.example.project.controller.SimulationFlowController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlPanelController {
    private static final String BUTTON_START_TEXT = "Rozpocznij";
    private static final String BUTTON_PAUSE_TEXT = "Zatrzymaj";

    private SimulationFlowController simulationFlowController;

    @FXML
    private Button nextStepButton;
    @FXML
    public Button previousStepButton;
    @FXML
    private Button runButton;
    @FXML
    private Button saveImageButton;

    public void nextStep(){
        simulationFlowController.nextStep();
    }

    public void previousStep(){
        simulationFlowController.previousStep();
    }

    public void start(){
        simulationFlowController.start();
        runButton.setText(BUTTON_PAUSE_TEXT);
        runButton.setOnAction(event -> pause());
    }

    public void pause(){
        simulationFlowController.pause();
        runButton.setText(BUTTON_START_TEXT);
        runButton.setOnAction(event -> start());
    }

    public void saveImage(){
        simulationFlowController.saveImage();
    }

    public void injectSimulationFlowController(SimulationFlowController controller){
        this.simulationFlowController = controller;
    }

}
