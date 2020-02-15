/**
 * This is the Elevator class. It get elevator gets the requests from the floors to be serviced
 * @version Iteration 2: Feb 15th 2020
 *
 */
public class Elevator implements Runnable {
	Scheduler s;
	State currentState; //holds the current state of the elevator
	boolean isElevatorMoving = false;
	boolean isDoorOpen = false;
	private int currentFloor;
	
	/**
	 * This is the constructor
	 * @param s is a type Scheduler
	 */
	public Elevator(Scheduler s) {
		this.s = s;
	}
	
	/**
	 * This is to set the currentFloor the elevator is on
	 * @param floor The floor to set the current floor too
	 */
	public void setFloor(int floor) {
		currentFloor = floor;
		currentState.moveDoor();
		currentState.moveElevator();
	}
	
	/**
	 * Returns current floor the elevator is on
	 */
	public int getFloor() {
		return currentFloor;
	}
	
	/**
	 * This is called when we arrive at a new floor
	 */
	public void arriveAtFloor() {
		currentState.moveDoor();
		currentState.moveElevator();
	}
	
	/**
	 * This is called to set a new state (moving, stopped, end)
	 * @param state
	 */
	public void setState(State state) {
		this.currentState = state;
	}
	
	/**
	 * When the elevator is moving, we set these boolean values
	 */
	public void setElevatorMoving()
	{
		isElevatorMoving = true;
		isDoorOpen = false;
	}
	
	/**
	 *  When elevator is stopped, we set these boolean values
	 */
	public void setElevatorStopped()
	{
		isElevatorMoving = false;
		isDoorOpen = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		StopState ms = new StopState(this);
		setState(ms);
		
		while(true) {
			FloorButtonRequest request = s.getElevator();//the destination floor the elevator is asked to get to
			System.out.println(Thread.currentThread().getName() + " has been requested and elevator goes to floor " + request.getFloorNum() + " for pickup.");
			
			// TODO: Add a set method for the rest of the elevator information so it can be passed to states
			setFloor(Integer.parseInt(request.getFloorNum()));
			arriveAtFloor();
			System.out.println("The passenger gets on the elevator at floor " + request.getFloorNum() + " and goes " + request.getDirection().toString() + " to Floor " + request.getDestinationFloor());
			// if we are done the requests move to end state
			if(request.isLastRequest()) {
				setState(new EndState(this));
				currentState.moveDoor();
				currentState.moveElevator();
			}
		}
		
	}
	
}
