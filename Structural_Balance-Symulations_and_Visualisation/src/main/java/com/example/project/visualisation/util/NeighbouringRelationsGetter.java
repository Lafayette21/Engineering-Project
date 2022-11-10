package com.example.project.visualisation.util;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Relation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NeighbouringRelationsGetter {
    private NeighbouringRelationsGetter() {
        throw new InstantiationNotAllowedException();
    }

    public static Set<Relation> get(Relation relation, List<Relation> relationList) {
        return relationList.stream()
                .filter(relation1 -> relation1.isNeighbouringRelation(relation))
                .collect(Collectors.toSet());
    }


}
