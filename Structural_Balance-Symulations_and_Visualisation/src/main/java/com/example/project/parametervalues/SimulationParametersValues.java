package com.example.project.parametervalues;

public class SimulationParametersValues implements ParameterValue {
    private int numberOfSteps;
    private double annealingValue;

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public double getAnnealingValue() {
        return annealingValue;
    }

    public void setAnnealingValue(double annealingValue) {
        this.annealingValue = annealingValue;
    }
}
