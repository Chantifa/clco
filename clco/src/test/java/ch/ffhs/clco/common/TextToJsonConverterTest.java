package ch.ffhs.clco.common;

import ch.ffhs.clco.entity.Quote;
import ch.ffhs.clco.persistence.QuoteRepository;
import ch.ffhs.clco.service.QuoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TextToJsonConverterTest {

    @Mock
    QuoteRepository quoteRepository;

    @Test
    public void testConvertTextToJson() throws IOException {
        // Mock QuoteService
        QuoteService quoteService = Mockito.mock(QuoteService.class);

        // Create test quotes
        Quote quote1 = new Quote("Quote 1", "Author 1");
        Quote quote2 = new Quote("Quote 2", "Author 2");
        List<Quote> quotes = Arrays.asList(quote1, quote2);

        // Mock ObjectMapper
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        when(objectMapper.writeValueAsString(quotes)).thenReturn("[{\"quote\":\"Quote 1\",\"author\":\"Author 1\"},{\"quote\":\"Quote 2\",\"author\":\"Author 2\"}]");

        // Mock ClassPathResource
        ClassPathResource resource = Mockito.mock(ClassPathResource.class);
        when(resource.getInputStream()).thenReturn(new ClassPathResource("static/json/quotes.txt").getInputStream());

        // Create test file data
        byte[] fileData = "Quote 1: Author 1\nQuote 2: Author 2".getBytes();

        // Mock FileCopyUtils
        FileCopyUtils fileCopyUtils = Mockito.mock(FileCopyUtils.class);
        when(fileCopyUtils.copyToByteArray(resource.getInputStream())).thenReturn(fileData);

        // Mock FileWriter
        FileWriter writer = Mockito.mock(FileWriter.class);
        doNothing().when(writer).write(String.valueOf(any()));
        doNothing().when(writer).close();

        // Create TextToJsonConverter instance
        TextToJsonConverter converter = new TextToJsonConverter(objectMapper, quoteRepository);

        // Set mocked dependencies
        // Execute the conversion
        converter.convertTextToJson();

        // Verify the quotes are saved in the database
        verify(quoteService, times(1)).saveAllQuotes(quotes);

        // Verify the JSON file is saved in the resources folder
        File jsonFile = new File("src/main/resources/static/json/quotes.json");
        assertThat(jsonFile.exists()).isTrue();

        // Verify the content of the JSON file
        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFile.getPath())));
        assertThat(jsonData).isEqualTo("[{\"quote\":\"Quote 1\",\"author\":\"Author 1\"},{\"quote\":\"Quote 2\",\"author\":\"Author 2\"}]");

        // Clean up the test file
        jsonFile.delete();
    }
}
