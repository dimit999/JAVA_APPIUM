package base;

import config.Config;
import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

/**
 * Abstract base class for all screen/page objects.
 * Ensures each page waits until it is opened (ready for interaction).
 */
public abstract class BaseScreen {
    /** Appium driver instance. */
    private final AppiumDriver driver;
    /** Locator for the unique element that identifies the screen. */
    private final By screenLocator;

    /**
     * Constructs a BaseScreen and waits until the screen is opened.
     *
     * @param screenLocator the locator for the unique element identifying the screen
     */
    public BaseScreen(final By screenLocator) {
        this.driver = DriverManager.getDriver();
        this.screenLocator = screenLocator;
        waitForIsOpened(Config.DEFAULT_TIMEOUT);
    }

    /**
     * Returns the Appium driver instance.
     * @return the Appium driver
     */
    public AppiumDriver getDriver() {
        return driver;
    }

    /**
     * Returns the screen's unique locator.
     * @return the locator
     */
    public By getScreenLocator() {
        return screenLocator;
    }

    /**
     * Checks if the screen is opened (unique element is displayed).
     * Override in subclasses if needed.
     * @return true if opened, false otherwise
     */
    public boolean isOpened() {
        try {
            return driver.findElement(screenLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Waits until the screen is opened with the default timeout.
     * Override in subclasses if needed.
     * @return true if opened, false otherwise
     */
    public boolean waitForIsOpened() {
        return waitForIsOpened(Config.DEFAULT_TIMEOUT);
    }

    /**
     * Waits until the screen is opened with a custom timeout.
     * Override in subclasses if needed.
     * @param timeoutSeconds the timeout in seconds
     * @return true if opened, false otherwise
     */
    public boolean waitForIsOpened(final int timeoutSeconds) {
        try {
            Waiters.waitFor(this::isOpened, timeoutSeconds,
                "Screen not opened: " + screenLocator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}