package com.example.project.visualisation.model;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NeighbourGetterTest {
    private static final int ROW_NUMBER = 3;
    private static final int COLUMN_NUMBER = 3;

    NeighbourGetter neighbourGetter = new NeighbourGetter(ROW_NUMBER, COLUMN_NUMBER);

    @Test
    public void shouldReturnListOfNeighboursWhenActorInTheMiddle() {
        int middleActorsId = 5;
        Actor actor = new Actor(middleActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(1, 2, 3, 4, 6, 7, 8, 9);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInUpper() {
        int leftUpperActorsId = 2;
        Actor actor = new Actor(leftUpperActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(1, 3, 4, 5, 6);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInLeftUpper() {
        int leftUpperActorsId = 1;
        Actor actor = new Actor(leftUpperActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(2, 4, 5);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInLeft() {
        int leftActorsId = 4;
        Actor actor = new Actor(leftActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(1, 2, 5, 7, 8);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInLeftBottom() {
        int leftBottomActorId = 7;
        Actor actor = new Actor(leftBottomActorId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(4, 5, 8);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInBottom() {
        int bottomActorId = 8;
        Actor actor = new Actor(bottomActorId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(4, 5, 6, 7, 9);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInRightBottom() {
        int rightBottomActorsId = 9;
        Actor actor = new Actor(rightBottomActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(5, 6, 8);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInRight() {
        int rightActorsId = 6;
        Actor actor = new Actor(rightActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(2, 3, 5, 8, 9);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }

    @Test
    public void shouldReturnListOfNeighboursWhenActorInRightUpper() {
        int rightUpperActorsId = 3;
        Actor actor = new Actor(rightUpperActorsId);

        List<Integer> actualNeighbours = neighbourGetter.getNeighbours(actor);
        List<Integer> expectedNeighbours = List.of(2, 5, 6);

        assertThat(actualNeighbours).isEqualTo(expectedNeighbours);
    }
}