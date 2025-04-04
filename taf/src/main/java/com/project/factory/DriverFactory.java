package com.project.factory;

import com.project.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@Configuration  // Ensures this class is a Spring configuration
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private final ConfigManager configManager;

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @Autowired
    public DriverFactory(ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Registers WebDriver as a ThreadLocal Bean for Parallel Execution.
     */
    @Bean
    @Lazy
//    @Scope("cucumber-glue")  // ✅ Ensures it remains the same during a scen
    public WebDriver initializeDriver() {
        if (driverThreadLocal.get() == null) {
            String browser = configManager.getBrowser().toLowerCase();
            boolean headless = configManager.getHeadless();

            logger.info("Initializing WebDriver: browser={}, headless={}", browser, headless);
            WebDriver driver;

            switch (browser) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
            logger.info("WebDriver initialized successfully for thread: {}", Thread.currentThread().getId());
        }
        return driverThreadLocal.get();
    }

//    /**
//     * Retrieves the current WebDriver instance.
//     */
//    public WebDriver getCurrentDriver() {
//        return driverThreadLocal.get();
//    }

    /**
     * Quits WebDriver and removes from ThreadLocal storage.
     */
    public void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            logger.info("Quitting WebDriver for thread: {}", Thread.currentThread().getId());
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
