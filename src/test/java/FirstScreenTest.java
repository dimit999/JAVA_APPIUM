import org.junit.Test;
import org.junit.Assert;
import screens.base.FirstScreen;

public class FirstScreenTest extends BaseTest {

    @Test
    public void testFirstScreenTitleVisible() {
        FirstScreen firstLaunchScreen = screenFactory.firstScreen();
        Assert.assertTrue(firstLaunchScreen.isAt());
    }
}
