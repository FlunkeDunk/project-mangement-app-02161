Feature: Change Registered Time
  Description: When an user wants to change their already registered time on both assigned and unassigned activities
  Actor: User

  Background:
    Given a user is logged in
    And a project
    And the project has the activity "Run tests"

  Scenario: Change registered time to positive
    Given the user has registered 2 hours on "Run tests"
    When the user changes the registered time on activity "Run tests" to 5
    Then the activity "Run tests" has 5 hours worked

  Scenario: Change registered time to positive double
    Given the user has registered 2 hours on "Run tests"
    When the user changes the registered time on activity "Run tests" to 6.5
    Then the activity "Run tests" has 6.5 hours worked

  Scenario: Change registered time to negative
    Given the user has registered 2 hours on "Run tests"
    When the user changes the registered time on activity "Run tests" to -3
    Then the activity "Run tests" has 2 hours worked
    And an exception is thrown "Cannot register negative time"

  Scenario: Change time for an activity where user has no time registered
    When the user changes the registered time on activity "Run tests" to 6.7
    Then an exception is thrown "Cannot edit time for an activity where the employee has no time registered"

  Scenario: Change time for a date where user has no time registered
    Given the user has registered 2 hours on "Run tests"
    When the user changes the registered time on activity "Run tests" at date "09-11-2001" to 6.7
    Then an exception is thrown "Cannot edit time for a date where the employee has no time registered"
