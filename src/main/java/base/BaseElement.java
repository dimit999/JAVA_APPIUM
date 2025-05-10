package base;

import config.Config;
import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.ArrayList;
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

    private static final String POINTER_INPUT_NAME = "finger";

    public String getText() {
        return driver.findElement(selector).getText();
    }

    public WebElement getElement() {
        return driver.findElement(selector);
    }

    public boolean isDisplayed() {
        try {
            return driver.findElement(selector).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getElements(By locator, int timeoutSeconds) {
        Waiters.waitFor(() -> !driver.findElements(locator).isEmpty(), timeoutSeconds,
                "Elements not found: " + locator);
        return driver.findElements(locator);
    }

    public List<WebElement> getElements() {
        return driver.findElements(selector);
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
            } catch (NoSuchElementException ignored) {
                return null;
            }
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

    public void swipeDown() {
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
                WebElement element = driver.findElement(selector);
                int x = element.getLocation().getX();
                int y = element.getLocation().getY();

                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, POINTER_INPUT_NAME);
                Sequence longPress = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(new Pause(finger, Duration.ofMillis(durationMs)))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(List.of(longPress));
                return true;
            } catch (Exception ignored) {
                return false;
            }
        }, timeoutSeconds, "Can't long tap element");
    }

    public void tapViaCoordinates(int timeoutSeconds) {
        Waiters.waitFor(() -> {
            try {
                WebElement element = driver.findElement(selector);
                int x = element.getLocation().getX();
                int y = element.getLocation().getY();

                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, POINTER_INPUT_NAME);
                Sequence tap = new Sequence(finger, 1)
                        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                driver.perform(List.of(tap));
                return true;
            } catch (Exception ignored) {
                return false;
            }
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

    public List<String> getElementsText() {
        List<WebElement> elements = driver.findElements(selector);
        List<String> texts = new ArrayList<>();
        for (WebElement el : elements) {
            texts.add(el.getText());
        }
        return texts;
    }

    public Point getLocation() {
        Waiters.waitFor(() -> {
            try {
                WebElement element = driver.findElement(selector);
                return element.isDisplayed();
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                return false;
            }
        }, Config.DEFAULT_TIMEOUT, "Element not present or not visible for location retrieval: " + selector);

        try {
            return driver.findElement(selector).getLocation();
        } catch (StaleElementReferenceException e) {
            return getLocation();
        }
    }

    /**
     * Wait until the element's location is stable (does not change between polls).
     *
     * @param timeoutSeconds      How long to wait before giving up
     * @param pollFrequencyMillis How often to poll the location
     */
    @SuppressWarnings("BusyWait")
    public void waitUntilLocationStable(int timeoutSeconds, int pollFrequencyMillis) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeoutSeconds * 1000L) {
            Point firstLocation = getLocation();
            try {
                Thread.sleep(pollFrequencyMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            Point secondLocation = getLocation();
            if (firstLocation.equals(secondLocation)) {
                return;
            }
        }
    }

    /**
     * Overload with the default timeout and poll frequency.
     */
    public void waitUntilLocationStable() {
        waitUntilLocationStable(Config.DEFAULT_TIMEOUT, 200);
    }

    private void swipe(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, POINTER_INPUT_NAME);
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(500))) // equivalent to waitAction
                .addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }
}