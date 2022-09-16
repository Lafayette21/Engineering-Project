package com.example.project.visualisation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeighbourGetter {
    private final int columnNumber;
    private final int rowNumber;

    public NeighbourGetter(int columnNumber, int rowNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }

    public List<Integer> getNeighbours(Actor actor) {
        ArrayList<Integer> neighboursList = new ArrayList<>();
        neighboursList.addAll(getSameRowNeighbours(actor.getActorId()));
        neighboursList.addAll(getUpperRowNeighbours(actor.getActorId()));
        neighboursList.addAll(getLowerRowNeighbours(actor.getActorId()));
        return neighboursList;
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
        return upperNeighbourId < 0;
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
        return lowerNeighbourId < columnNumber * rowNumber;
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

    private boolean existsLeftNeighbour(int leftUpperNeighbourId) {
        return leftUpperNeighbourId > 0;
    }

    private boolean existsRightNeighbour(int rightUpperNeighbourId) {
        return rightUpperNeighbourId < columnNumber;
    }
}
