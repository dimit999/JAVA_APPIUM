package screens.ios;

import screens.android.SpinnerScreenAndroid;
import screens.base.MainScreen;
import screens.base.ScreenFactory;
import screens.base.SearchScreen;
import screens.base.SpinnerScreen;
import screens.base.introScreens.*;
import screens.ios.introScreens.*;

public class IosScreenFactory implements ScreenFactory {

    @Override
    public WalletEntryScreen walletEntryScreen() {
        return new WalletEntryScreenIOS();
    }

    @Override
    public CreatePasscodeScreen createPasscodeScreen() {
        return new CreatePasscodeScreenIOS();
    }

    @Override
    public ConfirmPasscodeScreen confirmPasscodeScreen() {
        return new ConfirmPasscodeScreenIOS();
    }

    @Override
    public EntryNotificationScreen entryNotificationScreen() {
        return new EntryNotificationScreenIOS();
    }

    @Override
    public SuccessWalletReadyScreen successWalletReadyScreen() {
        return new SuccessWalletReadyScreenIOS();
    }

    @Override
    public WhatIsNewScreen whatIsNewScreen() {
        return new WhatIsNewScreenIOS();
    }

    @Override
    public MainScreen mainScreen() {
        return new MainScreenIOS();
    }

    @Override
    public SearchScreen searchScreen() {
        return new SearchScreenIOS();
    }

    @Override
    public SpinnerScreen spinnerScreen() {
        return new SpinnerScreenIOS();
    }
}