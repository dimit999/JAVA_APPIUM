package tests;

import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import screens.ScreenResolver;
import screens.base.ScreenFactory;

public abstract class BaseTest {
    protected AppiumDriver driver;
    protected ScreenFactory screenFactory;//

    @BeforeEach
    public void setUp() throws Exception {
        DriverManager.createDriver();
        screenFactory = new ScreenResolver().getFactory();
    }


    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
