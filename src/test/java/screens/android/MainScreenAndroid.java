package screens.android;

import elements.Button;
import elements.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.MainScreen;

public class MainScreenAndroid extends MainScreen {
    private static final String WALLET_AMOUNT_XPATH = "//android.widget.TextView[@resource-id=\"mainBalance\"]";
    private static final By SCREEN_LOCATOR = AppiumBy.xpath(WALLET_AMOUNT_XPATH);
    private static final By WALLET_AMOUNT = AppiumBy.xpath(WALLET_AMOUNT_XPATH);
    private static final By SEARCH_BUTTON = AppiumBy.xpath("//android.widget.Button[@resource-id=\"topBarSearchIcon\"]");

    public MainScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Label walletAmount() {
        return new Label(WALLET_AMOUNT, "Wallet Amount");
    }

    @Override
    protected Button searchButton() {
        return new Button(SEARCH_BUTTON, "Search button");
    }
}