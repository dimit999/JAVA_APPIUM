package elements;

import base.BaseElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {
    public Button(AppiumDriver driver, WebElement element) {
        super(driver, element);
    }
    // Add button-specific methods if needed
}
