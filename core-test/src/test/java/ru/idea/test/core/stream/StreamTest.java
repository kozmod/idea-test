package ru.idea.test.core.stream;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void shouldChangeObject() {
        List<Beer> beers = List.of(new Beer().setName("Bag Beer").setAlcohol(9.9));
        List<Beer> changeBears = beers.stream()
                .map(beer -> beer.setName("Hamovniki").setAlcohol(4.5))
                .collect(Collectors.toList());
        System.err.println("------------------------------------------------------------------------------------------");
        System.err.println("Beers: " + beers);
        System.err.println("Change Bears: " + changeBears);
        System.err.println("------------------------------------------------------------------------------------------");
    }

}
