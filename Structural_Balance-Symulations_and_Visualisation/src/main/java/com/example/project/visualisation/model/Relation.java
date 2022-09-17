package com.example.project.visualisation.model;

public class Relation {
    private final Actor firstActor;
    private final Actor secondActor;
    private RelationType relationType;

    public Relation(Actor firstActor, Actor secondActor, RelationType relationType) {
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.relationType = relationType;
    }
}
