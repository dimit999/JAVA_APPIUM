import junit.framework.Assert;
import org.junit.Test;
import screens.base.MainScreen;
import screens.base.introScreens.*;

public class CreateNewWalletTest extends BaseTest {

    @Test
    public void testCreateNewWalletTest() {
        WalletEntryScreen walletEntryScreen = screenFactory.walletEntryScreen();
        walletEntryScreen.tapCreateNewWallet();

        CreatePasscodeScreen createPasscodeScreen = screenFactory.createPasscodeScreen();
        for (int i = 1; i <= 6; i++) {
            createPasscodeScreen.tapPassCodeNumber(String.valueOf(i));
        }

        ConfirmPasscodeScreen confirmPasscodeScreen = screenFactory.confirmPasscodeScreen();
        for (int i = 1; i <= 6; i++) {
            confirmPasscodeScreen.tapPassCodeNumber(String.valueOf(i));
        }

        EntryNotificationScreen entryNotificationScreen = screenFactory.entryNotificationScreen();
        entryNotificationScreen.tapSkipNotifications();

        SuccessWalletReadyScreen successWalletReadyScreen = screenFactory.successWalletReadyScreen();
        successWalletReadyScreen.tapSkipButton();

        WhatIsNewScreen whatIsNewScreen = screenFactory.whatIsNewScreen();
        if (whatIsNewScreen.isOpened()) {
            whatIsNewScreen.tapBackButton();
        }

        MainScreen mainScreen = screenFactory.mainScreen();
        String walletAmount = mainScreen.getWalletAmount();
        Assert.assertTrue(walletAmount.trim().contains("$0.00"));


    }
}
