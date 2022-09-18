package com.example.project.visualisation.model;

import java.util.Objects;

public class Actor {
    private final Integer actorId;

    public Actor(Integer actorsId) {
        this.actorId = actorsId;
    }

    public Integer getActorId() {
        return actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return actorId.equals(actor.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId);
    }
}
