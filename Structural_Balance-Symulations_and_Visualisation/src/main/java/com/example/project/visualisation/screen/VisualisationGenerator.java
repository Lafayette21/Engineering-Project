package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import javafx.scene.layout.AnchorPane;

import java.util.stream.IntStream;

public class VisualisationGenerator {
    private final ParametersValueHandler parametersValueHandler;
    private final AnchorPane visualisationPanel;

    public VisualisationGenerator(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        this.parametersValueHandler = parametersValueHandler;
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler) {
        clearVisualisationPanel();
        ActorsParametersValues actorParameters = (ActorsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ActorParameters);

        drawActorsToCanvas(actorParameters);
    }

    private void clearVisualisationPanel() {
        visualisationPanel.getChildren().removeAll(visualisationPanel);
    }

    private void drawActorsToCanvas(ActorsParametersValues actorParameters) {
        int columnNumber = actorParameters.columnNumber();
        int rowNumber = actorParameters.rowNumber();

        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (columnNumber + 1);
        double distanceY = height / (rowNumber + 1);

        IntStream.range(0, rowNumber)
                .mapToObj(i -> new RowDrawer(distanceX, columnNumber, distanceX, distanceY + i * distanceY))
                .forEachOrdered(rowDrawer -> rowDrawer.draw(visualisationPanel));
    }
}
