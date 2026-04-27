Feature: Change Registered Time
  Description: When an employee wants to change their already registered time on both assigned and unassigned activities
  Actor: Employee

  Background:
    Given an employee that is logged in
    And a project
    And the project contains an activity "Run tests" with 2 hours worked

  Scenario: Change Registered Time
    When the employee changes the registered time on the activity "Run tests" to 5 hours
    Then the activity "Run tests" has 5 hours worked
