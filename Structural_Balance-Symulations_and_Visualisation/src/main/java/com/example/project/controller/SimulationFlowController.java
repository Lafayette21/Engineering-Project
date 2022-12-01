package com.example.project.controller;

import com.example.project.RepositoryName;
import com.example.project.controller.simulationflow.*;
import com.example.project.database.model.SimulationParameters;
import com.example.project.database.repository.RepositoryManager;
import com.example.project.database.repository.SimulationParametersRepository;
import com.example.project.simulation.SimulationFlow;
import com.example.project.simulation.SimulationRequiredValuesDTO;
import com.example.project.util.ImageSaver;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationFlowController implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();
    private final SimulationParametersRepository repository = (SimulationParametersRepository) RepositoryManager
            .getInstance().getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);

    @FXML
    private NetScreenController netScreenController;
    @FXML
    private ChartScreenController chartScreenController;
    @FXML
    private StatePanelController statePanelController;
    @FXML
    private ControlScreenController controlScreenController;
    @FXML
    private ParameterScreenController parameterScreenController;

    private SimulationFlow simulationFlow;

    public void nextStep() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        getSimulationControllers().forEach(controller->controller.nextSimulationStep(simulationParameters));
    }

    public void previousStep() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        getSimulationControllers().forEach(controller->controller.previousSimulationStep(simulationParameters));
    }

    public void start() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        getSimulationControllers().forEach(controller->controller.start(simulationParameters));
    }

    public void pause(){
        getSimulationControllers().forEach(SimulationTabController::pause);
    }

    public void saveImage(String resource) {
        if (resource.equals("Net")){
            saveVisualisationPanel(netScreenController);
        } else {
            saveVisualisationPanel(chartScreenController);
        }
    }

    private void saveVisualisationPanel(Savable savable) {
        AnchorPane visualisationPanel = savable.getVisualisationPanel();
        ImageSaver.save(visualisationPanel, simulationFlow.getCurrentStepNumber());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controlScreenController.injectScreenController(this);
        setSimulationFlow();
        getSimulationControllers().forEach(controller -> controller.prepareInitial(simulationFlow));
    }

    private void setSimulationFlow() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        simulationFlow = new SimulationFlow(actorList, relationList);
    }

    private List<SimulationTabController> getSimulationControllers() {
        return List.of(this.netScreenController, this.chartScreenController, this.statePanelController);
    }
}
