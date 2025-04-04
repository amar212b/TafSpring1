package com.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for resolving placeholders dynamically
 * and invoking methods without requiring class names.
 */
@Component
public class ExpressionResolver {

    private static final Logger logger = LogManager.getLogger(ExpressionResolver.class);
    private final Map<String, String> variables = new HashMap<>();
    private final List<Class<?>> dynamicClasses = new ArrayList<>();

    /**
     * Registers a class for method lookup.
     *
     * @param clazz The class to register.
     */
    public void registerDynamicClass(Class<?> clazz) {
        logger.info("Registering dynamic class for method lookup: {}", clazz.getName());
        dynamicClasses.add(clazz);
    }

    /**
     * Stores a key-value pair for resolution.
     *
     * @param key   Key of the variable.
     * @param value Value of the variable.
     */
    public void setVariable(String key, String value) {
        logger.info("Setting variable: key={}, value={}", key, value);
        variables.put(key, value);
    }

    /**
     * Resolves a placeholder in the format $variable or $methodName.
     *
     * @param input Input string with placeholders.
     * @return Resolved value.
     */
    public String resolve(String input) {
        if (input.startsWith("$")) {
            String keyOrMethod = input.substring(1); // Remove '$'
            if (variables.containsKey(keyOrMethod)) {
                logger.info("Resolving variable: key={}, value={}", keyOrMethod, variables.get(keyOrMethod));
                return variables.get(keyOrMethod);
            } else {
                return invokeMethodAcrossClasses(keyOrMethod); // Invoke method dynamically
            }
        }
        return input;
    }

    /**
     * Clears all stored variables.
     */
    public void clear() {
        logger.info("Clearing all variables");
        variables.clear();
    }

    /**
     * Searches for and invokes a method by name across all registered classes.
     *
     * @param methodName Name of the method to invoke.
     * @return Result of the method or the original placeholder if not found.
     */


    private String invokeMethodAcrossClasses(String methodExpression) {
        String[] parts = methodExpression.split("\\(");
        String methodName = parts[0];
        String[] args = parts.length > 1 ? parts[1].replace(")", "").split(",") : new String[0];

        for (Class<?> clazz : dynamicClasses) {
            try {
                // Find method with matching number of parameters
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName) && method.getParameterCount() == args.length) {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        Object result = method.invoke(instance, (Object[]) args);
                        return result.toString();
                    }
                }
            } catch (Exception e) {
                logger.error("Failed to invoke method: {} in class: {}", methodName, clazz.getName(), e);
            }
        }
        return "$" + methodExpression;
    }

}
