package com.example.project.visualisation.model;

import java.util.Objects;
public class Actor {
    private int actorId;
    private Position position;

    public Actor() {}

    public Actor(int actorsId) {
        this.actorId = actorsId;
    }

    public int getActorId() {
        return actorId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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
