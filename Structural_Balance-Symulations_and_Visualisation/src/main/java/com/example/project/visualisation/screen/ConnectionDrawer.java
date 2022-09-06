package com.example.project.visualisation.screen;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public record ConnectionDrawer(AnchorPane panel){

    public void draw(int beginX, int beginY, int endX, int endY){
        Line line = new Line(beginX, beginY, endX, endY);
        panel.getChildren().add(line);
    }
}
