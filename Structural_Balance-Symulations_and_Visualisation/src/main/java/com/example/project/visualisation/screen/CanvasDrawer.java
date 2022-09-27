package com.example.project.visualisation.screen;

import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Set;

public class CanvasDrawer {
    private CanvasDrawer() {
        throw new RuntimeException("Class Canvas Drawer cannot be instantiated");
    }

    public static void draw(AnchorPane visualisationPanel, List<Actor> actorList, List<Relation> relationList) {
        clearPanel(visualisationPanel);
        ActorDrawer.draw(actorList, visualisationPanel);
        ConnectionDrawer.draw(relationList, visualisationPanel);
    }

    private static void clearPanel(AnchorPane visualisationPanel) {
        visualisationPanel.getChildren().clear();
    }
}
