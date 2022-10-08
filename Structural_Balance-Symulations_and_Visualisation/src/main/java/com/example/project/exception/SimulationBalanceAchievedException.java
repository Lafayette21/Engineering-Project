package com.example.project.exception;

public class SimulationBalanceAchievedException extends RuntimeException {
    public SimulationBalanceAchievedException() {
        super("Simulation Balance achieved at {}");
    }
}
