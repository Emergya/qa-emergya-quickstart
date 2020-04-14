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
			<artifactId>selenium3Remote</artifactId>
			<version>${selenium3-handler.version}</version>
		</dependency>
```
3. Add the following parameters in your ```pom.xml``` file, these parameters control how we connect
to CrossBrowserTesting:

```
		<!-- Remote configuration Crossbrowsertesting -->
		<remoteUserName></remoteUserName>
		<remoteUserKey></remoteUserKey>
		<remoteProtocol>http</remoteProtocol>
		<remoteURL>hub.crossbrowsertesting.com:80/wd/hub</remoteURL>
		<finalRemoteURL>${remoteProtocol}://${remoteUserName}:${remoteUserKey}@${remoteURL}</finalRemoteURL>
		<remoteBuild></remoteBuild>
		<remoteConfiguration>Windows 10,Chrome,80,1600x1200</remoteConfiguration>
		<remoteRecordVideo>true</remoteRecordVideo>
		<remoteRecordNetwork>true</remoteRecordNetwork>
```

4. Also there has been an important change in file ```pom.xml```, now drivers are organized in different
folders according to the OS where they are used, so now we have the following paths in our POM for our local drivers:

```
		<webdriver.chrome.driver>src/main/resources/files/software/${local.OS}/chromedriver</webdriver.chrome.driver>
		<webdriver.ie.driver>src/main/resourcesfiles/software/${local.OS}/IEDriverServer.exe</webdriver.ie.driver>
		<webdriver.firefox.driver>src/main/resourcesfiles/software/${local.OS}/geckodriver</webdriver.firefox.driver>
		
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
5. _test.properties_: In this file we store some common tests properties. Now we added parameters needed in our ```pom.xml``` file to connect to CrossBrowserTesting (they are placed at the end of the file)

### Items selectors
As we explained [here] (/README.md#srcmainresources-folder), the selectors are _.properties_ files used to save the way to locate elements of the page (ID's, Xpath, etc). To make it work the _.properties_ file should have exactly the same name of the PageObject refered with the next changes: the _.properties_ file name should go in lowercase and with the suffix _Page_.
> Example:  
_GoogleMainPage.java_ --> _googlemainpage.properties_
>

## src/main/java folder:
1. pageObject: Here we have every PageObjects. All PageObjects extends BasePageObject, it has important functions.
2. testSets: Here we store the tests classes. _@before_ and _@after_ methods are defined here. All tests classes extends BasicTestSet.
3. utils: 
 * BasicTestSet - Contains PageObjects variable references, also now handles how we set the result of our remote tests in CrossBrowserTesting. Extends DefaultTestSet.
 * CrossBrowserInitialization - Singleton pattern class that gets the parameters needed to connect and communicate with CrossBrowserTesting.
 * Factory - Generic class to do pre-steps and post-steps method. Extends DefaultTestSet.

## src/test/resources folder:
1. features: We should include all our feature cucumber files here. However, in we change some configuration we could use other path.