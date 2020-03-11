#Author: aoviedo@emergya.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

#This is the tag Feature: This tag identifies our feature file and is the parent tag
#In our framework this tag will be used to compose the name of the test in CrossBrowserTesting
#Following it, in the next line, we have to provide a basic description of our feature. 
#What is its objective, how it works, what it tests.
Feature: GoogleTestSet
  I want to test basic google features
 
#Each scenario is composed by steps. Cucumber will search in the classes that define steps for each step
#for a method with its corresponding annotation (Given,When,Then) and the text of the test. Let's see
#some examples:
#For this step, cucumber will search for a method with the following annotation:
#@Given("Im in {string} and is loaded")
#You noticed that we introduced {string} as a variable. This will be a variable given in our Gherkin
#step as a parameter for the method that will handle this step. In our case, this method will receive
#a string
#If it was an Integer, you would use {}
Scenario: We search for Emergya.es
    #For this step, cucumber will search for a method with the following annotation:
    #@Given("Im in {string} and is loaded")
    #You noticed that we introduced {string} as a variable. This will be a variable given in our Gherkin
    #step as a parameter for the method that will handle this step. In our case, this method will receive
    #a string
    Given Im in "google" and is loaded
    When I search for "www.emergya.es"
    Then I click on "Emergya main page"
    Given Im in "emergya" and is loaded
    And "Contact button" is visible
    When click in button "contact"
    #If it was an Integer, you would use {int} and so on, you can check this in 
    Then count to 5 before exiting
    #Given Im in "emergya contact page" and is loaded  

#This is a scenario outline. In other words, this is the template to use the same scenario several times
#with different parameters. As you can notice, it is very similar but it has some differences.
#Instead of passing directly the values of the parameters for the methods of each step, we state
#that step has a parameter.
Scenario Outline: We search for something in a site
    #For instance, this is the same step as in the previous scenario, but here instead of providing
    #a value for the parameter for the method that handles this step, we defined a parameter for this
    #scenario in this feature file with <variableName>
    Given Im in <site> and is loaded
    When I search for <concept>
    Then I click on <result>
    Given Im in <site2> and is loaded  
    And <element> is visible
    When click in button <element>
    Then count to <count> before exiting
    #Then Im in "emergya contact page" and is loaded
  #But how do you provide the values for this parameters, well you have the answer bellow:
  #We use the tag Examples:
  #Then we create a data table, in the first row, the header we put the name of the variables separated
  #by | |, then for each combination of values we fill in a row in this table
  #Our scenario will be executed as many times as there are rows in this table, using as parameters
  #the concrete values defined in each row. So the first time we will use the parameters in the first
  #row, in the second execution the ones in the second and so on
  Examples:
    | site     | concept          | result               | site2     | element          | count |
    | "google" | "www.emergya.es" | "Emergya main page"  | "emergya" | "Contact button" |   5   |
    | "google" | "www.emergya.com"| "Emergya com page"   | "emergya" | "Contact button" |   3   |
    | "google" | "www.emergya.fr" | "Emergya main page"  | "emergya" | "Contact button" |   2   |
