package be.galagio.demo.simplecloudnativeapplications.journalappbackend.controller;

import be.galagio.demo.simplecloudnativeapplications.journalappbackend.service.FileService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PutMapping("/file")
    public void write(@RequestBody(required = false) String text) {
        if (text == null) {
           text = "";
        }
        fileService.write(text);
    }

    @GetMapping("/file")
    public String read() {
        return fileService.read();
    }
}
