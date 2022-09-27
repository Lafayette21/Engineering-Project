package com.example.project.visualisation.screen;

import com.example.project.visualisation.model.Relation;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.util.Set;

public class ConnectionDrawer {
    private ConnectionDrawer() {
        throw new RuntimeException("Class ConnectionDrawer cannot be instantiated");
    }

    public static void draw(Set<Relation> relationList, AnchorPane panel) {
        relationList.stream().map(ConnectionDrawer::getLine).forEach(line -> addLineToPanel(line, panel));
    }

    private static Line getLine(Relation relation) {
        Line line = createLine(relation);
        line.setStroke(relation.getRelationType().getColor());
        line.setStrokeWidth(4);
        line.setOnMousePressed(click -> {
            line.setStroke(getNewStrokeColor(line));
        });
        return line;
    }

    private static Line createLine(Relation relation) {
        Point2D firstActorPosition = relation.getFirstActor().getPosition();
        Point2D secondActorPosition = relation.getSecondActor().getPosition();
        return new Line(firstActorPosition.getY(), firstActorPosition.getY(),
                secondActorPosition.getX(), secondActorPosition.getY());
    }

    private static Paint getNewStrokeColor(Line line) {
        if (line.getStroke().equals(Color.RED)) {
            return Color.BLUE;
        }
        if (line.getStroke().equals(Color.BLUE)) {
            return Color.GRAY;
        }
        return Color.RED;
    }

    private static boolean addLineToPanel(Line line, AnchorPane panel) {
        return panel.getChildren().add(line);
    }
}
