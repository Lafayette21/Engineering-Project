package com.example.project.visualisation.util;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.Triad;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class TriadExtractor {
    private TriadExtractor() {
        throw new InstantiationNotAllowedException();
    }

    public static Set<Triad> extract(Relation baseRelation, List<Relation> neighbouringRelations) {
        Set<Triad> triads = new HashSet<>();
        for (int i = 0; i < neighbouringRelations.size(); i++) {
            for (int j = 0; j < i; j++) {
                Set<Actor> actorSet = new HashSet<>();
                Relation relation1 = neighbouringRelations.get(i);
                Relation relation2 = neighbouringRelations.get(j);
                Stream.of(baseRelation, relation1, relation2)
                        .map(Relation::getRelationActors)
                        .forEach(actorSet::addAll);
                if (isTriad(actorSet)) {
                    triads.add(new Triad(baseRelation, relation1, relation2));
                }
            }
        }
        return triads;
    }

    private static boolean isTriad(Set<Actor> actorSet) {
        return actorSet.size() == 3;
    }
}
