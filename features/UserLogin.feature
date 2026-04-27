Feature: User Login
    Description: When the user logs into the program using their account details.
    Actor: User

    Scenario: Successful login
        Given a user that is logged out with username "Alan Smith" and password "banana"
        When the user enters "Alan Smith" into the username box
        And the user enters "banana" into the password box
        Then then the user is successfully logged in
    
    Scenario: Unsuccessful login
        Given a user that is logged out with username "Dennis Woods" and password "meatball"
        When the user enters "Smurf" into the username box
        And the user enters "cookie" into the password box
        Then then the user is not logged in
        And a unsuccessful login notification is given to the user
