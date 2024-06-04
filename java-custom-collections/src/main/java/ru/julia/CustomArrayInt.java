package ru.julia;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
* Benchmark                         Mode  Cnt    Score   Error  Units
* JMHArrayTest.ArrayListTest        ss           894,857         ms/op
* JMHArrayTest.CustomArrayIntTest   ss           57,627          ms/op
*/

public class CustomArrayInt implements AutoCloseable {
    private final sun.misc.Unsafe unsafe;
    private final long intSize;
    private long beginAddr;
    private long arraySize;

    public CustomArrayInt(int size) throws InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        this.arraySize = size;

        Constructor<sun.misc.Unsafe> unsafeConstructor =
                sun.misc.Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        unsafe = unsafeConstructor.newInstance();

        intSize = Integer.SIZE / 8;
        beginAddr = unsafe.allocateMemory(this.arraySize * intSize);
    }

    public int getValue(long index) {
        return unsafe.getInt(getAddrByIndex(index));
    }

    public void setValue(long index, int value) {
        if (index >= arraySize) {
            this.arraySize = Math.max(this.arraySize * 2, index + 1);
            beginAddr = unsafe.reallocateMemory(
                    beginAddr,
                    this.arraySize * intSize);
        }
        unsafe.putInt(getAddrByIndex(index), value);
    }

    private long getAddrByIndex(long offset) {
        return beginAddr + offset * this.intSize;
    }

    @Override
    public void close() {
        unsafe.freeMemory(beginAddr);
    }
}