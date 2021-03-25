package be.galagio.demo.simplecloudnativeapplications.journalappbackend.service.impl;

import java.io.IOException;

public class FileHandlingError extends RuntimeException {

    public FileHandlingError(IOException ioex) {
        super(ioex.getMessage(), ioex);
    }

}
