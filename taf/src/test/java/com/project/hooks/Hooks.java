package com.project.hooks;

import com.project.context.TestContext;
import com.project.utils.ExpressionResolver;
import com.project.utils.WaitUtils;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component // This is necessary for Spring to manage this class as a be
public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    private final TestContext testContext; // Add TestContext to initialize WebDriver
    private final ExpressionResolver expressionResolver;

    @Autowired
    public Hooks(ExpressionResolver expressionResolver, TestContext testContext) {
        this.expressionResolver = expressionResolver;
        this.testContext = testContext; // Inject TestContext
    }

    @Before(order = 1)  // ✅ Add order to ensure proper initialization sequence
    public void beforeScenario(Scenario scenario) {
        // Initialize WebDriver before each scenario
        logger.info("Initializing WebDriver using Hooks for Scenario: {}", scenario.getName());
        testContext.initializeDriver(); // Ensure the driver is initialized

        // Example: Setting a dynamic value before a scenario
//        expressionResolver.setVariable("val", "DynamicValue");
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario failed: {}", scenario.getName());
            }
        } finally {
            logger.info("Scenario completed: {}", scenario.getName());
            testContext.tearDown(); // Ensure tearDown happens in finally block
        }

    }
}
