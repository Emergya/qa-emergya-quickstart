package com.emergya.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.emergya.selenium.drivers.EmergyaWebDriver;
import com.emergya.selenium.pageObject.BasePageObject;

public class EmergyaWorkPage extends BasePageObject {

    /**
     * Logger class initialization.
     */
    static Logger log = Logger.getLogger(GoogleMainPage.class);

    /**
     * Items keys selectors.
     */
    private static final String TITLE = "title";
    /**
     * Constructor method
     * @param driver selenium webdriver
     */
    public EmergyaWorkPage(EmergyaWebDriver driver) {
        super(driver);
    }

    /**
     * Checks that the PO is ready
     * @param pageObject page object to be used
     */
    @Override
    public boolean isReady() {
        log.info("[log-PageObjects] " + this.getClass().getSimpleName()
                + " - Start isReady method");

        boolean status = this.isElementVisibleByXPath(TITLE);

        log.info("[log-PageObjects] " + this.getClass().getSimpleName()
                + " - End isReady method");

        return status;
    }

    // Page object methods

	public String checkTitle() {
		
		log.info("[log-PageObjects] " + this.getClass().getSimpleName()
                + " - Start isReady method");

		WebElement element = this.getElementByXPath(TITLE);

        log.info("[log-PageObjects] " + this.getClass().getSimpleName()
                + " - End isReady method");
		
		return element.getAttribute("innerText");
		
	}
    
    
}
