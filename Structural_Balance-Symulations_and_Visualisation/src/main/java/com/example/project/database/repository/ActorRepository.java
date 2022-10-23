package com.example.project.database.repository;

import com.example.project.database.model.Actor;
import com.example.project.database.model.Position;
import jakarta.persistence.Query;

public class ActorRepository extends EntityRepository {
    public ActorRepository() {
        super();
    }

    public void registerActor(Actor actor) {
        entityTransaction.begin();
        entityManager.persist(actor);
        entityTransaction.commit();
    }

    public Actor getActorById(Integer actorId) {
        Query query = entityManager
                .createQuery("select a from Actor a where a.actorId =" + actorId);
        return (Actor) query.getSingleResult();
    }

    public void addPositionToActor(Integer actorId, Position position) {
        entityTransaction.begin();
        Actor actor = getActorById(actorId);
        actor.setPosition(position);
        entityTransaction.commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
