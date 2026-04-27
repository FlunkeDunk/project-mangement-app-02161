Feature: Change Registered Time
  Description: When an user wants to change their already registered time on both assigned and unassigned activities
  Actor: User

  Background:
    Given an user that is logged in
    And a project
    And the project contains an activity "Run tests" with 2 hours worked

    Scenario: Change registered time to posetive
        When the user changes the registered time on the activity "Run tests" to 5 hours
        Then the activity "Run tests" has 5 hours worked

    Scenario: Change registered time to negative
        When the user changes the registered time on the activity "Run tests" to -3 hours
        Then the activity "Run tests" has 2 hours worked
        And an unsuccessful change notification is given to the user
