package com.example.project.visualisation.util;

import com.example.project.database.model.ActorParameters;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Position;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ActorFactoryTest {
    private static final int DISTANCE_X = 100;
    private static final int DISTANCE_Y = 100;
    private static final int NUMBER_OF_ROWS = 4;
    private static final int NUMBER_OF_COLUMNS = 4;

    @Test
    public void shouldCreateActors() {
        ActorParameters actorValues = new ActorParameters(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        CanvasPointsDistance pointsDistance = new CanvasPointsDistance(DISTANCE_X, DISTANCE_Y);

        List<Actor> actualActorsList = ActorFactory.createActors(actorValues, pointsDistance);

        assertThat(actualActorsList.size()).isEqualTo(16);
        assertActorsPositions(actualActorsList);
    }

    private void assertActorsPositions(List<Actor> actualActorsList) {
        assertPoint(actualActorsList, 1, new Position(100, 100));
        assertPoint(actualActorsList, 5, new Position(100, 200));
        assertPoint(actualActorsList, 12, new Position(400, 300));
        assertPoint(actualActorsList, actualActorsList.size(), new Position(400, 400));
    }

    private void assertPoint(List<Actor> actualActorsList, int actorId, Position expectedPosition) {
        Position actualPosition = actualActorsList.get(actorId - 1).getPosition();
        assertThat(actualPosition).isEqualTo(expectedPosition);
    }


}