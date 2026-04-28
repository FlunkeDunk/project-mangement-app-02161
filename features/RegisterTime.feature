Feature: Register Time
  Description: A user can register time for themselves on an activity, even if they are not assigned.
  Actor: Employee

  Background:
    Given a user is logged in
    And a project
    And the project has activities with the names
      | Invade Mordor |
      | Defend Gondor |
      | Defeat Smaug  |

  # Scenario: Employee registers 5 hours on an assigned activity
  #   Given the employee is assigned to the activity "Invade Mordor"
  #   And the employee's total time on "Invade Mordor" is 2 hours
  #   When the employee registers 5 hours on "Invade Mordor"
  #   Then the employee's timeLedger for "Invade Mordor" has an entry for 5 hours
  #   And the employee's total time on "Invade Mordor" is 7 hours

  # Scenario: Employee registers 3 hours on an unassigned activity
  #   Given the employee is not assigned to the activity "Defend Gondor"
  #   And the employee has no time registered on the activity "Defend Gondor"
  #   When the employee registers 3 hours on "Defend Gondor"
  #   Then the employee's timeLedger for "Defend Gondor" has an entry for 3 hours
  #   And the employee's total time for "Defend Gondor" is 3 hours
  #   And the employee is not assigned to the activity "Defend Gondor"
