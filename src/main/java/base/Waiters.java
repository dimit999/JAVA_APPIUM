package base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.util.function.BooleanSupplier;

public class Waiters {
    public static void waitFor(BooleanSupplier expression, int timeoutSeconds, String message) {
        long end = System.currentTimeMillis() + timeoutSeconds * 1000L;
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
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
        throw new TimeoutException(message, lastException);
    }

    public static boolean waitForFixedTime(BooleanSupplier expression, int timeoutSeconds) {
        long end = System.currentTimeMillis() + timeoutSeconds * 1000L;
        while (System.currentTimeMillis() < end) {
            try {
                if (expression.getAsBoolean()) {
                    return true;
                }
            } catch (NoSuchElementException ignored) {}
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
        return false;
    }
}
