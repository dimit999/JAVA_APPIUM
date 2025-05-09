package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {
    public static String getConfig(String key, String defaultVal) {
        return System.getProperty(key, System.getenv().getOrDefault(key, defaultVal));
    }
    public static final String APP_FILE_PATH = getConfig("APP_FILE_PATH", null);
    public static final String APK_FILE_PATH = getConfig("APK_FILE_PATH", "/Users/dzmitryananyeu/PLACES/PLACES_APPS/ANDROID/app-release.apk");
    public static final String IPA_FILE_PATH = getConfig("IPA_FILE_PATH", "/Users/dzmitryananyeu/PLACES/PLACES_APPS/IOS/Places.ipa");
    public static final boolean CAPTURE_LOG = "True".equalsIgnoreCase(getConfig("CAPTURE_LOG", "False"));
    public static final String APPIUM_SERVER_HOST = getConfig("APPIUM_SERVER_HOST", "127.0.0.1");
    public static final String APPIUM_SERVER_PORT = getConfig("APPIUM_SERVER_PORT", "4723");
    public static final String PLATFORM = getConfig("PLATFORM", "ANDROID");
    public static final String DEVICE_NAME = getConfig("DEVICE_NAME", "Pixel_7_Pro_API_35");
    public static final String REMOTE_URL = getConfig("REMOTE_URL", ""); // For BrowserStack
    public static final String DEVICES_JSON_PATH =  getConfig("DEVICES_JSON_PATH", "src/main/java/config/devices.json");

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
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Failed to read JSON test data: " + e.getMessage(), e);
        }
    }
}
