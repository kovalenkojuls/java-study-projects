package component;

import exception.WrongZipFileException;

public class Archiver {
    public static void main(String[] args) throws Exception {
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Error archive name.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Error command.");
            }

        } while (operation != Operation.EXIT);
    }

    public static Operation askOperation() {
        ConsoleHelper.writeMessage("Enter command:");
        ConsoleHelper.writeMessage(String.format("\t %d - create archive", Operation.CREATE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - add file to archive", Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - remove file from archive", Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - extract archive", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - show data from archive", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - exit", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}
