package ch.ffhs.clco.web;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import ch.ffhs.clco.service.QuoteService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    QuoteRepository quoteRepository;

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("quotes", quoteRepository.findAll());
        return "quotes";
    }


    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

/*
    @GetMapping
    public String getQuotes() {
        try {
            // Read JSON file from resources folder
            File jsonFile = new File("quotes.json");
            byte[] jsonData = Files.readAllBytes(Paths.get(jsonFile.getPath()));
            return new String(jsonData);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return "index";
        }
    }
    */
}

