Feature: Limit daily registered time
  Description: An employee should not be able to have registered more than 24 hours of work in a day.
  Actor: Employee

  Background:
    Given the date is "24-12-2025"
    And a user is logged in
    And a project with the name "project"
    And the project has activities with the names:
      | Analysis  |
      | Design    |
  
  Scenario: Employee registers more than 24 hours on activity with
    When the employee registers 25 hours on "Analysis" on the date "24-12-2025"
    Then an error is thrown "Time registered has to be between 0 and 24 hours"
  
  Scenario: Employee registers more than 24 hours on different activities
    When the employee registers 13 hours on "Analysis" on the date "24-12-2025"
    And the employee registers 13 hours on "Design" on the date "24-12-2025"
    Then an error is thrown "Cannot register more than 24 hour in a day"
  
  Scenario: Employee edits more than 24 hours on an activity
    When the employee registers 2 hours on "Analysis" on the date "24-12-2025"
    And the user changes the registered time on activity "Analysis" at date "24-12-2025" to 25
    Then an error is thrown "Time registered has to be between 0 and 24 hours"


  Scenario: Employee edits more than 24 hours on multiple acitvities
    When the employee registers 5 hours on "Analysis" on the date "24-12-2025"
    When the employee registers 5 hours on "Design" on the date "24-12-2025"
    And the user changes the registered time on activity "Analysis" at date "24-12-2025" to 20
    Then an error is thrown "Cannot register more than 24 hour in a day"


  Scenario: Employee registers more than 24 hours on different projects
    When the employee registers 13 hours on "Analysis" on the date "24-12-2025"
    Given a project with name "Other project"
    And the project has the activity "other acitivity"
    When the employee registers 13 hours on "other acitivity" on the date "24-12-2025"
    Then an error is thrown "Cannot register more than 24 hour in a day"
  

