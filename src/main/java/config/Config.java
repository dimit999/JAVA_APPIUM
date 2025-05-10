package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class for loading and accessing configuration properties and device
 * capabilities.
 * <p>
 * Loads properties from config.properties and allows overrides via system
 * properties.
 * Provides static access to all configuration values and device capabilities.
 * </p>
 */
public final class Config {
    /** Properties loaded from config.properties. */
    private static final Properties PROPS = new Properties();

    // Private constructor to prevent instantiation.
    private Config() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    static {
        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                PROPS.load(input);
            }
        } catch (IOException e) {
            System.err.println("Could not load config.properties: "
                    + e.getMessage());
        }
    }

    /**
     * Retrieves the configuration value for the specified key,
     * with optional default.
     *
     * @param key          the property key
     * @param defaultValue the default value if not found
     * @return the resolved value
     */
    public static String get(final String key, final String defaultValue) {
        return System.getProperty(key, PROPS.getProperty(
                key, defaultValue));
    }

    /**
     * Retrieves the configuration value for the specified key,
     * or null if not found.
     *
     * @param key the property key
     * @return the resolved value or null
     */
    public static String get(final String key) {
        return get(key, null);
    }

    /** Path to the .app or .ipa file (iOS) or .apk file (Android). */
    public static final String APP_FILE_PATH = get("APP_FILE_PATH");

    /** Path to the .apk file for Android. */
    public static final String APK_FILE_PATH = get(
            "APK_FILE_PATH",
            "/Users/dzmitryananyeu/Downloads/latest.apk");

    /** Path to the .ipa file for iOS. */
    public static final String IPA_FILE_PATH = get(
            "IPA_FILE_PATH",
            "/Users/dzmitryananyeu/XXX.ipa");

    /** Whether to capture device logs. */
    public static final boolean CAPTURE_LOG = "True".equalsIgnoreCase(
            get("CAPTURE_LOG", "False"));

    /** Appium server host. */
    public static final String APPIUM_SERVER_HOST = get(
            "APPIUM_SERVER_HOST",
            "127.0.0.1");

    /** Appium server port. */
    public static final String APPIUM_SERVER_PORT = get(
            "APPIUM_SERVER_PORT",
            "4723");

    /** Target platform (ANDROID or IOS). */
    public static final String PLATFORM = get(
            "PLATFORM",
            "ANDROID");

    /** Device name to use for testing. */
    public static final String DEVICE_NAME = get(
            "DEVICE_NAME",
            "Pixel_7_Pro_API_35");

    /** Remote server URL, e.g., for BrowserStack. */
    public static final String REMOTE_URL = get(
            "REMOTE_URL",
            "");

    /** Path to the devices JSON configuration. */
    public static final String DEVICES_JSON_PATH = get(
            "DEVICES_JSON_PATH",
            "src/main/java/config/devices.json");

    /** Default timeout in seconds. */
    public static final int DEFAULT_TIMEOUT = Integer.parseInt(
            get("DEFAULT_TIMEOUT", "30"));

    /**
     * Default timeout for pop-up waits (in seconds).
     */
    public static final int DEFAULT_WAIT_POP_UP_TIMEOUT = Integer.parseInt(
            get("DEFAULT_WAIT_POP_UP_TIMEOUT", "10"));

    // Magic numbers for capabilities (explain usage)
    /** WDA launch timeout in milliseconds. */
    public static final int WDA_LAUNCH_TIMEOUT_MS = 90000;

    /** WDA connection timeout in milliseconds. */
    public static final int WDA_CONNECTION_TIMEOUT_MS = 20000;

    /** WDA event loop idle delay in seconds. */
    public static final int WDA_EVENTLOOP_IDLE_DELAY_S = 5;

    /** adb exec timeout in milliseconds. */
    public static final int ADB_EXEC_TIMEOUT_MS = 120000;

    /** New command timeout in seconds. */
    public static final int NEW_COMMAND_TIMEOUT_S = 120;

    /** AVD launch timeout in milliseconds. */
    public static final int AVD_LAUNCH_TIMEOUT_MS = 120000;

    /** AVD ready timeout in milliseconds. */
    public static final int AVD_READY_TIMEOUT_MS = 120000;

    /**
     * Default iOS capabilities.
     */
    public static final Map<String, Object> IOS_CAPABILITIES =
            new HashMap<String, Object>() {{
                put("automationName", "XCUITest");
                put("wdaStartupRetries", 1);
                put("useNewWDA", true);
                put("wdaLaunchTimeout", WDA_LAUNCH_TIMEOUT_MS);
                put("wdaConnectionTimeout", WDA_CONNECTION_TIMEOUT_MS);
                put("wdaEventloopIdleDelay", WDA_EVENTLOOP_IDLE_DELAY_S);
                put("waitForQuiescence", false);
            }};

    /**
     * Default Android capabilities.
     */
    public static final Map<String, Object> ANDROID_CAPABILITIES =
            new HashMap<String, Object>() {{
                put("automationName", "UiAutomator2");
                put("adbExecTimeout", ADB_EXEC_TIMEOUT_MS);
                put("ignoreHiddenApiPolicyError", true);
                put("newCommandTimeout", NEW_COMMAND_TIMEOUT_S);
                put("avdLaunchTimeout", AVD_LAUNCH_TIMEOUT_MS);
                put("avdReadyTimeout", AVD_READY_TIMEOUT_MS);
                put("avd", DEVICE_NAME);
            }};

    /**
     * Loads and parses a JSON file containing test/device data.
     *
     * @param filePath the path to the JSON file
     * @return parsed JSONObject
     */
    public static JSONObject getTestData(final String filePath) {
        JSONParser parser = new JSONParser();
        try (java.io.FileReader reader = new java.io.FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Failed to read JSON test data: "
                    + e.getMessage(), e);
        }
    }
}
