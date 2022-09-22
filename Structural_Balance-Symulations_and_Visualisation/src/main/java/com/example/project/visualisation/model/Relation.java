package com.example.project.visualisation.model;

import java.util.Objects;

public class Relation {
    private final Actor firstActor;
    private final Actor secondActor;
    private RelationType relationType;

    public Relation(Actor firstActor, Actor secondActor, RelationType relationType) {
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.relationType = relationType;
    }

    public Actor getFirstActor() {
        return firstActor;
    }

    public Actor getSecondActor() {
        return secondActor;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation relation)) return false;
        return (firstActor.equals(relation.firstActor) && secondActor.equals(relation.secondActor))
                || (firstActor.equals(relation.secondActor) && secondActor.equals(relation.firstActor));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstActor, secondActor);
    }
}
