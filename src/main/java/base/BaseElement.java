package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    protected AppiumDriver driver;
    protected WebElement element;

    public BaseElement(AppiumDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public void click() {
        element.click();
    }

    public String getText() {
        return element.getText();
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }
}
