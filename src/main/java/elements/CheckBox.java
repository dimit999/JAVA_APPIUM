package elements;

import base.BaseElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class CheckBox extends BaseElement {
    public CheckBox(AppiumDriver driver, WebElement element) {
        super(driver, element);
    }
    // Add checkbox-specific methods if needed
}
