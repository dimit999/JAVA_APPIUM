package screens.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import screens.base.FirstScreen;

public class FirstScreenIOS extends FirstScreen {
    private final String titleId = "firstScreenTitle";

    public FirstScreenIOS(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        return driver.findElement(AppiumBy.accessibilityId(titleId)).isDisplayed();
    }

    @Override
    public boolean isLoginButtonVisible() {
        return driver.findElement(AppiumBy.accessibilityId("login_button")).isDisplayed();
    }
}