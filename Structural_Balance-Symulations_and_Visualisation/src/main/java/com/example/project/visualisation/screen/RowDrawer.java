package com.example.project.visualisation.screen;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class RowDrawer {
    private final int distance;

    public RowDrawer(int distance) {
        this.distance = distance;
    }

    public void draw(AnchorPane panel){
        Circle circle = new Circle();
        circle.setCenterX(20);
        circle.setCenterY(20);
        circle.setRadius(5);

        panel.getChildren().add(circle);
    }


}
