package tests;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import screens.base.MainScreen;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import steps.WalletCreationSteps;

@Tag("mobile")
@Epic("Wallet")
@Feature("Create")
public class CreateNewWalletTest extends BaseTest {

    @Test
    void CreateWalletTest() {
        WalletCreationSteps.createNewWallet(screenFactory);

        MainScreen mainScreen = screenFactory.mainScreen();
        String walletAmount = mainScreen.getWalletAmount();
        Assert.assertTrue(walletAmount.trim().contains("$0.00"));
    }
}
