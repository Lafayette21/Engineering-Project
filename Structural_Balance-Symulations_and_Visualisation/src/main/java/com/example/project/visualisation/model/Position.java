package com.example.project.visualisation.model;

public record Position(double x, double y) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return Double.compare(position.x, x) == 0 && Double.compare(position.y, y) == 0;
    }

}
