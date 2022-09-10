package com.example.project.visualisation.screen;

import com.example.project.Resource;
import com.example.project.controller.parameters.ParametersValueHandler;
import com.example.project.parametervalues.ActorsParametersValues;
import com.example.project.parametervalues.ConnectionsParametersValues;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;

import java.util.stream.IntStream;

public class VisualisationGenerator {
    private final ParametersValueHandler parametersValueHandler;
    private final AnchorPane visualisationPanel;

    private int rowNumber;
    private int columnNumber;
    private int connectionCreationPercentRatio;
    private int positiveToNegativePercentRatio;

    public VisualisationGenerator(ParametersValueHandler parametersValueHandler, AnchorPane visualisationPanel) {
        this.parametersValueHandler = parametersValueHandler;
        this.visualisationPanel = visualisationPanel;
    }

    public void generate(ParametersValueHandler parametersValueHandler) {
        clearCanvas();
        setParametersValues(parametersValueHandler);
        drawToCanvas();
    }

    private void setParametersValues(ParametersValueHandler parametersValueHandler) {
        ActorsParametersValues actorParameters = (ActorsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ActorParameters);
        ConnectionsParametersValues connectionParameters = (ConnectionsParametersValues)
                parametersValueHandler.getParameterValueByResource(Resource.ConnectionParameters);

        rowNumber = actorParameters.rowNumber();
        columnNumber = actorParameters.columnNumber();
        connectionCreationPercentRatio = connectionParameters.connectionCreationPercentRatio();
        positiveToNegativePercentRatio = connectionParameters.positiveToNegativePercentRatio();
    }

    private void drawToCanvas() {
        double width = visualisationPanel.getWidth();
        double height = visualisationPanel.getHeight();
        double distanceX = width / (columnNumber + 1);
        double distanceY = height / (rowNumber + 1);
        drawConnectionsToCanvas(distanceX,distanceY);
        drawActorsToCanvas(distanceX,distanceY);
    }

    private void clearCanvas() {
        visualisationPanel.getChildren().clear();
    }

    private void drawActorsToCanvas(double distanceX, double distanceY) {
        IntStream.range(0, rowNumber)
                .mapToObj(i ->
                        new ActorsRowDrawer(distanceX, columnNumber, distanceX, distanceY + i * distanceY))
                .forEachOrdered(actorsRowDrawer -> actorsRowDrawer.draw(visualisationPanel));
    }

    private void drawConnectionsToCanvas(double distanceX, double distanceY) {
        for (int curRow = 1; curRow < rowNumber; curRow++) {
            for (int curColumn = 1; curColumn < columnNumber; curColumn++) {
                drawHorizontalConnections(distanceX, distanceY, curRow, curColumn);
                drawVerticalConnections(distanceX, distanceY, curRow, curColumn);
                drawDiagonalConnections(distanceX, distanceY, curRow, curColumn);
            }
        }
        addMissingConnections(distanceX, distanceY, rowNumber, columnNumber);
    }

    private void drawHorizontalConnections(double distanceX, double distanceY, int curRow, int curColumn) {
        ConnectionDrawer connectionDrawer =
                new ConnectionDrawer(visualisationPanel,connectionCreationPercentRatio,positiveToNegativePercentRatio);

        Point2D beginPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + (curRow - 1) * distanceY);
        Point2D endPoint =
                new Point2D(distanceX + curColumn * distanceX, distanceY + (curRow - 1) * distanceY);

        connectionDrawer.draw(beginPoint, endPoint);
    }

    private void drawVerticalConnections(double distanceX, double distanceY, int curRow, int curColumn) {
        ConnectionDrawer connectionDrawer =
                new ConnectionDrawer(visualisationPanel,connectionCreationPercentRatio,positiveToNegativePercentRatio);

        Point2D beginPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + (curRow - 1) * distanceY);
        Point2D endPoint =
                new Point2D(distanceX + (curColumn - 1) * distanceX, distanceY + curRow * distanceY);

        connectionDrawer.draw(beginPoint, endPoint);
    }

    private void drawDiagonalConnections(double distanceX, double distanceY, int curRow, int curColumn) {
        ConnectionDrawer connectionDrawer =
                new ConnectionDrawer(visualisationPanel,connectionCreationPercentRatio,positiveToNegativePercentRatio);

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

    private void addMissingConnections(double distanceX, double distanceY, int rowNumber, int columnNumber) {
        ConnectionDrawer connectionDrawer =
                new ConnectionDrawer(visualisationPanel,connectionCreationPercentRatio,positiveToNegativePercentRatio);
        addConnectionsToLastRow(distanceX, distanceY, rowNumber, columnNumber, connectionDrawer);
        addConnectionsToLastColumn(distanceX, distanceY, rowNumber, columnNumber, connectionDrawer);
    }

    private void addConnectionsToLastRow(double distanceX, double distanceY, int rowNumber, int columnNumber, ConnectionDrawer connectionDrawer) {
        IntStream.range(1, columnNumber).forEachOrdered(curCol -> {
            Point2D beginPoint = new Point2D(distanceX + (curCol - 1) * distanceX, rowNumber * distanceY);
            Point2D endPoint = new Point2D(distanceX + curCol * distanceX, rowNumber * distanceY);
            connectionDrawer.draw(beginPoint, endPoint);
        });
    }

    private void addConnectionsToLastColumn(double distanceX, double distanceY, int rowNumber, int columnNumber, ConnectionDrawer connectionDrawer) {
        IntStream.range(1, rowNumber).forEachOrdered(curRow -> {
            Point2D beginPoint = new Point2D(columnNumber * distanceX, distanceY + (curRow - 1) * distanceY);
            Point2D endPoint = new Point2D(columnNumber * distanceX, distanceY + curRow * distanceY);
            connectionDrawer.draw(beginPoint, endPoint);
        });
    }
}
