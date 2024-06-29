package ru.julia.json.gson;

import java.util.Objects;

public class SimpleClass {
    private final int field1;
    private final String field2;
    private final int field3;

    SimpleClass(int field1, String field2, int field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleClass that = (SimpleClass) o;
        return field1 == that.field1 && field3 == that.field3 && Objects.equals(field2, that.field2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3);
    }

    @Override
    public String toString() {
        return "SimpleClass{" + "field1=" + field1 + ", field2='" + field2 + '\'' + ", field3=" + field3 + '}';
    }
}