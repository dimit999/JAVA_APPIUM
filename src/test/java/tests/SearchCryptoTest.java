package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import screens.base.MainScreen;
import screens.base.SearchScreen;
import screens.base.SpinnerScreen;
import steps.WalletCreationSteps;

import java.util.List;

@Tag("mobile")
@Epic("Wallet")
@Feature("Crypto search")
class SearchCryptoTest extends BaseTest {

    @Test
    void SearchCryptoFunctionTest() {
        WalletCreationSteps.createNewWallet(screenFactory);

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
