package com.example.project.simulation;

import com.example.project.parametervalues.SimulationParametersValues;
import com.example.project.visualisation.model.Relation;

import java.util.List;

public record SimulationRequiredValuesDTO(List<Relation> relationList,
                                          SimulationParametersValues simulationParameters) {
}
