Feature: Create report
  Description: A project leader creates a report
  Actor: Project leader

  Background: There is a project
    Given a project
    And a user is logged in

  Scenario: Project leader creates report
    When the project leader creates a report
    Then a report is created

  Scenario: Project leader creates report with budgeted time
    Given the project has the activity "Design"
    And the activity "Design" gets 10 hours budgeted
    And an employee has spent 7 hours on the activity "Design"
    When the project leader creates a report
    Then a report is created
    And the report indicates the time budget is 10 hours
    And the report indicates the time spent is 7 hours
    And the report indicates the estimated time remaining is 3 hours

  Scenario: Project leader creates report with budgeted time from multiple activities
    Given the project has the activity "Design"
    Given the project has the activity "Implementation"
    Given the project has the activity "Testing"
    And the activity "Design" gets 10 hours budgeted
    And the activity "Implementation" gets 9 hours budgeted
    And the activity "Testing" gets 16 hours budgeted
    And an employee has spent 7 hours on the activity "Design"
    And an employee has spent 9 hours on the activity "Implementation"
    And an employee has spent 91 hours on the activity "Testing"
    When the project leader creates a report
    Then a report is created
    And the report indicates the time budget is 35 hours
    And the report indicates the time spent is 107 hours
    And the report indicates the estimated time remaining is 0 hours