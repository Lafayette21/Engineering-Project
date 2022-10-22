package com.example.project.database.repository;

import jakarta.persistence.*;

public abstract class ParameterRepository {
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
