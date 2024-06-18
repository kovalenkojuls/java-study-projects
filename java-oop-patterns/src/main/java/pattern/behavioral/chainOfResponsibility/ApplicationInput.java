package pattern.behavioral.chainOfResponsibility;

public class ApplicationInput extends ApplicationProcessor {

    @Override
    protected void processInternal(Application application) {
        application.addHistoryRecord("The application has been accepted");
    }

    @Override
    public String getProcessorName() {
        return "Acceptance of the application";
    }
}
