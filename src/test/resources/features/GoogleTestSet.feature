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
@tag
Feature: GoogleTestSet
  I want to test basic google things

  @tag1
Scenario: We search for Emergya.es
    When I go to "google"
    Given Im in "google" and is loaded
    Then I search for "www.emergya.es"
    Then I click on "Emergya main page"
    Given Im in "emergya" and is loaded
    Given "Contact button" is visible
    Then click in button "contact"
    #Given Im in "emergya contact page" and is loaded
    
Scenario: We search for Emergya.es and go to contact page
    When I go to "google"
    Given Im in "google" and is loaded
    Then I search for "www.emergya.es"
    Then I click on "Emergya main page"
    Given Im in "emergya" and is loaded
    Given "Contact button" is visible
    Then click in button "contact"
    Given Im in "emergya contact page" and is loaded