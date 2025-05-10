package screens.android.introScreens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import elements.Button;
import screens.base.introScreens.WalletEntryScreen;

public class WalletEntryScreenAndroid extends WalletEntryScreen {
    private static final String CREATE_WALLET_XPATH = "//android.view.View[@resource-id=\"CreateNewWalletButton\"]";
    private static final By SCREEN_LOCATOR = AppiumBy.xpath(CREATE_WALLET_XPATH);
    private static final By CREATE_WALLET_BUTTON = AppiumBy.xpath(CREATE_WALLET_XPATH);

    public WalletEntryScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Button createNewWallet() {
        return new Button(CREATE_WALLET_BUTTON, "Create New Wallet button");
    }
}