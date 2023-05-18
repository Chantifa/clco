package ch.ffhs.clco.web;
import ch.ffhs.clco.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class QuoteControllerTest {

    @Mock
    private QuoteService quoteService;

    @Mock
    private Resource resource;

    @Test
    public void testGetQuotesJson() throws IOException {
        // Mock JSON file content
        String jsonContent = "[{\"quote\":\"Quote 1\",\"author\":\"Author 1\"},{\"quote\":\"Quote 2\",\"author\":\"Author 2\"}]";

        // Mock InputStream
        InputStream inputStream = Mockito.mock(InputStream.class);

        // Mock FileCopyUtils
        byte[] jsonData = jsonContent.getBytes(StandardCharsets.UTF_8);

        // Create QuoteController instance
        QuoteController quoteController = new QuoteController(quoteService);

        // Set mocked dependencies
        quoteController.setResource(resource);

        // Mock the resource's InputStream

        // Execute the method and get the JSON string
        String quotesJson = quoteController.getQuotesJson();

        // Verify the JSON string
        assertThat(quotesJson).isEqualTo(jsonContent);
    }
}
