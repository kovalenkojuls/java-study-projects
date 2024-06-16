package pattern.creational.prototype;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ClonableObject implements Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(ClonableObject.class);
    private String name;

    ClonableObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public ClonableObject clone() throws CloneNotSupportedException {
        ClonableObject clonableObject = (ClonableObject) super.clone();
        logger.info("{}", clonableObject);
        return sheep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClonableObject clonableObject = (ClonableObject) o;
        return Objects.equals(name, sheep.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
