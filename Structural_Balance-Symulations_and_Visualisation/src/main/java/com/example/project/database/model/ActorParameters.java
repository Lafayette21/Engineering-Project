package com.example.project.database.model;

import jakarta.persistence.*;

@Entity
public class ActorParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorParameterId;
    @Column(name = "number_of_rows")
    private int numberOfRows;
    @Column(name = "number_of_columns")
    private int numberOfColumns;

    public ActorParameters() {}

    public ActorParameters(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
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
