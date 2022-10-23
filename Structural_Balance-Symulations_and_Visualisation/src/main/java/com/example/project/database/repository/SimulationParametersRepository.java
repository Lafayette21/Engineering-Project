package com.example.project.database.repository;

import com.example.project.database.model.SimulationParameters;
import jakarta.persistence.Query;

public class SimulationParametersRepository extends ParameterRepository{
    public SimulationParametersRepository() {
        super();
    }

    public void registerSimulationParameters(SimulationParameters parameters) {
        entityTransaction.begin();
        entityManager.persist(parameters);
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

    public void updateAnnealingParameter(Double newAnnealingParameter) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update SimulationParameters set annealingParameter = "
                + newAnnealingParameter + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
