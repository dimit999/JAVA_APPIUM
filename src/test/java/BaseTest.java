import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import base.BaseScreen;
import screens.ScreenResolver;
import screens.base.ScreenFactory;

public abstract class BaseTest {
    protected AppiumDriver driver;
    protected ScreenFactory screenFactory;//

    @Before
    public void setUp() throws Exception {
        driver = TestSetup.createDriver();
        screenFactory = new ScreenResolver(driver).getFactory();
        launchDefaultFirstScreen();
    }

    // Unified method to launch first screen for any platform
    protected void launchDefaultFirstScreen() {
        BaseScreen firstScreen = screenFactory.firstScreen();
        assert firstScreen.isAt();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
