package command;

import component.ConsoleHelper;
import component.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RemoveZipCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Remove file from archive.");
        ConsoleHelper.writeMessage("Enter absolute path to archive:");

        ZipFileManager zipFileManager = getZipFileManager();
        Path source = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(source);

        ConsoleHelper.writeMessage("Done.");
    }
}
