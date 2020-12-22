package ru.job4j.stores;

import ru.job4j.models.Place;
import ru.job4j.models.Visitor;

import java.util.List;

public interface Store {
    List<Place> getOccupiedPlaces();
    Visitor takePlace(Place place, Visitor visitor);
}
