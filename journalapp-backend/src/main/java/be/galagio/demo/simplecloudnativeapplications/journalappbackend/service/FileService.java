package be.galagio.demo.simplecloudnativeapplications.journalappbackend.service;

public interface FileService {
    void write(String text);

    String read();
}
