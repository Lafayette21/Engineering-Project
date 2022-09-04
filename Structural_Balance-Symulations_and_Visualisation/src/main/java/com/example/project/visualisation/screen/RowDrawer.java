package com.example.project.visualisation.screen;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public record RowDrawer(int distance, int numberOfElements, int beginXPosition, int beginYPosition) {
    public void draw(AnchorPane panel) {
        for (int i = 0; i < numberOfElements; i++) {
            Circle circle = new Circle();
            circle.setCenterX(beginXPosition + i * distance);
            circle.setCenterY(beginYPosition);
            circle.setRadius(10);

            panel.getChildren().add(circle);
        }
    }


}
