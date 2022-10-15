package com.example.project.simulation;

import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.database.model.Actor;
import com.example.project.database.model.Relation;

import java.util.List;

public record SimulationRequiredValuesDTO(List<Actor> actorList, List<Relation> relationList,
                                          SimulationParametersValues simulationParameters) {
}
