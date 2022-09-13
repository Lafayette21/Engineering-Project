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
        List<Integer> sameRowNeighbours = getSameRowNeighbours(actor.getActorId());
        List<Integer> upperRowNeighbours = getUpperRowNeighbours(actor.getActorId());
        return List.of();
    }

    private List<Integer> getSameRowNeighbours(int actorId) {
        ArrayList<Integer> sameRowNeighbours = new ArrayList<>();
        int leftNeighbourId = actorId - 1;
        int rightNeighbourId = actorId + 1;
        if (isLeftNeighbour(leftNeighbourId)) {
            sameRowNeighbours.add(leftNeighbourId);
        }
        if (isRightNeighbour(rightNeighbourId)) {
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
        if (isLeftNeighbour(leftUpperNeighbourId)) {
            neighboursList.add(leftUpperNeighbourId);
        }
        if (isRightNeighbour(rightUpperNeighbourId)) {
            neighboursList.add(rightUpperNeighbourId);
        }
        neighboursList.add(upperNeighbourId);
        return neighboursList;
    }

    private boolean isRightNeighbour(int rightUpperNeighbourId) {
        return rightUpperNeighbourId < columnNumber;
    }

    private boolean isLeftNeighbour(int leftUpperNeighbourId) {
        return leftUpperNeighbourId > 0;
    }

}
