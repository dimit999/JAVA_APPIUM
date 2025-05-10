package base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.util.function.BooleanSupplier;

/**
 * Utility class for custom wait conditions.
 * Provides methods to wait for boolean expressions or fixed timeouts.
 */
public final class Waiters {
    /**
     * Polling interval in milliseconds (500ms).
     */
    private static final long POLL_INTERVAL_MS = 500L;

    /**
     * Multiplier to convert seconds to milliseconds.
     */
    private static final int SECOND_TO_MILLIS = 1000;

    /**
     * Private constructor to prevent instantiation.
     */
    private Waiters() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    /**
     * Waits until the given expression returns true or the timeout expires.
     * Throws TimeoutException with the given message if the condition is not met.
     *
     * @param expression     the boolean condition to evaluate
     * @param timeoutSeconds the timeout in seconds
     * @param message        the exception message on timeout
     * @throws TimeoutException if the condition is not met in time
     */
    public static void waitFor(final BooleanSupplier expression, final int timeoutSeconds,
                               final String message) {
        long end = System.currentTimeMillis() + (long) timeoutSeconds * SECOND_TO_MILLIS;
        Throwable lastException = null;
        while (System.currentTimeMillis() < end) {
            try {
                if (expression.getAsBoolean()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                lastException = e;
            }
            try {
                Thread.sleep(POLL_INTERVAL_MS);
            } catch (InterruptedException ignored) {
                // Ignore interruption
            }
        }
        throw new TimeoutException(message, lastException);
    }

    /**
     * Waits for the given expression to become true, but only for a fixed timeout.
     * Returns true if the condition is met, false otherwise.
     *
     * @param expression     the boolean condition to evaluate
     * @param timeoutSeconds the timeout in seconds
     * @return true if the condition is met, false otherwise
     */
    public static boolean waitForFixedTime(final BooleanSupplier expression, final int timeoutSeconds) {
        long end = System.currentTimeMillis() + (long) timeoutSeconds * SECOND_TO_MILLIS;
        while (System.currentTimeMillis() < end) {
            try {
                if (expression.getAsBoolean()) {
                    return true;
                }
            } catch (NoSuchElementException ignored) {
                // Ignore
            }
            try {
                Thread.sleep(POLL_INTERVAL_MS);
            } catch (InterruptedException ignored) {
                // Ignore interruption
            }
        }
        return false;
    }
}
