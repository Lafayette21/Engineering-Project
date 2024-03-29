package com.example.project.visualisation.util;

import com.example.project.visualisation.model.Actor;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NeighbourGetter {
    private final int columnNumber;
    private final int rowNumber;

    public NeighbourGetter(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public List<Integer> getNeighbours(Actor actor) {
        int actorId = actor.getActorId();

        List<Integer> neighbourIdList = ImmutableList.<Integer>builder()
                .addAll(getSameRowNeighbours(actorId))
                .addAll(getUpperRowNeighbours(actorId))
                .addAll(getLowerRowNeighbours(actorId))
                .build();
        return neighbourIdList.stream().sorted(Integer::compareTo).collect(Collectors.toList());
    }

    private List<Integer> getSameRowNeighbours(int actorId) {
        List<Integer> sameRowNeighbours = new ArrayList<>();
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
        List<Integer> neighboursList = new ArrayList<>();
        int rightUpperNeighbourId = upperNeighbourId + 1;
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
        List<Integer> neighboursList = new ArrayList<>();
        int leftLowerNeighbourId = lowerNeighbourId - 1;
        if (existsLeftNeighbour(leftLowerNeighbourId)) {
            neighboursList.add(leftLowerNeighbourId);
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
