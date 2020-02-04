package com.emergya.pageObjects;

import org.apache.log4j.Logger;

import com.emergya.selenium.drivers.EmergyaWebDriver;
import com.emergya.selenium.pageObject.BasePageObject;

/**
 * A Page Object (PO) contain the behavior of a specific page in the application
 * EmergyaMainPage: This PO contain the methods to interact with the emergya main page
 * @author Ivan Bermudez <ibermudez@emergya.com>
 * @author Jose Antonio Sanchez <jasanchez@emergya.com>
 * @author Ivan Gomez <igomez@emergya.com>
 */
public class EmergyaMainPage extends BasePageObject {

    /**
     * Logger class initialization.
     */
    static Logger log = Logger.getLogger(GoogleMainPage.class);

    /**
     * Items keys selectors.
     */
    private static final String IMG_LOGO_EMERGYA = "imgLogoEmergya";
    private static final String WORK_WITH_US = "buttonWorkWithUs";
    private static final String CONTACT_PAGE = "buttonContactPage";

    /**
     * Constructor method
     * @param driver selenium webdriver
     */
    public EmergyaMainPage(EmergyaWebDriver driver) {
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

        boolean status = this.isElementVisibleById(IMG_LOGO_EMERGYA);

        log.info("[log-PageObjects] " + this.getClass().getSimpleName()
                + " - End isReady method");

        return status;
    }

    // Page object methods
    /**
     * Check if emergya logo is displayed
     * @return
     */
    public boolean isEmergyaLogoDisplayed() {
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- Start isEmergyaLogoDisplayed method");
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- End isEmergyaLogoDisplayed method");

        return this.isElementVisibleById(IMG_LOGO_EMERGYA);
    }

    /**
     * Click on the button contact
     * to redirect to contact page
     * @return
     */
    public EmergyaContactPage clickOnEmergyaContactPage() {
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- Start clickOnEmergyaContactPage method");

        if (contactButtonIsVisible()) {
            this.getElementByXPath(CONTACT_PAGE).click();
        }

        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- End clickOnEmergyaContactPage method");
        return new EmergyaContactPage(driver);
    }

    /**
     * Click on the button work with us
     * to redirect to the page
     * @return
     */
    public EmergyaWorkPage clickOnWorkWithUs() {
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- Start clickOnWorkWithUs method");

        this.scrollBottom();

        this.waitForByXPath(WORK_WITH_US);

        if (workButtonIsVisible()) {
            this.getElementByXPath(WORK_WITH_US).click();
        }

        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- End clickOnWorkWithUs method");
        return new EmergyaWorkPage(driver);
    }

    /**
     * Return if the button of contact is visible
     * @return
     */
    public boolean contactButtonIsVisible() {
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- Start contactButtonIsVisible method");
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- End contactButtonIsVisible method");

        return this.isElementVisibleByXPath(CONTACT_PAGE);
    }

    /**
     * Return if the button of work with us
     *  is visible
     * @return
     */
    public boolean workButtonIsVisible() {
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- Start workButtonIsVisible method");
        log.info("[log-pageObjects]" + this.getClass().getSimpleName()
                + "]- End workButtonIsVisible method");

        return this.isElementVisibleByXPath(WORK_WITH_US);
    }

}
