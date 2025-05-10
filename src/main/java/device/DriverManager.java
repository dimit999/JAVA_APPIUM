package device;

import config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.json.simple.JSONObject;
import utils.JsonUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Appium Driver Manager with device locking for parallel-safe single-device runs (2025 best practice).
 */
public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    // Expose deviceLock for use in BaseTest
    public static final Object deviceLock = new Object();

    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Create a driver instance. Must be called inside a synchronized(deviceLock) block.
     */
    public static void createDriver() throws MalformedURLException, URISyntaxException {
        if (driverThreadLocal.get() == null) {
            String platform = System.getenv().getOrDefault("PLATFORM", "ANDROID");
            String deviceName = Config.DEVICE_NAME;
            String devicesJsonPath = Config.DEVICES_JSON_PATH;
            JSONObject devices = JsonUtils.loadJsonObject(devicesJsonPath);
            JSONObject deviceCaps = null;
            if (devices.containsKey(deviceName)) {
                JSONObject deviceObj = (JSONObject) devices.get(deviceName);
                deviceCaps = (JSONObject) deviceObj.get("capabilities");
            }

            AppiumDriver driver;
            if (platform.equalsIgnoreCase("IOS")) {
                XCUITestOptions options = new XCUITestOptions();
                Config.IOS_CAPABILITIES.forEach(options::setCapability);
                if (deviceCaps != null) {
                    for (Object key : deviceCaps.keySet()) {
                        options.setCapability(key.toString(), deviceCaps.get(key));
                    }
                }
                options.setCapability("app",
                        Config.APP_FILE_PATH != null ? Config.APP_FILE_PATH : Config.IPA_FILE_PATH);
                driver = new IOSDriver(new URI(
                        "http://" + Config.APPIUM_SERVER_HOST + ":" + Config.APPIUM_SERVER_PORT).toURL(), options);
            } else {
                UiAutomator2Options options = new UiAutomator2Options();
                Config.ANDROID_CAPABILITIES.forEach(options::setCapability);
                if (deviceCaps != null) {
                    for (Object key : deviceCaps.keySet()) {
                        options.setCapability(key.toString(), deviceCaps.get(key));
                    }
                }
                options.setCapability("app", Config.APK_FILE_PATH);
                driver = new AndroidDriver(new URI(
                        "http://" + Config.APPIUM_SERVER_HOST + ":" + Config.APPIUM_SERVER_PORT).toURL(), options);
            }
            driverThreadLocal.set(driver);
        }
    }

    /**
     * Quit the driver instance. Must be called inside a synchronized(deviceLock) block.
     */
    public static void quitDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    /**
     * Hides the keyboard if it is visible.
     * Works for AndroidDriver and IOSDriver.
     */
    public static void hideKeyboardIfVisible() {
        AppiumDriver driver = getDriver();
        if (driver == null) return;
        try {
            if (driver instanceof AndroidDriver) {
                ((AndroidDriver) driver).hideKeyboard();
            } else if (driver instanceof IOSDriver) {
                ((IOSDriver) driver).hideKeyboard();
            }
        } catch (Exception ignored) {
            // Ignore: keyboard not present or not supported
        }
    }
}