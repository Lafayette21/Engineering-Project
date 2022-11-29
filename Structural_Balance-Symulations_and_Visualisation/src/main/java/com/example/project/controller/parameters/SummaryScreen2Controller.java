package com.example.project.controller.parameters;

import com.example.project.controller.VisualisationGenerationScreen2Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SummaryScreen2Controller implements ParameterScreen {
    private VisualisationGenerationScreen2Controller screenParent;

    @FXML
    private Button generateSimulationButton;
    @FXML
    private Label connectionPercentageLabel;
    @FXML
    private Label positivePercentageLabel;
    @FXML
    private Label rowNumberLabel;
    @FXML
    private Label columnNumberLabel;

    @Override
    public void injectScreenParent(VisualisationGenerationScreen2Controller visualisationGenerationController) {
        this.screenParent = visualisationGenerationController;
    }

    public void setConnectionPercentageLabelValue(int connectionPercentageValue) {
        connectionPercentageLabel.setText(String.valueOf(connectionPercentageValue));
    }

    public void setPositivePercentageLabelValue(int positivePercentageValue) {
        positivePercentageLabel.setText(String.valueOf(positivePercentageValue));
    }

    public void setRowNumberValue(int rowNumberValue) {
        rowNumberLabel.setText(String.valueOf(rowNumberValue));
    }

    public void setColumnNumberLabelValue(int columnNumberValue) {
        columnNumberLabel.setText(String.valueOf(columnNumberValue));
    }


    public void generateVisualisation() {
        screenParent.changeScreenToVisualisationScreen();
    }
}
