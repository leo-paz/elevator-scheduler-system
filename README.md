ELEVATOR PROJECT
----------------

How to run the program?

Run the ProgramManager.java and see the results in the console. For the test cases run all three
JUint files.

Source Code  Includes:
---------------------

Scheduler.java
- The Scheduler class that extends Thread. This class allows the elevator to uses the input to assign the floor to the elevator while it also coordinates with the floor buttons.

FloorSubsystem.java
- This is the FloorSubsystem class that gets the direction of the elevator traveling to, time it will take and the destination floor.

Elevator.java
- This is the Elevator class. It get elevator gets the requests from the floors to be serviced.

FloorButtonRequest.java
- This is the FloorButtonRequest class which has setter and getter methods for the elevator  system as well as the up and down buttons pertaining to the floors.
 
ProgramManager.java
- This is the main method of the project. That create all the instance required for the Elevator system to function.  

Direction.java
- This is the direction class that represents the direction of elevator traveling. It is a enum, hence the UP and DOWN values are constant.

Test Included:
--------------

testScheduler.java
testFloorSubsystem.java
testFloorButtonRequest.java

UML Diagrams:
--------------

UML Class Diagram
UML Sequence Diagram

Breakdown of Responsibilities:
------------------------------

Sarah Lamonica
Mounica
Leo

Shoana Sharma:
- Documentation for all the source code
- UML Class Diagram
- Review/Modifications to source code
- testScheduler.java class
- README file


Fatima Hash:
- UML Sequence Diagram
- testFloorSubsystem.java
- testFloorButtonRequest.java
- README file
