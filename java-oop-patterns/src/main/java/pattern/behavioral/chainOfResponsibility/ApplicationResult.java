package pattern.behavioral.chainOfResponsibility;

public class ApplicationResult extends ApplicationProcessor {

    @Override
    protected void processInternal(Application application) {
        application.addHistoryRecord("The result is given");
    }

    @Override
    public String getProcessorName() {
        return "Output of the result";
    }
}
