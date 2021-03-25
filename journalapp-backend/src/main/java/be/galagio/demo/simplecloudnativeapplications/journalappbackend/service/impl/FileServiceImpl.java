package be.galagio.demo.simplecloudnativeapplications.journalappbackend.service.impl;

import be.galagio.demo.simplecloudnativeapplications.journalappbackend.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
    private final String fileLocation;

    public FileServiceImpl(@Value("${storage.filelocation}") final String fileLocation) {
        this.fileLocation = fileLocation;

        try {
            if (new File(fileLocation).createNewFile()) {
                LOG.info("Created new file {} to write our text in", fileLocation);
            } else {
                LOG.info("Reusing existing file {} to write our text in", fileLocation);
            }
        } catch (IOException ex) {
            throw new FileHandlingError(ex);
        }
    }

    @Override
    public void write(String text) {
        try (PrintWriter out = new PrintWriter(fileLocation)) {
            out.println(text);
        } catch (IOException ex) {
            throw new FileHandlingError(ex);
        }
    }

    @Override
    public String read() {
        try {
            return Files.readString(Path.of(fileLocation));
        } catch (IOException ex) {
            throw new FileHandlingError(ex);
        }
    }
}
