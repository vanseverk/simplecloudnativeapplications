package be.galagio.demo.simplecloudnativeapplications.journalappweb.controller;

import be.galagio.demo.simplecloudnativeapplications.journalappweb.gateway.FileServiceGateway;
import be.galagio.demo.simplecloudnativeapplications.journalappweb.gateway.impl.FileServiceGatewayException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileFrontendController {

    private final FileServiceGateway fileServiceGateway;

    public FileFrontendController(FileServiceGateway fileServiceGateway) {
        this.fileServiceGateway = fileServiceGateway;
    }

    @GetMapping("/")
    public String index(final Model model) {
        try {
            model.addAttribute("text", fileServiceGateway.read());
        } catch (FileServiceGatewayException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "index.html";
    }

    @PostMapping("/save")
    public String save(@RequestParam String text) {
        fileServiceGateway.write(text);
        return "redirect:/";
    }
}
