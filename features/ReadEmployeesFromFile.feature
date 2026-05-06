
Feature: Read file with list of employee initials
  Description: The program reads a file containing the initials of all the employees
  Actor: User

  Scenario: A user starts the program and the employees are put in a list successfully
    Given a file "initials.txt" exists
    And the file "initials.txt" contains the following initials
      | huba |
      | anda |
      | wilo |
    When a user start the program
    Then the program returns a list containing
      | huba |
      | anda |
      | wilo |

  Scenario: A user starts the program and throws an exception
    Given a file "initials.txt" does not exist
    When a user start the program
    Then an error is now thrown "File not found!"
  @skip
  Scenario: A user starts the program and is warned about corrupted data
    Given a file "initials.txt" exists
    And the file contains the following initials
      | hubau |
      | anda  |
      | wilo  |
    When a user start the program
    Then a notification is given to the user
    And the program returns a list containing
      | anda |
      | wilo |