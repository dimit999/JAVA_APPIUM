package screens.ios;

import io.appium.java_client.AppiumDriver;
import screens.base.FirstScreen;
import screens.base.ScreenFactory;

public class IosScreenFactory implements ScreenFactory {
    private AppiumDriver driver;

    public IosScreenFactory(AppiumDriver driver) {
        this.driver = driver;
    }

    @Override
    public FirstScreen firstScreen() {
        return new FirstScreenIOS(this.driver);
    }
}
