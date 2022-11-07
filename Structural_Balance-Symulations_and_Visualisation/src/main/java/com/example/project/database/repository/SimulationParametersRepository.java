package com.example.project.database.repository;

import com.example.project.database.model.SimulationParameters;
import jakarta.persistence.Query;

public class SimulationParametersRepository extends ParameterRepository{
    public SimulationParametersRepository() {
        super();
    }

    public void registerSimulationParameters(SimulationParameters simulationParameters){
        entityTransaction.begin();
        entityManager.persist(simulationParameters);
        entityTransaction.commit();
    }

    public SimulationParameters getSimulationParameters() {
        Query query = entityManager.createQuery("select sp from SimulationParameters sp where sp.simulationParametersId ="
                + SINGLE_ELEMENT_ID);
        return (SimulationParameters) query.getSingleResult();
    }

    public void updateNumberOfSteps(Integer newNumberOfSteps) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update SimulationParameters set numberOfSteps = "
                + newNumberOfSteps + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void updateTemperature(Double newTemperature) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update SimulationParameters set temperature = "
                + newTemperature + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void updateTime(Integer newTime) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update SimulationParameters set time = "
                + newTime + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
