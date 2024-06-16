package pattern.creational.prototype;

import java.util.Objects;

class CopyableObject implements Copyable<CopyableObject> {
    private String name;

    CopyableObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public CopyableObject copy() {
        return new CopyableObject(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyableObject sheep = (CopyableObject) o;
        return Objects.equals(name, sheep.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
