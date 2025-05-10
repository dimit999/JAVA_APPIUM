package screens;

import config.Config;
import screens.android.AndroidScreenFactory;
import screens.base.ScreenFactory;
import screens.ios.IosScreenFactory;

public class ScreenResolver {
    private final ScreenFactory factory;

    public ScreenResolver() {
        if ("IOS".equalsIgnoreCase(Config.PLATFORM)) {
            factory = new IosScreenFactory();
        } else {
            factory = new AndroidScreenFactory();
        }
    }
    public ScreenFactory getFactory() {
        return factory;
    }
}
