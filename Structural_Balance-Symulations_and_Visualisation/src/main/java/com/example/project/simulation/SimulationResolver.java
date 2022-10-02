package com.example.project.simulation;

import com.example.project.visualisation.model.Relation;

import java.util.List;

public class SimulationResolver {
    private final double annealingParameter;
    private final int numberOfActors;

    public SimulationResolver(double annealingParameter, int numberOfActors) {
        this.annealingParameter = annealingParameter;
        this.numberOfActors = numberOfActors;
    }

    public List<Relation> getNextStepRelations(List<Relation> currentRelationList){
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(currentRelationList, numberOfActors);
        RelationMatrix relationMatrix = new RelationMatrix(currentRelationList, numberOfActors);

        return List.of();
    }
}
