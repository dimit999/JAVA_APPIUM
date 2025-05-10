package screens.ios.introScreens;

import elements.Button;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.introScreens.WalletEntryScreen;


public class WalletEntryScreenIOS extends WalletEntryScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.view.View[@resource-id=\"CreateNewWalletButton\"]");
    private static final By BTN_MESSAGES_LOCATOR = AppiumBy.xpath("//android.view.View[@resource-id=\"CreateNewWalletButton\"]");

    public WalletEntryScreenIOS() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Button createNewWallet() {
        return new Button(BTN_MESSAGES_LOCATOR, "Messages");
    }
}
