package screens.android;

import io.appium.java_client.AppiumDriver;
import screens.base.FirstScreen;
import screens.base.ScreenFactory;

public class AndroidScreenFactory implements ScreenFactory {
    private AppiumDriver driver;

    public AndroidScreenFactory(AppiumDriver driver) {
        this.driver = driver;
    }

    @Override
    public FirstScreen firstScreen() {
        return new FirstScreenAndroid(this.driver);
    }
}
