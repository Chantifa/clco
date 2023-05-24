package ch.ffhs.clco.service;

import ch.ffhs.clco.common.TextToJsonConverter;
import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> getAllQuotes() {
        TextToJsonConverter textToJsonConverter = new TextToJsonConverter(quoteRepository);
        textToJsonConverter.convertTextToJson();
        return quoteRepository.findAll(Example.of(new Quote()));
    }
}
