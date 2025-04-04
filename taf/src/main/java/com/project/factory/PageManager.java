package com.project.factory;

import com.project.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Manages Page Object instances (Spring-Managed Beans).
 */
@Component
public class PageManager {

    private final WebDriver driver;
    private final LoginPage loginPage;

    @Autowired
    public PageManager(WebDriver driver, LoginPage loginPage) {
        this.driver = driver;
        this.loginPage = loginPage; // Injected via constructor
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}
