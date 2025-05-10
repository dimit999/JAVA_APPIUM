package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import screens.base.MainScreen;
import screens.base.SearchScreen;
import screens.base.SpinnerScreen;
import screens.base.introScreens.*;
import java.util.List;

@Tag("mobile")
class SearchCryptoTest extends BaseTest {

    @Test
    void SearchCryptoFunctionTest() {
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
        if (whatIsNewScreen.waitForIsOpened()) {
            whatIsNewScreen.tapBackButton();
        }

        MainScreen mainScreen = screenFactory.mainScreen();
        mainScreen.tapSearchButton();

        SearchScreen searchScreen = screenFactory.searchScreen();
        String searchValue = "BTC";
        searchScreen.setSearchText(searchValue);

        SpinnerScreen spinnerScreen = screenFactory.spinnerScreen();
        spinnerScreen.waitForSpinnerToDisappear();
        searchScreen.waitForIsOpened();

        List<String> resultTitles = searchScreen.getSearchResultTitles();
        for (String title : resultTitles) {
            Assertions.assertTrue(
                    title.toLowerCase().contains(searchValue.toLowerCase()),
                    "Search result does not contain search value. Failed item is: " + title
            );
        }
    }
}
