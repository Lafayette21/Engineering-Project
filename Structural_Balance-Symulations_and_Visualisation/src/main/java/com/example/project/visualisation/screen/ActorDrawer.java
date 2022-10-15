package com.example.project.visualisation.screen;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Position;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.List;

public class ActorDrawer {
    private static final int RADIUS = 8;

    private ActorDrawer() {
        throw new InstantiationNotAllowedException();
    }

    public static void draw(List<Actor> actorList, AnchorPane panel) {
        actorList.stream()
                .map(Actor::getPosition)
                .map(ActorDrawer::createPoint)
                .forEach(point -> addPointToCanvas(point, panel));
    }

    private static void addPointToCanvas(Circle point, AnchorPane panel) {
        panel.getChildren().add(point);
    }

    private static Circle createPoint(Position position) {
        Circle point = new Circle();
        point.setCenterX(position.getX());
        point.setCenterY(position.getY());
        point.setRadius(RADIUS);
        return point;
    }
}
