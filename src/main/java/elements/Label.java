package elements;

import base.BaseElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Label extends BaseElement {
    public Label(By selector, String name) {
        super(selector, name);
    }
    // Add label-specific methods if needed
}
