package com.example.project.database.repository;

import com.example.project.database.model.Position;
import jakarta.persistence.Query;

public class PositionRepository extends EntityRepository {
    public PositionRepository() {
        super();
    }

    public void registerPosition(Position position) {
        entityTransaction.begin();
        entityManager.persist(position);
        entityTransaction.commit();
    }

    public Position getPositionById(Integer positionId) {
        Query query = entityManager
                .createQuery("select p from Position p where p.positionId =" + positionId);
        return (Position) query.getSingleResult();
    }
}
