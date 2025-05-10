package steps;

import config.Config;
import screens.base.ScreenFactory;
import screens.base.introScreens.*;

/**
 * Contains reusable steps for wallet creation flows.
 */
public class WalletCreationSteps {
    /**
     * Completes the wallet creation flow from entry to skipping "What's New".
     */
    public static void createNewWallet(ScreenFactory screenFactory) {
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
        if (whatIsNewScreen.waitForIsOpened(Config.DEFAULT_WAIT_POP_UP_TIMEOUT)) {
            whatIsNewScreen.tapBackButton();
        }
    }
}
