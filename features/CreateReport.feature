Feature: Create report
  Description: A project leader creates a report
  Actor: Project leader

  Background: There is a project
    Given a project with name "My Project"
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
    And the report indecates the name of the project is "My Project"
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
    And an employee has spent 21 hours on the activity "Testing"
    When the project leader creates a report
    Then a report is created
    And the report indecates the name of the project is "My Project"
    And the report indicates the time budget is 35 hours
    And the report indicates the time spent is 37 hours
    And the report indicates the estimated time remaining is 3 hours

  Scenario: Project leader creates report with budgeted time and checks entries
    Given the project has the activity "Knitting"
    And the project has the activity "Sewing"
    And the project has the activity "Tying knots"
    And the project has the activity "Scroll TikTok"
    And the activity "Knitting" gets 8234 hours budgeted
    And the activity "Sewing" gets 31.5 hours budgeted
    And the activity "Tying knots" gets 82 hours budgeted
    And the activity "Scroll TikTok" gets 5 hours budgeted
    And an employee has spent 12 hours on the activity "Knitting"
    And an employee has spent 22 hours on the activity "Sewing"
    And an employee has spent 12 hours on the activity "Scroll TikTok"
    When the project leader creates a report
    Then a report is created
    And the report has the entries
      | [Knitting] Time budgeted: 8234.0 hours, Time Spent: 12.0 hours, timeLeft: 8222.0 hours. |
      | [Sewing] Time budgeted: 31.5 hours, Time Spent: 22.0 hours, timeLeft: 9.5 hours.        |
      | [Tying knots] Time budgeted: 82.0 hours, Time Spent: 0.0 hours, timeLeft: 82.0 hours.   |
      | [Scroll TikTok] Time budgeted: 5.0 hours, Time Spent: 12.0 hours, timeLeft: 0.0 hours.  |