package com.example.project.database.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "actor_parameters")
public class ActorParameters implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorParameterId;
    @Column(name = "number_of_rows")
    private int numberOfRows;
    @Column(name = "number_of_columns")
    private int numberOfColumns;

    public ActorParameters() {}

    public ActorParameters(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public Integer getActorParameterId() {
        return actorParameterId;
    }

    public void setActorParameterId(Integer actorParameterId) {
        this.actorParameterId = actorParameterId;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }
}
