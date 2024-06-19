package pattern.behavioral.chainOfResponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class ApplicationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationProcessor.class);
    private ApplicationProcessor next;

    private ApplicationProcessor getNext() {
        return next;
    }

    void setNext(ApplicationProcessor next) {
        this.next = next;
    }

    void process(Application application) {
        logger.info("Process {}", getProcessorName());
        processInternal(application);

        if (getNext() != null) {
            getNext().process(application);
        }
    }

    protected abstract void processInternal(Application application);

    public abstract String getProcessorName();
}
