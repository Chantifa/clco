package ch.ffhs.clco.web;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import ch.ffhs.clco.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    QuoteRepository quoteRepository;


    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }


    @RequestMapping("/quotes")
    public ModelAndView home(ModelAndView model) {
        Quote quotes = quoteService.getAQuote();
        model.setViewName("quotes");
        model.addObject("quotes", quotes);
        return model;
    }

    @RequestMapping("/newquotes")
    public ResponseEntity<Quote> getNewQuote() {
        Quote quote = quoteService.getANewQuote();
        if (quote != null) {
            return ResponseEntity.ok(quote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

