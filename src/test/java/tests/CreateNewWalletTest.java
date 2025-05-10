package tests;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import screens.base.MainScreen;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import steps.WalletCreationSteps;

import static io.qameta.allure.Allure.step;

@Tag("mobile")
@Epic("Wallet")
@Feature("Create")
public class CreateNewWalletTest extends BaseTest {

    @Test
    void CreateWalletTest() {
        step("Create new Wallet");
        WalletCreationSteps.createNewWallet(screenFactory);

        step("Validate default total amount");
        MainScreen mainScreen = screenFactory.mainScreen();
        String walletAmount = mainScreen.getWalletAmount();
        Assert.assertTrue(walletAmount.trim().contains("$0.00"));
    }
}
