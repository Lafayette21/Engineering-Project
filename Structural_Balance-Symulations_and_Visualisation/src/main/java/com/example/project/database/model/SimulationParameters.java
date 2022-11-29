package com.example.project.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "simulation_parameters")
public class SimulationParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer simulationParametersId;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "time")
    private int time;

    public SimulationParameters() {
    }

    public SimulationParameters(double temperature, int time) {
        this.temperature = temperature;
        this.time = time;
    }

    public Integer getSimulationParametersId() {
        return simulationParametersId;
    }

    public void setSimulationParametersId(Integer simulationParametersId) {
        this.simulationParametersId = simulationParametersId;
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
