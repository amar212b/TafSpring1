package com.project.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores data specific to a Cucumber scenario, enabling sharing between steps.
 */
@Component
public class ScenarioContext {
    private static final Logger logger = LogManager.getLogger(ScenarioContext.class);
    private final Map<String, Object> scenarioData = new HashMap<>();

    /**
     * Stores a value for a given key in the scenario context.
     *
     * @param key   Key to identify the data.
     * @param value Value to store.
     */
    public void set(String key, Object value) {
        logger.info("Setting scenario data for key: {}", key);
        scenarioData.put(key, value);
    }

    /**
     * Retrieves a value for a given key from the scenario context.
     *
     * @param key Key to identify the data.
     * @return Value associated with the key.
     */
    public Object get(String key) {
        logger.info("Getting scenario data for key: {}", key);
        return scenarioData.get(key);
    }

    /**
     * Checks if a key exists in the scenario context.
     *
     * @param key Key to check.
     * @return True if the key exists, false otherwise.
     */
    public boolean containsKey(String key) {
        logger.info("Checking if scenario data contains key: {}", key);
        return scenarioData.containsKey(key);
    }

    public void clear() {
        scenarioData.clear();
    }

    /**
     * Retrieves a value from the scenario context.
     */
    public <T> T get(String key, Class<T> type) {
        return type.cast(scenarioData.get(key));
    }
}
