package com.example.project.visualisation.screen;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Triad;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Set;

public class TriadDrawer {
    private TriadDrawer() {
        throw new InstantiationNotAllowedException();
    }

    public static void draw(AnchorPane visualisationPane, Set<Triad> triadList){
        for (Triad triad : triadList){
            Color typeLevelColor = triad.getTypeLevel().getColor();
            Polygon polygon = createPolygon(triad, typeLevelColor);
            visualisationPane.getChildren().add(polygon);
        }
    }

    private static Polygon createPolygon(Triad triad, Color typeLevelColor) {
        Polygon polygon = new Polygon();
        triad.getTriadActors().stream()
                .map(Actor::getPosition)
                .forEach(position -> polygon.getPoints()
                            .addAll(position.getX(), position.getY())
                );
        polygon.setFill(typeLevelColor);
        return polygon;
    }
}
