package com.example.project.dto;

public class ConnectionsParametersDTO implements DTO {
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
