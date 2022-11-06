package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;

public interface TabController {
    void nextSimulationStep(SimulationParameters simulationParameters);

    void previousSimulationStep(SimulationParameters simulationParameters);

    void skipToEnd(SimulationParameters simulationParameters);
}
