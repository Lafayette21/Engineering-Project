package com.example.project.visualisation.screen;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.util.TriadFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class CanvasDrawer {
    private CanvasDrawer() {
        throw new InstantiationNotAllowedException();
    }

    public static void draw(AnchorPane visualisationPanel, List<Actor> actorList, List<Relation> relationList,
                            boolean drawTriad) {
        clearPanel(visualisationPanel);
        if (drawTriad){
            TriadDrawer.draw(TriadFactory.createTriads(relationList), visualisationPanel);
        }
        ConnectionDrawer.draw(relationList, visualisationPanel);
        ActorDrawer.draw(actorList, visualisationPanel);
    }

    private static void clearPanel(AnchorPane visualisationPanel) {
        visualisationPanel.getChildren().clear();
    }
}
