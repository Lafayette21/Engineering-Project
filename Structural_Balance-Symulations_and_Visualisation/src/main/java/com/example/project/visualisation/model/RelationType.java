package com.example.project.visualisation.model;

import javafx.scene.paint.Color;

public enum RelationType {
    POSITIVE("Positive", Color.RED, 1),
    NEGATIVE("Negative", Color.BLUE, -1),
    NONE("None", Color.GRAY, 0);

    private final String relationName;
    private final Color color;
    private final Integer relationAnnotation;

    RelationType(String relationName, Color color, Integer relationAnnotation) {
        this.relationName = relationName;
        this.color = color;
        this.relationAnnotation = relationAnnotation;
    }

    public Color getColor() {
        return color;
    }

    public Integer getRelationAnnotation() {
        return relationAnnotation;
    }
}
