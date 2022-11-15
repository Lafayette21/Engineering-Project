package com.example.project.simulation;

import com.example.project.util.SimulationBalanceAlert;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.util.RelationMatrixToRelationListConverter;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SimulationResolver {
    private static final int POSITIVE_RELATION_ANNOTATION = 1;
    private static final int NEGATIVE_RELATION_ANNOTATION = -1;

    private final double temperature;
    private final int numberOfActors;

    private ConnectionMatrix connectionMatrix;
    private RelationMatrix relationMatrix;

    public SimulationResolver(double temperature, int numberOfActors) {
        this.temperature = temperature;
        this.numberOfActors = numberOfActors;
    }

    public List<Relation> getNextStepRelations(List<Relation> currentRelationList) {
        connectionMatrix = new ConnectionMatrix(currentRelationList, numberOfActors);
        relationMatrix = new RelationMatrix(currentRelationList, numberOfActors);

        IntStream.range(0, connectionMatrix.getNumberOfExistingRelations()).forEach(smallStep -> {
            SimulationActorIds actorIds = getActorIds();
            changeRelation(actorIds);
        });

        if (SimulationBalanceChecker.check(connectionMatrix, relationMatrix)) {
            new SimulationBalanceAlert().showAndWait();
        }

        return RelationMatrixToRelationListConverter.convert(relationMatrix, currentRelationList);
    }

    private SimulationActorIds getActorIds() {
        int firstActorId = new Random().nextInt(1, numberOfActors);
        int secondActorId = new Random().nextInt(1, numberOfActors);

        while (!connectionMatrix.existsRelation(firstActorId, secondActorId)) {
            firstActorId = new Random().nextInt(1, numberOfActors);
            secondActorId = new Random().nextInt(1, numberOfActors);
        }
        return new SimulationActorIds(firstActorId, secondActorId);
    }

    private void changeRelation(SimulationActorIds actorIds) {
        int firstActorId = actorIds.firstActorId();
        int secondActorId = actorIds.secondActorId();
        double changeProbability = getChangeProbability(firstActorId, secondActorId);
        double randomNumber = new Random().nextDouble();
        if (randomNumber < changeProbability) {
            relationMatrix.set(firstActorId, secondActorId, POSITIVE_RELATION_ANNOTATION);
        } else {
            relationMatrix.set(firstActorId, secondActorId, NEGATIVE_RELATION_ANNOTATION);
        }
    }

    private double getChangeProbability(int firstActorId, int secondActorId) {
        int ksi = 0;
        for (int k = 1; k <= numberOfActors; k++) {
            ksi += connectionMatrix.get(firstActorId, k) *
                    relationMatrix.get(firstActorId, k) *
                    connectionMatrix.get(k, secondActorId) *
                    relationMatrix.get(secondActorId, k);
        }
        return 1 / (1 + Math.exp(-2 * ksi / temperature));
    }
}
