package ch.ffhs.clco.service;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public void saveAllQuotes(List<Quote> quotes) {
        quoteRepository.saveAll(quotes);
    }
}
