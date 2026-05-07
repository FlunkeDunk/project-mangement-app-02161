Feature: Find available employees
  Description: A project leader finds available employees
  Actor: Project leader

   Scenario: Project leader finds available employees in week range
     Given a project
     And a user is logged in
     And the user is the project leader
     And the following employees exist
       | Balin  |
       | Dwalin |
       | Bifur  |
       | Bombur |
     And the project has the activities with the names, start and end weeks
      | Take the hobbits to Isengard | 2 | 4 |
      | Simply walk into Mordor      | 5 | 7 |
      | Retake the lonely mountain   | 5 | 7 |
      | Reclaim stolen silverware    | 5 | 7 |
     And "Balin" gets assigned to "Take the hobbits to Isengard"
     And "Balin" gets assigned to "Simply walk into Mordor"
     And "Dwalin" gets assigned to "Simply walk into Mordor"
     And "Dwalin" gets assigned to "Retake the lonely mountain"
     And "Bifur" has a fixed activity from week 5 to week 7
     And "huba" has a fixed activity from week 6 to week 18
     When the user searches for available employees for "Reclaim stolen silverware" in between week 5 and 7
     Then the employees are returned in the following order
       | Bombur |
       | Balin  |
       | Dwalin |

     # Partial overlap? TODO

  # Sørg for dem der allerede er på listen ikke returneres