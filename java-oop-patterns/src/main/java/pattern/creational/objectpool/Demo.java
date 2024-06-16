package pattern.creational.objectpool;

import pattern.creational.objectpool.connection.Connection;
import pattern.creational.objectpool.connection.ConnectionObjectFactory;

public class Demo {
    public static void main(String[] args) {
        ConnectionObjectFactory connectionFactory = new ConnectionObjectFactory();

        ObjectPool<Connection> pool = new ObjectPool<>(
                2,
                5,
                connectionFactory,
                Connection::connect);

        var con1 = pool.get();
        con1.execSelect();

        var con2 = pool.get();
        con2.execSelect();

        var con3 = pool.get();
        con3.execSelect();
        pool.release(con3);

        var con4 = pool.get();
        con4.execSelect();

        var con5 = pool.get();
        var con6 = pool.get();
        var con7 = pool.get();
        var con8 = pool.get();
    }
}
