//package com.project.factory;
//
//import com.project.utils.ConfigManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//@Component
//public class DriverFactory1 {
//    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
//    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
//    private final ConfigManager configManager;
//
//    @Autowired
//    public DriverFactory1(ConfigManager configManager) {
//        this.configManager = configManager;
//    }
//
//    public WebDriver getDriver() {
//        logger.info("Getting WebDriver for thread: {}", Thread.currentThread().getId());
//        return driverThreadLocal.get();
//    }
//
//    public void initializeDriver() {
//        if (driverThreadLocal.get() == null) {
//            String browser = configManager.getBrowser().toLowerCase();
//            boolean remoteExecution = configManager.isRemoteExecution();
//            WebDriver driver;
//
//            try {
//                if (remoteExecution) {
//                    driver = createRemoteDriver(browser);
//                } else {
//                    driver = createLocalDriver(browser);
//                }
//
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                driver.manage().window().maximize();
//                driverThreadLocal.set(driver);
//                logger.info("WebDriver initialized for thread: {}", Thread.currentThread().getId());
//
//            } catch (Exception e) {
//                logger.error("Error initializing WebDriver: {}", e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    private WebDriver createLocalDriver(String browser) {
//        logger.info("Initializing local WebDriver for browser: {}", browser);
//        switch (browser) {
//            case "firefox":
//                return new FirefoxDriver(new FirefoxOptions());
//            case "edge":
//                return new EdgeDriver(new EdgeOptions());
//            case "chrome":
//            default:
//                return new ChromeDriver(new ChromeOptions());
//        }
//    }
//
//    private WebDriver createRemoteDriver(String browser) throws MalformedURLException {
//        logger.info("Initializing remote WebDriver for browser: {}", browser);
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        switch (browser) {
//            case "firefox":
//                capabilities.setBrowserName("firefox");
//                break;
//            case "edge":
//                capabilities.setBrowserName("MicrosoftEdge");
//                break;
//            case "chrome":
//            default:
//                capabilities.setBrowserName("chrome");
//                break;
//        }
//
//        return new RemoteWebDriver(new URL(configManager.getRemoteGridUrl()), capabilities);
//    }
//
//    public void quitDriver() {
//        WebDriver driver = driverThreadLocal.get();
//        if (driver != null) {
//            logger.info("Quitting WebDriver for thread: {}", Thread.currentThread().getId());
//            driver.quit();
//            driverThreadLocal.remove();
//        }
//    }
//}
