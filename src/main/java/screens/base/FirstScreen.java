package screens.base;

import base.BaseScreen;
import io.appium.java_client.AppiumDriver;

public abstract class FirstScreen extends BaseScreen {

    protected FirstScreen(AppiumDriver driver) {
        super(driver);
    }

    public abstract boolean isAt();

    public abstract boolean isLoginButtonVisible();
}
