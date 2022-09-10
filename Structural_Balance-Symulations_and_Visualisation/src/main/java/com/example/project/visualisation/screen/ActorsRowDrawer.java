package com.example.project.visualisation.screen;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.stream.IntStream;

public record ActorsRowDrawer(double distance, int numberOfElements, double beginXPosition, double beginYPosition) {
    public void draw(AnchorPane panel) {
        IntStream.range(0, numberOfElements)
                .mapToObj(this::createPoint)
                .forEach(circle -> panel.getChildren().add(circle));
    }

    private Circle createPoint(int i) {
        Circle circle = new Circle();
        circle.setCenterX(beginXPosition + i * distance);
        circle.setCenterY(beginYPosition);
        circle.setRadius(8);
        return circle;
    }


}
