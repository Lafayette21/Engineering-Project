package com.example.project.database.model;

import javafx.scene.paint.Color;

public enum RelationType {
    POSITIVE("Positive",Color.RED),
    NEGATIVE("Negative",Color.BLUE),
    NONE("None",Color.GRAY);

    private final String relationName;
    private final Color color;

    RelationType(String relationName, Color color) {
        this.relationName = relationName;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
