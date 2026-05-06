Feature: User Login
  Description: When the user logs into the program using their account details.
  Actor: User

  Scenario: Successful login
    And the initials "AS" exists in the database
    When the user tries to log in as "AS"
    Then then the user is successfully logged in as "AS"

  Scenario: Unsuccessful login
    And the initials "DW" exists in the database
    When the user tries to log in as "cookie"
    Then the user is not logged in
    And an exception is thrown "Employee with initials cookie does not exist."
