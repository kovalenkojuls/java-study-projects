package pattern.behavioral.strategy;

class Context {
    private Strategy strategy;

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    void applyStrategy() {
        strategy.transportation();
    }
}
