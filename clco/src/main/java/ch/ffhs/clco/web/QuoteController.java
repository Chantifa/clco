package ch.ffhs.clco.web;

import ch.ffhs.clco.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    private Resource resource;
    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    @GetMapping("/json")
    public String getQuotesJson() {
        try {
            // Read JSON file from resources folder
            File jsonFile = new File("src/main/resources/quotes.json");
            byte[] jsonData = Files.readAllBytes(Paths.get(jsonFile.getPath()));
            return new String(jsonData);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return null;
        }
    }
}
