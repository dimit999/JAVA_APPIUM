package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import screens.base.introScreens.*;

import static io.qameta.allure.Allure.step;

@Tag("mobile")
@Epic("Wallet")
@Feature("Passcode")
class InvalidPassCodeValidationTest extends BaseTest {

    @Test
    void PassCodeValidationTest() {
        step("Create new Wallet");
        WalletEntryScreen walletEntryScreen = screenFactory.walletEntryScreen();
        walletEntryScreen.tapCreateNewWallet();

        step("Enter passcode first time");
        CreatePasscodeScreen createPasscodeScreen = screenFactory.createPasscodeScreen();
        for (int i = 1; i <= 6; i++) {
            createPasscodeScreen.tapPassCodeNumber(String.valueOf(i));
        }

        step("Enter passcode second time");
        ConfirmPasscodeScreen confirmPasscodeScreen = screenFactory.confirmPasscodeScreen();
        for (int i = 2; i <= 7; i++) {
            confirmPasscodeScreen.tapPassCodeNumber(String.valueOf(i));
        }

        step("Check that validation message is visible");
        Assert.assertTrue(confirmPasscodeScreen.getPasscodeValidation());
    }
}
