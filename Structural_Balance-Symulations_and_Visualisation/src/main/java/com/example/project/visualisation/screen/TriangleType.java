package com.example.project.visualisation.screen;


import javafx.scene.paint.Color;

public enum TriangleType {
    FULL_POSITIVE(Color.CRIMSON),
    HALF_POSITIVE(Color.color(255, 102, 102)),
    FULL_NEGATIVE(Color.ROYALBLUE),
    HALF_NEGATIVE(Color.color(153, 204, 255));

    private final Color color;

    TriangleType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}





