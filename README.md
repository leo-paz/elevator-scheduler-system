ELEVATOR PROJECT - Iteration Three
-----------------------------------
GitHub Link: https://github.com/Truzian/elevator-scheduler-system

How to run the program?
-----------------------
Command Line:

1. Navigate to src folder
2. javac ElevatorSystem.java
3. javac FloorSystem.java
3. javac Scheduler.java

Run the mentioned programs on three different consoles and see the results. For the test cases run all three
JUint files on Java 13 on Eclipse. 

Reflection on how concurrency control at the scheduler changed from Iteration 2 to Iteration 3.
---------------------
In Interation 2, threads were implemented to concurrently schedule tasks between the floor calls and the user calls inside one elevator. In Iteration 3, UDP was used to create Datagram packets to send and receive messages throug the scheduler to the floor. The Scheduler was also used to coordinate the movement of elevators so that each elevator carries roughly the same number of passengers as all of the others and so that the waiting time for passengers at floors is minimized. This logic was implemented using the datagram packets as well.

Source Code Includes:
---------------------
Direction.java
- This is the direction class that represents the direction of elevator traveling. It is a enum, hence the UP and DOWN values are constant.

ElevatorSubsystem.java
- This is the ElevatorSubSystem class that is creating 4 threads for 4 different elevators.

Elevator.java
- This is the Elevator class. It get elevator gets the requests from the floors to be serviced.

Scheduler.java
- The Scheduler class that extends Thread. This class allows the elevator to uses the input to assign the floor to the elevator while it also coordinates with the floor buttons.

FloorSubsystem.java
- This is the FloorSubsystem class that gets the direction of the elevator traveling to, time it will take and the destination floor.

Floor.java
- This is the floor class that has the getter and setter method for the floor user desires to go to and access the current floor.

FloorButtonRequest.java
- This is the FloorButtonRequest class which has setter and getter methods for the elevator system as well as the up and down buttons pertaining to the floors.
 
ReceiveConfirmation
- This is the ReceiveConfirmation class that receives a request using datagram packets after a request has been sent. 

Sendrequest
- This is the SendRequest Class that sends the request to the scheduler. The elevator direction button, the user input floor all are sent though this class.

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
testFloorButtonRequest.java
testElevatorSubsytem.java -- a test file to verify that elevator subsytem is working as intende


UML Diagrams:
--------------
UML Class Diagram
UML Sequence Diagram

Breakdown of Responsibilities of Iteration Three:
-----------------------------------------------
Sarah Lamonica
- Source Code:
	- State.java
	- MoveState.java
	- Elevator.java
- Review and modifications to other pieces of source code
- Documentation

Leonardo Paz:
- Source code:
     - Scheduler.java
- Review and modifications to other pieces of source code
- Documentation

Mounica Pillarisetty:
- FloorSubsytem code
- Elevator subsystem test file
- Review and modifications to other pieces of source code
- Modifications to ReadME

Shoana Sharma:
- Reviewed and modified source code
- Java Documentation
- README
- UML Class Diagrams

Fatima Hashi:
- Reviewed and modified source
- Test cases
- UML Sequence Diagram

