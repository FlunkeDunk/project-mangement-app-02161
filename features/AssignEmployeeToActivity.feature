Feature: Assign employee to activity
  Description: An employee with Project-Leader permissions (Project leader or any employee when no project leader exists)
  can assign another employee (including themselves) to an activity
  Actor: Employee

  Background:
    Given a user is logged in
    And a project
    And the following employees exist
      | gagr |
      | sawh |
    And the project has the activities with the names
      | Invade Mordor |
      | Defend Gondor |
      | Defeat Smaug  |

  Scenario: Employee assigns themselves to an activity in project with no project leader
    Given the project has no project leader
    And "gagr" is not assigned to activity "Defeat Smaug"
    When "gagr" assigns "gagr" to "Defeat Smaug"
    Then "gagr" is assigned to "Defeat Smaug"

  Scenario: Employee fails to assign themselves to an activity in project with a project leader
    Given the project has a project leader
    And "gagr" is not assigned to activity "Defend Gondor"
    When "gagr" assigns "gagr" to "Defend Gondor"
    Then "gagr" is not assigned to "Defend Gondor"
    And an error is thrown "Only the project leader can assign employees to activities"

  Scenario: Employee assigns another employee to an activity in project with no project leader
    Given the project has no project leader
    And "sawh" is not assigned to activity "Invade Mordor"
    When "gagr" assigns "sawh" to "Invade Mordor"
    Then "sawh" is assigned to "Invade Mordor"

  Scenario: Employee fails to assign another employee to an activity in project with a project leader
    Given the project has a project leader
    And "sawh" is not assigned to activity "Invade Mordor"
    When "gagr" assigns "sawh" to "Invade Mordor"
    Then "sawh" is not assigned to "Invade Mordor"

  Scenario: Employee fails to assign an employee to an activity they are already assigned to
    Given the project has no project leader
    And "sawh" is assigned to activity "Invade Mordor"
    When "gagr" assigns "sawh" to "Invade Mordor"
    Then an error is thrown "Employee is already added to the activity"
    And "sawh" is assigned to "Invade Mordor"

  Scenario: Employee assigns themselves to an activity in a project they are the project leader of
    Given "gagr" is the project leader
    And "gagr" is not assigned to activity "Defend Gondor"
    When "gagr" assigns "gagr" to "Defend Gondor"
    Then "gagr" is assigned to "Defend Gondor"

  Scenario: Employee assigns another employee to an activity in a project they are the project leader of
    Given "gagr" is the project leader
    And "sawh" is not assigned to activity "Defend Gondor"
    When "gagr" assigns "sawh" to "Defend Gondor"
    Then "sawh" is assigned to "Defend Gondor"

  Scenario: Project leader tries to assign an employee that does not exist to an activity
    Given "gagr" is the project leader
    When "gagr" assigns "help" to "Defend Gondor"
    Then an exception is thrown "Invalid employee initials"

  Scenario: Activity added to Employee's assigned activities twice
    When "Defend Gondor" is added to the assigned activities of "gagr"
    And "Defend Gondor" is added to the assigned activities of "gagr"
    Then an exception is thrown "The employee already has that activity"