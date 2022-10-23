package com.example.project.database.repository;

import com.example.project.database.model.ActorParameters;
import com.example.project.database.model.ConnectionParameters;
import jakarta.persistence.Query;

public class ConnectionParametersRepository extends ParameterRepository {
    public ConnectionParametersRepository() {
        super();
    }

    public void registerConnectionParameters(ConnectionParameters parameters) {
        entityTransaction.begin();
        entityManager.persist(parameters);
        entityTransaction.commit();
    }

    public ConnectionParameters getConnectionParameters() {
        Query query = entityManager.createQuery("select cp from ConnectionParameters cp where cp.connectionParameterId ="
                + SINGLE_ELEMENT_ID);
        return (ConnectionParameters) query.getSingleResult();
    }

    public void updateConnectionExistencePercentage(Integer newConnectionExistencePercentage) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update ConnectionParameters set connectionExistencePercentage = "
                + newConnectionExistencePercentage + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void updatePositiveConnectionsPercentage(Integer newPositiveConnectionPercentage) {
        entityTransaction.begin();
        Query query = entityManager.createQuery("update ConnectionParameters set positiveConnectionsPercentage = "
                + newPositiveConnectionPercentage + " where id = " + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
