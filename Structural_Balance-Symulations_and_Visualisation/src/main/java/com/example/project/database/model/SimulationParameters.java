package com.example.project.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "simulation_parameters")
public class SimulationParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer simulationParametersId;
    @Column(name = "number_of_steps")
    private int numberOfSteps;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "time")
    private int time;

    public SimulationParameters() {
    }

    public SimulationParameters(int numberOfSteps, double temperature, int time) {
        this.numberOfSteps = numberOfSteps;
        this.temperature = temperature;
        this.time = time;
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
