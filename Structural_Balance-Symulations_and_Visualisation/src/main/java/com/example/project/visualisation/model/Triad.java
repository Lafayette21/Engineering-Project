package com.example.project.visualisation.model;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public class Triad {
    private final Relation relation1;
    private final Relation relation2;
    private final Relation relation3;

    public Triad(Relation relation1, Relation relation2, Relation relation3) {
        this.relation1 = relation1;
        this.relation2 = relation2;
        this.relation3 = relation3;
    }

    public TriadType getTypeLevel() {
        if (isTriadType(TriadType.NONE, getRelationAnnotationProduct())) {
            return TriadType.NONE;
        }
        return TriadType.getTriadTypes()
                .filter(triadType -> isTriadType(triadType, getRelationAnnotationSum()))
                .toList().get(0);
    }

    private boolean isTriadType(TriadType triadType, int factor) {
        return triadType.getAnnotation() == factor;
    }

    private int getRelationAnnotationSum() {
        return getRelationAnnotations().stream()
                .reduce(0, Integer::sum);
    }

    private int getRelationAnnotationProduct() {
        return getRelationAnnotations().stream()
                .reduce(1, (a, b) -> a * b);
    }

    private List<Integer> getRelationAnnotations() {
        return getRelations().stream()
                .map(Relation::getRelationType)
                .map(RelationType::getRelationAnnotation)
                .collect(Collectors.toList());
    }

    private List<Relation> getRelations() {
        return ImmutableList.of(relation1, relation2, relation3);
    }

}
