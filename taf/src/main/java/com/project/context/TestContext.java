package com.project.context;

import com.project.factory.DriverFactory;
import com.project.factory.PageManager;
import com.project.utils.ConfigManager;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Manages WebDriver, PageManager, and scenario context lifecycle across tests.
 */

//Why this works for parallel execution?
//
//        Each test thread has its own PageManager instance.
//        WebDriver is safely managed per thread.
//        Avoids test interference when multiple tests are running
@Component
public class TestContext {
    private static final Logger logger = LogManager.getLogger(TestContext.class);
    //    private WebDriver driver;  // Store WebDriver instance
    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();  // ✅ Use ThreadLocal


    /**
     * -- GETTER --
     * Retrieves ScenarioContext instance.
     */
    @Autowired
    private ScenarioContext scenarioContext;

    @Autowired
    private DriverFactory driverFactory;

    @Autowired
    private ConfigManager configManager;
    @Autowired
    private ApplicationContext applicationContext; // To get beans from Spring conte
    private final ThreadLocal<PageManager> pageManagerThreadLocal = new ThreadLocal<>();

    @Autowired
    public TestContext(ScenarioContext scenarioContext, DriverFactory driverFactory, ConfigManager configManager, ApplicationContext applicationContext) {
        this.scenarioContext = scenarioContext;
        this.driverFactory = driverFactory;
        this.configManager = configManager;
        this.applicationContext = applicationContext;
    }

    /**
     * Initializes WebDriver and PageManager.
     */
    public void initializeDriver() {
        // Change the check condition
        if (driverThreadLocal.get() == null) {  // Previously checking 'driverThreadLocal == null'
            logger.info("Initializing WebDriver and PageManager for thread: {}", Thread.currentThread().getId());
            applicationContext.getBean(WebDriver.class);  // ✅ Ensures WebDriver is created

            WebDriver driver = driverFactory.initializeDriver();
            driverThreadLocal.set(driver);
            // Note: Keep your PageManager configuration as is since you're using Spring DI
            PageManager pageManager = applicationContext.getBean(PageManager.class);
            pageManagerThreadLocal.set(pageManager);
            logger.info("WebDriver and PageManager initialized successfully.");
        }
    }

    /**
     * Retrieves the WebDriver instance.
     */
    /**
     * Retrieves the current WebDriver instance.
     */
    public WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            logger.warn("WebDriver is null. Reinitializing...");
            initializeDriver();
            driver = driverThreadLocal.get();
        }
        return driver;
    }

    /**
     * Retrieves the PageManager instance.
     */
    public PageManager getPageManager() {
        return pageManagerThreadLocal.get();
    }


    /**
     * Navigates to the application URL.
     */
    public void navigateToApplication() {
        String baseUrl = configManager.getBaseUrl();
        logger.info("Navigating to application URL: {}", baseUrl);
        getDriver().get(baseUrl);
    }

    /**
     * Cleans up WebDriver resources.
     */
    public void tearDown() {
        logger.info("Tearing down WebDriver and cleaning up resources for thread: {}", Thread.currentThread().getId());
        driverFactory.quitDriver();
        pageManagerThreadLocal.remove();
    }

}
