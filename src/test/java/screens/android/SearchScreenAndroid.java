package screens.android;

import elements.Button;
import elements.Input;
import elements.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import screens.base.MainScreen;
import screens.base.SearchScreen;

public class SearchScreenAndroid extends SearchScreen {
    private static final By SCREEN_LOCATOR = AppiumBy.xpath("//android.widget.TextView[contains(@text, \"Search\")]");
    private static final By SEARCH_RESULT_TITLES = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"itemTitle\"]");
    private static final By SEARCH_INPUT = AppiumBy.className("android.widget.EditText");

    public SearchScreenAndroid() {
        super(SCREEN_LOCATOR);
    }

    @Override
    protected Input searchInput() {
        return new Input(SEARCH_INPUT, "Search input");
    }

    @Override
    protected Label searchResultTitles() {
        return new Label(SEARCH_RESULT_TITLES, "Search result titles");
    }
}