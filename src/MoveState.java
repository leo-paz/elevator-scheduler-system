/**
 * MoveState class describes the state of the elevator and doors in motion
 * either of the doors or between floors.
 * @version Iteration 2: Feb 15th 2020 
 *
 */
public class MoveState extends State {

	/**
	 * This is the constructor class for MoveState
	 * @param ele is the type from elevator class
	 */
	public MoveState(Elevator ele) {
		super(ele);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * This method is for to see 
	 * if the doors are closed and the elevators are
	 * moving as requested
	 */
	public void moveDoor() {
		// TODO Auto-generated method stub
		System.out.println("Doors are closed while the elevator is moving");
	}

	@Override
	/**
	 * This method checks if the elevators have stopped and
	 * the doors are opened for the passengers to enter
	 */
	public void moveElevator() {
		// TODO Auto-generated method stub
		elevator.setState(new StopState(elevator));
		try {
			System.out.println("Doors are opening and passenger is entering");
			Thread.sleep(OpenDoorTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
