package com.denniscorvers.recipeexporter;

import org.apache.logging.log4j.Logger;

public class MyLogger {

    private final Logger m_logger;

    public MyLogger(Logger logger) {
        m_logger = logger;
    }

    public void log(String message) {
        m_logger.info(message);
    }

    public void log(String message, Object... params) {
        m_logger.info(message, params);
    }

    public void error(String message) {
        m_logger.error(message);
    }

    public void error(String message, Object... params) {
        m_logger.error(message, params);
    }
}
