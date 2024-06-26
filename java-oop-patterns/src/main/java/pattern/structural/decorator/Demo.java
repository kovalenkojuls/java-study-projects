package pattern.structural.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        var ds = new DataSourceImpl();
        printer(ds);

        printer(new DataSourceDecoratorAdder(ds));
        printer(new DataSourceDecoratorMultiplicator(ds));
        printer(
                new DataSourceDecoratorMultiplicator(
                        new DataSourceDecoratorAdder(
                                new DataSourceDecoratorMultiplicator(ds)
                        )
                )
        );
    }

    private static void printer(DataSource ds) {
        logger.info("{}:", ds.getInteger());
    }

    private static void JDKExamples() {
        /*FileInputStream fis = new FileInputStream("/objects.gz");
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);
        ZipInputStream zis = new ZipInputStream(fis);
        GzipInputStream gis = new GzipInputStream(bis);

        ObjectInputStream ois = new ObjectInputStream(gis);
        SomeObject someObject = (SomeObject) ois.readObject();*/
    }
}
