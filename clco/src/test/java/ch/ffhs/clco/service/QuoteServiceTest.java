package ch.ffhs.clco.service;
import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class QuoteServiceTest {

    @Test
    public void testSaveAllQuotes() {
        // Mock QuoteRepository
        QuoteRepository quoteRepository = Mockito.mock(QuoteRepository.class);

        // Create test quotes
        Quote quote1 = new Quote("Quote 1", "Author 1");
        Quote quote2 = new Quote("Quote 2", "Author 2");
        List<Quote> quotes = new ArrayList<>();
        quotes.add(quote1);
        quotes.add(quote2);

        // Create QuoteService instance
        QuoteService quoteService = new QuoteService(quoteRepository);

        // Save quotes
        quoteService.saveAllQuotes(quotes);

        // Verify that the save method of QuoteRepository is called for each quote
        verify(quoteRepository, times(1)).save(quote1);
        verify(quoteRepository, times(1)).save(quote2);
    }
}
