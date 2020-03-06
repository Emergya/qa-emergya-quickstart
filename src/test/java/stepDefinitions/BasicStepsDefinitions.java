package stepDefinitions;

import com.emergya.pageObjects.EmergyaContactPage;
import com.emergya.pageObjects.EmergyaMainPage;
import com.emergya.pageObjects.GoogleMainPage;
import com.emergya.selenium.testSet.DefaultTestSet;

/**
 * 
 * @author Antonio Pérez <aoviedo@emergya.com>
 * This abstract class is equivalent to the class BasicTestSet for tests not executed using cucumber.
 * In this class we will include all the page objects that we will need as protected parameters
 * However, we have a very interesting constructor. This constructor class to its parent DefaultTestSet
 * and then executes the method before from its parent class DefaultTest which will the method that will initialize
 * the driver like in the project. But in this case, we have to do these actions here to provide a functional
 * driver, why?
 * This is the class that all our classes that define steps for our feature files (all our step definition classes)
 * must extend.
 */

public abstract class BasicStepsDefinitions extends DefaultTestSet{
	/**
	 * For instance, in the test we provide as example we need these 3 pageObjects that have been defined using
	 * our selenium handler and the framework. When we extend this class, all our step definitions will have these
	 * available so we will be able to use its methods to define our steps
	 */
	protected GoogleMainPage googleMainPage;
	protected EmergyaMainPage emergyaMainPage;
	protected EmergyaContactPage emergyaContactPage;
	
	public BasicStepsDefinitions() {
		super();
		super.before();
    }
}
