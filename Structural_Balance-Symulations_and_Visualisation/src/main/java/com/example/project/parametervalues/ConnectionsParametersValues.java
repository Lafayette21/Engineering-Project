package com.example.project.parametervalues;

public class ConnectionsParametersValues implements ParameterValue {
    int connectionCreationPercentRatio;
    int positiveToNegativePercentRatio;

    public int getConnectionCreationPercentRatio() {
        return connectionCreationPercentRatio;
    }

    public void setConnectionCreationPercentRatio(int connectionCreationPercentRatio) {
        this.connectionCreationPercentRatio = connectionCreationPercentRatio;
    }

    public int getPositiveToNegativePercentRatio() {
        return positiveToNegativePercentRatio;
    }

    public void setPositiveToNegativePercentRatio(int positiveToNegativePercentRatio) {
        this.positiveToNegativePercentRatio = positiveToNegativePercentRatio;
    }
}
