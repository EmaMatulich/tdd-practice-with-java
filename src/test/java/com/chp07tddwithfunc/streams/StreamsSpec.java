package com.chp07tddwithfunc.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StreamsSpec {

    @Test
    public void filterByNameReturnsCollectionFiltered(){
        List<String> names = Arrays.asList("Alex", "Paul", "Viktor", "Kobe","Tom","Andrea");

        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        assertThat(filteredNames)
                .hasSize(2)
                .containsExactlyInAnyOrder("Alex", "Andrea");
    }

    @Test
    public void mapToUppercaseTransformAllElementsToUppercase(){
        List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
        List<String> namesToUppercase =
                names.stream().map(String::toUpperCase).collect(Collectors.toList());
        assertThat(namesToUppercase).hasSize(3).containsExactly("ALEX","PAUL","VIKTOR");
    }

    @Test
    public void gettingLettersUsedInNames(){
        List<String> names = Arrays.asList("Alex", "Paul");

        List<String> letterUsed =
                names.stream()
                        .map(String::toLowerCase)
                        .flatMap(name -> Stream.of(name.split("")))
                        .distinct().collect(Collectors.toList());

        assertThat(letterUsed).hasSize(6).containsExactly("a","l","e","x","p","u");
    }

    @Test
    public void countingLettersUsedInNames(){
        List<String> names = Arrays.asList("Alex", "Paul");
        long count = names.stream()
                .map(String::toLowerCase)
                .flatMap(name -> Stream.of(name.split("")))
                .distinct().mapToLong(l -> 1L).reduce(0L, (v1,v2) -> v1 + v2);
        assertThat(count).isEqualTo(6);
    }

    @Test
    public void countingLettersUsedInNamesWithCount(){
        List<String> names = Arrays.asList("Alex", "Paul");
        long count = names.stream()
                .map(String::toLowerCase)
                .flatMap(name -> Stream.of(name.split("")))
                .distinct().count();
        assertThat(count).isEqualTo(6);
    }
}
