Feature: See registered time
  Description: A user can see registered time for given date
  Actor: Employee

  Background:
    Given a user "huba" is logged in
    And a user "huba" has no time registered
    And a project
    And the project has activities with the names:
      | Analysis  |
      | Design    |
      | Run tests |

  Scenario: User checks time registered for a date succesfully
    Given the date is "23-12-2025"
    When the employee registers 2.0 hours on "Design" on the date "23-12-2025"
    And the user checks time worked for the date "23-12-2025"
    Then 2.0 hours is returned

  Scenario: User checks time registered with different dates
    Given the date is "25-12-2025"
    When the employee registers 4.0 hours on "Analysis" on the date "24-12-2025"
    When the employee registers 5.0 hours on "Analysis" on the date "25-12-2025"
    And the user checks time worked for the date "25-12-2025"
    Then 5.0 hours is returned

  Scenario: User checks time registered with different activities
    Given the date is "26-12-2025"
    When the employee registers 4.0 hours on "Design" on the date "26-12-2025"
    When the employee registers 5.0 hours on "Analysis" on the date "26-12-2025"
    And the user checks time worked for the date "26-12-2025"
    Then 9.0 hours is returned

  Scenario: User checks time registered with different activities
    Given the date is "26-12-2025"
    When the employee registers 4.0 hours on "Design" on the date "26-12-2025"
    When the employee registers 5.0 hours on "Analysis" on the date "26-12-2025"
    And the user checks time worked for the date "26-12-2025"
    Then 9.0 hours is returned

  Scenario: User checks time registered with edited time
    Given the date is "27-12-2025"
    When the employee registers 2.0 hours on "Run tests" on the date "27-12-2025"
    And the user changes the registered time on activity "Run tests" at date "27-12-2025" to 3.0
    And the user checks time worked for the date "27-12-2025"
    Then 3.0 hours is returned