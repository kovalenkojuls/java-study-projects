package pattern.behavioral.visitor;

public interface Element {
    void accept(Visitor v);
}
