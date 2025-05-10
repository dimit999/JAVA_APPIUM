package screens.android.introScreens;

import elements.Button;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.introScreens.SuccessWalletReadyScreen;

public class SuccessWalletReadyScreenAndroid extends SuccessWalletReadyScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget.TextView[contains(@text, \"your wallet is ready!\")]");
    private static final By SKIP_BUTTON = AppiumBy.xpath("//android.widget.TextView[contains(@text, \"Skip\")]");

    public SuccessWalletReadyScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Button skipButton() {
        return new Button(SKIP_BUTTON, "Skip button");
    }
}