package ru.julia.tableineritance.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "B")
@DiscriminatorValue("BLeaf")
public class B extends A {
    private String fieldB;

    public B() {}

    public B(long id, String fieldA, String fieldB) {
        super(id, fieldA);
        this.fieldB = fieldB;
    }

    @Override
    public String toString() {
        return "B{" + "id=" + id + ", fieldA='" + fieldA + '\'' + ", fieldB='" + fieldB + '\'' + '}';
    }
}
