package screens.android;

import screens.android.introScreens.*;
import screens.base.*;
import screens.base.introScreens.*;

public class AndroidScreenFactory implements ScreenFactory {

    @Override
    public WalletEntryScreen walletEntryScreen() {
        return new WalletEntryScreenAndroid();
    }

    @Override
    public CreatePasscodeScreen createPasscodeScreen() {
        return new CreatePasscodeScreenAndroid();
    }

    @Override
    public ConfirmPasscodeScreen confirmPasscodeScreen() {
        return new ConfirmPasscodeScreenAndroid();
    }

    @Override
    public EntryNotificationScreen entryNotificationScreen() {
        return new EntryNotificationScreenAndroid();
    }

    @Override
    public SuccessWalletReadyScreen successWalletReadyScreen() {
        return new SuccessWalletReadyScreenAndroid();
    }

    @Override
    public WhatIsNewScreen whatIsNewScreen() {
        return new WhatIsNewScreenAndroid();
    }

    @Override
    public MainScreen mainScreen() {
        return new MainScreenAndroid();
    }

}
