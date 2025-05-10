package screens.android.introScreens;

import elements.Button;
import elements.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.introScreens.ConfirmPasscodeScreen;

public class ConfirmPasscodeScreenAndroid extends ConfirmPasscodeScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget.TextView[@text=\"Confirm passcode\"]");
    private static final By INCORRECT_PASSCODE_VALIDATION = AppiumBy.xpath("//android.widget.TextView[@text=\"Those passwords didn’t match!\"]");

    public ConfirmPasscodeScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Label passcodeValidation() {
        return new Label(INCORRECT_PASSCODE_VALIDATION, "Incorrect Passcode validation");
    }

    @Override
    protected Button passCodeNumber(String number) {
        By passcodeButton = AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", number));
        return new Button(passcodeButton, "Passcode number button");
    }
}