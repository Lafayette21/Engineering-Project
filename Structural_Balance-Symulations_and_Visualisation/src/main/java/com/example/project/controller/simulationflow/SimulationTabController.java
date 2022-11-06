package com.example.project.controller.simulationflow;

import com.example.project.database.model.SimulationParameters;
import com.example.project.simulation.SimulationFlow;

public interface SimulationTabController {
    void prepareInitial(SimulationFlow simulationFlow);
    void nextSimulationStep(SimulationParameters simulationParameters);

    void previousSimulationStep(SimulationParameters simulationParameters);

    void skipToEnd(SimulationParameters simulationParameters);
}
