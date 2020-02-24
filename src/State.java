/**
 * State class describes the state of the system; 
 * whether the elevator has begun and is moving & doors are opening/closing,
 * whether the elevator has stopped at a floor & doors have opened,
 * whether the elevator has reached its destination and stopped at an end state.
 *  
 * @version Iteration 2: Feb 15th 2020 
 */
public abstract class State {
	
	// use our values we calculated for the iteration 0 for the time taken to for
	// all required services
	protected static final long FloorToFloorTime = 3500;
	protected static final long OpenDoorTime = 1300;
	protected static final long HoldDoorTime = 4500;
	protected static final long CloseDoorTime = 1600;
    protected Elevator elevator;

    /**
     * This is the constructor method
     * @param ele is type from elevator class
     */
    public State(Elevator ele) {
        this.elevator = ele;
    }

	public abstract void moveDoor(); //calling moveDoor method from StopState class
    public abstract void moveElevator(); //calling moveElevator method from the StopState class
  

}