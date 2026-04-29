Feature: Add activity
  Description: A user can add an activity to a project with an budgeted time
  Actor: Employee

  Background:
    Given a user is logged in

  Scenario: Add activity to a project with no project leader and no projects
    Given a project
    And the project has no project leader
    And the project has no activities
    When an employee tries to add activity "Visit Gondor" with budgeted time 12 weeks
    Then the project should have the activities with the names and budgeted times
      | Visit Gondor | 12 |

  Scenario: Add activity to a project with no project leader and existing projects
    Given a project
    And the project has no project leader
    And the project has the activities with the names and budgeted times
      | Practice Newspeak | 5 |
    When an employee tries to add activity "Attend 2 minutes of hate" with budgeted time 8 weeks
    Then the project should have the activities with the names and budgeted times
      | Practice Newspeak | 5 |
      | Attend 2 minutes of hate | 8 |

  Scenario: Add activity to a project with a project leader and existing projects
    Given a project
    And the project has a project leader
    And the user is not the project leader
    And the project has the activities with the names and budgeted times
      | Practice Newspeak | 5 |
    When an employee tries to add activity "Attend 2 minutes of hate" with budgeted time 8 weeks
    Then the project has the activities with the names and budgeted times
      | Practice Newspeak | 8 |
 #   And an error is thrown "Only the project leader can create activities"
