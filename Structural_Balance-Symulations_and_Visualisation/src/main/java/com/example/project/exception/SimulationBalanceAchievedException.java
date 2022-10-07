package com.example.project.exception;

import java.text.MessageFormat;

public class SimulationBalanceAchievedException extends RuntimeException {
    public SimulationBalanceAchievedException(Integer stepNumber) {
        super(MessageFormat.format("Simulation Balance achieved at {}", stepNumber));
    }
}
