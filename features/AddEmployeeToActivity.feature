Feature: Add employee to activity
  Description: A project leader adds employee to activity
  Actor: Project leader

  Background:
    Given a project
    And the user is a project leader

  Scenario: Project leader successfully puts employee on activity
    Given the activity "Analysis"
    When the user puts "Employee1" on "Analysis"
    Then the employee "Employee1" is on the activity "Analysis"

  Scenario: Project leader unsuccessfully puts employee on activity
    Given the activity "Analysis"
    And the employee "Employee1" is already on the activity "Analysis"
    When the user puts "Employee1" on "Analysis"
    Then the message "The employee \"Employee1\" is already on \"Analysis\"" is shown
    And the employee "Employee1" is on "Analysis" only 1 time



       


