Feature: Change Activity Name
  Description: When an user wants to change the name of an activity
  Actor: User, Project Leader

  Background:
    Given a user is logged in
    And a project
    And the project has the activity "Fix bugs"

  Scenario: Change activity name without project leader
    Given the project has no project leader
    When the user changes the activity name to "Solve bugs"
    Then the project has activities with the names
      | Solve bugs |

  Scenario: Change activity name with project leader as non-project leader
    Given the project has a project leader
    When the user changes the activity name to "Fix glitches"
    Then the project has activities with the names
      | Fix bugs |
    And an error is thrown "Only the project leader can change activity names"

  Scenario: Change activity name with project leader as project leader
    Given the user is the project leader
    When the user changes the activity name to "Fix the program"
    Then the project has activities with the names
      | Fix the program |
