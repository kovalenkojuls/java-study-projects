package com.datasrc;

import static com.datasrc.MyProducer.TOPIC_NAME;

import com.datasrc.model.StringValue;
import java.util.function.Consumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSender {
    private static final Logger log = LoggerFactory.getLogger(DataSender.class);

    private final MyProducer myProducer;
    private final Consumer<StringValue> sendAsk;

    public DataSender(MyProducer myProducer, Consumer<StringValue> sendAsk) {
        this.myProducer = myProducer;
        this.sendAsk = sendAsk;
    }

    public void dataHandler(StringValue value) {
        log.info("value:{}", value);
        try {
            myProducer
                    .getMyProducer()
                    .send(new ProducerRecord<>(TOPIC_NAME, value.id(), value), (metadata, exception) -> {
                        if (exception != null) {
                            log.error("message wasn't sent", exception);
                        } else {
                            log.info("message id:{} was sent, offset:{}", value.id(), metadata.offset());
                            sendAsk.accept(value);
                        }
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
