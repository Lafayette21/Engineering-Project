package com.example.project.visualisation.model;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

public class Relation {
    private final Actor firstActor;
    private final Actor secondActor;
    private RelationType relationType;

    public Relation(Actor firstActor, Actor secondActor) {
        this.firstActor = firstActor;
        this.secondActor = secondActor;
    }

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

    public boolean isNeighbouringRelation(Relation relation){
        if (relation.equals(this)){
            return false;
        }
        return containsActor(relation.getFirstActor()) || containsActor(relation.getSecondActor());
    }

    private boolean containsActor(Actor actor){
        return firstActor.equals(actor) || secondActor.equals(actor);
    }

    public List<Actor> getRelationActors() {
        return ImmutableList.of(this.firstActor, this.secondActor);
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
