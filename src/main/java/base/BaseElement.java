package base;

import config.Config;
import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import base.Waiters;
import java.time.Duration;
import java.util.List;

public abstract class BaseElement {
    protected AppiumDriver driver;
    protected By selector;
    protected String name;

    protected BaseElement(By selector, String name) {
        this.driver = DriverManager.getDriver();
        this.selector = selector;
        this.name = name;
    }

    public String getText() {
        return driver.findElement(selector).getText();
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(selector).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

//    public void click() {
//        driver.findElement(selector).click();
//    }

    public List<WebElement> getElements(By locator, int timeoutSeconds) {
        Waiters.waitFor(() -> !driver.findElements(locator).isEmpty(), timeoutSeconds, "Elements not found: " + locator);
        return driver.findElements(locator);
    }

    public WebElement scrollToElement(By locator, int timeoutSeconds, int maxSwipes) {
        int swipes = 0;
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.4);
        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000L;
        while (System.currentTimeMillis() < endTime && swipes < maxSwipes) {
            try {
                WebElement el = driver.findElement(locator);
                if (el.isDisplayed()) {
                    return el;
                }
            } catch (NoSuchElementException ignored) {}
            swipe(startX, startY, startX, endY);
            swipes++;
        }
        throw new NoSuchElementException("Element not found after swiping: " + locator);
    }

    public void scrollToText(String text) {
        String platform = driver.getCapabilities().getCapability("platformName").toString().toLowerCase();
        if (platform.contains("android")) {
            driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"
            ));
        } else if (platform.contains("ios")) {
            driver.findElement(AppiumBy.iOSNsPredicateString("label CONTAINS '" + text + "'"));
        } else {
            throw new RuntimeException("Platform not supported for scrollToText");
        }
    }

    public void swipeDown(int timeoutSeconds) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.4);
        swipe(startX, startY, startX, endY);
    }

    public void tap() {
        tap(Config.DEFAULT_TIMEOUT);
    }

    public void tap(int timeoutSeconds) {
        Waiters.waitFor(() -> {
            try {
                driver.findElement(selector).click();
                return true;
            } catch (Exception ignored) { return false; }
        }, timeoutSeconds, "Can't tap element");
    }

    public void longTap(int durationMs, int timeoutSeconds) {
        Waiters.waitFor(() -> {
            try {
                new TouchAction<>((PerformsTouchActions) driver)
                    .longPress(PointOption.point(driver.findElement(selector).getLocation()))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationMs)))
                    .release().perform();
                return true;
            } catch (Exception ignored) { return false; }
        }, timeoutSeconds, "Can't long tap element");
    }

    public void tapViaCoordinates(int timeoutSeconds) {
        Waiters.waitFor(() -> {
            try {
                new TouchAction<>((PerformsTouchActions) driver)
                    .tap(PointOption.point(driver.findElement(selector).getLocation()))
                    .perform();
                return true;
            } catch (Exception ignored) { return false; }
        }, timeoutSeconds, "Can't tap via coordinates");
    }

    public void actionTap(int timeoutSeconds) {
        Waiters.waitFor(() -> {
            try {
                new Actions(driver).moveToElement(driver.findElement(selector)).click().perform();
                return true;
            } catch (Exception ignored) { return false; }
        }, timeoutSeconds, "Can't action tap element");
    }

    private void swipe(int startX, int startY, int endX, int endY) {
        new TouchAction<>((PerformsTouchActions) driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(PointOption.point(endX, endY))
            .release().perform();
    }
}
