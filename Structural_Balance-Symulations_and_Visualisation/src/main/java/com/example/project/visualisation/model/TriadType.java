package com.example.project.visualisation.model;


import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Stream;

public enum TriadType {
    FULL_POSITIVE(Color.CRIMSON, 3),
    HALF_POSITIVE(Color.color(255, 102, 102), -1),
    FULL_NEGATIVE(Color.ROYALBLUE, 1),
    HALF_NEGATIVE(Color.color(153, 204, 255), -3);

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

    public static Stream<TriadType> getAllTypes(){
        return Stream.of(TriadType.values());
    }
}





