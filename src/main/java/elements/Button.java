package elements;

import base.BaseElement;
import org.openqa.selenium.By;

/**
 * Represents a button UI element.
 */
public class Button extends BaseElement {
    /**
     * Constructs a Button element.
     * @param selector the selector for the button.
     * @param name the name for reporting.
     */
    public Button(final By selector, final String name) {
        super(selector, name);
    }
    // Add button-specific methods if needed

}
