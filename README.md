# QA Emergya Quickstart Project
The aim of this project is to provide a maven archetype for selenium projets using the _qa-selenium-handler_ available in https://github.com/Emergya/qa-selenium-handler/tree/selenium3-handler

## Prerequisites
1. [Maven 3 configuration](/documentation/prerequisites/maven3Installation.md)
2. [Oracle Java 8 installation](/documentation/prerequisites/java8Installation.md)

## Adding dependencies
 1. Add the repository:

```
        <!-- GtHub Selenium WebDriver Handler Repository -->
        <repository>
                <id>selenium-handler</id>
                <name>Selenium WebDriver Handler</name>
                <url>https://raw.github.com/Emergya/qa-selenium-handler/mvn-repo</url>
        </repository>
```
 2. Adding the following maven dependency in your ```pom.xml``` file:
```
        <dependency>
                <groupId>com.emergya</groupId>
                <artifactId>selenium3-handler</artifactId>
                <version>0.0.7</version>
        </dependency>
```

## How to use the archetype
Firstly, you have to clone the repository to get the project structure.

Secondly, change in the _pom.xml_ the project groupId, artifactId, name and version for the values you need.

Finally, enjoy coding!

_Do not remove the default developers who made this project real._

## Configuring pom.xml parameters
_pom.xml_ is located in the root path of the project. We have defined repositories for SeleniumHandler, versions of plugins and technologies, parameters for test executions, dependencies, profiles, and all the config for the Build phase. 

### Parameters for test executions
Important parameters to execute tests:

1. _browser_: Browser used to launch tests. Allowed values: _Firefox_, _Chrome_ or _IE_.
2. _local.OS_: OS used to launch tests. Allowed values: _Windows_ (only with Chrome or IE) or empty.
3. _video.Recording.Path_: Path to save video recording if _activate.Video.Recording_ option is activated.
4. _activate.Video.Recording_: Activate video recording. Allowed values: true or false.
5. _save.Video.For.Passed_: Save videos for passed tests. Allowed values: true or false.
6. _webdriver.chrome.driver_: Path in which the Chrome driver is.
7. _webdriver.ie.driver_: Path in which the IE driver is.
8. _webdriver.firefox.driver_: Path in which the Firefox (geckodriver) driver is.

## src/main/resources folder:
1. files/software: In this folder we store the browser drivers.
2. selectors: In this .properties we store the way to access to every element of every page. Using Xpath, ID, etc. That method modulates and encapsulates these variables, with the advantage that entails.
3. suites: Here we can group sets of tests to run it together.
4. _log4j.properties_: This file sets the logging properties, in this case the output to console.
5. _test.properties_: In this file we store some common tests properties.

### Items selectors
As we explained [here] (/README.md#srcmainresources-folder), the selectors are _.properties_ files used to save the way to locate elements of the page (ID's, Xpath, etc). To make it work the _.properties_ file should have exactly the same name of the PageObject refered with the next changes: the _.properties_ file name should go in lowercase and with the suffix _Page_.
> Example:  
_GoogleMainPage.java_ --> _googlemainpage.properties_
>

## src/main/java folder:
1. pageObject: Here we have every PageObjects. All PageObjects extends BasePageObject, it has important functions.
2. testSets: Here we store the tests classes. _@before_ and _@after_ methods are defined here. All tests classes extends BasicTestSet.
3. utils: 
 * BasicTestSet - Contains PageObjects variable references. Extends DefaultTestSet.
 * Factory - Generic class to do pre-steps and post-steps method. Extends DefaultTestSet.

## src/test/java folder:
1. runner: In this package we will have the classes that make TestNG run cucumber. All these classes extend the abstract
class AbstractTestNGCucumberTests. By default, we include the class TestNGCucumberRunner that will run all the feature
files in folder src/test/resources/features and will look for step definitions in package stepDefinitions.
2. stepDefinitions: Here we will include all classes that provide step definitions for our feature cucumber files. All these classes should extend the class BasicStepsDefinitions (that extends DefaultTestSet). This is the equivalent
to BasicTestSets in a "regular" TestNG project so _@before_ and _@after_ methods should/can be defined here

## src/test/resources folder:
1. features: We should include all our feature cucumber files here. However, in we change some configuration we could use other path.