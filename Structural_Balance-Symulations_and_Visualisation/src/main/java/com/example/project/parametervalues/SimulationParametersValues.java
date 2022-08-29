package com.example.project.parametervalues;

public record SimulationParametersValues(int numberOfSteps, double annealingValue)
        implements ParameterValue {}
