package ru.job4j.services;

import ru.job4j.models.Place;
import ru.job4j.models.Visitor;
import ru.job4j.stores.MemStore;
import ru.job4j.stores.Store;

import java.util.List;

public class MemService implements Service {
    private final Store store = MemStore.instOf();

    private MemService() {

    }

    private static final class Lazy {
        private static final Service INST = new MemService();
    }

    public static Service instOf() {
        return MemService.Lazy.INST;
    }

    @Override
    public List<Place> getOccupiedPlaces() {
        return store.getOccupiedPlaces();
    }

    @Override
    public Visitor takePlace(Place place, Visitor visitor) {
        return store.takePlace(place, visitor);
    }
}
