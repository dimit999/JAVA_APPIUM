package elements;

import base.BaseElement;
import org.openqa.selenium.By;

public class Input extends BaseElement {
    public Input(By selector, String name) {
        super(selector, name);
    }

    public void setText(String text) {
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(text);
    }
}
