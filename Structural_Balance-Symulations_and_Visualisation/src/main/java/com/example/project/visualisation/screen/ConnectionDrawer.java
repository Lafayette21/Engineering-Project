package com.example.project.visualisation.screen;

import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import java.util.List;

public class ConnectionDrawer {
    private static final int STROKE_WIDTH = 4;

    private ConnectionDrawer() {
        throw new RuntimeException("Class ConnectionDrawer cannot be instantiated");
    }

    public static void draw(List<Relation> relationList, AnchorPane panel) {
        for (Relation relation : relationList) {
            Line line = getLine(relation);
            addLineToPanel(line, panel);
        }
    }

    private static Line getLine(Relation relation) {
        Line line = createLine(relation);
        line.setStroke(relation.getRelationType().getColor());
        line.setStrokeWidth(STROKE_WIDTH);
        line.setOnMousePressed(click -> changeColorAndAdjustRelationType(relation, line));
        return line;
    }

    private static Line createLine(Relation relation) {
        Point2D firstActorPosition = relation.getFirstActor().getPosition();
        Point2D secondActorPosition = relation.getSecondActor().getPosition();
        return new Line(firstActorPosition.getX(), firstActorPosition.getY(),
                secondActorPosition.getX(), secondActorPosition.getY());
    }

    private static void changeColorAndAdjustRelationType(Relation relation, Line line) {
        Paint newStrokeColor = getNewStrokeColor(line);
        line.setStroke(newStrokeColor);
        relation.setRelationType(getRelationTypeByColor(newStrokeColor));
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

    private static RelationType getRelationTypeByColor(Paint color) {
        if (color.equals(Color.RED)) {
            return RelationType.POSITIVE;
        }
        if (color.equals(Color.BLUE)) {
            return RelationType.NEGATIVE;
        }
        return RelationType.NONE;
    }

    private static void addLineToPanel(Line line, AnchorPane panel) {
        panel.getChildren().add(line);
    }
}
