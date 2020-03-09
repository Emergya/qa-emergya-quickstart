package com.emergya.utils;

import java.io.IOException;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This is the class that will handle the communication between our framework and CrossBrowserTesting
 * It is especially important when setting the results of a test which is also important to make a 
 * test stop and be able to start a new one.
 * Basically, this class contains the parameters to log in in CrossBrowserTesting and allows the user
 * to get them to send requests to CrossBrowserTestingAPI
 * @author Antonio Pérez <aoviedo@emergya.com>
 */

public class CrossBrowserInitialization {

    private static CrossBrowserInitialization instance = null;
    private static Logger log = Logger.getLogger(CrossBrowserInitialization.class);
    private String properties;
    private String remoteUserName;
    private String remoteUserKey;

    /**
     * Singleton pattern
     * 
     * @return a single instance
     */
    public static CrossBrowserInitialization getInstance() {
        if (instance == null) {
            instance = new CrossBrowserInitialization();
        }
        return instance;
    }

    /**
     * Reads properties when the class is instanced
     */
    private CrossBrowserInitialization() {
        this.readProperties();
    }

    /**
     * Read properties method
     */
    public void readProperties() {
        log.info("[log-Properties] " + this.getClass().getName()
                + "- Start readProperties test");

        properties = "test.properties";
        Properties prop = new Properties();

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            // Load a properties file
            prop.load(loader.getResourceAsStream(properties));

            // Get the property value
            remoteUserName = prop.getProperty("remoteUserName");
            remoteUserKey = prop.getProperty("remoteUserKey");
        } catch (IOException ex) {
            log.error(ex.getMessage());
            log.error("Exception: " + ex);
            log.error(
                    "test.properties file is not found. If this is the first time you excuted your test you can copy the settings properties file in the test folder in svn and customized it to match your environment");
        }

        log.info("[log-Properties] " + this.getClass().getName()
                + "- End readProperties test");
    }

    // **** Getters methods section ****//
    public String getRemoteUserName() {
        return remoteUserName;
    }

    public String getRemoteUserKey() {
        return remoteUserKey;
    }

}
