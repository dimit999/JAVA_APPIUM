package screens.ios.introScreens;

import elements.Button;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.introScreens.EntryNotificationScreen;

public class EntryNotificationScreenIOS extends EntryNotificationScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget.TextView[contains(@text, \"Turn on notifications\")]");
    private static final By SKIP_NOTIFICATIONS = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"secondaryAction\"]");

    public EntryNotificationScreenIOS() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Button skipNotifications() {
        return new Button(SKIP_NOTIFICATIONS, "Skip notifications button");
    }
}