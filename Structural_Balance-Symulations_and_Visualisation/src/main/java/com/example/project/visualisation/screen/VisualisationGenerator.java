package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.visualisation.actorshandler.*;
import javafx.scene.layout.AnchorPane;

public class VisualisationGenerator {
    private final ParametersValueHandler parametersValueHandler;
    private final AnchorPane visualisationPanel;

    private ActorHandler actorHandler;

    public VisualisationGenerator(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        this.parametersValueHandler = parametersValueHandler;
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        int actorsNumber = getActorsNumber(parametersValueHandler);
        actorHandler = prepareActorsHandler(actorsNumber);
    }

    private ActorHandler prepareActorsHandler(int actorsNumber) {
        if (actorsNumber <= 10) {
            return new TenActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 20) {
            return new TwentyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 30) {
            return new ThirtyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 40) {
            return new FortyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 50) {
            return new FiftyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 60) {
            return new SixtyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 70) {
            return new SeventyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 80) {
            return new EightyActorsHandler(actorsNumber);
        }
        if (actorsNumber <= 90) {
            return new NinetyActorsHandler(actorsNumber);
        }
        return new HundredActorsHandler(actorsNumber);
    }

    private int getActorsNumber(ParametersValueHandler parametersValueHandler) {
        ActorsParametersValues parameterValue =
                (ActorsParametersValues) parametersValueHandler.getParameterValueByResource(Resource.ActorParameters);
        return parameterValue.actorNumber();
    }


}
