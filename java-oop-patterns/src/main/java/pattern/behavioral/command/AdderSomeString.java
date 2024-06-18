package pattern.behavioral.command;

public class AdderSomeString implements Command {
    @Override
    public String execute(SomeObject object) {
        object.setValue(object.getValue() + " + SomeString");
        return "SomeString added";
    }
}
