package ch.ffhs.clco.web;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import ch.ffhs.clco.service.QuoteService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    QuoteRepository quoteRepository;


    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }


    @RequestMapping("/")
    public ModelAndView home(ModelAndView model) {
        List<Quote> quotes = quoteService.getAllQuotes();
        model.setViewName("quotes");
        model.addObject("quotes", quotes);
        return model;
    }

/*
    @GetMapping
    public List<Quote> getQuotes() {
        List<Quote> quotes = quoteService.getAllQuotes();
        return quotes;
    }/

    /*
    @GetMapping("/json")
    public String getAllQuotes() {
        try {
            // Read JSON file from resources folder
            File jsonFile = new File("quotes.json");
            byte[] jsonData = Files.readAllBytes(Paths.get(jsonFile.getPath()));
            return new String(jsonData);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return "index";
        }
    }*/
}

