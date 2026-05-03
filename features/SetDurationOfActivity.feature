Feature: Set duration of activity
  Description: A project leader sets the duration of an activity
  Actor: Project leader

  Background:
    Given a project
    And the project has the activity "Design"
    And the project has a project leader

  Scenario Outline: A project leader sets duration of an activity successfully
    When the project leader sets the activity "Design" to start in week <start week>, year <start year>
    And the project leader sets the activity "Design" to end in week <end week>, year <end year>
    Then the activity starts in year <expected start year>
    And the activity starts in week <expected start week>
    And the activity ends in year <expected end year>
    And the activity ends in week <expected end week>

    Examples:
      | start year | end year | start week | end week | expected start year | expected end year | expected start week | expected end week |
      | 2027       | 2027     | 1          | 4        | 2027                | 2027              | 1                   | 4                 |
      | 2027       | 2027     | 5          | 8        | 2027                | 2027              | 5                   | 8                 |
      | 2026       | 2027     | 51         | 1        | 2026                | 2027              | 51                  | 1                 |
      | 2026       | 2026     | 50         | 58       | 2026                | 2027              | 50                  | 5                 |
      | 1957       | 1957     | 51         | 99       | 1957                | 1958              | 51                  | 47                |
      | 1988       | 2043     | 2          | 1920     | 1988                | 2079              | 2                   | 41                |


  Scenario Outline: A project leader sets start date to be after end date
    When the project leader sets the activity "Design" to start in week <start week>, year <start year>
    And the project leader sets the activity "Design" to end in week <end week>, year <end year>
    Then the activity starts in year <start year>
    And the activity starts in week <start week>
    And the activity ends in year <start year>
    And the activity ends in week <start week>
    And the user is notified that the start week is after end week

    Examples:
      | start year | end year | start week | end week |
      | 2027       | 2027     | 7          | 4        |
      | 2027       | 2026     | 1          | 51       |

#  Scenario Outline: A project leader sets duration of an activity unsuccessfully
#    When the project leader sets the activity "Design" to start in year <start year>
#    And sets "Design" to end in year <end year>
#    And sets "Design" to start in week <start week>
#    And sets "Design" to end in week <end week>
#    Then an exception is thrown
#
#    Examples:
#      | start year | end year | start week | end week |
#      | -2027      | 2027     | 1          | 4        |
#      | 2027       | -2027    | 5          | 8        |
#      | 2026       | 2027     | -51        | 1        |
#      | 2026       | 2027     | 51         | -1       |
#
#
#
#