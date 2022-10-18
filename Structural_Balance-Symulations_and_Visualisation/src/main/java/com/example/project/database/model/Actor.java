package com.example.project.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorId;

    @OneToOne
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
