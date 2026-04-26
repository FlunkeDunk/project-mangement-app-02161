Feature: Add activity
  A user can add an activity to a project with an expected time

  Actor: Employee

  Background:
    Given a user is logged in

  Scenario: Add activity to a project with no project leader and no projects
    Given 1 project exists
    And the project has no Project leader
    And the project has the activities with the names and expected times

    When an employee tries to add activity "Visit MiniTrue" with expected time 12 weeks
    Then the project has the activities with the names and expected times
      | Visit MiniTrue | 12 weeks |

  Scenario: Add activity to a project with no project leader and existing projects
    Given 1 project exists
    And the project has no Project leader
    And the project has the activities with the names and expected times
      | Practice Newspeak | 5 weeks |
    When an employee tries to add activity "Attend 2 minutes of hate" with expected time 8 weeks
    Then the project has the activities with the names and expected times
      | Practice Newspeak | 5 weeks |
      | Attend 2 minutes of hate | 8 weeks |

  Scenario: Add activity to a project with a project leader and existing projects
    Given 1 project exists
    And the project has a Project leader
    And the user is not the project leader
    And the project has the activities with the names and expected times
      | Practice Newspeak | 5 weeks |
    When an employee tries to add activity "Attend 2 minutes of hate" with expected time 8 weeks
    Then the project has the activities with the names and expected times
      | Practice Newspeak | 8 weeks |
    And An error is thrown "Only the project leader can create activities"
