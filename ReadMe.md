# Run the JAR
A jar is provided at ``/out/artifacts/javaFX_project_jar``, run it with ``java -jar SuperPlanner2000.jar``.

# Run the application from an IDE
The application was written in and tested for Java 21.

To run the application press `mvn javafx:run`
Alternatively, in an IDE, go to ``/src/main/java/dtu/planner.ui`` and run ``App.java``

You will have to log in as one of the users in the database, one of which is ``huba``

# Run tests

To run the tests, press `mvn clean test` (Both cucumber TDD tests and WhiteBox tests).
Alternatively, in an IDE, go to ``/src/test/java/hellocucumber`` and run ``RunCucumberTest.java`` to run the cucumber TDD tests,
and to run WhiteBox tests go to ``/src/test/java/dtu/whitebox`` and run the files in the folder (right-click and run the entire folder).

# Using the application

When logged in, you will have the option to add a project (bottom left), or open the `User` menu (top left)

## User menu
The user menu provides the features:
- `Logout` - log out of the application, allowing you to change the user.
- `Register Time` - a shortcut to quickly see assigned activities and register time to them. [^1] [^2]
- `Add fixed activity` - allows marking Vacation, Sick, or Emergency for a period of time, causing you to be unavailable for the period.

## Create Project
When creating a project, you may choose a Project leader, or leave it unset, and may give the project a name.

## Project view
After a project has been created, you can click on it in the left list of projects. Here it will show:
- Project details (Name, Leader, ID, Start Date)
- List of activities
- "Add activity" button
- "Edit project" button
- "View Report" button

### Add activity
Only accessible if logged in as the project's project leader, or the project has no project leader.

Allows creating an activity for the project, with the options:
- Name (optional)
- Start date (defaults to today)
- End date (defaults to today)
- Budgeted hours (defaults to 0)

### Edit project
Only accessible if logged in as the project's project leader, or the project has no project leader.

Allows for changing the project name and project leader. Only possible if logged in as the project leader, or the project does not have a project leader.

### View Report
Only accessible if logged in as the project's project leader, or the project has no project leader.

This shows 2 graphs:
1. a pie chart over what proportion of the budgeted time on all the activities has been used
2. a bar graph over how much of each activity's budgeted time has been used

## Activities

Pressing on an activity shows the buttons:
- Register time
- Edit registered time
- Edit activity
- Assign employee

And displays:
- Hours spent
- Budgeted hours

### Register time

Here you can register time to the activity, allowing for choice of date and time (30 minutes intervals, 0 to 23:30)

### Edit registered time

Here you can chose any of the dates you have time registrations on, and change the time registered for that date (30 minute intervals, 0 to 23:30)

### Edit activity

Only accessible if logged in as the project's project leader, or the project has no project leader.

Here you can change:
- Activity name
- Start date
- End date
- Budgeted hours

### Assign Employees

Only accessible if logged in as the project's project leader, or the project has no project leader.

Here you can see a list of employees that:
1. Does not have a fixed activity in the period the activity runs for
2. Is not already assigned to the activity

You can then assign any of these users to the activity.

[^1]: It is also possible to register time to activities the employee is not assigned to, this is just a quick shortcut for registering time on assigned activities.

[^2]: It is also possible to access this menu with ``ctrl + r``
