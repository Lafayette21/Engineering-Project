package com.example.project.visualisation.model;

public enum RelationType {
    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    NONE("None");

    private final String relationName;

    RelationType(String relationName) {
        this.relationName = relationName;
    }
}
