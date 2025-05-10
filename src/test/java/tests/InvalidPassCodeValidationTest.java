package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import screens.base.introScreens.*;

@Tag("mobile")
@Epic("Wallet")
@Feature("Passcode")
class InvalidPassCodeValidationTest extends BaseTest {

    @Test
    void PassCodeValidationTest() {
        WalletEntryScreen walletEntryScreen = screenFactory.walletEntryScreen();
        walletEntryScreen.tapCreateNewWallet();

        CreatePasscodeScreen createPasscodeScreen = screenFactory.createPasscodeScreen();
        for (int i = 1; i <= 6; i++) {
            createPasscodeScreen.tapPassCodeNumber(String.valueOf(i));
        }

        ConfirmPasscodeScreen confirmPasscodeScreen = screenFactory.confirmPasscodeScreen();
        for (int i = 2; i <= 7; i++) {
            confirmPasscodeScreen.tapPassCodeNumber(String.valueOf(i));
        }

        Assert.assertTrue(confirmPasscodeScreen.getPasscodeValidation());
    }
}
