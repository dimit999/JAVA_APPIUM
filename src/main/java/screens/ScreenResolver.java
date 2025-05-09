package screens;

import config.Config;
import io.appium.java_client.AppiumDriver;
import screens.android.AndroidScreenFactory;
import screens.base.ScreenFactory;
import screens.ios.IosScreenFactory;

public class ScreenResolver {
    private final ScreenFactory factory;

    public ScreenResolver(AppiumDriver driver) {
        if ("IOS".equalsIgnoreCase(Config.PLATFORM)) {
            factory = new IosScreenFactory(driver);
        } else {
            factory = new AndroidScreenFactory(driver);
        }
    }

    public ScreenFactory getFactory() {
        return factory;
    }
}
