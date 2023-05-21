package ch.ffhs.clco.web;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import ch.ffhs.clco.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        List<Quote> quotes = quoteService.getAllQuotes();
        model.setViewName("quotes");
        model.addObject("quotes", quotes);
        return model;
    }
}

