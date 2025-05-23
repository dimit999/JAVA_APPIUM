package screens.base.introScreens;

import base.BaseScreen;
import elements.Button;
import org.openqa.selenium.By;

public abstract class CreatePasscodeScreen extends BaseScreen {

    protected CreatePasscodeScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Button passCodeNumber(String number);

    public void tapPassCodeNumber(String number) {
        passCodeNumber(number).tap();
    }

}
