Feature: Change Registered Time
  Description: When an user wants to change their already registered time on both assigned and unassigned activities
  Actor: User

  Background:
    Given a user is logged in
    And a project
    And the project has the activity "Run tests"
    And the user has registered 2 hours on "Run tests"

    Scenario: Change registered time to positive
        When the user changes the time of the prior registration on activity "Run tests" to 5
        Then the activity "Run tests" has 5 hours worked

    Scenario: Change registered time to positive double
      When the user changes the time of the prior registration on activity "Run tests" to 6.5
      Then the activity "Run tests" has 6.5 hours worked

    Scenario: Change registered time to negative
      When the user changes the time of the prior registration on activity "Run tests" to -3
        Then the activity "Run tests" has 2 hours worked
        And an exception is thrown "Negative time not allowed for activities"
