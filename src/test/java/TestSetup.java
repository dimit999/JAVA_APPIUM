import config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestSetup {
    public static AppiumDriver createDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String platform = System.getenv().getOrDefault("PLATFORM", "ANDROID");

        if (platform.equalsIgnoreCase("IOS")) {
            Config.IOS_CAPABILITIES.forEach(caps::setCapability);
            caps.setCapability("app", Config.APP_FILE_PATH != null ? Config.APP_FILE_PATH : Config.IPA_FILE_PATH);
            return new IOSDriver(new URL("http://" + Config.APPIUM_SERVER_HOST + ":" + Config.APPIUM_SERVER_PORT + "/wd/hub"), caps);
        } else {
            Config.ANDROID_CAPABILITIES.forEach(caps::setCapability);
            caps.setCapability("app", Config.APK_FILE_PATH);
            return new AndroidDriver(new URL("http://" + Config.APPIUM_SERVER_HOST + ":" + Config.APPIUM_SERVER_PORT + "/wd/hub"), caps);
        }
    }
}
