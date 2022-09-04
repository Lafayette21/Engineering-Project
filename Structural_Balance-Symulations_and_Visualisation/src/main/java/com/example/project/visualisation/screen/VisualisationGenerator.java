package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.visualisation.actorshandler.*;
import javafx.scene.layout.AnchorPane;

import static com.example.project.visualisation.screen.ActorsHandlerFactory.prepareActorsHandler;

public class VisualisationGenerator {
    private final ParametersValueHandler parametersValueHandler;
    private final AnchorPane visualisationPanel;

    private ActorHandler actorHandler;

    public VisualisationGenerator(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        this.parametersValueHandler = parametersValueHandler;
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler) {
        int actorsNumber = getActorsNumber(parametersValueHandler);
        actorHandler = prepareActorsHandler(actorsNumber);
        actorHandler.organizeActors(visualisationPanel);
    }

    private int getActorsNumber(ParametersValueHandler parametersValueHandler) {
        ActorsParametersValues parameterValue =
                (ActorsParametersValues) parametersValueHandler.getParameterValueByResource(Resource.ActorParameters);
        return parameterValue.actorNumber();
    }


}
