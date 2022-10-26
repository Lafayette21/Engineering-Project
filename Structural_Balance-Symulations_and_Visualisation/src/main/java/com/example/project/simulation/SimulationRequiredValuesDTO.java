package com.example.project.simulation;

import com.example.project.database.model.SimulationParameters;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;

import java.util.List;

public record SimulationRequiredValuesDTO(List<Actor> actorList, List<Relation> relationList,
                                          SimulationParameters simulationParameters) {
}
