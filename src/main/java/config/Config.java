package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.err.println("Could not load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, props.getProperty(key, defaultValue));
    }
    public static String get(String key) {
        return get(key, null);
    }

    public static final String APP_FILE_PATH = get("APP_FILE_PATH");
    public static final String APK_FILE_PATH = get("APK_FILE_PATH", "/Users/dzmitryananyeu/Downloads/latest.apk");
    public static final String IPA_FILE_PATH = get("IPA_FILE_PATH", "/Users/dzmitryananyeu/XXX.ipa");
    public static final boolean CAPTURE_LOG = "True".equalsIgnoreCase(get("CAPTURE_LOG", "False"));
    public static final String APPIUM_SERVER_HOST = get("APPIUM_SERVER_HOST", "127.0.0.1");
    public static final String APPIUM_SERVER_PORT = get("APPIUM_SERVER_PORT", "4723");
    public static final String PLATFORM = get("PLATFORM", "ANDROID");
    public static final String DEVICE_NAME = get("DEVICE_NAME", "Pixel_7_Pro_API_35");
    public static final String REMOTE_URL = get("REMOTE_URL", ""); // For BrowserStack
    public static final String DEVICES_JSON_PATH = get("DEVICES_JSON_PATH", "src/main/java/config/devices.json");
    public static final int DEFAULT_TIMEOUT = Integer.parseInt(get("DEFAULT_TIMEOUT", "30"));

    public static final Map<String, Object> IOS_CAPABILITIES = new HashMap<String, Object>() {{
        put("automationName", "XCUITest");
        put("wdaStartupRetries", 5);
        put("useNewWDA", true);
        put("wdaLaunchTimeout", 90000);
        put("wdaConnectionTimeout", 20000);
        put("wdaEventloopIdleDelay", 5);
        put("waitForQuiescence", false);
    }};

    public static final Map<String, Object> ANDROID_CAPABILITIES = new HashMap<String, Object>() {{
        put("automationName", "UiAutomator2");
        put("adbExecTimeout", 120000);
        put("ignoreHiddenApiPolicyError", true);
        put("newCommandTimeout", 120);
        put("avdLaunchTimeout", 120000);
        put("avdReadyTimeout", 120000);
        put("avd", DEVICE_NAME);
    }};

    public static JSONObject getTestData(String filePath) {
        JSONParser parser = new JSONParser();
        try (java.io.FileReader reader = new java.io.FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Failed to read JSON test data: " + e.getMessage(), e);
        }
    }
}
