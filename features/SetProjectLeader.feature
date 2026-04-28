Feature: Set project leader
  Description: This describes when a employee inside a project is promoted to attempts to promote someone to project leader.
  A employee can both promote themselves and others, but only if there is no existing project leader.

  Actors: Project leader, Employee

#   Scenario: Promote an employee to a project leader
#       Given a project
#       And the project has at least one member
#       And the project has no project leader
#       When an employee promotes an employee to a project leader
#       Then the promoted employee is a project leader in the project

#   Scenario: Attempt to promote employee with already existing project leader
#       Given a project
#       And the project has at least one member
#       And the project has a project leader
#       When a employee attempts to promote an employee to a project leader
#       Then the promoted employee is not a project leader in the project