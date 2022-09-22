package com.example.project.visualisation.screen;

import com.example.project.visualisation.model.Actor;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.stream.IntStream;

public class ActorDrawer {
    private ActorDrawer() {
        throw new RuntimeException("Class ActorDrawer cannot be instantiated");
    }

    public static void draw(List<Actor> actorList, AnchorPane panel) {
        actorList.stream()
                .map(Actor::getPosition)
                .map(ActorDrawer::createPoint)
                .forEach(point -> addPointToCanvas(point, panel));
    }

    private static boolean addPointToCanvas(Circle point, AnchorPane panel) {
        return panel.getChildren().add(point);
    }

    private static Circle createPoint(Point2D position) {
        Circle circle = new Circle();
        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());
        circle.setRadius(8);
        return circle;
    }


}
