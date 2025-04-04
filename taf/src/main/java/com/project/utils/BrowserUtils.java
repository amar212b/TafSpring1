package com.project.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {

    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void click(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
