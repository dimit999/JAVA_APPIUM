package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading and accessing configuration from multiple sources:
 * system properties, properties file, and JSON configuration file.
 */
public final class ConfigLoader {

    /** Loaded properties from config.properties. */
    private static final Properties PROPS;

    /** JSON node for environment-specific config. */
    private static final JsonNode CFG;

    static {
        try {
            PROPS = loadProperties();
            String env = determineEnvironment();
            CFG = loadEnvironmentConfig(env);
        } catch (Exception e) {
            throw new ConfigurationException(
                    "Failed to load configuration", e);
        }
    }

    // Prevent instantiation
    private ConfigLoader() {
        throw new UnsupportedOperationException(
                "Utility class should not be instantiated");
    }

    /**
     * Loads the properties file from the classpath.
     *
     * @return loaded Properties object
     */
    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream in = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception e) {
            throw new ConfigurationException(
                    "Failed to load properties file", e);
        }
        return props;
    }

    /**
     * Determines the current environment key.
     *
     * @return environment name
     */
    private static String determineEnvironment() {
        String envKey = PROPS.getProperty("env.key", "env");
        return System.getProperty(envKey,
                PROPS.getProperty(envKey));
    }

    /**
     * Loads the JSON config for the given environment.
     *
     * @param env environment name
     * @return JsonNode of the environment config
     */
    private static JsonNode loadEnvironmentConfig(final String env) {
        String primary = String.format("env/%s.json", env);
        String fallback = String.format("%s.json", env);
        try (InputStream in = loadConfigStream(primary, fallback)) {
            if (in == null) {
                throw new ConfigurationException(
                        "Could not find config for env: " + env);
            }
            return new ObjectMapper().readTree(in);
        } catch (Exception e) {
            throw new ConfigurationException(
                    "Failed to load environment config", e);
        }
    }

    /**
     * Attempts to load a resource stream from primary or fallback path.
     *
     * @param primary   primary resource path
     * @param fallback  fallback resource path
     * @return InputStream or null if not found
     */
    private static InputStream loadConfigStream(
            final String primary,
            final String fallback) {
        InputStream in = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(primary);
        if (in == null) {
            in = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(fallback);
        }
        return in;
    }

    /**
     * Retrieves a configuration value by key. Checks system properties,
     * then properties file, then JSON config.
     *
     * @param key configuration key
     * @return configuration value or empty string
     */
    public static String get(final String key) {
        String val = System.getProperty(key);
        if (isNotEmpty(val)) {
            return val;
        }
        val = PROPS.getProperty(key);
        if (isNotEmpty(val)) {
            return val;
        }
        return CFG.path(key).asText();
    }

    /**
     * Checks if a string is neither null nor empty.
     *
     * @param value string to check
     * @return true if not null/empty
     */
    private static boolean isNotEmpty(final String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Exception thrown when configuration loading fails.
     */
    public static class ConfigurationException
            extends RuntimeException {

        /**
         * @param message exception message
         */
        public ConfigurationException(final String message) {
            super(message);
        }

        /**
         * @param message exception message
         * @param cause   root cause
         */
        public ConfigurationException(
                final String message,
                final Throwable cause) {
            super(message, cause);
        }
    }
}
