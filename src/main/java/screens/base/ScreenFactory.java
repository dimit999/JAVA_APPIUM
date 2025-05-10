package screens.base;

import screens.base.introScreens.*;

public interface ScreenFactory {
    WalletEntryScreen walletEntryScreen();

    CreatePasscodeScreen createPasscodeScreen();

    ConfirmPasscodeScreen confirmPasscodeScreen();

    EntryNotificationScreen entryNotificationScreen();

    SuccessWalletReadyScreen successWalletReadyScreen();

    WhatIsNewScreen whatIsNewScreen();

    MainScreen mainScreen();
}

