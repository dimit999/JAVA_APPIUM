package base;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public final class JsonUtils {
    private static final String ERROR_MESSAGE = "Failed to read JSON test data";

    /**
     * Private constructor to prevent instantiation of utility class
     */
    private JsonUtils() {
        throw new AssertionError("Utility class JsonUtils should not be instantiated");
    }

    /**
     * Custom exception for JSON operations
     */
    public static class JsonOperationException extends RuntimeException {
        public JsonOperationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Reads and parses JSON data from the specified file path.
     *
     * @param filePath path to the JSON file
     * @return parsed JSONObject
     * @throws JsonOperationException if file reading or parsing fails
     * @throws IllegalArgumentException if filePath is null or empty
     */
    public static JSONObject getTestData(String filePath) {
        validateFilePath(filePath);

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw handleJsonException(e);
        }
    }

    private static void validateFilePath(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
    }

    private static JsonOperationException handleJsonException(Exception e) {
        return new JsonOperationException(ERROR_MESSAGE + ": " + e.getMessage(), e);
    }
}