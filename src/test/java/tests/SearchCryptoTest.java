package tests;

import device.DriverManager;
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

import static io.qameta.allure.Allure.step;

@Tag("mobile")
@Epic("Wallet")
@Feature("Crypto search")
class SearchCryptoTest extends BaseTest {

    @Test
    void SearchCryptoFunctionTest() {
        step("Create new Wallet");
        WalletCreationSteps.createNewWallet(screenFactory);

        step("Go to Search screen");
        MainScreen mainScreen = screenFactory.mainScreen();
        mainScreen.tapSearchButton();

        step("Put search criteria");
        SearchScreen searchScreen = screenFactory.searchScreen();
        String searchValue = "BTC";
        searchScreen.setSearchText(searchValue);
        DriverManager.hideKeyboardIfVisible();

        step("Wait spinner and check search results");
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
