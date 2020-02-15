/**
 * State class describes the state of the system; 
 * whether the elevator has begun and is moving & doors are opening/closing,
 * whether the elevator has stopped at a floor & doors have opened,
 * whether the elevator has reached its destination and stopped at an end state.
 *  
 * @version Iteration 2: Feb 14th 2020 
 */
public abstract class State {
	
	// use our values we calculated for the iteration 0
	protected static final long FloorToFloorTime = 3000;
	protected static final long OpenDoorTime = 1350;
	protected static final long HoldDoorTime = 5000;
	protected static final long CloseDoorTime = 1650;
    protected Elevator elevator;

    public State(Elevator ele) {
        this.elevator = ele;
    }

	public abstract void moveDoor();
    public abstract void moveElevator();
  

}