package command;

import component.ConsoleHelper;
import component.ZipFileManager;
import exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateZipCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Create the archive.");
            ConsoleHelper.writeMessage("Enter absolute path to the file of directory:");

            ZipFileManager zipFileManager = getZipFileManager();
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.createZip(sourcePath);

            ConsoleHelper.writeMessage("Done.");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Error path.");
        }
    }
}
