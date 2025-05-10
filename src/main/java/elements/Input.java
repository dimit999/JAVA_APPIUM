package elements;

import base.BaseElement;
import org.openqa.selenium.By;

/**
 * Represents an input UI element.
 */
public final class Input extends BaseElement {
    /**
     * Constructs an Input element.
     * @param selector the selector for the input.
     * @param name the name for reporting.
     */
    public Input(final By selector, final String name) {
        super(selector, name);
    }

    /**
     * Sets the text value of the input.
     * @param text the text to set.
     */
    public void setText(final String text) {
        driver.findElement(selector).clear();
        driver.findElement(selector).sendKeys(text);
    }
}
