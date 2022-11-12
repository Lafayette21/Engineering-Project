package com.example.project.visualisation.model;


import javafx.scene.paint.Color;

import java.util.stream.Stream;

public enum TriadType {
    FULL_POSITIVE(Color.FIREBRICK, 3),
    HALF_POSITIVE(Color.LIGHTCORAL, -1),
    FULL_NEGATIVE(Color.ROYALBLUE, -3),
    HALF_NEGATIVE(Color.CORNFLOWERBLUE, 1),
    NONE(Color.LIGHTGRAY, 0);

    private final Color color;
    private final Integer annotation;

    TriadType(Color color, Integer annotation) {
        this.color = color;
        this.annotation = annotation;
    }

    public Color getColor() {
        return color;
    }

    public Integer getAnnotation() {
        return annotation;
    }

    public static Stream<TriadType> getTriadTypes() {
        return Stream.of(TriadType.values());
    }
}





