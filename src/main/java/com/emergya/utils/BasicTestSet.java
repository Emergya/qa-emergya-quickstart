package com.emergya.utils;

import com.emergya.pageObjects.EmergyaContactPage;
import com.emergya.pageObjects.EmergyaMainPage;
import com.emergya.pageObjects.EmergyaWorkPage;
import com.emergya.pageObjects.GoogleMainPage;
import com.emergya.selenium.testSet.DefaultTestSet;

/**
 * PageObjects variable references
 * @author Jose Antonio Sanchez <jasanchez@emergya.com>
 */
public abstract class BasicTestSet extends DefaultTestSet {
    protected GoogleMainPage googleMainPage;
    protected EmergyaMainPage emergyaMainPage;
    protected EmergyaContactPage emergyaContactPage;
    protected EmergyaWorkPage emergyaWorkPage;

}
