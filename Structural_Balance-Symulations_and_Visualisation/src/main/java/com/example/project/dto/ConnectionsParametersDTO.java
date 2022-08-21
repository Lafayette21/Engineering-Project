package com.example.project.dto;

public class ConnectionsParametersDTO implements DTO {
    double connectionCreationPercentRatio;
    double positiveToNegativePercentRatio;

    public ConnectionsParametersDTO(double connectionCreationPercentRatio, double positiveToNegativePercentRatio) {
        this.connectionCreationPercentRatio = connectionCreationPercentRatio;
        this.positiveToNegativePercentRatio = positiveToNegativePercentRatio;
    }

    public double getConnectionCreationPercentRatio() {
        return connectionCreationPercentRatio;
    }

    public double getPositiveToNegativePercentRatio() {
        return positiveToNegativePercentRatio;
    }
}
