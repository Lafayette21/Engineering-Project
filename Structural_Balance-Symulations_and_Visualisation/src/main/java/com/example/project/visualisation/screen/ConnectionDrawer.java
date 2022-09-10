package com.example.project.visualisation.screen;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Objects;
import java.util.Random;

public final class ConnectionDrawer {
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
        panel.getChildren().add(line);
    }

    private Color setColor() {
        int randomConnection = new Random().nextInt(0, 100);
        if (randomConnection > connectionCreationPercentage) {
            return Color.GRAY;
        } else {
            int randomPosToNeg = new Random().nextInt(0, 100);
            if (randomPosToNeg < posToNegRatio) {
                return Color.RED;
            } else {
                return Color.BLUE;
            }
        }
    }
}
