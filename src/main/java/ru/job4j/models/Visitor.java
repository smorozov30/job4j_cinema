package ru.job4j.models;

import java.util.Objects;

public class Visitor {
    private String name;
    private String phone;

    public Visitor(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(name, visitor.name) && Objects.equals(phone, visitor.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }
}
