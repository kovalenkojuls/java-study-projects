package pattern.behavioral.chainOfResponsibility;

public class Demo {
    public static void main(String[] args) {
        var application = new Application();

        var workerInput = new ApplicationInput();
        var workerReader = new ApplicationReader();
        var workerResult = new ApplicationResult();

        workerInput.setNext(workerReader);
        workerReader.setNext(workerResult);

        workerInput.process(application);
        application.printHistory();
    }
}
