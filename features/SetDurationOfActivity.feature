Feature: Set duration of activity
  Description: A project leader sets the duration of an activity
  Actor: Project leader

  Background:
    Given a project
    And an activity "Design" for the project

  Scenario Outline: A project leader sets duration of an activity successfully
    Given a project leader
    When the project leader sets the activity "Design" to start in year <start year>
    And sets "Design" to end in year <end year>
    And sets "Design" to start in week <start week>
    And sets "Design" to end in week <end week>
    Then the activity starts in year <start year>
    And the activity starts in week <start week>
    And the activity ends in year <end year>
    And the activity ends in week <end week>

    Examples:
      | start year | end year | start week | end week |
      | 2027       | 2027     | 1          | 4        |
      | 2027       | 2027     | 5          | 8        |
      | 2026       | 2027     | 51         | 1        |

  Scenario Outline: A project leader sets start date to be after end date
    Given a project leader
    When the project leader sets the activity "Design" to start in year <start year>
    And sets "Design" to end in year <end year>
    And sets "Design" to start in week <start week>
    And sets "Design" to end in week <end week>
    Then the activity starts in year <start year>
    And the activity starts in week <start week>
    And the activity ends in year <start year>
    And the activity ends in week <start week>
    And the message "Ending week was later than starting week" is shown
  # Having both set to start year and start week is intentional

    Examples:
      | start year | end year | start week | end week |
      | 2027       | 2027     | 7          | 4        |
      | 2027       | 2026     | 1          | 51       |
