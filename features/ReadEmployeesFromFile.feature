Feature: Read file with list of employee initials
  Description: The program reads a file containing the initials of all the employees
  Actor: User

  Scenario: A user starts the program and the employees are put in a list successfully
    Given a file "initials.txt" exists
    And the file "initials.txt" contains the following initials
      | huba |
      | anda |
      | wilo |
    When the file is loaded
    Then the program returns a list containing
      | huba |
      | anda |
      | wilo |

  Scenario: A user starts the program and throws an exception
    Given a file "initials.txt" does not exist
    When the file is loaded
    Then an error is thrown "File not found!"

  Scenario: A user starts the program and is warned about corrupted data
    Given a file "initials.txt" exists
    And the file "initials.txt" contains the following initials
      | hubau |
      | anda  |
      | wilo  |
    When the file is loaded
    Then a notification is given to the user 
    And the program returns a list containing
      | anda |
      | wilo |