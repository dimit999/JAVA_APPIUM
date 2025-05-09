package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseScreen {
    protected AppiumDriver driver;

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
    }

    public abstract boolean isAt(); // Should be implemented in each screen
    public abstract boolean isLoginButtonVisible(); // Must be implemented in each screen

    // Common methods for all screens
    public void back() {
        driver.navigate().back();
    }
}
