package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import javafx.geometry.Point2D;
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
        drawConnectionsToCanvas(actorParameters);

    }

    private void clearVisualisationPanel() {
        visualisationPanel.getChildren().clear();
    }

    private void drawActorsToCanvas(ActorsParametersValues actorParameters) {
        int columnNumber = actorParameters.columnNumber();
        int rowNumber = actorParameters.rowNumber();

        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (columnNumber + 1);
        double distanceY = height / (rowNumber + 1);

        IntStream.range(0, rowNumber)
                .mapToObj(i ->
                        new ActorsRowDrawer(distanceX, columnNumber, distanceX, distanceY + i * distanceY))
                .forEachOrdered(actorsRowDrawer -> actorsRowDrawer.draw(visualisationPanel));
    }

    private void drawConnectionsToCanvas(ActorsParametersValues actorParameters) {
        int columnNumber = actorParameters.columnNumber();
        int rowNumber = actorParameters.rowNumber();

        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (columnNumber + 1);
        double distanceY = height / (rowNumber + 1);

        for (int curRow = 1; curRow < rowNumber; curRow++) {
            for (int curColumn = 1; curColumn < columnNumber; curColumn++) {
                drawHorizontalConnections(distanceX, distanceY, curRow, curColumn);
                drawVerticalConnections(distanceX, distanceY, curRow, curColumn);
                drawDiagonalConnections(distanceX, distanceY, curRow, curColumn);
            }
        }
    }

    private void drawHorizontalConnections(double distanceX, double distanceY, int curRow, int curColumn) {
        ConnectionDrawer connectionDrawer = new ConnectionDrawer(visualisationPanel);

        Point2D beginPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + (curRow - 1) * distanceY);
        Point2D endPoint =
                new Point2D(distanceX + curColumn * distanceX, distanceY + (curRow - 1) * distanceY);

        connectionDrawer.draw(beginPoint, endPoint);
    }

    private void drawVerticalConnections(double distanceX, double distanceY, int curRow, int curColumn) {
        ConnectionDrawer connectionDrawer = new ConnectionDrawer(visualisationPanel);

        Point2D beginPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + (curRow - 1) * distanceY);
        Point2D endPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + curRow * distanceY);

        connectionDrawer.draw(beginPoint, endPoint);
    }

    private void drawDiagonalConnections(double distanceX, double distanceY, int curRow, int curColumn) {
        ConnectionDrawer connectionDrawer = new ConnectionDrawer(visualisationPanel);

        drawUpLeftDownRight(distanceX, distanceY, curRow, curColumn, connectionDrawer);
        drawDownLeftUpRight(distanceX, distanceY, curRow, curColumn, connectionDrawer);
    }

    private void drawUpLeftDownRight(double distanceX, double distanceY, int curRow, int curColumn, ConnectionDrawer connectionDrawer) {
        Point2D beginPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + (curRow - 1) * distanceY);
        Point2D endPoint =
                new Point2D(distanceX + curColumn * distanceX, distanceY + curRow * distanceY);

        connectionDrawer.draw(beginPoint, endPoint);
    }

    private void drawDownLeftUpRight(double distanceX, double distanceY, int curRow, int curColumn, ConnectionDrawer connectionDrawer) {
        Point2D beginPoint =
                new Point2D(distanceX + curColumn * distanceX, distanceY + (curRow - 1) * distanceY);
        Point2D endPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + curRow * distanceY);

        connectionDrawer.draw(beginPoint, endPoint);
    }
}
