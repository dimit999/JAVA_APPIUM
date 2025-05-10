package elements;

import base.BaseElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Button extends BaseElement {
    public Button(By selector, String name) {
        super(selector, name);
    }
    // Add button-specific methods if needed
}
