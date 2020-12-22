package ru.job4j.stores;

import org.junit.Test;
import ru.job4j.models.Place;
import ru.job4j.models.Visitor;

import java.util.List;

import static org.junit.Assert.*;

public class MemStoreTest {

    @Test
    public void whenGetOccupiedPlacesThenReturnSetAsExpected() {
        List<Place> expected = List.of(new Place(1, 3));
        assertEquals(MemStore.instOf().getOccupiedPlaces(), expected);
    }

    @Test
    public void whenTakePlaceThenReturnNull() {
        assertNull(MemStore.instOf().takePlace(new Place(1, 1), new Visitor("Sergey", "+7")));
        assertNotNull(MemStore.instOf().takePlace(new Place(1, 3), new Visitor("Sergey", "+7")));
    }
}