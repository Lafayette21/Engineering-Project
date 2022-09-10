package com.example.project.visualisation.screen;

import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public record ConnectionDrawer(AnchorPane panel) {

    public void draw(Point2D beginPoint, Point2D endPoint) {
        Line line = new Line(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());
        panel.getChildren().add(line);
    }
}
