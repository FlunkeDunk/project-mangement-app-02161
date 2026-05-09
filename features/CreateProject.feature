Feature: Create project
  Description: A user can create a project with or without a name.
  Actor: Employee

  Background:
    Given a user is logged in

  Scenario: Creates a project
    Given there are no projects created this year
    And the date is "24-01-2027"
    When the user creates a project
    Then there is a project
    And the project starts in week 3 and year 2027
    And the project has the id 27001
    And the project has no project leader

  Scenario: User try creating the 1000th
    When there are 999 projects created this year
    And the user creates a project
    Then an error is thrown "Cannot create more than 999 projects a year"
    And under 1000 projects have been created this year

  Scenario: Creates a project 2
    Given there are no projects created this year
    And the date is "31-01-2028"
    When the user creates a project
    Then there is a project
    And the project starts in week 5 and year 2028
    And the project has the id 28001
    And the project has no project leader

  Scenario: Create 2 projects with different names
    Given there are no projects created this year
    And the date is "24-01-2027"
    When the user creates a project "First project"
    Then there is a project
    And the project has the name "First project"
    And the project starts in week 3 and year 2027
    And the project has the id 27001
    And the project has no project leader
    When the user creates a project "Second project"
    Then there is a project
    And the project has the name "Second project"
    And the project starts in week 3 and year 2027
    And the project has the id 27002
    And the project has no project leader