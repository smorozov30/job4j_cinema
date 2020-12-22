package ru.job4j.models;

import java.util.Objects;

public class Place {
    private int row;
    private int place;

    public Place(int row, int place) {
        this.row = row;
        this.place = place;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place1 = (Place) o;
        return row == place1.row && place == place1.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, place);
    }
}
