package screens.android;

import elements.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.SpinnerScreen;

public class SpinnerScreenAndroid extends SpinnerScreen {
    private static final String SPINNER_XPATH = "//android.widget.ProgressBar";
    private static final By SCREEN_LOCATOR = AppiumBy.xpath(SPINNER_XPATH);
    private static final By SPINNER = AppiumBy.xpath(SPINNER_XPATH);

    public SpinnerScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Label spinner() {
        return new Label(SPINNER, "Spinner");
    }
}