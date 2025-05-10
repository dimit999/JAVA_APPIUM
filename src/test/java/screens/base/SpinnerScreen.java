package screens.base;

import base.BaseScreen;
import base.Waiters;
import config.Config;
import elements.Label;
import org.openqa.selenium.By;


public abstract class SpinnerScreen extends BaseScreen {

    protected SpinnerScreen(By screenLocator) {
        super(screenLocator);
    }

    protected abstract Label spinner();

    public void waitForSpinnerToDisappear() {
        waitForSpinnerToDisappear(Config.DEFAULT_TIMEOUT);
    }

    public void waitForSpinnerToDisappear(int timeoutSeconds) {
        // Wait for spinner to appear (optional, improves reliability)
//        Waiters.waitFor(
//                () -> !spinner().getElements().isEmpty() && spinner().getElement().isDisplayed(),
//                3,
//                "Spinner did not appear"
//        );
        // Wait for spinner to disappear
        Waiters.waitFor(
                () -> spinner().getElements().isEmpty() || !spinner().getElement().isDisplayed(),
                timeoutSeconds,
                "Spinner did not disappear"
        );
    }
}
