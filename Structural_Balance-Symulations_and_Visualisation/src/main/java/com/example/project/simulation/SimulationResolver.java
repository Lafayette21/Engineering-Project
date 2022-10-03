package com.example.project.simulation;

import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.util.RelationMatrixToRelationListConverter;

import java.util.List;
import java.util.Random;

public class SimulationResolver {
    private final double annealingParameter;
    private final int numberOfActors;

    public SimulationResolver(double annealingParameter, int numberOfActors) {
        this.annealingParameter = annealingParameter;
        this.numberOfActors = numberOfActors;
    }

    public List<Relation> getNextStepRelations(List<Relation> currentRelationList) {
        ConnectionMatrix connectionMatrix = new ConnectionMatrix(currentRelationList, numberOfActors);
        RelationMatrix relationMatrix = new RelationMatrix(currentRelationList, numberOfActors);
        int firstActorId = new Random().nextInt(numberOfActors);
        int secondActorId = new Random().nextInt(numberOfActors);


        return RelationMatrixToRelationListConverter.convert(relationMatrix, currentRelationList);
    }
}
