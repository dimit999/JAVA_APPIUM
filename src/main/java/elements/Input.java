package elements;

import base.BaseElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class Input extends BaseElement {
    public Input(AppiumDriver driver, WebElement element) {
        super(driver, element);
    }

    public void setText(String text) {
        element.clear();
        element.sendKeys(text);
    }
}
