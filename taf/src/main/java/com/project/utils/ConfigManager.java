package com.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Manages environment configurations such as base URL, browser, and remote execution settings.
 */
@Component
public class ConfigManager {

    private static final Logger logger = LogManager.getLogger(ConfigManager.class);


    @Value("${base.url:#{null}}")
    private String baseUrl;

    @Value("${browser:chrome}")
    private String browser;

    @Value("${headless:false}")
    private boolean headless;

    @Value("${selenium.grid.enabled:false}")
    private boolean remoteExecution;

    @Value("${selenium.grid.url:#{null}}")
    private String remoteGridUrl;

    public String getBaseUrl() {
        if (baseUrl == null) {
            throw new RuntimeException("base.url is missing from application.properties");
        }
        logger.info("Fetching base URL: {}", baseUrl);
        return baseUrl;
    }

    public String getBrowser() {
        logger.info("Fetching browser: {}", browser);
        return browser;
    }

    public boolean getHeadless() {
        logger.info("Fetching headless: {}", headless);
        return headless;
    }

    public boolean isRemoteExecution() {
        logger.info("Remote Execution Enabled: {}", remoteExecution);
        return remoteExecution;
    }

    public String getRemoteGridUrl() {
        if (remoteExecution && remoteGridUrl == null) {
            throw new RuntimeException("Selenium Grid URL is missing in remote execution mode.");
        }
        logger.info("Fetching Selenium Grid URL: {}", remoteGridUrl);
        return remoteGridUrl;
    }
}

