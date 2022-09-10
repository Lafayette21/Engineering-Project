package com.example.project.visualisation.screen;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.util.Random;

public final class ConnectionDrawer {
    private static final int LOWER_BOUND_FOR_RANDOM_NUMBER = 0;
    private static final int UPPER_BOUND_FOR_RANDOM_NUMBER = 100;

    private final AnchorPane panel;
    private final int connectionCreationPercentage;
    private final int posToNegRatio;

    public ConnectionDrawer(AnchorPane panel, int connectionCreationPercentage, int posToNegRatio) {
        this.panel = panel;
        this.connectionCreationPercentage = connectionCreationPercentage;
        this.posToNegRatio = posToNegRatio;
    }

    public void draw(Point2D beginPoint, Point2D endPoint) {
        Line line = new Line(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
        line.setStroke(setColor());
        line.setStrokeWidth(4);
        line.setOnMousePressed(click -> {
            line.setStroke(getNewStrokeColor(line));
        });
        panel.getChildren().add(line);
    }

    private Color setColor() {
        int randomConnection = new Random().nextInt(LOWER_BOUND_FOR_RANDOM_NUMBER, UPPER_BOUND_FOR_RANDOM_NUMBER);
        if (randomConnection > connectionCreationPercentage) {
            return Color.GRAY;
        } else {
            int randomPosToNeg = new Random().nextInt(LOWER_BOUND_FOR_RANDOM_NUMBER, UPPER_BOUND_FOR_RANDOM_NUMBER);
            if (randomPosToNeg < posToNegRatio) {
                return Color.RED;
            } else {
                return Color.BLUE;
            }
        }
    }

    private Paint getNewStrokeColor(Line line) {
        if (line.getStroke().equals(Color.RED)) {
            return Color.BLUE;
        }
        if (line.getStroke().equals(Color.BLUE)) {
            return Color.GRAY;
        }
        return Color.RED;

    }
}
