Feature: Add fixed activity
  Description: When adding a fixed activity to an employee that fixed activity will exist to show others when
  the employee is available for regular activities. A fixed activity could be things such as a holiday or sick days.
  Actor: Employee

  Background:
    Given a user is logged in

  Scenario Outline: User successfully sets fixed activity
    When the user creates a fixed activity "Vacation" with timeframe
      | <start week> | <start year> |
      | <end week>   | <end year>   |
    Then the user has the fixed activity "Vacation" with timeframe
      | <expected start week> | <expected start year> |
      | <expected end week>   | <expected end year>   |

    Examples:
      | start year | end year | start week | end week | expected start year | expected end year | expected start week | expected end week |
      |       2027 |     2027 |          1 |        4 |                2027 |              2027 |                   1 |                 4 |
      |       2027 |     2027 |          5 |        8 |                2027 |              2027 |                   5 |                 8 |
      |       2026 |     2027 |         51 |        1 |                2026 |              2027 |                  51 |                 1 |
      |       2026 |     2026 |         50 |       58 |                2026 |              2027 |                  50 |                 5 |

  Scenario Outline: User fails in setting fixed activity
    Given a fixed activity "Sick" with timeframe
      | <start week> | <start year> |
      | <end week>   | <end year>   |
    When the user creates a fixed activity "Sick" with timeframe
      | <start week> | <start year> |
      | <end week>   | <end year>   |
    Then an exception is thrown "The employee already has that fixed activity"
    And the user has 1 fixed activities

    Examples:
      | start year | end year | start week | end week |
      |       2027 |     2027 |          1 |        4 |
      |       2027 |     2027 |          5 |        8 |
      |       2026 |     2027 |         51 |        1 |

   Scenario Outline: User sets duration of an activity unsuccessfully
     When the user creates a fixed activity "Vacation" with timeframe
       | <start week> | <start year> |
       | <end week>   | <end year>   |
     Then an exception is thrown "DateError: End date must be after start date"
     Examples:
       | start year | end year | start week | end week |
       |       2027 |    -2027 |          5 |        8 |
       |         -1 |       -2 |          1 |       40 |
       |       1987 |     1987 |          5 |        4 |
       |       1653 |     1654 |         52 |      -16 |
