package ru.job4j.stores;

import ru.job4j.models.Place;
import ru.job4j.models.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemStore implements Store {
    private final Map<Place, Visitor> hall = new ConcurrentHashMap<>();

    private MemStore() {
        hall.put(new Place(1, 3), new Visitor("Sergey", "+70000000000"));
    }

    private static final class Lazy {
        private static final Store INST = new MemStore();
    }

    public static Store instOf() {
        return MemStore.Lazy.INST;
    }

    @Override
    public List<Place> getOccupiedPlaces() {
        return new ArrayList<>(hall.keySet());
    }

    @Override
    public Visitor takePlace(Place place, Visitor visitor) {
        return hall.putIfAbsent(place, visitor);
    }
}
