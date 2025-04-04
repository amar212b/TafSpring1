package com.project.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingUtils {

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static void logInfo(Logger logger, String message) {
        logger.info(message);
    }

    public static void logError(Logger logger, String message, Throwable t) {
        logger.error(message, t);
    }

    public static void logDebug(Logger logger, String message) {
        logger.debug(message);
    }
}
