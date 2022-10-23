package com.example.project.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "simulation_parameterss")
public class SimulationParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer simulationParametersId;
    @Column(name = "number_of_steps")
    private int numberOfSteps;
    @Column(name = "annealing_Parameter")
    private int annealingParameter;

    public SimulationParameters() {
    }

    public SimulationParameters(int numberOfSteps, int annealingParameter) {
        this.numberOfSteps = numberOfSteps;
        this.annealingParameter = annealingParameter;
    }

    public Integer getSimulationParametersId() {
        return simulationParametersId;
    }

    public void setSimulationParametersId(Integer simulationParametersId) {
        this.simulationParametersId = simulationParametersId;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getAnnealingParameter() {
        return annealingParameter;
    }

    public void setAnnealingParameter(int annealingParameter) {
        this.annealingParameter = annealingParameter;
    }
}
