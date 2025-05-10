package screens.base;

import base.BaseScreen;
import base.Waiters;
import config.Config;
import elements.Input;
import elements.Label;
import org.openqa.selenium.By;

import java.util.List;

public abstract class SearchScreen extends BaseScreen {

    protected SearchScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Input searchInput();

    protected abstract Label searchResultTitles();

    public void setSearchText(String text) {
        searchInput().setText(text);
    }

    public List<String> getSearchResultTitles() {
        searchResultTitles().waitUntilLocationStable();
        Waiters.waitFor(
                () -> !searchResultTitles().getElements().isEmpty(),
                Config.DEFAULT_TIMEOUT,
                "No search result titles found"
        );
        return searchResultTitles().getElementsText();
    }

}
