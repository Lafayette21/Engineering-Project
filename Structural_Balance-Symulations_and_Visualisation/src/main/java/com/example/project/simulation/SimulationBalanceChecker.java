package com.example.project.simulation;

import com.example.project.exception.InstantiationNotAllowedException;

public class SimulationBalanceChecker {
    private static final int BALANCE_ANNOTATION = -1;

    private SimulationBalanceChecker() {
        throw new InstantiationNotAllowedException();
    }

    public static boolean check(ConnectionMatrix connMatrix, RelationMatrix relMatrix) {
        double factor = EnergyCalculator.calculate(connMatrix,relMatrix);

        return isBalanced(factor);
    }

    private static boolean isBalanced(double factor) {
        return factor == BALANCE_ANNOTATION;
    }
}
