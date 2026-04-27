Feature: View Projects
  Description: A user can view the currently existing projects in the system
  Actor: Employee

  Background:
    Given a user is logged in

  Scenario: View 1 existing project
    Given 1 project exists
    When the user views the list of projects
    Then the project is shown

  # Fra projektbeskrivelse angives at der typisk er 30 igangværende projekter hos SoftwareHuset.
  Scenario: View 30 projects
    Given 30 projects exist
    When the user views the list of projects
    Then all the 30 projects are shown

  Scenario: View 0 projects
    Given 0 projects exist
    When the user views the list of projects
    Then the user is told no projects exist