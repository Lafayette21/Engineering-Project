package com.example.project.visualisation.model;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TriadTest {
    @Test
    public void shouldGetTriadTypeForTriad() {
        Relation relation1 = createRelationWithType(RelationType.POSITIVE);
        Relation relation2 = createRelationWithType(RelationType.NEGATIVE);
        Relation relation3 = createRelationWithType(RelationType.POSITIVE);
        Triad triad = new Triad(relation1, relation2, relation3);

        TriadType actualTriadType = triad.getTypeLevel();

        assertThat(actualTriadType).isEqualTo(TriadType.HALF_NEGATIVE);
    }

    @Test
    public void shouldGetNoneTriadTypeForTriad() {
        Relation relation1 = createRelationWithType(RelationType.POSITIVE);
        Relation relation2 = createRelationWithType(RelationType.NONE);
        Relation relation3 = createRelationWithType(RelationType.POSITIVE);
        Triad triad = new Triad(relation1, relation2, relation3);

        TriadType actualTriadType = triad.getTypeLevel();

        assertThat(actualTriadType).isEqualTo(TriadType.NONE);
    }

    private Relation createRelationWithType(RelationType relationType) {
        Relation relation = mock(Relation.class);
        when(relation.getRelationType()).thenReturn(relationType);
        return relation;
    }

    @Test
    public void shouldReturnTrueWhenTriadsAreEqual() {
        Triad triad1 = new Triad(createRelationWithActors(1, 2), createRelationWithActors(1, 3), createRelationWithActors(2, 3));
        Triad triad2 = new Triad(createRelationWithActors(1, 3), createRelationWithActors(2, 3), createRelationWithActors(1, 2));
        Triad triad3 = new Triad(createRelationWithActors(2, 3), createRelationWithActors(1, 2), createRelationWithActors(1, 3));

        assertThat(triad1.equals(triad2)).isTrue();
        assertThat(triad1.equals(triad3)).isTrue();
    }

    private Relation createRelationWithActors(int firstActorId, int secondActorId) {
        return new Relation(new Actor(firstActorId), new Actor(secondActorId));
    }
}