package elements;

import base.BaseElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class Label extends BaseElement {
    public Label(AppiumDriver driver, WebElement element) {
        super(driver, element);
    }
    // Add label-specific methods if needed
}
