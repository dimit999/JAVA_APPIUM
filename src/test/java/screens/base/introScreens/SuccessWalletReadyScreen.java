package screens.base.introScreens;

import base.BaseScreen;
import elements.Button;
import org.openqa.selenium.By;

public abstract class SuccessWalletReadyScreen extends BaseScreen {

    protected SuccessWalletReadyScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Button skipButton();

    public void tapSkipButton() {
        skipButton().tap();
    }

}
