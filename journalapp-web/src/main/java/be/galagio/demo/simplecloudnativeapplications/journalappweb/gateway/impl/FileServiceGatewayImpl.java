package be.galagio.demo.simplecloudnativeapplications.journalappweb.gateway.impl;

import be.galagio.demo.simplecloudnativeapplications.journalappweb.gateway.FileServiceGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FileServiceGatewayImpl implements FileServiceGateway {

    private final RestTemplate restTemplate;
    private final String fileServiceLocation;

    public FileServiceGatewayImpl(
            final RestTemplate restTemplate,
            @Value("${fileservice.location}") final String fileServiceLocation
    ) {
        this.restTemplate = restTemplate;
        this.fileServiceLocation = fileServiceLocation;
    }

    @Override
    public void write(String text) {
        final HttpEntity<String> request = new HttpEntity<>(text);
        try {
            restTemplate.put(fileServiceLocation + "/file", request);
        } catch (Exception ex) {
            throw new FileServiceGatewayException(String.format("Problem during writing to the fileservice backend: %s", ex.getMessage()), ex);
        }
    }

    @Override
    public String read() {
        try {
            return restTemplate.getForObject(fileServiceLocation + "/file", String.class);
        } catch (Exception ex) {
            throw new FileServiceGatewayException(String.format("Problem during reading from the fileservice backend: %s", ex.getMessage()), ex);
        }
    }
}
