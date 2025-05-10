package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for loading and parsing JSON files.
 */
public final class JsonUtils {
    /**
     * Error message for JSON operations.
     */
    private static final String ERROR_MESSAGE = "Failed to read JSON data";

    /**
     * Private constructor to prevent instantiation.
     */
    private JsonUtils() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    /**
     * Custom exception for JSON errors.
     */
    public static class JsonUtilsException extends RuntimeException {
        /**
         * Constructs a new JsonUtilsException.
         * @param message the error message.
         * @param cause the cause of the error.
         */
        public JsonUtilsException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Loads a JSON object from a file.
     * @param filePath path to the JSON file.
     * @return parsed JSONObject.
     * @throws JsonUtilsException if file reading or parsing fails.
     * @throws IllegalArgumentException if filePath is null or empty.
     */
    public static JSONObject loadJsonObject(final String filePath) {
        validateFilePath(filePath);

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw handleJsonException(e);
        }
    }

    /**
     * Loads a JSON array from a file.
     * @param filePath path to the JSON file.
     * @return parsed JSONArray.
     * @throws JsonUtilsException if file reading or parsing fails.
     * @throws IllegalArgumentException if filePath is null or empty.
     */
    public static JSONArray loadJsonArray(final String filePath) {
        validateFilePath(filePath);

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw handleJsonException(e);
        }
    }

    /**
     * Validates the file path.
     * @param filePath path to the JSON file.
     * @throws IllegalArgumentException if filePath is null or empty.
     */
    private static void validateFilePath(final String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "File path cannot be null or empty");
        }
    }

    /**
     * Handles JSON exceptions.
     * @param e the exception.
     * @return JsonUtilsException.
     */
    private static JsonUtilsException handleJsonException(final Exception e) {
        return new JsonUtilsException(ERROR_MESSAGE + ": " + e.getMessage(), e);
    }
}
