package ch.ffhs.clco.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class TextFileToJsonConverter {

    public static String readTextFromFile(String fileName) throws IOException {
        ClassLoader classLoader = TextFileToJsonConverter.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }
        }

        return stringBuilder.toString();
    }

    public static ObjectNode createJsonObject(String text) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put("text", text);
        return jsonObject;
    }

    public static void writeJsonToFile(ObjectNode jsonObject, String outputPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), jsonObject);
    }
}
