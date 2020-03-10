package runner;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


/**
 * 
 * @author Antonio Pérez <aoviedo@emergya.com>
 * This kind of class is the one that TestNG will use to run our cucumber tests. Why? Because it extends the class 
 * AbstractTestNGCucumberTests provided by TestNG. Classes that extend this class that be executed by TestNG and
 * run cucumber tests, how?
 * You can see we used two annotations for this class
 * @Test
 * @CucumberOptions
 * In @CucumberOptions you will tell cucumber where to search for the necessary components to run a test
 * Cucumber uses a type of files called features (<filename>.feature) that contains the steps for our tests
 * and also Java classes that contain the definition for those steps, in other words with method to execute for
 * each step in the feature file
 * In this annotation will set the folder where our features are. The recommendations are to keep our feature files
 * in the folder described down below. However, if we wanted to execute a certain group of feature files but not all,
 * we could include those in a folder inside this folder features and create their own class that extents AbstractTestNGCucumberTests
 * and include that class in the xml for the TestNG suite of those test cases
 * The other parameter in that @CucumberOptions is glue. This is where cucumber will search for our test definitions
 * (the methods that has to execute for each step). In this case and following convention, we indicate that these
 * are included in the package stepDefintions (notice, that in features we give a path and here we have to give a package,
 * NOT a path)
 * To sum up, this class will execute all your tests in all feature files. If you want to execute just a number of
 * this, you will have to create your own class like these and change the parameter features to point to the folder
 * where those feature files are
 */

@Test
@CucumberOptions(features = "src/test/resources/features",glue= {"stepDefinitions"})
public class TestNGCucumberRunnerClass extends AbstractTestNGCucumberTests{
	
	static Logger log = Logger.getLogger(TestNGCucumberRunnerClass.class); 
	
	@BeforeClass
	public void Before() {
		log.info("[log-TestNGCucumberRunner] " + this.getClass().getName()
                + " - Starting TestNG runner for cucumber:");
	}
	
	@AfterClass
	public void After() {
		log.info("[log-TestNGCucumberRunner] " + this.getClass().getName()
                + " - Ending TestNG runner for cucumber:");
	}
}