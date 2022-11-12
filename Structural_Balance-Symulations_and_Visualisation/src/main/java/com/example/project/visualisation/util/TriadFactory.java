package com.example.project.visualisation.util;

import com.example.project.exception.InstantiationNotAllowedException;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.Triad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TriadFactory {
    private TriadFactory() {
        throw new InstantiationNotAllowedException();
    }
    //TODO if it is needed add removing redundant reletions (does not affect the state of colorizing)
    public static Set<Triad> createTriads(List<Relation> relationList){
        List<Triad> triads = new ArrayList<>();
        for (Relation relation : relationList){
            List<Relation> neighbouringRelations = NeighbouringRelationsGetter.get(relation, relationList);
            Set<Triad> triadSet = TriadExtractor.extract(relation, neighbouringRelations);
            triads.addAll(triadSet);
        }
        return new HashSet<>(triads);
    }
}
