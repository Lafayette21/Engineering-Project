package com.example.project.visualisation.util;

import com.example.project.visualisation.model.Actor;
import com.example.project.visualisation.model.Relation;
import com.example.project.visualisation.model.Triad;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TriadExtractorTest {
    @Test
    public void shouldExtractTriads(){
        Relation relation12 = new Relation(new Actor(1), new Actor(2));
        Relation relation13 = new Relation(new Actor(1), new Actor(3));
        Relation relation23 = new Relation(new Actor(2), new Actor(3));
        Relation relation14 = new Relation(new Actor(1), new Actor(4));
        Relation relation24 = new Relation(new Actor(2), new Actor(4));
        Relation relation15 = new Relation(new Actor(1), new Actor(5));
        List<Relation> neighbouringRelations = List.of(relation13, relation23, relation14, relation24, relation15);

        Set<Triad> actualTriadSet = TriadExtractor.extract(relation12, neighbouringRelations);
        Set<Triad> expectedTriadSet = Set.of(
                new Triad(relation12, relation14, relation24),
                new Triad(relation12, relation13, relation23)
        );

      List<Triad> triadList = actualTriadSet.stream().filter(expectedTriadSet::contains).toList();
      assertThat(triadList.size()).isEqualTo(2);
    }
}
