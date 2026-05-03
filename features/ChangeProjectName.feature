@skip # Fjern denne linje når du vil implementere
Feature: Change Project Name
  Description: When an user wants to change the name of a project
  Actor: User, Project Leader

  Background:
    Given a user is logged in
    And the project has the name "Calculator"

  Scenario: Change project name without project leader
    Given the project has no project leader
    When the user changes the project name to "Develop calculator"
    Then the project has the name "Develop calculator"

  Scenario: Change project name with project leader as non-project leader
    Given the project has a project leader
    And the user is not the project leader
    When the user changes the project name to "Create Calculator"
    Then the project has the name "Calculator"
    And an error is thrown "Only the project leader can rename the activities"

  Scenario: Change project name with project leader as project leader
    Given the project has a project leader
    And the user is the project leader
    When the user changes the project name to "Make calculator"
    Then the project has the name "Make calculator"
