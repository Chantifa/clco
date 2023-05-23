package ch.ffhs.clco.common;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TextToJsonConverter {

    private final ObjectMapper objectMapper;
    private final QuoteRepository quoteRepository;

    @Autowired
    public TextToJsonConverter(ObjectMapper objectMapper, QuoteRepository quoteRepository) {
        this.objectMapper = objectMapper;
        this.quoteRepository = quoteRepository;
    }

    @PostConstruct
    public void convertTextToJson() {
        try {
            // Read text file from resources folder
            ClassPathResource resource = new ClassPathResource("quotes.txt");
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String quotesText = new String(fileData).trim();

            // Process the quotes and authors
            List<Quote> quotes = processQuotes(quotesText);

            // Convert quotes to JSON
            String json = objectMapper.writeValueAsString(quotes);

            // Save JSON file in resources folder
            File jsonFile = new File("quotes.json");
            FileWriter writer = new FileWriter(jsonFile);
            writer.write(json);
            writer.close();

            // Register quotes in the PostgreSQL database
            quoteRepository.saveAll(quotes);

            System.out.println("Conversion successful!");
        } catch (IOException e) {
            System.err.println("Error converting text to JSON: " + e.getMessage());
        }
    }

    private List<Quote> processQuotes(String quotesText) {
        List<Quote> quotes = new ArrayList<>();

        // Split quotes and authors by lines
        String[] lines = quotesText.split("\\r?\\n");

        for (String line : lines) {
            // Split quote and author by the delimiter (e.g., ":")
            String[] parts = line.split(".\"");

            if (parts.length == 2) {
                String quote = parts[0].trim();
                String author = parts[1].trim();
                quotes.add(new Quote(quote, author));
            } else {
                System.err.println("Invalid quote format: " + line);
            }
        }
        return quotes;
    }
}
