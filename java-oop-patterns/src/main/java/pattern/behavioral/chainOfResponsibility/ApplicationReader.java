package pattern.behavioral.chainOfResponsibility;

public class ApplicationReader extends ApplicationProcessor {

    @Override
    protected void processInternal(Application application) {
        application.addHistoryRecord("The application has been reviewed");
    }

    @Override
    public String getProcessorName() {
        return "Review of the application";
    }
}
