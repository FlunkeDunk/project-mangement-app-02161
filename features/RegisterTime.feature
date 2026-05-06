Feature: Register Time
  Description: A user can register time for themselves on an activity, even if they are not assigned.
  Actor: Employee

  Background:
    Given a user is logged in
    And a project
    And the project has activities with the names:
      | Invade Mordor |
      | Defend Gondor |
      | Defeat Smaug  |

  Scenario: Employee registers 5 hours on an assigned activity that has been worked on already without a date
    Given the employee is assigned to the activity "Invade Mordor"
    And that the employee's total time on "Invade Mordor" is 2.0 hours
    When the employee registers 5.0 hours on "Invade Mordor"
    Then the employee's timeLedger for "Invade Mordor" has an entry for 5.0 hours
    And the employee's total time on "Invade Mordor" is 7.0 hours

  Scenario: Employee registers 5 hours that has not been worked on already with a valid date
    Given that the employee's total time on "Invade Mordor" is 0.0 hours
    When the employee registers 5.0 hours on "Invade Mordor" on the date "24-12-2025"
    Then the employee's timeLedger for "Invade Mordor" has an entry with the date "24-12-2025" for 5.0 hours
    And the employee's total time on "Invade Mordor" is 5.0 hours
    When the employee registers 4.0 hours on "Invade Mordor" on the date "24-12-2025"
    Then the employee's timeLedger for "Invade Mordor" has an entry with the date "24-12-2025" for 9.0 hours
    And the employee's total time on "Invade Mordor" is 9.0 hours

  Scenario: Employee registers 5 hours that has been worked on already with a valid date
    Given that the employee's total time on "Invade Mordor" is 0.0 hours
    When the employee registers 5.0 hours on "Invade Mordor" on the date "1-1-2025"
    Then the employee's timeLedger for "Invade Mordor" has an entry with the date "1-1-2025" for 5.0 hours
    And the employee's total time on "Invade Mordor" is 5.0 hours

    When the employee registers 4.0 hours on "Invade Mordor" on the date "2-2-2025"
    Then the employee's timeLedger for "Invade Mordor" has an entry with the date "2-2-2025" for 4.0 hours
    And the employee's total time on "Invade Mordor" is 9.0 hours

  Scenario: Employee registers 5.0 hours with an invalid date
    Given the date is "1-1-2025"
    When the employee registers 5.0 hours on "Invade Mordor" on the date "2-2-2025"
    Then an error is thrown "Cannot register time in the future"

  Scenario: Employee registers 3 hours on an unassigned activity
    Given the employee is not assigned to the activity "Defend Gondor"
    And the employee has no time registered on the activity "Defend Gondor"
    When the employee registers 3.0 hours on "Defend Gondor" on the date "24-12-2025"
    Then the employee's timeLedger for "Defend Gondor" has an entry with the date "24-12-2025" for 3.0 hours
    And the employee's total time for "Defend Gondor" is 3.0 hours
    And the employee is still not assigned to the activity "Defend Gondor"

  Scenario: Employee registers negative time on activity
    When the employee registers a negative -5.0 hours on "Invade Mordor"
    Then an error is thrown "Cannot register negative time"

  Scenario: Employee registers negative time on activity with a valid date
    When the employee registers a negative -5.0 hours on "Invade Mordor" on the date "24-12-2025"
    Then an error is thrown "Cannot register negative time"
