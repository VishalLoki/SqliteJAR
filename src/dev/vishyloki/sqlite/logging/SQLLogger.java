package dev.vishyloki.sqlite.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SQLLogger - shows log in the Android's Logcat
 * @author vishal
 */
public class SQLLogger {

    /**
     * Used for normal, informational messages about what the app is doing
     * Debugging, Showing progress, Printing variable values
     */
    public void log(String className, String methodName, String logMessage) {
        String logString = "[" + className + "][" + methodName + "][LOG] -- (" + logMessage + ")";
        Logger.getLogger(className).log(Level.INFO, logString);
    }

    /**
     * Used to indicate something unexpected happened, but it’s not critical — the app can still continue
     * Missing optional configuration
     * Deprecated API usage
     * Recoverable issues
     * List Empty issues
     */
    public void warn(String className, String methodName, String logMessage) {
        String logString = "[" + className + "][" + methodName + "][WARN] -- (" + logMessage + ")";
        Logger.getLogger(className).log(Level.WARNING, logString);
    }

    /**
     * Indicates a serious issue that likely prevents part of the program from working correctly
     * Exceptions
     * Database connection failure
     * Missing required resources
     */
    public void error(String className, String methodName, String logMessage) {
        String logString = "[" + className + "][" + methodName + "][ERROR] -- (" + logMessage + ")";
        Logger.getLogger(className).log(Level.SEVERE, logString);
    }
}