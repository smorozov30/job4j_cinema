package ru.job4j.services;

import ru.job4j.models.Place;
import ru.job4j.models.Visitor;
import ru.job4j.stores.PsqlStore;
import ru.job4j.stores.Store;

import java.util.List;

public class PsqlService implements Service {
    private final Store store = PsqlStore.instOf();

    private PsqlService() {

    }

    private static final class Lazy {
        private static final Service INST = new PsqlService();
    }

    public static Service instOf() {
        return PsqlService.Lazy.INST;
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
