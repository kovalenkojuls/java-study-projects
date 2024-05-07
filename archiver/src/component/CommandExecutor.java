package component;

import command.*;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> ALL_COMMANDS = new HashMap<>();

    static {
        ALL_COMMANDS.put(Operation.CREATE, new CreateZipCommand());
        ALL_COMMANDS.put(Operation.ADD, new AddZipCommand());
        ALL_COMMANDS.put(Operation.REMOVE, new RemoveZipCommand());
        ALL_COMMANDS.put(Operation.EXTRACT, new ExtractZipCommand());
        ALL_COMMANDS.put(Operation.CONTENT, new ContentZipCommand());
        ALL_COMMANDS.put(Operation.EXIT, new ExitCommand());
    }
    private CommandExecutor() {
    }

    public static void execute(Operation operation) throws Exception {
        ALL_COMMANDS.get(operation).execute();
    }
}
