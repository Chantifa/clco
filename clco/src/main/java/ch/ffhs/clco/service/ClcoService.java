package ch.ffhs.clco.service;

import ch.ffhs.clco.common.TextFileToJsonConverter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ch.ffhs.clco.common.TextFileToJsonConverter.createJsonObject;

@Service
public class ClcoService  {
    String fileName = "json/quotes.txt";
    String outputPath = "json/data.json";

    @Autowired
    private DataSource dataSource;

    public ClcoService() {
    }

    public String getData() {
        TextFileToJsonConverter textFileToJsonConverter = new TextFileToJsonConverter();
        String text = null;
        try {
            text = textFileToJsonConverter.readTextFromFile(fileName);

            ObjectNode jsonObject = createJsonObject(text);
            textFileToJsonConverter.writeJsonToFile(jsonObject, outputPath);

            ClassPathResource resource = new ClassPathResource("data.json");
            byte[] fileData = new byte[0];
            fileData = StreamUtils.copyToByteArray(resource.getInputStream());

            String jsonData = new String(fileData, StandardCharsets.UTF_8);
            saveJsonToDatabase(jsonData);

            return jsonData;
        } catch (IOException e) {
            throw new RuntimeException(e);
            // Save JSON data to the PostgreSQL database
        }
    }

    private void saveJsonToDatabase(String jsonData) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO quotes (author, text) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, jsonData);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
