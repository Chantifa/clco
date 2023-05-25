package ch.ffhs.clco.persistence;

import ch.ffhs.clco.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {
    @Query(value = "SELECT * FROM quotes.public.quote TABLESAMPLE SYSTEM (1) LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();
}
