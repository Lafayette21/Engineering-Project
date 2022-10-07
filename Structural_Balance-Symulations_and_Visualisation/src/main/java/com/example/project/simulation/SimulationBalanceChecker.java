package com.example.project.simulation;

public class SimulationBalanceChecker {
    private static final int BALANCE_ANNOTATION = -1;

    private static ConnectionMatrix connectionMatrix;
    private static RelationMatrix relationMatrix;

    private SimulationBalanceChecker() {
        throw new RuntimeException("Class SimulationBalanceChecker cannot be instantiated");
    }

    public static boolean check(ConnectionMatrix connMatrix, RelationMatrix relMatrix) {
        setStaticParameters(connMatrix, relMatrix);
        double factor = calculateBalanceFactor();

        return isBalanced(factor);
    }

    private static void setStaticParameters(ConnectionMatrix connMatrix, RelationMatrix relMatrix) {
        connectionMatrix = connMatrix;
        relationMatrix = relMatrix;
    }

    private static double calculateBalanceFactor() {
        double sum = 0;
        double delta = 0;
        for (int i = 0; i < connectionMatrix.getNumberOfExistingRelations(); i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    sum += calculateStepSum(i, j, k);
                    delta += calculateStepDelta(i, j, k);
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

    private static boolean isBalanced(double factor) {
        return factor == BALANCE_ANNOTATION;
    }
}
