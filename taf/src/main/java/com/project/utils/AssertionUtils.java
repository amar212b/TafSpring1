package com.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class AssertionUtils {

    private static final Logger logger = LogManager.getLogger(AssertionUtils.class);

    /**
     * Asserts that a condition is true with logging.
     *
     * @param condition The condition to assert.
     * @param message   The message to log and use for assertion failure.
     */
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            logger.error("Assertion failed: {}", message);
        }
        MatcherAssert.assertThat(message, condition, Matchers.is(true));
        logger.info("Assertion passed: {}", message);
    }

    /**
     * Asserts that two objects are equal.
     *
     * @param actual   The actual value.
     * @param expected The expected value.
     * @param message  The assertion message.
     */
    public static void assertEquals(Object actual, Object expected, String message) {
        if (!actual.equals(expected)) {
            logger.error("Assertion failed: {}", message);
        }
        MatcherAssert.assertThat(message, actual, Matchers.equalTo(expected));
        logger.info("Assertion passed: {} (actual: {}, expected: {})", message, actual, expected);
    }
}
