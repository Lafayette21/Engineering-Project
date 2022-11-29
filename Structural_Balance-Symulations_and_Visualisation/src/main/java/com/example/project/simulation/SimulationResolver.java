package com.example.project.simulation;

import com.example.project.util.SimulationBalanceAlert;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;
import com.example.project.visualisation.model.Triad;
import com.example.project.util.RelationMatrixToRelationListConverter;
import com.example.project.visualisation.util.TriadExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class SimulationResolver {
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
            if (temperature == 0) {
                changeRelation(currentRelationList, actorIds);
            } else {
                changeRelation(actorIds);
            }
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

    private void changeRelation(List<Relation> currentRelationList, SimulationActorIds actorIds) {
        int firstActorId = actorIds.firstActorId();
        int secondActorId = actorIds.secondActorId();
        List<Actor> actors = getNeighbouringActors(currentRelationList, firstActorId, secondActorId);
        int changeProbability = getProbability(firstActorId, secondActorId, actors);
        if (changeProbability == RelationType.NEGATIVE.getRelationAnnotation()) {
            relationMatrix.set(firstActorId, secondActorId, RelationType.NEGATIVE.getRelationAnnotation());
        }
        if (changeProbability == RelationType.POSITIVE.getRelationAnnotation()){
            relationMatrix.set(firstActorId, secondActorId, RelationType.POSITIVE.getRelationAnnotation());
        }
    }

    private int getProbability(int firstActorId, int secondActorId, List<Actor> actors) {
        int xim = relationMatrix.get(firstActorId, actors.get(0).getActorId());
        int xjm = relationMatrix.get(secondActorId, actors.get(0).getActorId());
        int xin = relationMatrix.get(firstActorId, actors.get(1).getActorId());
        int xjn = relationMatrix.get(secondActorId, actors.get(1).getActorId());
        return Integer.signum(xim * xjm + xin * xjn);
    }

    private List<Actor> getNeighbouringActors(List<Relation> currentRelationList, int firstActorId, int secondActorId) {
        Relation baseRelation = new Relation(new Actor(firstActorId), new Actor(secondActorId));
        Set<Triad> triads = TriadExtractor.extract(baseRelation, currentRelationList);
        Set<Actor> distinctActors = new HashSet<>();
        triads.stream().map(Triad::getTriadActors).distinct().forEach(distinctActors::addAll);
        return distinctActors.stream()
                .filter(actor -> !isRelationActor(new Actor(firstActorId), new Actor(secondActorId), actor))
                .toList();
    }

    private boolean isRelationActor(Actor firstActor, Actor secondActor, Actor actor) {
        return actor.equals(firstActor) || actor.equals(secondActor);
    }

    private void changeRelation(SimulationActorIds actorIds) {
        int firstActorId = actorIds.firstActorId();
        int secondActorId = actorIds.secondActorId();
        double changeProbability = getChangeProbability(firstActorId, secondActorId);
        double randomNumber = new Random().nextDouble();
        if (randomNumber < changeProbability) {
            relationMatrix.set(firstActorId, secondActorId, RelationType.POSITIVE.getRelationAnnotation());
        } else {
            relationMatrix.set(firstActorId, secondActorId, RelationType.NEGATIVE.getRelationAnnotation());
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
