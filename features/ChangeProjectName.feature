Feature: Change Project Name
    Description: When an employee wants to change the name of a project
    Actor: Employee, Project Leader

    Background:
    Given an employee that is logged in
    And a project with name "Calculator"

    Scenario: Change project name without project leader
        Given the project has no project leader
        When the employee changes the project name to "Develop calculator"
        Then the project has the name "Develop calculator"
    
    Scenario: Change project name with project leader as non-project leader
        Given the project has a project leader
        And the employee is not a project leader
        When the employee changes the project name to "Create Calculator"
        Then the project has the name "Calculator"
        And an unsuccessful rename notification is given to the user
    
    Scenario: Change project name with project leader as project leader
        Given the project has a project leader
        And the employee is a project leader
        When the employee changes the project name to "Make calculator"
        Then the project has the name "Make calculator"
