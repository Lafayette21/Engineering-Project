package com.example.project.visualisation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NeighbourGetter {
    private final int columnNumber;
    private final int rowNumber;

    public NeighbourGetter(int columnNumber, int rowNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }

    public List<Integer> getNeighbours(Actor actor) {
        Integer actorId = actor.getActorId();

        ArrayList<Integer> neighboursList = new ArrayList<>();
        neighboursList.addAll(getSameRowNeighbours(actorId));
        neighboursList.addAll(getUpperRowNeighbours(actorId));
        neighboursList.addAll(getLowerRowNeighbours(actorId));
        return neighboursList.stream().sorted(Integer::compareTo).collect(Collectors.toList());
    }

    private List<Integer> getSameRowNeighbours(int actorId) {
        ArrayList<Integer> sameRowNeighbours = new ArrayList<>();
        int leftNeighbourId = actorId - 1;
        int rightNeighbourId = actorId + 1;
        if (existsLeftNeighbour(leftNeighbourId)) {
            sameRowNeighbours.add(leftNeighbourId);
        }
        if (existsRightNeighbour(rightNeighbourId)) {
            sameRowNeighbours.add(rightNeighbourId);
        }
        return Collections.unmodifiableList(sameRowNeighbours);
    }

    private List<Integer> getUpperRowNeighbours(int actorId) {
        int upperNeighbourId = actorId - columnNumber;
        if (upperRowExists(upperNeighbourId)) {
            return getNeighboursFromUpperRow(upperNeighbourId);
        }
        return Collections.emptyList();
    }

    private boolean upperRowExists(int upperNeighbourId) {
        return upperNeighbourId > 0;
    }

    private List<Integer> getNeighboursFromUpperRow(int upperNeighbourId) {
        ArrayList<Integer> neighboursList = new ArrayList<>();
        int leftUpperNeighbourId = upperNeighbourId - 1;
        int rightUpperNeighbourId = upperNeighbourId + 1;
        if (existsLeftNeighbour(leftUpperNeighbourId)) {
            neighboursList.add(leftUpperNeighbourId);
        }
        if (existsRightNeighbour(rightUpperNeighbourId)) {
            neighboursList.add(rightUpperNeighbourId);
        }
        neighboursList.add(upperNeighbourId);
        return neighboursList;
    }

    private List<Integer> getLowerRowNeighbours(int actorId) {
        int lowerNeighbourId = actorId + columnNumber;
        if (lowerRowExists(lowerNeighbourId)) {
            return getNeighboursFromLowerRow(lowerNeighbourId);
        }
        return Collections.emptyList();
    }

    private boolean lowerRowExists(int lowerNeighbourId) {
        return lowerNeighbourId <= columnNumber * rowNumber;
    }

    private List<Integer> getNeighboursFromLowerRow(int lowerNeighbourId) {
        ArrayList<Integer> neighboursList = new ArrayList<>();
        int leftLowerNeighbourId = lowerNeighbourId - 1;
        int rightLowerNeighbourId = lowerNeighbourId + 1;
        if (existsLeftNeighbour(leftLowerNeighbourId)) {
            neighboursList.add(leftLowerNeighbourId);
        }
        if (existsRightNeighbour(rightLowerNeighbourId)) {
            neighboursList.add(rightLowerNeighbourId);
        }
        neighboursList.add(lowerNeighbourId);
        return neighboursList;

    }

    private boolean existsLeftNeighbour(int leftNeighbourId) {
        return leftNeighbourId % columnNumber > 0;
    }

    private boolean existsRightNeighbour(int rightNeighbourId) {
        return (rightNeighbourId - 1) % columnNumber > 0;
    }
}
