Feature: View Projects
  Description: A user can view the currently existing projects in the system
  Actor: Employee

  Background:
    Given a user is logged in

  Scenario: View 1 existing project
    Given no projects have been created
    When the user creates 1 project
    Then 1 project exists

  # Fra projektbeskrivelse angives at der typisk er 30 igangværende projekter hos SoftwareHuset.
  Scenario: View 30 projects
    Given no projects have been created
    When the user creates 30 projects
    Then 30 projects exists

  Scenario: View 0 projects
    Given no projects have been created
    Then 0 projects exists

  Scenario: View 0 projects
    Given no projects have been created
    When the user tries to get the project with id 27001
    Then an error is thrown "Invalid project id"