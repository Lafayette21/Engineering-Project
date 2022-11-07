package com.example.project.simulation;

import com.example.project.exception.InstantiationNotAllowedException;

public class EnergyCalculator {
    private static ConnectionMatrix connectionMatrix;
    private static RelationMatrix relationMatrix;

    private EnergyCalculator() {
        throw new InstantiationNotAllowedException();
    }

    public static double calculate(ConnectionMatrix connMatrix, RelationMatrix relMatrix){
        setMatrices(connMatrix, relMatrix);
        return calculateEnergy();
    }

    private static void setMatrices(ConnectionMatrix connMatrix, RelationMatrix relMatrix) {
        connectionMatrix = connMatrix;
        relationMatrix = relMatrix;
    }

    private static double calculateEnergy() {
        double sum = 0;
        double delta = 0;
        for (int i = 0; i < connectionMatrix.getNumberOfActors(); i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    sum += calculateStepSum(i + 1, j + 1, k + 1);
                    delta += calculateStepDelta(i + 1, j + 1, k + 1);
                }
            }
        }
        return -(sum / delta);
    }

    private static int calculateStepSum(int i, int j, int k) {
        return connectionMatrix.get(i, j) *
                relationMatrix.get(i, j) *
                connectionMatrix.get(j, k) *
                relationMatrix.get(j, k) *
                connectionMatrix.get(k, i) *
                relationMatrix.get(k, i);
    }

    private static int calculateStepDelta(int i, int j, int k) {
        return connectionMatrix.get(i, j) *
                connectionMatrix.get(j, k) *
                connectionMatrix.get(k, i);
    }
}
