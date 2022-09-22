package com.example.project.visualisation.model;

import javafx.geometry.Point2D;

import java.util.Objects;

public class Actor {
    private final int actorId;

    private Point2D position;

    public Actor(int actorsId) {
        this.actorId = actorsId;
    }

    public int getActorId() {
        return actorId;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return actorId == ((Actor) o).actorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId);
    }
}
