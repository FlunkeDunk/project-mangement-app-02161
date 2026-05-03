@skip # Fjern denne linje når du vil implementere
Feature: Add fixed activity
  Description: When adding a fixed activity to an employee that fixed activity will exist to show others when
  the employee is available for regular activities. A fixed activity could be things such as a holiday or sick days.
  Actor: Employee

  Scenario Outline: Employee successfully sets fixed activity
    Given a user is logged in
    When the employee sets the fixed activity "Vacation" to start in year <start year>
    And sets "Vacation" to end in year <end year>
    And sets "Vacation" to start in week <start week>
    And sets "Vacation" to end in week <end week>
    Then the fixed activity starts in year <expected start year>
    And the fixed activity starts in week <expected start week>
    And the fixed activity ends in year <expected end year>
    And the fixed activity ends in week <expected end week>


    Examples:
      | start year | end year | start week | end week | expected start year | expected end year | expected start week | expected end week |
      | 2027       | 2027     | 1          | 4        | 2027                | 2027              | 1                   | 4                 |
      | 2027       | 2027     | 5          | 8        | 2027                | 2027              | 5                   | 8                 |
      | 2026       | 2027     | 51         | 1        | 2026                | 2027              | 51                  | 1                 |
      | 2026       | 2026     | 50         | 58       | 2026                | 2027              | 50                  | 6                 |

  Scenario Outline: Employee fails in setting fixed activity
    Given a user is logged in
    And a fixed activity "Sick"
    And "Sick" is set to start in year <start year>
    And "Sick" is set to end in year <end year>
    And "Sick" is set to start in week <start week>
    And "Sick" is set to end in week <end week>
    When the employee sets the fixed activity "Sick" to start the year <start year>
    And sets "Sick" to end in year <end year>
    And sets "Sick" to start in week <start week>
    And sets "Sick" to end in week <end week>
    Then the fixed activity is not added
    And the message "The fixed activity \"Sick\" already exists for that time period" is shown

    Examples:
      | start year | end year | start week | end week |
      | 2027       | 2027     | 1          | 4        |
      | 2027       | 2027     | 5          | 8        |
      | 2026       | 2027     | 51         | 1        |

  Scenario Outline: Employee sets duration of an activity unsuccessfully
    When the employee sets the activity fixed "Vacation" to start in year <start year>
    And sets "Vacation" to end in year <end year>
    And sets "Vacation" to start in week <start week>
    And sets "Vacation" to end in week <end week>
    Then an exception is thrown

    Examples:
      | start year | end year | start week | end week |
      | -2027      | 2027     | 1          | 4        |
      | 2027       | -2027    | 5          | 8        |
      | 2026       | 2027     | -51        | 1        |
      | 2026       | 2027     | 51         | -1       |
