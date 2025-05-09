import config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import base.JsonUtils;
import org.json.simple.JSONObject;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestSetup {
    public static AppiumDriver createDriver() throws MalformedURLException, URISyntaxException {
        String platform = System.getenv().getOrDefault("PLATFORM", "ANDROID");
        String deviceName = Config.DEVICE_NAME;
        String devicesJsonPath = Config.DEVICES_JSON_PATH;
        JSONObject devices = JsonUtils.getTestData(devicesJsonPath);
        JSONObject deviceCaps = null;
        if (devices.containsKey(deviceName)) {
            JSONObject deviceObj = (JSONObject) devices.get(deviceName);
            deviceCaps = (JSONObject) deviceObj.get("capabilities");
        }

        if (platform.equalsIgnoreCase("IOS")) {
            XCUITestOptions options = new XCUITestOptions();
            Config.IOS_CAPABILITIES.forEach(options::setCapability);
            if (deviceCaps != null) {
                for (Object key : deviceCaps.keySet()) {
                    options.setCapability(key.toString(), deviceCaps.get(key));
                }
            }
            options.setCapability("app", Config.APP_FILE_PATH != null ? Config.APP_FILE_PATH : Config.IPA_FILE_PATH);
            return new IOSDriver(new URI("http://" + Config.APPIUM_SERVER_HOST + ":" + Config.APPIUM_SERVER_PORT).toURL(), options);
        } else {
            UiAutomator2Options options = new UiAutomator2Options();
            Config.ANDROID_CAPABILITIES.forEach(options::setCapability);
            if (deviceCaps != null) {
                for (Object key : deviceCaps.keySet()) {
                    options.setCapability(key.toString(), deviceCaps.get(key));
                }
            }
            options.setCapability("app", Config.APK_FILE_PATH);
            return new AndroidDriver(new URI("http://" + Config.APPIUM_SERVER_HOST + ":" + Config.APPIUM_SERVER_PORT).toURL(), options);
        }
    }
}
