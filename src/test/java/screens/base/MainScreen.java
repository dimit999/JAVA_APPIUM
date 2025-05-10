package screens.base;

import base.BaseScreen;
import elements.Button;
import elements.Label;
import org.openqa.selenium.By;

public abstract class MainScreen extends BaseScreen {

    protected MainScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Label walletAmount();


    protected abstract Button searchButton();

    public String getWalletAmount() {
        return walletAmount().getText();
    }

    public void tapSearchButton() {
        searchButton().tap();
    }

}
