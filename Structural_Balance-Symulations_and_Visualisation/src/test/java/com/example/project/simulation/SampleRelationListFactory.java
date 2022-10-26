package com.example.project.simulation;

import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.RelationType;

import java.util.List;

public class SampleRelationListFactory {
    private SampleRelationListFactory() {
        throw new RuntimeException("Class SampleRelationListFactory cannot be instantiated");
    }

    public static List<Relation> create() {
        return List.of(
                new Relation(new Actor(1), new Actor(2), RelationType.NEGATIVE),
                new Relation(new Actor(2), new Actor(3), RelationType.NONE),
                new Relation(new Actor(4), new Actor(5), RelationType.POSITIVE),
                new Relation(new Actor(5), new Actor(6), RelationType.NEGATIVE),
                new Relation(new Actor(2), new Actor(4), RelationType.NONE),
                new Relation(new Actor(3), new Actor(5), RelationType.POSITIVE),
                new Relation(new Actor(1), new Actor(4), RelationType.POSITIVE),
                new Relation(new Actor(2), new Actor(5), RelationType.NONE),
                new Relation(new Actor(3), new Actor(6), RelationType.NEGATIVE)
        );
    }
}
