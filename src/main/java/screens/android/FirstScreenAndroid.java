package screens.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import screens.base.FirstScreen;


public class FirstScreenAndroid extends FirstScreen {
    private final String titleId = "first_screen_title";
    private final String loginButtonId = "login_button";

    public FirstScreenAndroid(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        return driver.findElement(By.id(titleId)).isDisplayed();
    }

    @Override
    public boolean isLoginButtonVisible() {
        return driver.findElement(By.id(loginButtonId)).isDisplayed();
    }
}
