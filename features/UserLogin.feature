Feature: User Login
  Description: When the user logs into the program using their account details.
  Actor: User

  Scenario: Successful login
    Given a user that is logged out with username initials "AS"
    When the user enters "AS" into the username box
    Then then the user is successfully logged in

  Scenario: Unsuccessful login
    Given a user that is logged out with username "DW"
    When the user enters "cookie" into the username box
    Then the user is not logged in
    And an unsuccessful login notification is given to the user
