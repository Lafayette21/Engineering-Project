package com.example.project.database.repository;

import jakarta.persistence.*;

public abstract class EntityRepository {
    protected EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    protected EntityManager entityManager;
    protected EntityTransaction entityTransaction;

    public EntityRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("simulation");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }
}
