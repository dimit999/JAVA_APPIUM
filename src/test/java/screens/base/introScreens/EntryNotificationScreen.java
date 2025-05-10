package screens.base.introScreens;

import base.BaseScreen;
import elements.Button;
import org.openqa.selenium.By;

public abstract class EntryNotificationScreen extends BaseScreen {

    protected EntryNotificationScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Button skipNotifications();

    public void tapSkipNotifications() {
        skipNotifications().tap();
    }

}
