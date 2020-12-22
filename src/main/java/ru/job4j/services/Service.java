package ru.job4j.services;

import ru.job4j.models.Place;
import ru.job4j.models.Visitor;

import java.util.List;

public interface Service {
    List<Place> getOccupiedPlaces();
    Visitor takePlace(Place place, Visitor visitor);
}
