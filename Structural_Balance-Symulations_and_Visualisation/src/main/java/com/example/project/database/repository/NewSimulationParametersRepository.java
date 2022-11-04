package com.example.project.database.repository;

import com.example.project.database.model.NewSimulationParameters;
import com.example.project.database.model.SimulationParameters;
import jakarta.persistence.Query;

public class NewSimulationParametersRepository extends ParameterRepository{
    public NewSimulationParametersRepository() {
        super();
    }

    public void registerSimulationParameters(NewSimulationParameters simulationParameters){
        entityTransaction.begin();
        entityManager.persist(simulationParameters);
        entityTransaction.commit();
    }

    public SimulationParameters getSimulationParameters() {
        Query query = entityManager.createQuery("select sp from NewSimulationParameters sp where sp.simulationParametersId ="
                + SINGLE_ELEMENT_ID);
        return (SimulationParameters) query.getSingleResult();
    }

    public void updateNumberOfSteps(Integer newNumberOfSteps) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update NewSimulationParameters set numberOfSteps = "
                + newNumberOfSteps + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void updateTemperature(Double newTemperature) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update NewSimulationParameters set temperature = "
                + newTemperature + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void updateTime(Integer newTime) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update NewSimulationParameters set time = "
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
