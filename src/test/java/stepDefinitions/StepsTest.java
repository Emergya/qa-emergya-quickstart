package stepDefinitions;

import org.openqa.selenium.support.ui.Sleeper;

import com.emergya.pageObjects.GoogleMainPage;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * 
 * @author Antonio Pérez <aoviedo@emergya.com>
 * This is an example of a class that defines steps for cucumber feature files.
 * As you can see, we have just to extend the class BasicStepsDefinitions.
 */

public class StepsTest extends BasicStepsDefinitions{
	
	public StepsTest() {
		super();
	}
	
	/**
	 * This is an example of a step definition.
	 * When in a test we include a step that says:
	 *  When I go to <site> for instance: When I go to "google" (Notice that by putting "" we indicate that string is a
	 *  parameter) cucumber will search for this @When("I go to {string}")  annotation in all the methods
	 *  in the classes that are step definition classes.
	 *  Once it finds it, it replaces the parameter given in the feature file and gives it to the entrance parameter
	 *  in our function and executes it
	 *  NOTE: It must be noted that the first step should be the initialization of a pageObject object using the driver
	 *  that BasicStepsDefinitions provides
	**/
	@When("I go to {string}")
	public void i_go_to(String site) {
		if(site.contentEquals("google")) {
			super.googleMainPage = new GoogleMainPage(driver);
		}
	}
	
	/***
	 * With these parameters, we can reuse the same step several times. Like in this case where we
	 * prepared this step to be reusable several times
	 */
	
	@Given("Im in {string} and is loaded")
	public void in_site(String site){
		boolean loaded = false;
		if(site.equals("google")) {
			if(this.googleMainPage==null) {
				super.googleMainPage = new GoogleMainPage(driver);
			}
			loaded = this.googleMainPage.isLogoDisplayed(); 
		}
		if(site.equals("emergya")) {
			loaded = this.emergyaMainPage!=null && this.emergyaMainPage.isReady();
		}
		if(site.equals("emergya contact page")) {
			loaded = this.emergyaContactPage!=null && this.emergyaContactPage.isReady();
		}
		assert(loaded);
	}

	@When("I search for {string}")
	public void searchToken(String searchToken) {
		this.googleMainPage.doSearch(searchToken);
	}
	
	@Then("I click on {string}")
	public void clickOnSearchResult(String searchResult) {
		if(searchResult.contentEquals("Emergya main page")) {
			this.emergyaMainPage = this.googleMainPage.clickOnEmergyaPage();
		}
		
		if(searchResult.contentEquals("Emergya com page")) {
			this.emergyaMainPage = this.googleMainPage.clickOnEmergyacomPage();
		}
	}
	
	@Given("{string} is visible")
	public void isElementVisible(String element){
		boolean visible = false;
		if(element.contentEquals("Contact button")) {
			visible = this.emergyaMainPage.contactButtonIsVisible();
		}
		assert(visible);
	}
	
	@When("click in button {string}")
	public void clickButton(String button) {
		if(button.contentEquals("contact")) {
			this.emergyaContactPage = this.emergyaMainPage.clickOnEmergyaContactPage();
		}
	}
	
	@Then("count to {int} before exiting")
	public void countTo(Integer count) throws InterruptedException {
		Thread.sleep(count*1000);
	}
	
	/**
	 * We have to include a method with the cucumber annotation @After that has to call to
	 * super.afterScenario (and then we can tell it to do more things)
	 * @param scenario
	 */
	@After
	public void after(Scenario scenario) {
		super.afterScenario(scenario);
	}
	
}
