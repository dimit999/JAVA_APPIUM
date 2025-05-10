package elements;

import base.BaseElement;
import org.openqa.selenium.By;

/**
 * Represents a checkbox UI element.
 */
public class CheckBox extends BaseElement {
    /**
     * Constructs a CheckBox element.
     * @param selector the selector for the checkbox.
     * @param name the name for reporting.
     */
    public CheckBox(final By selector, final String name) {
        super(selector, name);
    }
    // Add checkbox-specific methods if needed

}
