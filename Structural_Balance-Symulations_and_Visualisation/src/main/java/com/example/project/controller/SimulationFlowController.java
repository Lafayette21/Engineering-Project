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
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationFlowController implements ControlledScreen, Initializable {
    private final MainApplicationScreenController screenParent = MainApplicationScreenController.getInstance();
    private final SimulationParametersRepository repository = (SimulationParametersRepository) RepositoryManager
            .getInstance().getParameterRepositoryByName(RepositoryName.SIMULATION_PARAMETERS);

    @FXML
    private TabPane selectionTabPane;
    @FXML
    private NetSimulationTabController netTabController;
    @FXML
    private ChartSimulationTabController chartTabController;
    @FXML
    private ControlPanelController controlPanelController;

    private SimulationFlow simulationFlow;


    public void nextStep() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        getAllControllers().forEach(simulationTabController -> simulationTabController.nextSimulationStep(simulationParameters));
        updateState();
    }

    public void previousStep() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        netTabController.previousSimulationStep(simulationParameters);
        updateState();
    }

    public void start() {
        SimulationParameters simulationParameters = repository.getSimulationParameters();
        getAllControllers().forEach(simulationTabController -> simulationTabController.start(simulationParameters));
        updateState();
    }

    public void pause(){
        getAllControllers().forEach(SimulationTabController::pause);
        updateState();
    }

    public void saveImage() {
        SingleSelectionModel<Tab> selectionModel = selectionTabPane.getSelectionModel();
        if (selectionModel.isSelected(1)) {
            saveVisualisationPanel(netTabController);
        } else if (selectionModel.isSelected(2)) {
            saveVisualisationPanel(chartTabController);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nie mozna zapisac tego ekranu");
            alert.showAndWait();
        }
    }

    private void saveVisualisationPanel(Savable savable) {
        AnchorPane visualisationPanel = savable.getVisualisationPanel();
        ImageSaver.save(visualisationPanel, simulationFlow.getCurrentStepNumber());
    }

    private void updateState() {
        getAllStateControllableControllers()
                .stream()
                .map(StateControllable::getStatePanelController)
                .forEach(statePanelController -> statePanelController.updateStateOfSimulation(simulationFlow));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareInitialSimulationVisualisation();
        controlPanelController.injectSimulationFlowController(this);
    }

    private void prepareInitialSimulationVisualisation() {
        SimulationRequiredValuesDTO requiredValuesDTO = (SimulationRequiredValuesDTO) screenParent.getUserData();
        List<Actor> actorList = requiredValuesDTO.actorList();
        List<Relation> relationList = requiredValuesDTO.relationList();
        simulationFlow = new SimulationFlow(actorList, relationList);

        getAllControllers().forEach(simulationTabController -> simulationTabController.prepareInitial(simulationFlow));
    }

    private List<SimulationTabController> getAllControllers() {
        return List.of(this.netTabController, this.chartTabController);
    }

    private List<StateControllable> getAllStateControllableControllers() {
        return List.of(this.netTabController, this.chartTabController);
    }
}
