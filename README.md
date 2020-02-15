ELEVATOR PROJECT - Iteration Two
--------------------------------
GitHub Link: https://github.com/Truzian/elevator-scheduler-system

How to run the program?

Run the ProgramManager.java and see the results in the console. For the test cases run all three
JUint files to run them use Java 13 on Eclipse. 

Source Code Includes:
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
- This is the main method of the project. That create all the instance required for the Elevator system to function. Since threads were used for implemetation, the call to the elevator are random. 

Direction.java
- This is the direction class that represents the direction of elevator traveling. It is a enum, hence the UP and DOWN values are constant.

State.java
- This is State class where it describes the state of the elevator system. It describes when the elevator is starting, moving, stopping and when the doors are opening and closing. It also describes whether the elevator has reached its destination, stopped at a floor and opened its doors. This is the superclass to the rest of the state classes. 

MoveState.java
- This is a subclass of State describing the motion behaviour of the elevator and its doors. It describes when the doors are opening and when passengers are entering. 

StopState.java
- This is a subclass of State describing the motion behaviour of the elevator between floors. It describes where the elevator is heading to stop and let passengers in.

EndState.java
- This is a subclass of State describing the final state of the elevator, when there is no more movement between floors and of the doors. 

Test Included:
--------------
testScheduler.java
testFloorSubsystem.java
testFloorButtonRequest.java
testEndState.java
testMoveState.java

UML Diagrams:
--------------
UML Class Diagram
UML Sequence Diagram
UML State Diagram

Breakdown of Responsibilities of Iteration Two:
-----------------------------------------------
Sarah Lamonica
- Source Code:
       - Elevator.java
       - Scheduler.java
       - FloorSubsystem.java
       - ProgramManager.java
       - Review and modifications to other source code
       - Documentation

Leonardo Paz:
- Source code:
     - FloorSubsystem.java
     - Scheduler.java
- Review and modifications to other pieces of soure code

Mounica Pillarisetty:
- UML Class Diagram 
- Reviewed and modified code
- ReadME

Shoana Sharma:
- Test cases
- Reviewed and modified source code
- READMe
- UML State Diagram

Fatima Hashi:
- Reviewed and modified source
- Test cases
- UML Sequence Diagram
