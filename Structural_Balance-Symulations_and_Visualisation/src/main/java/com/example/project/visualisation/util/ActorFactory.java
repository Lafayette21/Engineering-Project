package com.example.project.visualisation.util;

import com.example.project.database.model.ActorParameters;
import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Position;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActorFactory {
    private static List<Actor> actorList;
    private static int numberOfColumns;
    private static int numberOfRows;


    private ActorFactory() {
        throw new InstantiationNotAllowedException();
    }

    public static List<Actor> createActors(ActorParameters actorParameters,
                                           CanvasPointsDistance canvasPointsDistance){
        setNumberOfRowsAndColumns(actorParameters);
        createActors();
        setActorsPositions(canvasPointsDistance);
        return actorList;
    }

    private static void setNumberOfRowsAndColumns(ActorParameters actorParameters) {
        numberOfRows = actorParameters.getNumberOfRows();
        numberOfColumns = actorParameters.getNumberOfColumns();
    }

    private static void createActors() {
        int numberOfActors = numberOfRows * numberOfColumns;
        actorList = IntStream.range(1, numberOfActors + 1)
                .mapToObj(Actor::new).collect(Collectors.toList());
    }

    private static void setActorsPositions(CanvasPointsDistance canvasPointsDistance) {
        double distanceX = canvasPointsDistance.distanceX();
        double distanceY = canvasPointsDistance.distanceY();

        Iterator<Actor> actorIterator = actorList.iterator();
        for (int i = 1; i <= numberOfRows; i++) {
            for (int j = 1; j <= numberOfColumns; j++) {
                Actor actor = actorIterator.next();
                actor.setPosition(new Position(distanceX * j, distanceY * i));
            }
        }
    }
}
