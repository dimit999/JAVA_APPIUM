package screens.ios.introScreens;

import elements.Button;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.introScreens.CreatePasscodeScreen;

public class CreatePasscodeScreenIOS extends CreatePasscodeScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget.TextView[contains(@text, \"Create passcode\")]");

    public CreatePasscodeScreenIOS() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Button passCodeNumber(String number) {
        By passcodeButton = AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", number));
        return new Button(passcodeButton, "Passcode number button");
    }
}