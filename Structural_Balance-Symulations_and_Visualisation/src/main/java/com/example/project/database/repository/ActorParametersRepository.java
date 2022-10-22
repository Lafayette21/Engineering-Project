package com.example.project.database.repository;

import com.example.project.database.model.ActorParameters;
import jakarta.persistence.Query;

public class ActorParametersRepository extends ParameterRepository {
    public ActorParametersRepository() {
        super();
    }

    public void registerActorParameters(ActorParameters parameters) {
        entityTransaction.begin();
        entityManager.persist(parameters);
        entityTransaction.commit();
    }

    public ActorParameters getActorParameters() {
        Query query = entityManager
                .createQuery("select ap from ActorParameters ap where ap.actorParameterId =" + SINGLE_ELEMENT_ID);
        return (ActorParameters) query.getSingleResult();
    }

    public void updateNumberOfRow(Integer newNumberOfRows) {
        entityTransaction.begin();
        Query query = entityManager
                .createQuery("update ActorParameters set numberOfRows = " + newNumberOfRows + " where id = "
                        + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void updateNumberOfColumns(Integer newNumberOfColumns) {
        entityTransaction.begin();
        Query query = entityManager
                .createQuery("update ActorParameters set numberOfColumns = " + newNumberOfColumns + " where id = "
                        + SINGLE_ELEMENT_ID);
        query.executeUpdate();
        entityTransaction.commit();
        entityManager.clear();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
