package screens.base.introScreens;

import base.BaseScreen;
import elements.Button;
import org.openqa.selenium.By;

public abstract class WhatIsNewScreen extends BaseScreen {

    protected WhatIsNewScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Button backButton();

    public void tapBackButton() {
        backButton().tap();
    }

}
