package elements;

import base.BaseElement;
import org.openqa.selenium.By;

/**
 * Represents a label UI element.
 */
public class Label extends BaseElement {
    /**
     * Constructs a Label element.
     * @param selector the selector for the label.
     * @param name the name for reporting.
     */
    public Label(final By selector, final String name) {
        super(selector, name);
    }
    // Add label-specific methods if needed

}
