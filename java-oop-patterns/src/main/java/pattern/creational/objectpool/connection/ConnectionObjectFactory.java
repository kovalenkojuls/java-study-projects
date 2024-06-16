package pattern.creational.objectpool.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionObjectFactory implements ObjectFactory<Connection> {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionObjectFactory.class);

    @Override
    public Connection create() {
        logger.info("Create new Connection");
        return new ConnectionPostgres();
    }
}
