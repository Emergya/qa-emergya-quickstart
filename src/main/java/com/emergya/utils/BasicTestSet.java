package com.emergya.utils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.emergya.pageObjects.EmergyaContactPage;
import com.emergya.pageObjects.EmergyaMainPage;
import com.emergya.pageObjects.EmergyaWorkPage;
import com.emergya.pageObjects.GoogleMainPage;
import com.emergya.selenium.testSet.DefaultTestSet;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * PageObjects variable references
 * @author Jose Antonio Sanchez <jasanchez@emergya.com>
 * @author Antonio Pérez <aoviedo@emergya.com>
 * In this version where we connect to CrossBrowserTesting to perform our tests, this class has a new meaning
 * Before we included here protected attributes representing the page objects we will need for our tests.
 * Now it also handles the responsibility of setting our tests to pass or fail to CrossBrowserTesting API
 * also controlling how tests end now too
 */
public abstract class BasicTestSet extends DefaultTestSet {
    protected GoogleMainPage googleMainPage;
    protected EmergyaMainPage emergyaMainPage;
    protected EmergyaContactPage emergyaContactPage;
    protected EmergyaWorkPage emergyaWorkPage;
    // ************************ After Methods *************************
    /**
     * Now this method will set our tests to pass or fail in CrossBrowserTesting's API after our tests methods
     * have been executed. It is really important not to overwrite the annotation @AfterMethod with another method 
     * because in that case we could cause an error. We only could override this annotation if we also 
     * execute this method in the method that overrides it
     * @param result
     */
    @AfterMethod(alwaysRun = true)
    public void setRemoteTestStatus(ITestResult result) {
        log.info("---------- setRemoteTestStatus - Start ----------");
        if ("Remote".equalsIgnoreCase(config.getBrowser())) {
        	try {
        		
        		switch (result.getStatus()) {
                case ITestResult.SUCCESS:
                    log.info("======PASS=====");
                    setScore(driver.getSessionId().toString(), "pass");
                    break;

                case ITestResult.FAILURE:
                    log.info("======FAIL=====");
                    setScore(driver.getSessionId().toString(), "fail");
                    break;

                case ITestResult.SKIP:
                    log.info("======SKIP BLOCKED=====");
                    setScore(driver.getSessionId().toString(), "unset");
                    break;

                default:
                    throw new RuntimeException("Invalid status");
                }
        	} catch (Exception e) {
        		System.out.println(e);
        		System.out.println(e.getStackTrace());
			}
        	finally {
        		log.info("---------- setRemoteTestStatus - End ----------");
       
        	}
        }
    }

    // ************************ Private Methods *************************
    /**
     * This is the method that does the real task of sending a request to CrossBrowserTesting's API
     * to set our tests pass or fail
     * @param seleniumTestId
     * @param score
     * @return
     */
    private JsonNode setScore(String seleniumTestId, String score) {
        // Mark a Selenium test as Pass/Fail
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.put(
                    "http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
                    .basicAuth(
                            CrossBrowserInitialization.getInstance().getRemoteUserName(),
                            CrossBrowserInitialization.getInstance().getRemoteUserKey())
                    .routeParam("seleniumTestId", seleniumTestId)
                    .field("action", "set_score").field("score", score)
                    .asJson();
        } catch (UnirestException e) {
            log.error(e.getMessage());
        }
        return response.getBody();
    }
}
