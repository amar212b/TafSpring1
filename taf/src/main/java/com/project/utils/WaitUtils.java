package com.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitUtils {
    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private WaitUtils() {
        // Prevent instantiation of utility class
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    /**
     * Waits for an element to be visible using medium wait.
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        logger.info("Waiting for element to be visible: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable using medium wait.
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        logger.info("Waiting for element to be clickable: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for text to be present in an element using medium wait.
     */
    public static boolean waitForTextInElement(WebDriver driver, By locator, String text) {
        logger.info("Waiting for text '{}' in element: {}", text, locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Waits for an element using a short duration (5s).
     */
    public static WebElement shortWaitForElement(WebDriver driver, By locator) {
        logger.info("Short wait (5s) for element: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element using a long duration (20s).
     */
    public static WebElement longWaitForElement(WebDriver driver, By locator) {
        logger.info("Long wait (20s) for element: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Implements a Fluent Wait for elements.
     */
    public static WebElement fluentWaitForElement(WebDriver driver, By locator, int timeoutInSeconds, int pollingInMillis) {
        logger.info("Fluent wait for element: {} (Timeout: {}s, Polling: {}ms)", locator, timeoutInSeconds, pollingInMillis);

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }
}
