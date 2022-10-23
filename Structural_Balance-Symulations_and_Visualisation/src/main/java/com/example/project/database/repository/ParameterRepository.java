package com.example.project.database.repository;

import jakarta.persistence.*;

public abstract class ParameterRepository {
    protected static final Integer SINGLE_ELEMENT_ID = 1;

    protected EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;

    public ParameterRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("simulation");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }
}
