Feature: Create report
  Description: A project leader creates a report
  Actor: Project leader

  Background: There is a project
    Given a project exists
    And a user is logged in

  Scenario: Project leader creates report
    When the project leader creates a report
    Then a report is created

  Scenario: Project leader creates report with budgeted time
    Given the project has the activity "Design"
    And the activity "Design" has 10 hours budgeted
    And the activity "Design" has 7 hours spent
    When the project leader creates a report
    Then a report is created
    And the report indicates the time budget is 10 hours
    And the report indicates the time spent is 7 hours