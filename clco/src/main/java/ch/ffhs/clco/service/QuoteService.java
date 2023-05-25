package ch.ffhs.clco.service;

import ch.ffhs.clco.common.TextToJsonConverter;
import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote getAQuote() {
        TextToJsonConverter textToJsonConverter = new TextToJsonConverter(quoteRepository);
        textToJsonConverter.convertTextToJson();
        return quoteRepository.findRandomQuote();
    }

    public Quote getANewQuote() {
        return quoteRepository.findRandomQuote();
    }
}
