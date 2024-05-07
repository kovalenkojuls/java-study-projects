package command;

import component.ConsoleHelper;
import component.ZipFileManager;
import exception.WrongZipFileException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtractZipCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Extract the archive.");
            ConsoleHelper.writeMessage("Enter path to extract:");

            ZipFileManager zipFileManager = getZipFileManager();
            Path destinationPath = Paths.get(ConsoleHelper.readString());
            zipFileManager.extractAll(destinationPath);

            ConsoleHelper.writeMessage("Done.");
        } catch (WrongZipFileException e) {
            ConsoleHelper.writeMessage("Archive not found.");
        }
    }
}
