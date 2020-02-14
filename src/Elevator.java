/**
 * This is the Elevator class. It get elevator gets the requests from the floors to be serviced
 * @version Iteration 1: Feb 1st 2020
 *
 */
public class Elevator implements Runnable {
	Scheduler s;
	State currentState; //holds the current state of the elevator
	boolean isElevatorMoving = false;
	boolean isDoorOpen = false;
	
	/**
	 * This is the constructor
	 * @param s is a type Scheduler
	 */
	public Elevator(Scheduler s) {
		this.s = s;
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
		MoveState ms = new MoveState(this);
		
		while(true) {
			FloorButtonRequest request = s.getElevator();//the destination floor the elevator is asked to get to
			currentState = ms;
			System.out.println(Thread.currentThread().getName() + " has been requested and elevator goes to floor " + request.getFloorNum() + " for pickup.");	
			currentState.moveDoor();
			currentState.moveElevator();
			//set current state to stop state here
			System.out.println("The passenger gets on the elevator at floor " + request.getFloorNum() + " and goes " + request.getDirection().toString() + " to Floor " + request.getDestinationFloor());
		}
		
	}
	
}
