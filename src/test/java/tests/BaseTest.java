package tests;

import device.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import screens.ScreenResolver;
import screens.base.ScreenFactory;

public abstract class BaseTest {
    protected AppiumDriver driver;
    protected ScreenFactory screenFactory;//

    @Before
    public void setUp() throws Exception {
        DriverManager.createDriver();
        screenFactory = new ScreenResolver().getFactory();
    }


    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
