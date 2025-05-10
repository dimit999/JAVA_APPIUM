package screens.ios;

import elements.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.SpinnerScreen;

public class SpinnerScreenIOS extends SpinnerScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget");
    private static final By SPINNER = AppiumBy.className("//android.widget.ProgressBar");

    public SpinnerScreenIOS() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Label spinner() {
        return new Label(SPINNER, "Spinner");
    }
}