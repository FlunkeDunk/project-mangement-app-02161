Feature: Find available employees
  Description: A project leader finds available employees
  Actor: Project leader

#   Scenario: Project leader finds available employees in week range
#     Given a project
#     And an activity
#     And a project leader
#     And the following employees exist
#       | Employee1 |
#       | Employee2 |
#       | Employee3 |
#       | Employee4 |
#     And the following activities exist
#       | 2 - 4 |
#       | 5 - 7 |
#     And the following fixed activities exist
#       | 5 to 7 |
#     And "Employee1" has 1 activity 2 to 4
#     And "Employee1" has 1 activity 5 to 7
#     And "Employee2" has 2 activities 5 to 7
#     And "Employee3" has 1 fixed activity 5 to 7
#     When the user searches for available employees in between week 5 and 7
#     Then the employees are returned in the following order
#       | Employee4 |
#       | Employee1 |
#       | Employee2 |
#       | Employee3 |
