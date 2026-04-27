Feature: Change Activity Name
  Description: When an user wants to change the name of an activity
  Actor: User, Project Leader

  Background:
    Given a user that is logged in
    And a project with an activity named "Fix bugs"

  Scenario: Change activity name without project leader
    Given the project has no project leader
    When the user changes the activity name to "Solve bugs"
    Then the activity has the name "Solve bugs"

  Scenario: Change activity name with project leader as non-project leader
    Given the project has a project leader
    And the user is not a project leader
    When the user changes the activity name to "Fix glitches"
    Then the activity has the name "Fix bugs"
    And an unsuccessful rename notification is given to the user

  Scenario: Change activity name with project leader as project leader
    Given the project has a project leader
    And the user is a project leader
    When the user changes the activity name to "Fix the program"
    Then the activity has the name "Fix the program"
