package screens.base.introScreens;

import base.BaseScreen;
import elements.Button;
import org.openqa.selenium.By;

public abstract class WalletEntryScreen extends BaseScreen {

    protected WalletEntryScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Button createNewWallet();

    public void tapCreateNewWallet() {
        createNewWallet().tap();
    }

}
