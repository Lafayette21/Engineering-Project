package com.example.project.visualisation.util;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Relation;

import java.util.List;
import java.util.stream.Collectors;

public class NeighbouringRelationsGetter {
    private NeighbouringRelationsGetter() {
        throw new InstantiationNotAllowedException();
    }

    public static List<Relation> get(Relation searchedOutRelation, List<Relation> relationList) {
        return relationList.stream()
                .filter(relation -> relation.isNeighbouringRelation(searchedOutRelation))
                .distinct()
                .collect(Collectors.toList());
    }


}
