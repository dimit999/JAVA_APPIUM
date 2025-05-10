package tests;

import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import screens.ScreenResolver;
import screens.base.ScreenFactory;

/**
 * Ensures that each test's full lifecycle (setup, test, teardown) is synchronized on the device lock.
 * This prevents parallel test overlap on a single device, following 2025 best practices for mobile automation.
 */
public abstract class BaseTest {
    protected AppiumDriver driver;
    protected ScreenFactory screenFactory;

    @BeforeEach
    public void setUp() throws Exception {
        synchronized (DriverManager.deviceLock) {
            DriverManager.createDriver();
            screenFactory = new ScreenResolver().getFactory();
            driver = DriverManager.getDriver();
        }
    }

    @AfterEach
    public void tearDown() {
        synchronized (DriverManager.deviceLock) {
            DriverManager.quitDriver();
        }
    }
}
