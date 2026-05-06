Feature: Set duration of activity
  Description: A project leader sets the duration of an activity
  Actor: Project leader

  Background:
    Given a project
    And the project has the activity "Design"
    And the project has a project leader

  Scenario Outline: A project leader sets duration of an activity successfully
    When the project leader sets the timeframe of activity "Design" to
      | <start week> | <start year> |
      | <end week>   | <end year>   |
    Then the activity starts in year <expected start year> and week <expected start week>
    And the activity ends in year <expected end year> and week <expected end week>

    Examples:
      | start year | end year | start week | end week | expected start year | expected end year | expected start week | expected end week |
      |       2027 |     2027 |          1 |        4 |                2027 |              2027 |                   1 |                 4 |
      |       2027 |     2027 |          5 |        8 |                2027 |              2027 |                   5 |                 8 |
      |       2026 |     2027 |         51 |        1 |                2026 |              2027 |                  51 |                 1 |
      |       2026 |     2026 |         50 |       58 |                2026 |              2027 |                  50 |                 5 |
      |       1957 |     1957 |         51 |       99 |                1957 |              1958 |                  51 |                47 |
      |       1988 |     2043 |          2 |     1920 |                1988 |              2079 |                   2 |                41 |
      |      -2027 |     2027 |          1 |        4 |               -2027 |              2027 |                   1 |                 4 |
      |       2026 |     2027 |         51 |       -1 |                2026 |              2026 |                  51 |                53 |
      |       2026 |     2027 |        -51 |        2 |                2025 |              2027 |                   2 |                 2 |
      |       1934 |     1987 |         56 |      321 |                1935 |              1993 |                   4 |                 7 |


  Scenario Outline: A project leader sets start date to be after end date
    When the project leader sets the timeframe of activity "Design" to
      | <start week> | <start year> |
      | <end week>   | <end year>   |
    Then an exception is thrown "End date must be after start date"

    Examples:
      | start year | end year | start week | end week |
      |       2027 |     2027 |          7 |        4 |
      |       2027 |     2026 |          1 |       51 |

  Scenario Outline: A project leader sets invalid weeks unsuccessfully
    When the project leader sets the timeframe of activity "Design" to
      | <start week> | <start year> |
      | <end week>   | <end year>   |
    Then an exception is thrown "DateError"

    Examples:
      | start year | end year | start week | end week |
      |       2027 |    -2027 |          5 |        8 |
      |       2023 |     2025 |          0 |        2 |
      |       2017 |     2019 |          3 |        0 |
