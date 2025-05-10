package base;

import config.Config;
import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public abstract class BaseScreen {
    protected AppiumDriver driver;
    protected By screenLocator;

    public BaseScreen(By screenLocator) {
        this.driver = DriverManager.getDriver();
        this.screenLocator = screenLocator;
    }

    public boolean isOpened() {
        try {
            return driver.findElement(screenLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean waitForIsOpened() {
        return waitForIsOpened(Config.DEFAULT_TIMEOUT);
    }

    public boolean waitForIsOpened(int timeoutSeconds) {
        try {
            Waiters.waitFor(this::isOpened, timeoutSeconds, "Screen not opened: " + screenLocator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}