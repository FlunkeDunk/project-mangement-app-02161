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
    Given an activity "Design" for the project
    And 10 hours have been budgeted for "Design"
    And 7 hours have been spent on "Design"
    When the project leader creates a report
    Then a report is created
    And the report indicates the time budget is 10 hours
    And the report indicates the time spent is 7 hours