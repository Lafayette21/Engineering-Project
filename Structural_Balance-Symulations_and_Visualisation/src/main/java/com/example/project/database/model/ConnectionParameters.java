package com.example.project.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "connection_parameters")
public class ConnectionParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer connectionParameterId;
    @Column(name = "connection_existence_percentage")
    private int connectionExistencePercentage;
    @Column(name = "positive_connections_percentage")
    private int positiveConnectionsPercentage;

    public ConnectionParameters() {
    }

    public ConnectionParameters(int connectionExistencePercentage, int positiveConnectionsPercentage) {
        this.connectionExistencePercentage = connectionExistencePercentage;
        this.positiveConnectionsPercentage = positiveConnectionsPercentage;
    }

    public Integer getConnectionParameterId() {
        return connectionParameterId;
    }

    public void setConnectionParameterId(Integer connectionParameterId) {
        this.connectionParameterId = connectionParameterId;
    }

    public int getConnectionExistencePercentage() {
        return connectionExistencePercentage;
    }

    public void setConnectionExistencePercentage(int connectionExistencePercentage) {
        this.connectionExistencePercentage = connectionExistencePercentage;
    }

    public int getPositiveConnectionsPercentage() {
        return positiveConnectionsPercentage;
    }

    public void setPositiveConnectionsPercentage(int positiveConnectionsPercentage) {
        this.positiveConnectionsPercentage = positiveConnectionsPercentage;
    }
}
