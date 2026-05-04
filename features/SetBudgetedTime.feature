Feature: Set Budgeted Time
  Description: A user can set budgeted time for an activity
  Actor: Employee

  Background:
    Given a user is logged in
    And a project
    And an activity "Visit Gondor" with a duration of 3 weeks

  Scenario: Set budgeted time for an activity of a project with no project leader
    When the user sets the budgeted time of the activity to 67 hours
    Then the activity has a budgeted time of 67 hours

  Scenario: Set budgeted time for an activity of a project with no project leader
    When the user sets the budgeted time of the activity to 69 hours
    Then the activity has a budgeted time of 69 hours

  Scenario: Set budgeted time for an activity of a project with a project leader that is not the user
    Given the project has a project leader
    When the user sets the budgeted time of the activity to 67 hours
    Then an error is thrown "Only the project leader can set budgeted time for activities"
    And the activity has a budgeted time of 0 hours

  Scenario: Set budgeted time for an activity of a project before and after it has a project leader that is not the user
    When the user sets the budgeted time of the activity to 67 hours
    Then the activity has a budgeted time of 67 hours
    Given the project has a project leader
    When the user sets the budgeted time of the activity to 69 hours
    Then an error is thrown "Only the project leader can set budgeted time for activities"
    And the activity has a budgeted time of 67 hours

  Scenario: Set budgeted time for an activity of a project with the user being project leader
    Given the user is the project leader
    When the user sets the budgeted time of the activity to 67 hours
    Then the activity has a budgeted time of 67 hours
