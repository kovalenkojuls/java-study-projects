package pattern.creational.objectpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.creational.objectpool.connection.ObjectFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class ObjectPool<T> {
    private static final Logger logger = LoggerFactory.getLogger(ObjectPool.class);

    private final int maxSize;
    private final ObjectFactory<T> objectFactory;
    private final Consumer<T> pooledObjectInitializer;

    private final Queue<T> free = new LinkedList<>();
    private final Queue<T> used = new LinkedList<>();

    ObjectPool(int initialSize, int maxSize, ObjectFactory<T> objectFactory, Consumer<T> pooledObjectInitializer) {
        this.maxSize = maxSize;
        this.objectFactory = objectFactory;
        this.pooledObjectInitializer = pooledObjectInitializer;

        initPool(initialSize);
    }

    public T get() {
        var freeBefore = free.size();
        var usedBefore = used.size();

        T obj = free.poll();
        if (obj == null) {
            if (used.size() == this.maxSize)
                throw new ObjectPoolMaxSizeException(maxSize);

            obj = objectFactory.create();
            pooledObjectInitializer.accept(obj);
        }
        used.offer(obj);

        logger.info(
                "get() free={} used={} | free={} used={}",
                freeBefore, usedBefore, free.size(), used.size());
        return obj;
    }

    public void release(T obj) {
        var freeBefore = free.size();
        var usedBefore = used.size();

        used.remove(obj);
        free.add(obj);

        logger.info(
                "release() free={} used={} | free={} used={}",
                freeBefore, usedBefore, free.size(), used.size());
    }

    private void initPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            T obj = objectFactory.create();
            pooledObjectInitializer.accept(obj);
            free.add(obj);
        }
    }
}

class ObjectPoolMaxSizeException extends RuntimeException {
    public ObjectPoolMaxSizeException(int maxSize) {
        super("Cannot create object. Reached maximum pool size of " + maxSize + " objects.");
    }
}
