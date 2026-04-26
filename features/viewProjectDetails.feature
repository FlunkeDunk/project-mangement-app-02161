Feature: View Project details
  A user can click on a project and view details about it

  Actor: Employee

  Background:
    Given a user is logged in

  Scenario: View the project leader of a project with no project leader
    Given 1 project exists
    And the project has no Project leader
    When the user views the details of the project
    Then "none" is shown for the field "project_leader"

  Scenario: View the project leader of a project with a project leader 1
    Given 1 project exists
    And the project has the project leader "Sauron"
    When the user views the details of the project
    Then "Sauron" is shown for the field "project_leader"

  Scenario: View the project leader of a project with a project leader 2
    Given 1 project exists
    And the project has the project leader "Frodo"
    When the user views the details of the project
    Then "Frodo" is shown for the field "project_leader"

  Scenario: View the project name of a project with no name
    Given 1 project exists
    And the project has no name
    When the user views the details of the project
    Then "none" is shown for the field "project_leader"

  Scenario: View the project name of a project with a name 1
    Given 1 project exists
    And the project has the name "Lord of the Rings"
    When the user views the details of the project
    Then "Lord of the Rings" is shown for the field "project_name"

  Scenario: View the project name of a project with a name 2
    Given 1 project exists
    And the project has the name "The Hobbit"
    When the user views the details of the project
    Then "The Hobbit" is shown for the field "project_name"

  Scenario: View the project start-date 1
    Given 1 project exists
    And the project starts in week 6 and year 2007
    When the user views the details of the project
    Then "Week 6, 2007" is shown for the field "start_date"

  Scenario: View the project start-date 2
    Given 1 project exists
    And the project starts in week 4 and year 2020
    When the user views the details of the project
    Then "Week 4, 2020" is shown for the field "start_date"

  Scenario: View the project activities with no activities
    Given 1 project exists
    And the project has no activties
    When the user views the details of the project
    Then "no activities" is shown for the field "activities"

  Scenario: View the project activities with 1 activity
    Given 1 project exists
    And the project has activities with the names
      | Invade Mordor |
    When the user views the details of the project
    Then activities with these names are shown
      | Invade Mordor |

  # Projektbeskrivelse siger at 30 er et typisk antal aktiviteter for et projekt.
  Scenario: View the project activities with 30 activities
    Given 1 project exists
    And the project has activities with the names
      | Invade Mordor                        |
      | Defend Gondor                        |
      | Defeat Smaug                         |
      | Forge alliance with the elves        |
      | Watch fireworks in Hobbiton          |
      | Bring ring to Mount Doom             |
      | Escape from Moria                    |
      | Cross the Misty Mountains            |
      | Take the hobbits to Isengard         |
      | Die fighting side by side with elf   |
      | Guard the gates of Minas Tirith      |
      | Solve riddles with Gollum            |
      | Deliver message to Rivendell         |
      | Train with the Rangers               |
      | Negotiate with the dwarves           |
      | Explore the depths of Fangorn Forest |
      | Prepare defenses at Helm's Deep      |
      | Track orc movements                  |
      | Secure passage through Bree          |
      | Gather herbs in the Shire            |
      | Light the beacons of Gondor          |
      | Escort caravan to Dale               |
      | Investigate ruins of Dol Guldur      |
      | Visit the Old Forest                 |
      | Ride Barrels down river              |
      | Defend Lake-Town                     |
      | Escape giant spiders                 |
      | Recover stolen silverware            |
      | Evade the orc scouts                 |
      | Recruit Aragorn, son of Arathorn     |
      | Mash potatoes and put them in a stew |
    When the user views the details of the project
    Then activities with these names are shown
      | Invade Mordor                        |
      | Defend Gondor                        |
      | Defeat Smaug                         |
      | Forge alliance with the elves        |
      | Watch fireworks in Hobbiton          |
      | Bring ring to Mount Doom             |
      | Escape from Moria                    |
      | Cross the Misty Mountains            |
      | Take the hobbits to Isengard         |
      | Die fighting side by side with elf   |
      | Guard the gates of Minas Tirith      |
      | Solve riddles with Gollum            |
      | Deliver message to Rivendell         |
      | Train with the Rangers               |
      | Negotiate with the dwarves           |
      | Explore the depths of Fangorn Forest |
      | Prepare defenses at Helm's Deep      |
      | Track orc movements                  |
      | Secure passage through Bree          |
      | Gather herbs in the Shire            |
      | Light the beacons of Gondor          |
      | Escort caravan to Dale               |
      | Investigate ruins of Dol Guldur      |
      | Visit the Old Forest                 |
      | Ride Barrels down river              |
      | Defend Lake-Town                     |
      | Escape giant spiders                 |
      | Recover stolen silverware            |
      | Evade the orc scouts                 |
      | Recruit Aragorn, son of Arathorn     |
      | Mash potatoes and put them in a stew |

  # Projektbeskrivelse siger projekter kan have "næsten 100", derfor også brug for understøttelse for mere end 30
  Scenario: View the project activities with 35 activities
    Given 1 project exists
    And the project has activities with the names
      | Invade Mordor                        |
      | Defend Gondor                        |
      | Defeat Smaug                         |
      | Forge alliance with the elves        |
      | Watch fireworks in Hobbiton          |
      | Bring ring to Mount Doom             |
      | Escape from Moria                    |
      | Cross the Misty Mountains            |
      | Take the hobbits to Isengard         |
      | Die fighting side by side with elf   |
      | Guard the gates of Minas Tirith      |
      | Solve riddles with Gollum            |
      | Deliver message to Rivendell         |
      | Train with the Rangers               |
      | Negotiate with the dwarves           |
      | Explore the depths of Fangorn Forest |
      | Prepare defenses at Helm's Deep      |
      | Track orc movements                  |
      | Secure passage through Bree          |
      | Gather herbs in the Shire            |
      | Light the beacons of Gondor          |
      | Escort caravan to Dale               |
      | Investigate ruins of Dol Guldur      |
      | Visit the Old Forest                 |
      | Ride Barrels down river              |
      | Defend Lake-Town                     |
      | Escape giant spiders                 |
      | Recover stolen silverware            |
      | Evade the orc scouts                 |
      | Recruit Aragorn, son of Arathorn     |
      | Mash potatoes and put them in a stew |
      | Invite Thorin Oakenshield to party   |
      | Invite Balin to party                |
      | Invite Dwalin to party               |
      | Invite Fili to party                 |
      | Invite Kili to party                 |
      | Invite Dori to party                 |
      | Invite Nori to party                 |
      | Invite Ori to party                  |
      | Invite Oin to party                  |
      | Invite Gloin to party                |
      | Invite Bifur to party                |
      | Invite Bofur to party                |
      | Invite Bombur to party               |
    When the user views the details of the project
    Then activities with these names are shown
      | Invade Mordor                        |
      | Defend Gondor                        |
      | Defeat Smaug                         |
      | Forge alliance with the elves        |
      | Watch fireworks in Hobbiton          |
      | Bring ring to Mount Doom             |
      | Escape from Moria                    |
      | Cross the Misty Mountains            |
      | Take the hobbits to Isengard         |
      | Die fighting side by side with elf   |
      | Guard the gates of Minas Tirith      |
      | Solve riddles with Gollum            |
      | Deliver message to Rivendell         |
      | Train with the Rangers               |
      | Negotiate with the dwarves           |
      | Explore the depths of Fangorn Forest |
      | Prepare defenses at Helm's Deep      |
      | Track orc movements                  |
      | Secure passage through Bree          |
      | Gather herbs in the Shire            |
      | Light the beacons of Gondor          |
      | Escort caravan to Dale               |
      | Investigate ruins of Dol Guldur      |
      | Visit the Old Forest                 |
      | Ride Barrels down river              |
      | Defend Lake-Town                     |
      | Escape giant spiders                 |
      | Recover stolen silverware            |
      | Evade the orc scouts                 |
      | Recruit Aragorn, son of Arathorn     |
      | Mash potatoes and put them in a stew |
    And an option to cycle shown activities is given