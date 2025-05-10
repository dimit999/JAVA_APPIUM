package screens.android.introScreens;

import elements.Button;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.introScreens.WhatIsNewScreen;

public class WhatIsNewScreenAndroid extends WhatIsNewScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget.TextView[@text=\"What's New\"]");
    private static final By SKIP_BUTTON = AppiumBy.xpath("//android.view.View[2]/android.view.View/android.widget.Button");

    public WhatIsNewScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Button backButton() {
        return new Button(SKIP_BUTTON, "Back button");
    }
}