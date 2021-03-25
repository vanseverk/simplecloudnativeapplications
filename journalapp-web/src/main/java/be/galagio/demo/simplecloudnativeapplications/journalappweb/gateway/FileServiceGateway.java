package be.galagio.demo.simplecloudnativeapplications.journalappweb.gateway;

public interface FileServiceGateway {
    void write(String text);

    String read();
}
