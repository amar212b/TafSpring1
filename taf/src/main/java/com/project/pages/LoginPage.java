package com.project.pages;

import com.project.utils.LoggingUtils;
import com.project.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    private static final org.apache.logging.log4j.Logger logger = LoggingUtils.getLogger(LoginPage.class);

    private final WebDriver driver; // WebDriver is injected via constructor


    private final By usernameField = By.cssSelector("input[name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By dashboardHeader = By.xpath("//*[@class='oxd-topbar-header']");

    private final By loginErrorMessage = By.cssSelector("oxd-alert-content oxd-alert-content--error");
    // Constructor injection for WebDriver
    @Autowired
    public LoginPage(WebDriver driver) {
        this.driver = driver; // Assign injected WebDriver to the class field
    }

    public void login(String username, String password) {
        logger.info("Logging in with username: {}", username);
        WaitUtils.waitForElementVisible(driver, usernameField, 10).sendKeys(username);
        WaitUtils.waitForElementVisible(driver, passwordField, 10).sendKeys(password);
        WaitUtils.waitForElementClickable(driver, loginButton, 10).click();
    }

    public boolean isDashboardVisible() {
        WebElement dashboard = WaitUtils.waitForElementVisible(driver, dashboardHeader, 10);
        boolean isVisible = dashboard.isDisplayed();
        logger.info("Dashboard visibility: {}", isVisible);
        return isVisible;
    }

    public boolean isLoginErrorMessageVisible() {
        WebElement errorMessage = WaitUtils.waitForElementVisible(driver, loginErrorMessage, 10);
        boolean isVisible = errorMessage.isDisplayed();
        logger.info("Login error message visibility: {}", isVisible);
        return isVisible;
    }
}
