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
//        launchDefaultFirstScreen();
    }
//
//    // Unified method to launch first screen for any platform
//    protected void launchDefaultFirstScreen() {
//        BaseScreen firstScreen = screenFactory.walletEntryPage();
//        assert firstScreen.isAt();
//    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
//        if (driver != null) {
//            driver.quit();
//        }
    }
}
