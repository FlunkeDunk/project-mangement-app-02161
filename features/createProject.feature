Feature: Create project
    A user can create a project with or without a duration
    and the user will then be the first Employee added to the project.

    Actor: Employee

    Background:
        Given a user is logged in
        And the date is "24-01-2027"
    
    Scenario: Creates a project
        Given there are no projects created this year
        When the user creates a project
        Then there is a project
        And the project starts in week 3 and year 2027
        And the project has the id 27001
        And the project has no Project leader

    Scenario: User try creating the 1000th
        When there are 999 projects created this year
        And the user creates a project
        Then An error is thrown "Cannot create more than 999 projects a year"