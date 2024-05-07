package command;

import component.ConsoleHelper;
import component.ZipFileManager;
import exception.PathIsNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddZipCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Add new file to the archive.");
            ConsoleHelper.writeMessage("Enter absolute path to the file:");

            ZipFileManager zipFileManager = getZipFileManager();
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.addFile(sourcePath);

            ConsoleHelper.writeMessage("Done.");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("File not found.");
        }
    }
}
