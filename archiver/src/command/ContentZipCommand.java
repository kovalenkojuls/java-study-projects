package command;

import component.ConsoleHelper;
import component.FileProperties;
import component.ZipFileManager;

import java.util.List;

public class ContentZipCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Show files from the archive.");
        ConsoleHelper.writeMessage("Files:");

        ZipFileManager zipFileManager = getZipFileManager();
        List<FileProperties> files = zipFileManager.getFilesList();
        for (FileProperties file : files) {
            ConsoleHelper.writeMessage(file.toString());
        }

        ConsoleHelper.writeMessage("Done.");
    }
}
