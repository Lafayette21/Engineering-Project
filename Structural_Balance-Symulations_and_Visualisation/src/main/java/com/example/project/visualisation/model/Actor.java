package com.example.project.visualisation.model;

import java.util.List;

public class Actor {
    private final Integer actorId;
    private List<Relation> relations;

    public Actor(Integer actorsId) {
        this.actorId = actorsId;
    }

    public Integer getActorId() {
        return actorId;
    }
}
