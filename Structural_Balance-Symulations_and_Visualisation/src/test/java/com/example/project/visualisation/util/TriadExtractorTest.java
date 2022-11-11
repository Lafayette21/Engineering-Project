package com.example.project.visualisation.util;

import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.Triad;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TriadExtractorTest {
    @Test
    public void shouldExtractTriads(){
        Relation baseRelation = new Relation(new Actor(1), new Actor(2));
        Relation relation1 = new Relation(new Actor(1), new Actor(3));
        Relation relation2 = new Relation(new Actor(2), new Actor(3));
        Relation relation3 = new Relation(new Actor(1), new Actor(4));
        Relation relation4 = new Relation(new Actor(2), new Actor(4));
        Relation relation5 = new Relation(new Actor(1), new Actor(5));
        List<Relation> neighbouringRelations = List.of(relation1, relation2, relation3, relation4, relation5);

        Set<Triad> actualTriadSet = TriadExtractor.extract(baseRelation, neighbouringRelations);
        Set<Triad> expectedTriadSet = Set.of(
                new Triad(baseRelation, relation3, relation4),
                new Triad(baseRelation, relation1, relation2)
        );
        //TODO fix test
        assertThat(actualTriadSet).isEqualTo(expectedTriadSet);
    }
}