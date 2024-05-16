package ru.julia;

/*
* Benchmark                      Mode  Cnt    Score   Error  Units
* JMHMapTest.CustomMapIntTest    ss           98,616          ms/op
* JMHMapTest.hashMapTest         ss           159,314         ms/op
*/
public class CustomMapInt {
    private final int mapSize;
    private static final int STEP_LIMIT = 5;
    private final String[] arrayKeys;
    private final int[] arrayValues;
    private final boolean[] arrayAreSets;

    public CustomMapInt(int mapSize) {
        this.mapSize = mapSize * 8 - 1;
        arrayKeys = new String[this.mapSize];
        arrayValues = new int[this.mapSize];
        arrayAreSets = new boolean[this.mapSize];
    }

    public int get(String key) {
        var step = 0;
        int index;

        do {
            index = calcIndex(key, step++);
        } while (index >= 0 && !key.equals(arrayKeys[index]));

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return arrayValues[index];
    }

    public void put(String key, int value) {
        var index = getIndexByKey(key);

        this.arrayKeys[index] = key;
        this.arrayValues[index] = value;
        this.arrayAreSets[index] = true;
    }

    private int calcIndex(String key, int step) {
        if (step >= STEP_LIMIT) {
            return -1;
        }

        var hash = key.hashCode();
        return (((hash + step * hash) & 0x7fffffff) % mapSize); //probing double hashing
    }

    private int getIndexByKey(String key) {
        int step = 0, index;

        do {
            index = calcIndex(key, step++);
        } while (index >= 0 && arrayAreSets[index]);

        return index;
    }
}