package screens.base.introScreens;

import base.BaseScreen;
import elements.Button;
import elements.Label;
import org.openqa.selenium.By;

public abstract class ConfirmPasscodeScreen extends BaseScreen {

    protected ConfirmPasscodeScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Label passcodeValidation();

    protected abstract Button passCodeNumber(String number);

    public void tapPassCodeNumber(String number) {
        passCodeNumber(number).tap();
    }

    public boolean getPasscodeValidation() {
        passcodeValidation().waitUntilLocationStable();
       return  passcodeValidation().isDisplayed();
    }
}
